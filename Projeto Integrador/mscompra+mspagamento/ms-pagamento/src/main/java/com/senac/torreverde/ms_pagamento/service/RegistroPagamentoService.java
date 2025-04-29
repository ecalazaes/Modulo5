package com.senac.torreverde.ms_pagamento.service;

import com.senac.torreverde.ms_pagamento.dto.PedidoDTO;
import com.senac.torreverde.ms_pagamento.model.RegistroPagamento;
import com.senac.torreverde.ms_pagamento.repository.RegistroPagamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class RegistroPagamentoService {

    private final RegistroPagamentoRepository registroPagamentoRepository;
    private final RegistroPedidoItemService registroPedidoItemService;

    public RegistroPagamentoService(RegistroPagamentoRepository registroPagamentoRepository, RegistroPedidoItemService registroPedidoItemService) {
        this.registroPagamentoRepository = registroPagamentoRepository;
        this.registroPedidoItemService = registroPedidoItemService;
    }

    public RegistroPagamento processarPagamento(PedidoDTO pedidoDTO) {

        int statusPagamento = new Random().nextInt(2);

        RegistroPagamento registroPagamento = RegistroPagamento.builder()
                .usuarioId(pedidoDTO.getUsuarioId())
                .numero(pedidoDTO.getPedidoId())
                .data(LocalDateTime.now())
                .chaveNfe(new Random().nextInt(100000000) + "")
                .valorTotal(pedidoDTO.getPedidoValorTotal())
                .status(statusPagamento)
                .build();

        registroPagamentoRepository.save(registroPagamento);

        return registroPagamento;
    }
}
