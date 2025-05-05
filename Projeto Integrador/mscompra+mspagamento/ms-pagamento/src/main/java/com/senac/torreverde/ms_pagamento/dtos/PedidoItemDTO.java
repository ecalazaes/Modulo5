package com.senac.torreverde.ms_pagamento.dtos;

// Classe DTO para recebimento do evento de criar PedidoItem via Rabbitmq
public class PedidoItemDTO {

    private Integer estoqueId;
    private Integer quantidade;
    private Double precoUnitario;
    private Double subTotal;

    public PedidoItemDTO() {
    }

    public PedidoItemDTO(Integer estoqueId, Integer quantidade, Double precoUnitario, Double subTotal) {
        this.estoqueId = estoqueId;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subTotal = subTotal;
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
}
