package com.torreverde.ms_compra.service;

import com.torreverde.ms_compra.dto.PagamentoDTO;
import com.torreverde.ms_compra.model.Pedido;
import com.torreverde.ms_compra.repository.PedidoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PedidoConsumer {

    private final PedidoRepository pedidoRepository;

    public PedidoConsumer(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

@RabbitListener(queues = "pagamento.concluido")
public void receberPagamentoConcluido(PagamentoDTO pagamentoDTO) {
    Optional<Pedido> pedidoOpt = pedidoRepository.findById(pagamentoDTO.getPedidoId());

    if (pedidoOpt.isPresent()) {
        Pedido pedido = pedidoOpt.get();

        if ("APROVADO".equals(pagamentoDTO.getStatus())) {
            pedido.setStatus("PAGO");

            if (pagamentoDTO.getDoacao()) {
                System.out.println("Obrigado pela doação!");
            }
        } else {
            pedido.setStatus("CANCELADO");
        }
        pedidoRepository.save(pedido);
        }
    }
}
