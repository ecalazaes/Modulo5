package com.senac.torreverde.ms_pagamento.controller;

import com.senac.torreverde.ms_pagamento.model.RegistroItemDoacao;
import com.senac.torreverde.ms_pagamento.service.RegistroItemDoacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registro-item-doacao")
public class RegistroItemDoacaoController {

    private final RegistroItemDoacaoService registroItemDoacaoService;

    public RegistroItemDoacaoController(RegistroItemDoacaoService registroItemDoacaoService) {
        this.registroItemDoacaoService = registroItemDoacaoService;
    }

    @PostMapping
    public ResponseEntity<RegistroItemDoacao> criarRegistro(@RequestBody RegistroItemDoacao registroItemDoacao) {
        RegistroItemDoacao novoRegistro = registroItemDoacaoService.save(registroItemDoacao);
        return new ResponseEntity<>(novoRegistro, HttpStatus.CREATED);
    }
}
