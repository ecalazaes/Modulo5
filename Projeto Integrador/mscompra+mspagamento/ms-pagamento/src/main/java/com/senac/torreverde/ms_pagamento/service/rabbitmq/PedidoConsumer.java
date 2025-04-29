package com.senac.torreverde.ms_pagamento.service.rabbitmq;

import com.senac.torreverde.ms_pagamento.dto.PedidoDTO;
import com.senac.torreverde.ms_pagamento.dto.PedidoItemDTO;
import com.senac.torreverde.ms_pagamento.model.RegistroPagamento;
import com.senac.torreverde.ms_pagamento.model.RegistroPagamentoItem;
import com.senac.torreverde.ms_pagamento.repository.RegistroPagamentoItemRepository;
import com.senac.torreverde.ms_pagamento.repository.RegistroPagamentoRepository;
import com.senac.torreverde.ms_pagamento.service.RegistroPagamentoService;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class PedidoConsumer {

    private final RegistroPagamentoService registroPagamentoService;
    private final RegistroPagamentoRepository registroPagamentoRepository;
    private final RegistroPagamentoItemRepository registroPagamentoItemRepository;

    public PedidoConsumer(RegistroPagamentoService registroPagamentoService, RegistroPagamentoRepository registroPagamentoRepository, RegistroPagamentoItemRepository registroPagamentoItemRepository) {
        this.registroPagamentoService = registroPagamentoService;
        this.registroPagamentoRepository = registroPagamentoRepository;
        this.registroPagamentoItemRepository = registroPagamentoItemRepository;
    }

//    @RabbitListener(queues = "pedido.criado")
//    public void receberPedidoCriado(PedidoDTO pedidoDTO) {
//        System.out.println("Recebendo pedido para pagamento. Usuário: " + pedidoDTO.getUsuarioId());
//
//        RegistroPagamento registroPagamento = registroPagamentoService.processarPagamento(pedidoDTO);
//        System.out.println("Pagamento processado! ID: " + registroPagamento.getId() + " - Status: " + registroPagamento.getStatus());
//
//
//    }

    @Transactional
    @RabbitListener(queues = "pedido.criado")
    public void receberPedidoCriado(PedidoDTO pedidoDTO) {
        System.out.println("Recebido pedido criado: " + pedidoDTO);

        int statusPagamento = new Random().nextInt(2);

        RegistroPagamento registroPagamento = RegistroPagamento.builder()
                .usuarioId(pedidoDTO.getUsuarioId())
                .numero(pedidoDTO.getPedidoId())
                .data(LocalDateTime.now())
                .chaveNfe(new Random().nextInt(100000000) + "")
                .valorTotal(pedidoDTO.getPedidoValorTotal())
                .status(statusPagamento)
                .build();

        RegistroPagamento registroPagamentoSalvo = registroPagamentoRepository.save(registroPagamento);

        if (pedidoDTO.getItens() != null && !pedidoDTO.getItens().isEmpty()) {
            for (PedidoItemDTO itemDTO : pedidoDTO.getItens()) {
                RegistroPagamentoItem registroPagamentoItem = RegistroPagamentoItem.builder()
                        .registroPagamentoId(registroPagamentoSalvo.getId())
                        .estoqueId(itemDTO.getEstoqueId())
                        .quantidade(itemDTO.getQuantidade())
                        .precoUnitario(itemDTO.getPrecoUnitario())
                        .subTotal(itemDTO.getSubTotal())
                        .registroPagamento(registroPagamentoSalvo)
                        .build();
                registroPagamentoItemRepository.save(registroPagamentoItem);
            }
            System.out.println("RegistroPagamento e seus itens criados para o pedido ID: " + pedidoDTO.getPedidoId());
        } else {
            System.out.println("Pedido ID: " + pedidoDTO.getPedidoId() + " não possui itens.");
        }
    }


}
