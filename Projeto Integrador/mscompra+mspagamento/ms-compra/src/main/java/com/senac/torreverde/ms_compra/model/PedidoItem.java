package com.senac.torreverde.ms_compra.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_item_id")
    private Integer id;

    @Column(name = "estoque_id", nullable = false)
    private Integer estoqueId;

    @Column(name = "pedido_item_quantidade")
    private Integer quantidade;

    @Column(name = "pedido_item_preco_unitario")
    private Double precoUnitario;

    @Column(name = "pedido_item_sub_total")
    private Double subTotal;

    @Column(name = "pedido_item_status")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @OneToOne(mappedBy = "pedidoItem", cascade = CascadeType.ALL)
    private PedidoItemDoacao doacao;

}
