package com.torreverde.ms_pagamento.service;

import com.torreverde.ms_pagamento.dto.PedidoDTO;
import com.torreverde.ms_pagamento.model.Pagamento;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoConsumer {

    private final PagamentoService pagamentoService;

    public PagamentoConsumer(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @RabbitListener(queues = "pedido.criado")
    public void receberPedidoCriado(PedidoDTO pedidoDTO) {
        System.out.println("Recebendo pedido para pagamento. Usuário: " + pedidoDTO.getUsuarioId());

        Pagamento pagamento = pagamentoService.processarPagamento(pedidoDTO);
        System.out.println("Pagamento processado! ID: " + pagamento.getId() + " - Status: " + pagamento.getStatus());
    }
}

