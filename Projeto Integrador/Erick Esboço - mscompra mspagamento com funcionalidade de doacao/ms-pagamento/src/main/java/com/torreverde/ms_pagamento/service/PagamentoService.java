package com.torreverde.ms_pagamento.service;

import com.torreverde.ms_pagamento.dto.*;
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

    public Pagamento processarPagamento(PedidoDTO pedidoDTO) {
        boolean contemDoacao = pedidoDTO.getItensPedido().stream().anyMatch(PedidoItemDTO::getDoacao);

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

    public Pagamento processarDoacao(DoacaoDTO doacaoDTO) {
        String statusPagamento = new Random().nextBoolean() ? "APROVADO" : "RECUSADO";

        Pagamento pagamento = Pagamento.builder()
                .nomeDoador(doacaoDTO.getNomeDoador()) // Armazena o nome do doador
                .documentoDoador(doacaoDTO.getDocumentoDoador())  // Armazena CPF ou CNPJ
                .valor(doacaoDTO.getValor())
                .status(statusPagamento)
                .doacao(true)
                .dataCriacao(LocalDateTime.now())
                .build();

        return pagamentoRepository.save(pagamento);
    }
}
