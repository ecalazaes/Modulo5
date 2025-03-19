package com.torreverde.ms_pagamento.controller;

import com.torreverde.ms_pagamento.dto.DoacaoDTO;
import com.torreverde.ms_pagamento.model.Pagamento;
import com.torreverde.ms_pagamento.service.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/doacao")
    public ResponseEntity<Pagamento> realizarDoacao(@RequestBody DoacaoDTO doacaoDTO) {
        Pagamento pagamento = pagamentoService.processarDoacao(doacaoDTO);
        return ResponseEntity.ok(pagamento);
    }
}
