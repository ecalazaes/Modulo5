package com.torreverde.ms_compra.service;

import com.torreverde.ms_compra.dto.PedidoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoPublisher {

    private final RabbitTemplate rabbitTemplate;

    public PedidoPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarPedidoCriado(PedidoDTO pedidoDTO) {
        rabbitTemplate.convertAndSend("pedido.criado", pedidoDTO);
    }
}
