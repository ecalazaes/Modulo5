package com.senac.torreverde.ms_pagamento.service;

import com.senac.torreverde.ms_pagamento.repository.RegistroPagamentoItemRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistroPedidoItemService {

    private final RegistroPagamentoItemRepository registroPagamentoItemRepository;

    public RegistroPedidoItemService(RegistroPagamentoItemRepository registroPagamentoItemRepository) {
        this.registroPagamentoItemRepository = registroPagamentoItemRepository;
    }

}
