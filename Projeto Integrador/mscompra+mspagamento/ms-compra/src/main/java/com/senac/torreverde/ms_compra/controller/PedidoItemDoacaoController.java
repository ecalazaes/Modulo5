package com.senac.torreverde.ms_compra.controller;

import com.senac.torreverde.ms_compra.model.PedidoItemDoacao;
import com.senac.torreverde.ms_compra.service.PedidoItemDoacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido-item-doacoes")
public class PedidoItemDoacaoController {

    private final PedidoItemDoacaoService pedidoItemDoacaoService;

    public PedidoItemDoacaoController(PedidoItemDoacaoService pedidoItemDoacaoService) {
        this.pedidoItemDoacaoService = pedidoItemDoacaoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoItemDoacao>> findAll() {
        return ResponseEntity.ok(pedidoItemDoacaoService.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoItemDoacao> findById(@PathVariable Integer id) {
        return pedidoItemDoacaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PedidoItemDoacao>  criar(@RequestBody PedidoItemDoacao doacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoItemDoacaoService.salvarDoacao(doacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoItemDoacao> atualizar(@PathVariable Integer id, @RequestBody PedidoItemDoacao doacao) {
        if (!pedidoItemDoacaoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        doacao.setId(id);
        return ResponseEntity.ok(pedidoItemDoacaoService.salvarDoacao(doacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!pedidoItemDoacaoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pedidoItemDoacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
