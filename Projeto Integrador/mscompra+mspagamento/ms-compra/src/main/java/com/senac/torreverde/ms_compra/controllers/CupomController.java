package com.senac.torreverde.ms_compra.controllers;

import com.senac.torreverde.ms_compra.entities.Cupom;
import com.senac.torreverde.ms_compra.services.CupomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cupons")
public class CupomController {

    @Autowired
    private CupomService cupomService;

    // Endpoint para listar todos os cupons
    @GetMapping
    public ResponseEntity<List<Cupom>> buscarTodosCupons() {
        return ResponseEntity.ok(cupomService.findAll());
    }

    // Endpoint para listar um cupom por id
    @GetMapping("/{id}")
    public ResponseEntity<Cupom> buscarCuponsPorId(@PathVariable Integer id) {
        return cupomService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para listar um cupom por codigo
    @GetMapping("codigo/{codigo}")
    public ResponseEntity<Cupom> buscarCuponsPorCodigo(@PathVariable String codigo) {
        return cupomService.findByCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para criar um cupom
    @PostMapping
    public ResponseEntity<Cupom> criarCupom(@RequestBody Cupom cupom) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cupomService.save(cupom));

    }

    // Endpoint para atualizar um cupom
    @PutMapping("/{id}")
    public ResponseEntity<Cupom> atualizarCupom(@PathVariable Integer id, @RequestBody Cupom cupom) {
        if (cupomService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        cupom.setId(id);
        return ResponseEntity.ok(cupomService.save(cupom));
    }

    // Endpoint para deletar um cupom
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCupom(@PathVariable Integer id) {
        if (cupomService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        cupomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
