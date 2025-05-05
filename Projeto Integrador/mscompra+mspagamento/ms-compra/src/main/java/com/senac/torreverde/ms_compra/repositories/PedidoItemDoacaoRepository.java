package com.senac.torreverde.ms_compra.repositories;

import com.senac.torreverde.ms_compra.entities.PedidoItemDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositório para PedidoItemDoacao
@Repository
public interface PedidoItemDoacaoRepository extends JpaRepository<PedidoItemDoacao, Integer> {
}
