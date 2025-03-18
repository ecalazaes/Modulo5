package com.torreverde.compra_service.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class CompraProducer {

    private final RabbitTemplate rabbitTemplate;

    public CompraProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensagem(String mensagem) {
        rabbitTemplate.convertAndSend("compraExchange", "compraRoutingKey", mensagem);
    }
}
