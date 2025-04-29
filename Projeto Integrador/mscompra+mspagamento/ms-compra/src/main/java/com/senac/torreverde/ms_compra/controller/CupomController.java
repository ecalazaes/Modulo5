package com.senac.torreverde.ms_compra.controller;

import com.senac.torreverde.ms_compra.model.Cupom;
import com.senac.torreverde.ms_compra.service.CupomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cupons")
public class CupomController {

    private final CupomService cupomService;

    public CupomController(CupomService cupomService) {
        this.cupomService = cupomService;
    }

    @GetMapping
    public ResponseEntity<List<Cupom>> findAll() {
        return ResponseEntity.ok(cupomService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cupom> findById(@PathVariable Integer id) {
        return cupomService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Cupom> findByCodigo(@PathVariable String codigo) {
        Cupom cupom = cupomService.findByCodigo(codigo);
        return cupom != null ? ResponseEntity.ok(cupom) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cupom> save(@RequestBody Cupom cupom) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cupomService.save(cupom));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Cupom> update(@PathVariable Integer id, @RequestBody Cupom cupom) {
        if (!cupomService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cupom.setId(id);
        return ResponseEntity.ok(cupomService.save(cupom));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!cupomService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cupomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
