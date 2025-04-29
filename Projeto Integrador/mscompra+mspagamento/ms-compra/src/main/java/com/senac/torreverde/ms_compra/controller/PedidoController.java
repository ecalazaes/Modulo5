package com.senac.torreverde.ms_compra.controller;

import com.senac.torreverde.ms_compra.dto.PedidoDTO;
import com.senac.torreverde.ms_compra.model.Pedido;
import com.senac.torreverde.ms_compra.service.PedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>>  findAll() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
        return pedidoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Pedido pedido) {
        log.info("Recebendo pedido: {}", pedido);
        log.info("Itens recebidos: {}", pedido.getItens());

        try {
            Pedido saved = pedidoService.criarPedido(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (DataIntegrityViolationException e) {
            log.error("Erro ao salvar pedido: ", e);
            return ResponseEntity.badRequest().body("Erro ao processar pedido");
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Pedido> findByUsuarioId(@PathVariable Integer usuarioId) {
        return pedidoService.findByUsuarioId(usuarioId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!pedidoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
