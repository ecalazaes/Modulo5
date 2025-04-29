package com.senac.torreverde.ms_pagamento.repository;

import com.senac.torreverde.ms_pagamento.model.RegistroPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroPagamentoRepository extends JpaRepository<RegistroPagamento, Integer> {
}
