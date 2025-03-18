package com.torreverde.pagamento_service.controller;

import com.torreverde.pagamento_service.model.RegistroItemDoacao;
import com.torreverde.pagamento_service.service.PagamentoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/doacao")
    public RegistroItemDoacao adicionarDoacao(@RequestBody RegistroItemDoacao registroItemDoacao) {
        return pagamentoService.adicionarDoacao(registroItemDoacao);
    }
}
