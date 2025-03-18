package com.torreverde.compra_service.controller;

import com.torreverde.compra_service.model.PedidoItemDoacao;
import com.torreverde.compra_service.service.CompraService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compra")
public class CompraController {


    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping("/doacao")
    public PedidoItemDoacao adicionarDoacao(@RequestBody PedidoItemDoacao pedidoItemDoacao) {
        return compraService.adicionarDoacao(pedidoItemDoacao);
    }
}
