package com.torrev.ms_compra.service;

import com.torrev.ms_compra.model.Cupom;
import com.torrev.ms_compra.repository.CupomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CupomService {

    private final CupomRepository cupomRepository;

    public CupomService(CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    public Optional<Cupom> buscarCupomValido(String codigo) {
        return cupomRepository.findByCupomCodigoAndCupomValidadeAfterAndCupomStatus(codigo, LocalDateTime.now(), 1); // Assumindo status 1 para cupons ativos
    }

    @Transactional
    public void incrementarQuantidadeUso(Cupom cupom) {
        if (cupom.getCupomQuantidadeUso() == null) {
            cupom.setCupomQuantidadeUso(1);
        } else {
            cupom.setCupomQuantidadeUso(cupom.getCupomQuantidadeUso() + 1);
        }
        cupomRepository.save(cupom);
    }
}
