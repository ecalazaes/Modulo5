package com.senac.torreverde.ms_pagamento.repositories;

import com.senac.torreverde.ms_pagamento.entities.RegistroPagamentoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositório para RegistroPagamentoItem
@Repository
public interface RegistroPagamentoItemRepository extends JpaRepository<RegistroPagamentoItem, Integer> {
}
