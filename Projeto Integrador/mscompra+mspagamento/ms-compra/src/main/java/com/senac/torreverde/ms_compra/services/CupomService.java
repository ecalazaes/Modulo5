package com.senac.torreverde.ms_compra.services;

import com.senac.torreverde.ms_compra.entities.Cupom;
import com.senac.torreverde.ms_compra.repositories.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CupomService {

    @Autowired
    private CupomRepository cupomRepository;

    // Metodo para buscar todos cupons
    public List<Cupom> findAll() {
        return cupomRepository.findAll();
    }

    // Metodo para buscar um cupom por id
    public Optional<Cupom> findById(Integer id) {
        return cupomRepository.findById(id);
    }

    // Metodo para buscar um cupom por codigo
    public Optional<Cupom> findByCodigo(String codigo) {
        return cupomRepository.findByCodigo(codigo);
    }

    // Metodo para salvar um cupom
    public Cupom save(Cupom cupom) {
        return cupomRepository.save(cupom);
    }

    // Metodo para deletar um cupom
    public void delete(Integer id) {
        cupomRepository.deleteById(id);
    }

    // Metodo para validar um cupom
    public Cupom validarCupom(Cupom cupomId) {
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

    // Metodo para incrementar a quantidade de uso de um cupom
    protected void incrementarQuantidadeUso(Cupom cupom) {
        if (cupom.getQuantidadeUso() == null) {
            cupom.setQuantidadeUso(1);
        } else {
            cupom.setQuantidadeUso(cupom.getQuantidadeUso() + 1);
        }
        cupomRepository.save(cupom);
    }
}
