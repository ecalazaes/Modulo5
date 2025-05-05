package com.senac.torreverde.ms_pagamento.repositories;

import com.senac.torreverde.ms_pagamento.entities.EntregaEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositório para EntregaEndereco
@Repository
public interface EntregaEnderecoRepository extends JpaRepository<EntregaEndereco, Integer> {
}
