package com.senac.torreverde.ms_compra.repository;

import com.senac.torreverde.ms_compra.model.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, Integer> {
    List<PedidoItem> findByPedidoId(Integer pedidoId);
}
