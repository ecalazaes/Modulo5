package com.senac.torreverde.ms_compra.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PedidoItemDoacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_item_doacao_id")
    private Integer id;

    @Column(name = "pedido_item_doacao_valor")
    private Double valor;

    @OneToOne
    @JoinColumn(name = "pedido_item_id", nullable = false)
    private PedidoItem pedidoItem;
}
