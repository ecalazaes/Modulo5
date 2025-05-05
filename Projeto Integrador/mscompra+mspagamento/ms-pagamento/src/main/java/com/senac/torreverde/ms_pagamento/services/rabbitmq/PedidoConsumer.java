package com.senac.torreverde.ms_pagamento.services.rabbitmq;

import com.senac.torreverde.ms_pagamento.dtos.PedidoDTO;
import com.senac.torreverde.ms_pagamento.services.RegistroPagamentoService;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// Consumidor do evento de criar Pedido que foi enviado pelo ms-compra.
@Component
public class PedidoConsumer {

    private final RegistroPagamentoService registroPagamentoService;

    public PedidoConsumer(RegistroPagamentoService registroPagamentoService) {
        this.registroPagamentoService = registroPagamentoService;
    }

    @Transactional
    @RabbitListener(queues = "pedido.criado")
    public void receberPedidoCriado(PedidoDTO pedidoDTO) {
        registroPagamentoService.processarPagamento(pedidoDTO);
    }
}
