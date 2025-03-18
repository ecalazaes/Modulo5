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
public class PedidoItemDoacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_item_docao_id")
    private Long id;

    @Column(name = "pedido_item_doacao_valor")
    private Integer valor;

    @ManyToOne
    @JoinColumn(name = "pedido_item_id")
    private PedidoItem pedidoItem;
}
