package com.senac.torreverde.ms_compra.services.rabbitmq;

import com.senac.torreverde.ms_compra.dtos.PedidoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Publisher para enviar evento de pedido criado para o processamento de pagamento.
@Service
public class PedidoPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarPedidoCriado(PedidoDTO pedidoDTO) {
        rabbitTemplate.convertAndSend("pedido.criado", pedidoDTO);
        System.out.println("Enviado pedido " + pedidoDTO.getPedidoId() + " para o processamento de pagamento.");
    }
}
