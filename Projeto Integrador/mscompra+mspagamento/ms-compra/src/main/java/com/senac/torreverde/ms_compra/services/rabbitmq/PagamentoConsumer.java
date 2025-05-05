package com.senac.torreverde.ms_compra.services.rabbitmq;

import com.senac.torreverde.ms_compra.dtos.RegistroPagamentoDTO;
import com.senac.torreverde.ms_compra.entities.Pedido;
import com.senac.torreverde.ms_compra.repositories.PedidoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

// Consumidor para receber e persistir o evento do processamento de pagamento enviado pelo ms-pagamento.
@Component
public class PagamentoConsumer {

    @Autowired
    private PedidoRepository pedidoRepository;

    @RabbitListener(queues = "pagamento.concluido")
    public void receberPagamentoConcluido(RegistroPagamentoDTO registroPagamentoDTO) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(registroPagamentoDTO.getNumero());

        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();

            pedido.setStatus(registroPagamentoDTO.getStatus());
            pedidoRepository.save(pedido);

            if (pedido.getStatus() == 1) {
                System.out.println("Pagamento do pedido " + registroPagamentoDTO.getNumero() + " confirmado!.");

            } else if (pedido.getStatus() == 2) {
                System.out.println("Pagamento do pedido " + registroPagamentoDTO.getNumero() + " recusado!.");

            } else {
                System.out.println("Status de pagamento desconhecido: " + pedido.getStatus());
            }
        }
    }
}
