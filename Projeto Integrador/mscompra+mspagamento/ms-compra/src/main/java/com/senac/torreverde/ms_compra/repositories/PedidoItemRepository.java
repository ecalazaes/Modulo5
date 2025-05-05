package com.senac.torreverde.ms_compra.repositories;

import com.senac.torreverde.ms_compra.entities.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositorio para PedidoItem
@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, Integer> {
}
