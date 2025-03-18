package com.torreverde.pagamento_service.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class PagamentoConsumer {

    @RabbitListener(queues = "pagamentoQueue")
    public void receberMensagem(String mensagem) {
        System.out.println("Mensagem recebida: " + mensagem);
        // Lógica para processar a mensagem
    }
}
