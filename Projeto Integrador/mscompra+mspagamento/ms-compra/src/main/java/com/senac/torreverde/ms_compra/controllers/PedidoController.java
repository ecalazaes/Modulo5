package com.senac.torreverde.ms_compra.controllers;

import com.senac.torreverde.ms_compra.entities.Pedido;
import com.senac.torreverde.ms_compra.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Endpoint para listar todos os pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>>  buscarTodosPedidos() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    // Endpoint para listar um pedido por id
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Integer id) {
        return pedidoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para listar todos os pedidos por usuario id
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Pedido>> buscarPedidosPorUsuarioId(@PathVariable Integer usuarioId) {
        List<Pedido> pedidos = pedidoService.findByUsuarioId(usuarioId);
        if (!pedidos.isEmpty()) {
            return ResponseEntity.ok(pedidos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para criar um pedido
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
            Pedido saved = pedidoService.save(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Endpoint para atualizar um pedido
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
        if (pedidoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pedido.setId(id);
        return ResponseEntity.ok(pedidoService.save(pedido));
    }

    // Endpoint para deletar um pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Integer id) {
        if (!pedidoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
