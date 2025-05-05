package com.senac.torreverde.ms_compra.services;

import com.senac.torreverde.ms_compra.dtos.PedidoDTO;
import com.senac.torreverde.ms_compra.dtos.PedidoItemDTO;
import com.senac.torreverde.ms_compra.entities.Cupom;
import com.senac.torreverde.ms_compra.entities.Pedido;
import com.senac.torreverde.ms_compra.entities.PedidoItem;
import com.senac.torreverde.ms_compra.repositories.PedidoRepository;
import com.senac.torreverde.ms_compra.services.rabbitmq.PedidoPublisher;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CupomService cupomService;

    @Autowired
    private PedidoItemService pedidoItemService;

    @Autowired
    private PedidoItemDoacaoService pedidoItemDoacaoService;

    @Autowired
    private PedidoPublisher pedidoPublisher;


    // Medodo para buscar todos pedidos
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    // Metodo para buscar um pedido por id
    public Optional<Pedido> findById(Integer id) {
        return pedidoRepository.findById(id);
    }

    // Metodo para buscar um pedido por id do usuario
    public List<Pedido>  findByUsuarioId(Integer id) {
        return pedidoRepository.findByUsuarioId(id);
    }

    // Meodo para criar um pedido
    @Transactional
    public Pedido save(Pedido pedido) {

        // 1. Validações básicas
        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("O pedido deve conter pelo menos um item");
        }

        // 2. Carrega o cupom COMPLETO do banco se existir
        Cupom cupomValido = cupomService.validarCupom(pedido.getCupom());
        pedido.setCupom(cupomValido);

        // 3. Configura dados iniciais do pedido
        pedido.setData(LocalDateTime.now());
        pedido.setStatus(0);

        // 4. Estabelece a relação bidirecional ANTES de salvar
        pedido.getItens().forEach(item -> pedidoItemService.vincularPedidoItemAoPedido(item, pedido));

        // 5. Calcula valor total ANTES de salvar
        calcularValorTotal(pedido);

        // 6. Salva o Pedido (cascade cuida dos itens)
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        // 7. Salva os PedidoItemDoacao, se existirem, após o PedidoItem estar salvo
        pedido.getItens().forEach(item -> pedidoItemDoacaoService.salvarDoacao(item.getDoacao()));

        List<PedidoItemDTO> itensDTO = pedido.getItens().stream()
                .map(item -> {
                    PedidoItemDTO dto = new PedidoItemDTO();
                    dto.setEstoqueId(item.getEstoqueId());
                    dto.setQuantidade(item.getQuantidade());
                    dto.setPrecoUnitario(item.getPrecoUnitario());
                    dto.setSubTotal(item.getSubTotal());
                    return dto;
                })
                .toList();

        // 8. Converte o Pedido salvo para PedidoDTO para enviar mensagem ao rabbitmq
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setPedidoId(pedidoSalvo.getId());
        pedidoDTO.setUsuarioId(pedidoSalvo.getUsuarioId());
        pedidoDTO.setPedidoValorTotal(pedidoSalvo.getValorTotal());
        pedidoDTO.setItens(itensDTO);

        pedidoPublisher.enviarPedidoCriado(pedidoDTO);

        return pedidoSalvo;
    }

    // Metodo para deletar um pedido
    public void delete(Integer id) {
        pedidoRepository.deleteById(id);
    }

    // Metodo para calcular o valor total do pedido
    private void calcularValorTotal(Pedido pedido) {
        double total = pedido.getItens().stream()
                .mapToDouble(PedidoItem::getSubTotal)
                .sum();

        double totalDoacoes = pedido.getItens().stream()
                .filter(item -> item.getDoacao() != null)
                .mapToDouble(item -> item.getDoacao().getValor())
                .sum();

        total += totalDoacoes; // Adiciona o valor das doações ao total do pedido

        if (pedido.getCupom() != null) {
            double desconto = calcularDesconto(pedido.getCupom(), total);
            total -= desconto;
            pedido.setDescontoAplicado(desconto);
        }

        pedido.setValorTotal(total);
    }

    // Metodo para calcular o desconto aplicado ao pedido
    private double calcularDesconto(Cupom cupom, double valorTotal) {
        if (cupom.getTipoDesconto() == 1) { // Tipo de desconto: Porcentagem
            return valorTotal * (cupom.getValorDesconto() / 100.0);
        } else {
            return Math.min(cupom.getValorDesconto(), valorTotal);
        }
    }
}
