package com.torrev.ms_compra.service;

import com.torrev.ms_compra.dto.NovoPedidoEvent;
import com.torrev.ms_compra.model.Cupom;
import com.torrev.ms_compra.model.Pedido;
import com.torrev.ms_compra.model.PedidoItem;
import com.torrev.ms_compra.repository.CupomRepository;
import com.torrev.ms_compra.repository.PedidoItemDoacaoRepository;
import com.torrev.ms_compra.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoItemDoacaoRepository pedidoItemDoacaoRepository;
    private final CupomRepository cupomRepository;
    private final RabbitTemplate rabbitTemplate;
    private static final String PEDIDO_EVENT_QUEUE = "pedido.pagamento";

    public PedidoService(PedidoRepository pedidoRepository, PedidoItemDoacaoRepository pedidoItemDoacaoRepository, CupomRepository cupomRepository, RabbitTemplate rabbitTemplate) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoItemDoacaoRepository = pedidoItemDoacaoRepository;
        this.cupomRepository = cupomRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public Pedido criarPedido(Pedido pedido) {

        // 1. Validações básicas
        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("O pedido deve conter pelo menos um item");
        }

        // 2. Carrega o cupom COMPLETO do banco se o código do cupom foi fornecido
        if (pedido.getPedidoDescontoAplicado() != null && !pedido.getPedidoDescontoAplicado().isEmpty()) {
            Optional<Cupom> cupomOptional = cupomRepository.findByCupomCodigoAndCupomValidadeAfterAndCupomStatus(
                    pedido.getPedidoDescontoAplicado(), LocalDateTime.now(), 1);
            if (cupomOptional.isPresent()) {
                Cupom cupom = cupomOptional.get();
                if (cupom.getCupomQuantidadeUso() == null) {
                    cupom.setCupomQuantidadeUso(1);
                } else {
                    cupom.setCupomQuantidadeUso(cupom.getCupomQuantidadeUso() + 1);
                }
                cupomRepository.save(cupom);
                pedido.setCupomId(cupom.getCupomId()); // Salva o ID do cupom no pedido
            }
            // Se o cupom não for encontrado ou inválido, o pedido prossegue sem desconto
        }

        // 3. Configura dados iniciais
        pedido.setPedidoData(LocalDateTime.now());
        pedido.setPedidoStatus(0); // Status inicial: pendente

        double valorTotal = 0.0;

        // 4. Estabelece a relação bidirecional ANTES de salvar e calcula o subtotal
        for (PedidoItem item : pedido.getItens()) {
            item.setPedido(pedido); // Garante o vínculo
            item.setPedidoItemSubTotal(item.getPedidoItemPrecoUnitario().doubleValue() * item.getPedidoItemQuantidade());
            item.setPedidoItemStatus(1); // Defina um status inicial para o item, se necessário
            valorTotal += item.getPedidoItemSubTotal();

            // Se houver doação, garante a associação com o PedidoItem
            if (item.getPedidoItemDoacao() != null && item.getPedidoItemDoacao().getPedidoItemDoacaoValor() != null && item.getPedidoItemDoacao().getPedidoItemDoacaoValor() > 0) {
                item.getPedidoItemDoacao().setPedidoItem(item); // Associa o PedidoItem à doação
                valorTotal += item.getPedidoItemDoacao().getPedidoItemDoacaoValor();
            }
        }

        // 5. Aplica o desconto do cupom ao valor total
        if (pedido.getCupomId() != null && pedido.getCupomId() > 0) {
            Optional<Cupom> cupomOptional = cupomRepository.findById(pedido.getCupomId());
            double finalValorTotal = valorTotal;
            cupomOptional.ifPresent(cupom -> {
                if (cupom.getCupomTipoDesconto().equals("1")) { // Assumindo '1' para percentual
                    double desconto = finalValorTotal * (cupom.getCupomValorDesconto().doubleValue() / 100.0);
                    pedido.setPedidoValorTotal(finalValorTotal - desconto);
                } else if (cupom.getCupomTipoDesconto().equalsIgnoreCase("VALOR") || cupom.getCupomTipoDesconto().equals("2")) { // Para desconto fixo
                    double desconto = Math.min(cupom.getCupomValorDesconto().doubleValue(), finalValorTotal);
                    pedido.setPedidoValorTotal(finalValorTotal - desconto);
                } else {
                    pedido.setPedidoValorTotal(finalValorTotal); // Sem desconto se o tipo não for reconhecido
                }
            });
        } else {
            pedido.setPedidoValorTotal(valorTotal);
        }

        // 6. Salva o Pedido (cascade cuida dos itens)
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        // 7. Salva os PedidoItemDoacao, se existirem, após o PedidoItem estar salvo
        for (PedidoItem item : pedidoSalvo.getItens()) { // Use os itens salvos para garantir que tenham ID
            if (item.getPedidoItemDoacao() != null) {
                item.getPedidoItemDoacao().setPedido(pedidoSalvo); // Garante que a doação esteja ligada ao pedido
                pedidoItemDoacaoRepository.save(item.getPedidoItemDoacao()); // Salva a doação
            }
        }

        // 8. Envia o evento para o RabbitMQ
        NovoPedidoEvent evento = new NovoPedidoEvent();
        evento.setPedidoId(pedidoSalvo.getPedidoId());
        evento.setValorTotal(pedidoSalvo.getPedidoValorTotal().doubleValue());
        evento.setUsuarioId(pedidoSalvo.getUsuarioId());
        // Adicione outros dados relevantes para o ms de pagamento

        rabbitTemplate.convertAndSend(PEDIDO_EVENT_QUEUE, evento);
        System.out.println("Evento de novo pedido enviado para a fila: " + PEDIDO_EVENT_QUEUE);

        return pedidoSalvo;

    }

    public Pedido atualizarStatusPedido(Integer pedidoId, int novoStatus) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
        return pedidoOptional.map(pedido -> {
            pedido.setPedidoStatus(novoStatus);
            return pedidoRepository.save(pedido);
        }).orElse(null);
    }
}
