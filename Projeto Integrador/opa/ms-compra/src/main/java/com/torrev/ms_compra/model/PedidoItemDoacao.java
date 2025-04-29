package com.torrev.ms_compra.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PedidoItemDoacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_item_doacao_id")
    private Integer pedidoItemDoacaoId;

    @OneToOne
    @JoinColumn(name = "pedido_item_id")
    private PedidoItem pedidoItem;

//    @ManyToOne
//    @JoinColumn(name = "pedido_id")
//    private Pedido pedido;

    @Column(name = "pedido_item_doacao_valor")
    private Double pedidoItemDoacaoValor;
}
