package com.torrev.ms_compra.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_item_id")
    private Integer pedidoItemId;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Column(name = "estoque_id")
    private Integer estoqueId;

    @Column(name = "pedido_item_quantidade")
    private Integer pedidoItemQuantidade;

    @Column(name = "pedido_item_preco_unitario")
    private Double pedidoItemPrecoUnitario;

    @Column(name = "pedido_item_sub_total")
    private Double pedidoItemSubTotal;

    @Column(name = "pedido_item_status")
    private Integer pedidoItemStatus;

    @OneToOne(mappedBy = "pedidoItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private PedidoItemDoacao pedidoItemDoacao;
}
