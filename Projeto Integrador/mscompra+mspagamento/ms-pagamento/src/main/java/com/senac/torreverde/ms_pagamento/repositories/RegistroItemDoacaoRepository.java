package com.senac.torreverde.ms_pagamento.repositories;

import com.senac.torreverde.ms_pagamento.entities.RegistroItemDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositório para RegistroItemDoacao
@Repository
public interface RegistroItemDoacaoRepository extends JpaRepository<RegistroItemDoacao, Integer> {
}
