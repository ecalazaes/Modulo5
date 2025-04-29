package com.torrev.ms_compra.repository;

import com.torrev.ms_compra.model.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Integer> {
    Optional<Cupom> findByCupomCodigoAndCupomValidadeAfterAndCupomStatus(String cupomCodigo, LocalDateTime now, int status);
}
