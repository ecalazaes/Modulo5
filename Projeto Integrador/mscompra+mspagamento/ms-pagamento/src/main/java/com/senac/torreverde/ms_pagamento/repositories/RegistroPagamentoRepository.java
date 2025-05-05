package com.senac.torreverde.ms_pagamento.repositories;

import com.senac.torreverde.ms_pagamento.entities.RegistroPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositório para RegistroPagamento
@Repository
public interface RegistroPagamentoRepository extends JpaRepository<RegistroPagamento, Integer> {
}
