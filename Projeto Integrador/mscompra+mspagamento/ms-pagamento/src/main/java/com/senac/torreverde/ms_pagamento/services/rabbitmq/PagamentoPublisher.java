package com.senac.torreverde.ms_pagamento.services.rabbitmq;

import com.senac.torreverde.ms_pagamento.dtos.RegistroPagamentoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Publisher para enviar o pagamento concluido ao Rabbitmq para o ms-compra.
@Service
public class PagamentoPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarPagamentoConcluido(RegistroPagamentoDTO registroPagamentoDTO) {
        rabbitTemplate.convertAndSend("pagamento.concluido", registroPagamentoDTO);

        if (registroPagamentoDTO.getStatus() == 1) {
            System.out.println("Pagamento concluido enviado para o Rabbitmq, com status: 1 (Confirmado)");
        } else {
            System.out.println("Pagamento concluido enviado para o Rabbitmq, com status: 2 (Recusado)");
        }
    }
}
