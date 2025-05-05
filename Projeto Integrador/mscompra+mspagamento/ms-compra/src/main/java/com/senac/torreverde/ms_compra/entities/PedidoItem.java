package com.senac.torreverde.ms_compra.entities;

import jakarta.persistence.*;

// Entidade PedidoItem
@Entity
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

    public PedidoItem() {
    }

    public PedidoItem(Integer id, Integer estoqueId, Integer quantidade, Double precoUnitario, Double subTotal, Integer status, Pedido pedido, PedidoItemDoacao doacao) {
        this.id = id;
        this.estoqueId = estoqueId;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subTotal = subTotal;
        this.status = status;
        this.pedido = pedido;
        this.doacao = doacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstoqueId() {
        return estoqueId;
    }

    public void setEstoqueId(Integer estoqueId) {
        this.estoqueId = estoqueId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public PedidoItemDoacao getDoacao() {
        return doacao;
    }

    public void setDoacao(PedidoItemDoacao doacao) {
        this.doacao = doacao;
    }
}
