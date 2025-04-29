package com.senac.torreverde.ms_compra.service;

import com.senac.torreverde.ms_compra.dto.PedidoDTO;
import com.senac.torreverde.ms_compra.dto.PedidoItemDTO;
import com.senac.torreverde.ms_compra.model.Cupom;
import com.senac.torreverde.ms_compra.model.Pedido;
import com.senac.torreverde.ms_compra.model.PedidoItem;
import com.senac.torreverde.ms_compra.repository.PedidoItemRepository;
import com.senac.torreverde.ms_compra.repository.PedidoRepository;
import com.senac.torreverde.ms_compra.service.rabbitmq.PedidoPublisher;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoItemRepository pedidoItemRepository;
    private final CupomService cupomService;
    private final PedidoItemService pedidoItemService;
    private final PedidoItemDoacaoService pedidoItemDoacaoService;
    private final PedidoPublisher pedidoPublisher;

    public PedidoService(PedidoRepository pedidoRepository, PedidoItemRepository pedidoItemRepository, CupomService cupomService, PedidoItemService pedidoItemService, PedidoItemDoacaoService pedidoItemDoacaoService, PedidoPublisher pedidoPublisher) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoItemRepository = pedidoItemRepository;
        this.cupomService = cupomService;
        this.pedidoItemService = pedidoItemService;
        this.pedidoItemDoacaoService = pedidoItemDoacaoService;
        this.pedidoPublisher = pedidoPublisher;
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Integer id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> findByUsuarioId(Integer usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    @Transactional
    public Pedido criarPedido(Pedido pedido) {

        // 1. Validações básicas
        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("O pedido deve conter pelo menos um item");
        }

        // 2. Carrega o cupom COMPLETO do banco se existir
        Cupom cupomValido = cupomService.cupomValidate(pedido.getCupom());
        pedido.setCupom(cupomValido);

        // 3. Configura dados iniciais
        pedido.setData(LocalDateTime.now());
        pedido.setStatus(0);

        // 4. Estabelece a relação bidirecional ANTES de salvar
        if (pedido.getItens() != null) {
            pedido.getItens().forEach(item -> pedidoItemService.configurarItemPedido(item, pedido));
        }

        // 5. Calcula valor total ANTES de salvar
        calcularValorTotal(pedido);

        // 6. Salva o Pedido (cascade cuida dos itens)
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        // 7. Salva os PedidoItemDoacao, se existirem, após o PedidoItem estar salvo
        if (pedido.getItens() != null) {
            pedido.getItens().forEach(item -> pedidoItemDoacaoService.salvarDoacao(item.getDoacao()));
        }

        List<PedidoItemDTO> itensDTO = pedido.getItens().stream()
                .map(item -> PedidoItemDTO.builder()
                        .estoqueId(item.getEstoqueId())
                        .quantidade(item.getQuantidade())
                        .precoUnitario(item.getPrecoUnitario())
                        .subTotal(item.getSubTotal())
                        .build())
                .toList();

        // 8. Converte o Pedido salvo para PedidoDTO usando Builder e envia a mensagem
        PedidoDTO pedidoDTO = PedidoDTO.builder()
                .pedidoId(pedidoSalvo.getId())
                .usuarioId(pedidoSalvo.getUsuarioId())
                .pedidoValorTotal(pedidoSalvo.getValorTotal())
                .itens(itensDTO)
                .build();

        pedidoPublisher.enviarPedidoCriado(pedidoDTO);

        return pedidoSalvo;
    }

    public void delete(Integer id) {
        pedidoRepository.deleteById(id);
    }

    private void calcularValorTotal(Pedido pedido) {
        double total = pedido.getItens().stream()
                .mapToDouble(PedidoItem::getSubTotal)
                .sum();

        double totalDoacoes = pedido.getItens().stream()
                .filter(item -> item.getDoacao() != null)
                .mapToDouble(item -> item.getDoacao().getValor()) // Assumindo que 'getValor()' retorna o valor da doação
                .sum();

        total += totalDoacoes; // Adiciona o valor das doações ao total do pedido

        if (pedido.getCupom() != null) {
            double desconto = calcularDesconto(pedido.getCupom(), total);
            total -= desconto;
            pedido.setDescontoAplicado(desconto);
        }

        pedido.setValorTotal(total);
    }

    private double calcularDesconto(Cupom cupom, double valorTotal) {
        // Verificação de nulidade
        if (cupom == null || cupom.getValorDesconto() == null) {
            return 0.0;
        }

        // Cálculo do desconto
        if (cupom.getTipoDesconto() == 1) { // Percentual
            return valorTotal * (cupom.getValorDesconto() / 100.0);
        } else { // Valor fixo (ou tipo inválido)
            return Math.min(cupom.getValorDesconto(), valorTotal);
        }
    }

}
