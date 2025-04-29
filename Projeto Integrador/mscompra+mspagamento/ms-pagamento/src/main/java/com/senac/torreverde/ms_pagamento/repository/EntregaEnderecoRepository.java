package com.senac.torreverde.ms_pagamento.repository;

import com.senac.torreverde.ms_pagamento.model.EntregaEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaEnderecoRepository extends JpaRepository<EntregaEndereco, Integer> {
}
