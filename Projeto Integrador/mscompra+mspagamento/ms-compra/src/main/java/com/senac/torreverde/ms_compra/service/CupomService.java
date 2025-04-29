package com.senac.torreverde.ms_compra.service;

import com.senac.torreverde.ms_compra.model.Cupom;
import com.senac.torreverde.ms_compra.repository.CupomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CupomService {

    private final CupomRepository cupomRepository;

    public CupomService(CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    public List<Cupom> findAll() {
        return cupomRepository.findAll();
    }

    public Optional<Cupom> findById(Integer id) {
        return cupomRepository.findById(id);
    }

    public Cupom findByCodigo(String codigo) {
        return cupomRepository.findByCodigo(codigo);
    }

    public Cupom save(Cupom cupom) {
        return cupomRepository.save(cupom);
    }

    public void delete(Integer id) {
        cupomRepository.deleteById(id);
    }

    public Cupom cupomValidate(Cupom cupomId) {
        if (cupomId != null && cupomId.getId() != null) {
            Cupom cupom = cupomRepository.findById(cupomId.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Cupom não encontrado"));

            // Validações adicionais do cupom
            if (cupom.getStatus() != 1 || cupom.getValidade().isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Cupom inválido ou expirado");
            }

            incrementarQuantidadeUso(cupom);
            return cupom;
        }
        return null;
    }

    protected void incrementarQuantidadeUso(Cupom cupom) {
        if (cupom.getQuantidadeUso() == null) {
            cupom.setQuantidadeUso(1);
        } else {
            cupom.setQuantidadeUso(cupom.getQuantidadeUso() + 1);
        }
        cupomRepository.save(cupom);
    }
}
