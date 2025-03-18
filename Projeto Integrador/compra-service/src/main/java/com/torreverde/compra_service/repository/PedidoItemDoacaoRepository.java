package com.torreverde.compra_service.repository;

import com.torreverde.compra_service.model.PedidoItemDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoItemDoacaoRepository extends JpaRepository<PedidoItemDoacao, Long> {
}
