package com.senac.torreverde.ms_compra.controller;

import com.senac.torreverde.ms_compra.model.PedidoItem;
import com.senac.torreverde.ms_compra.service.PedidoItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido-itens")
public class PedidoItemController {

    private final PedidoItemService pedidoItemService;

    public PedidoItemController(PedidoItemService pedidoItemService) {
        this.pedidoItemService = pedidoItemService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoItem>> findAll() {
        return ResponseEntity.ok(pedidoItemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoItem> findById(@PathVariable Integer id) {
        return pedidoItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<PedidoItem>> findByPedidoId(@PathVariable Integer pedidoId) {
        List<PedidoItem> pedidoItem = pedidoItemService.findByPedidoId(pedidoId);
        return pedidoItem.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(pedidoItem);
    }

    @PostMapping
    public ResponseEntity<PedidoItem> save(@RequestBody PedidoItem pedidoItem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoItemService.save(pedidoItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoItem> update(@PathVariable Integer id, @RequestBody PedidoItem pedidoItem) {
       if (!pedidoItemService.findById(id).isPresent()) {
           return ResponseEntity.notFound().build();
       }
       pedidoItem.setId(id);
       return ResponseEntity.ok(pedidoItemService.save(pedidoItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!pedidoItemService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pedidoItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
