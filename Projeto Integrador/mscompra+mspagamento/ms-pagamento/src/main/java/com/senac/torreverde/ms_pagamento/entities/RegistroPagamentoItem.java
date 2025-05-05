package com.senac.torreverde.ms_pagamento.entities;

import jakarta.persistence.*;

// Entidade RegistroPagamentoItem
@Entity
public class RegistroPagamentoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_pagamento_item_id")
    private Integer id;

    @Column(name = "registro_pagamento_id", insertable = false, updatable = false)
    private Integer registroPagamentoId;

    @Column(name = "estoque_id")
    private Integer estoqueId;

    @Column(name = "registro_pagamento_item_quantidade")
    private Integer quantidade;

    @Column(name = "registro_pagamento_item_preco_unitario")
    private Double precoUnitario;

    @Column(name = "registro_pagamento_item_sub_total")
    private Double subTotal;

    @ManyToOne
    @JoinColumn(name = "registro_pagamento_id")
    private RegistroPagamento registroPagamento;

    @OneToOne(mappedBy = "registroPagamentoItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private RegistroItemDoacao doacao;

    public RegistroPagamentoItem() {
    }

    public RegistroPagamentoItem(Integer id, Integer registroPagamentoId, Integer estoqueId, Integer quantidade, Double precoUnitario, Double subTotal, RegistroPagamento registroPagamento, RegistroItemDoacao doacao) {
        this.id = id;
        this.registroPagamentoId = registroPagamentoId;
        this.estoqueId = estoqueId;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subTotal = subTotal;
        this.registroPagamento = registroPagamento;
        this.doacao = doacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRegistroPagamentoId() {
        return registroPagamentoId;
    }

    public void setRegistroPagamentoId(Integer registroPagamentoId) {
        this.registroPagamentoId = registroPagamentoId;
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

    public RegistroPagamento getRegistroPagamento() {
        return registroPagamento;
    }

    public void setRegistroPagamento(RegistroPagamento registroPagamento) {
        this.registroPagamento = registroPagamento;
    }

    public RegistroItemDoacao getDoacao() {
        return doacao;
    }

    public void setDoacao(RegistroItemDoacao doacao) {
        this.doacao = doacao;
    }
}
