package com.senac.torreverde.ms_compra.repository;

import com.senac.torreverde.ms_compra.model.PedidoItemDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoItemDoacaoRepository extends JpaRepository<PedidoItemDoacao, Integer> {
}
