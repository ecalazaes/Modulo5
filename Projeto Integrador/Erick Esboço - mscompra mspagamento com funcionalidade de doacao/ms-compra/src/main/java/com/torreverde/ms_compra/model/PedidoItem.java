package com.torreverde.ms_compra.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_item_id")
    private Long id;

    @Column(name = "pedido_item_nome", nullable = false)
    private String nome;

    @Column(name = "pedido_item_preco", nullable = false)
    private Integer preco;

    @Column(name = "pedido_item_quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "pedido_item_doacao", nullable = false)
    private Boolean doacao;  // Indica se o item é uma doação

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;
}
