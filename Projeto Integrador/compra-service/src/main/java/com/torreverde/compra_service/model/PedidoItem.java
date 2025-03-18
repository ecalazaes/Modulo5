package com.torreverde.compra_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_item_id")
    private Long id;

    @Column(name = "pedido_item_quantidade")
    private Integer quantidade;

    @Column(name = "pedido_item_estoque_id")
    private Long estoqueId;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
