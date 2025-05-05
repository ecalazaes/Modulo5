package com.senac.torreverde.ms_compra.entities;

import jakarta.persistence.*;

// Entidade PedidoItemDoacao

@Entity
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

    public PedidoItemDoacao() {
    }

    public PedidoItemDoacao(Integer id, Double valor, PedidoItem pedidoItem) {
        this.id = id;
        this.valor = valor;
        this.pedidoItem = pedidoItem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public PedidoItem getPedidoItem() {
        return pedidoItem;
    }

    public void setPedidoItem(PedidoItem pedidoItem) {
        this.pedidoItem = pedidoItem;
    }
}
