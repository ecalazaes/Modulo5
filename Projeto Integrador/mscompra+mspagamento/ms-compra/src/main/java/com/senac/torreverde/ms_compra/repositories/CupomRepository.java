package com.senac.torreverde.ms_compra.repositories;

import com.senac.torreverde.ms_compra.entities.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repositorio para Cupom
@Repository
public interface CupomRepository extends JpaRepository<Cupom, Integer> {
    Optional<Cupom> findByCodigo(String codigo);
}
