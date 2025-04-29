package com.senac.torreverde.ms_compra.repository;

import com.senac.torreverde.ms_compra.model.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Integer> {
    Cupom findByCodigo(String codigo);
}
