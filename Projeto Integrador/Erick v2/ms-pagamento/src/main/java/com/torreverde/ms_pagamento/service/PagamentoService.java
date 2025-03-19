package com.torreverde.ms_pagamento.service;

import com.torreverde.ms_pagamento.dto.PagamentoDTO;
import com.torreverde.ms_pagamento.dto.PedidoDTO;
import com.torreverde.ms_pagamento.dto.PedidoItemDTO;
import com.torreverde.ms_pagamento.model.Pagamento;
import com.torreverde.ms_pagamento.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoPublisher pagamentoPublisher;

    public PagamentoService(PagamentoRepository pagamentoRepository, PagamentoPublisher pagamentoPublisher) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoPublisher = pagamentoPublisher;
    }

//    public Pagamento processarPagamento(PedidoDTO pedidoDTO) {
//        // Simulando a aprovação ou recusa do pagamento (50% de chance de recusa)
//        String statusPagamento = new Random().nextBoolean() ? "APROVADO" : "RECUSADO";
//
//        Pagamento pagamento = Pagamento.builder()
//                .pedidoId(pedidoDTO.getId())
//                .usuarioId(pedidoDTO.getUsuarioId())
//                .valor(pedidoDTO.getTotal())
//                .status(statusPagamento)
//                .dataCriacao(LocalDateTime.now())
//                .build();
//
//        pagamentoRepository.save(pagamento);
//
//        // Criar DTO para enviar ao RabbitMQ
//        PagamentoDTO pagamentoDTO = PagamentoDTO.builder()
//                .pedidoId(pagamento.getPedidoId())
//                .usuarioId(pagamento.getUsuarioId())
//                .valor(pagamento.getValor())
//                .status(pagamento.getStatus())
//                .dataCriacao(pagamento.getDataCriacao())
//                .build();
//
//        // Publica evento no RabbitMQ
//        pagamentoPublisher.enviarPagamentoConcluido(pagamentoDTO);
//
//        return pagamento;
//    }

    public Pagamento processarPagamento(PedidoDTO pedidoDTO) {
        boolean contemDoacao = pedidoDTO.getItens().stream().anyMatch(PedidoItemDTO::getDoacao);

        String statusPagamento = new Random().nextBoolean() ? "APROVADO" : "RECUSADO";

        Pagamento pagamento = Pagamento.builder()
                .pedidoId(pedidoDTO.getId())
                .usuarioId(pedidoDTO.getUsuarioId())
                .valor(pedidoDTO.getTotal())
                .status(statusPagamento)
                .doacao(contemDoacao)
                .dataCriacao(LocalDateTime.now())
                .build();

        pagamento = pagamentoRepository.save(pagamento);

        // Criar DTO para RabbitMQ
        PagamentoDTO pagamentoDTO = PagamentoDTO.builder()
                .pedidoId(pagamento.getPedidoId())
                .usuarioId(pagamento.getUsuarioId())
                .valor(pagamento.getValor())
                .status(pagamento.getStatus())
                .doacao(pagamento.getDoacao())
                .dataCriacao(pagamento.getDataCriacao())
                .build();

        pagamentoPublisher.enviarPagamentoConcluido(pagamentoDTO);

        return pagamento;
    }

}
