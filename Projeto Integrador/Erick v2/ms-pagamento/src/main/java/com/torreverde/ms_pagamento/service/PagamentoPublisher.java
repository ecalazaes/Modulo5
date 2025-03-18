package com.torreverde.ms_pagamento.service;

import com.torreverde.ms_pagamento.dto.PagamentoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PagamentoPublisher {

    private final RabbitTemplate rabbitTemplate;

    public PagamentoPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarPagamentoConcluido(PagamentoDTO pagamentoDTO) {
        rabbitTemplate.convertAndSend("pagamento.concluido", pagamentoDTO);
    }
}
