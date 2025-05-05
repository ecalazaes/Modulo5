package com.senac.torreverde.ms_pagamento.controllers;

import com.senac.torreverde.ms_pagamento.dtos.RegistroDoacaoDTO;
import com.senac.torreverde.ms_pagamento.entities.RegistroPagamento;
import com.senac.torreverde.ms_pagamento.services.RegistroPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
public class RegistroPagamentoController {

    @Autowired
    private RegistroPagamentoService registroPagamentoService;

    // Endpoint doação direta pelo serviço de pagamento
    @PostMapping("/doacoes")
    public ResponseEntity<RegistroPagamento> criarRegistroDoacao(@RequestBody RegistroDoacaoDTO request) {
        RegistroPagamento registroPagamentoDoacao = registroPagamentoService.criarRegistroDoacao(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(registroPagamentoDoacao);
    }
}
