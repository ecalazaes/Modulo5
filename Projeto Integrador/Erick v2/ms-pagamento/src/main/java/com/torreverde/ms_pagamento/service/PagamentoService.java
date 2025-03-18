package com.torreverde.ms_pagamento.service;

import com.torreverde.ms_pagamento.dto.PagamentoDTO;
import com.torreverde.ms_pagamento.dto.PedidoDTO;
import com.torreverde.ms_pagamento.model.Pagamento;
import com.torreverde.ms_pagamento.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoPublisher pagamentoPublisher;

    public PagamentoService(PagamentoRepository pagamentoRepository, PagamentoPublisher pagamentoPublisher) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoPublisher = pagamentoPublisher;
    }

    public Pagamento processarPagamento(PedidoDTO pedidoDTO) {
        Pagamento pagamento = Pagamento.builder()
                .pedidoId(pedidoDTO.getId())
                .usuarioId(pedidoDTO.getUsuarioId())
                .valor(pedidoDTO.getTotal())
                .status("APROVADO") // Simulação de sucesso
                .dataCriacao(LocalDateTime.now())
                .build();

        pagamentoRepository.save(pagamento);

        // Criar DTO para enviar ao RabbitMQ
        PagamentoDTO pagamentoDTO = PagamentoDTO.builder()
                .pedidoId(pagamento.getPedidoId())
                .usuarioId(pagamento.getUsuarioId())
                .valor(pagamento.getValor())
                .status(pagamento.getStatus())
                .dataCriacao(pagamento.getDataCriacao())
                .build();

        // Publica evento no RabbitMQ
        pagamentoPublisher.enviarPagamentoConcluido(pagamentoDTO);

        return pagamento;
    }
}
