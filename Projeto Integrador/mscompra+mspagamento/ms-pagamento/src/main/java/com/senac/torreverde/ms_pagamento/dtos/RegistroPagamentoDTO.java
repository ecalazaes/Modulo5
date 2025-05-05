package com.senac.torreverde.ms_pagamento.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RegistroPagamentoDTO implements Serializable {

    private Integer id;
    private Integer usuarioId;
    private Integer numero;
    private LocalDateTime data;
    private String chaveNfe;
    private Double valorTotal;
    private Integer status;

    public RegistroPagamentoDTO() {
    }

    public RegistroPagamentoDTO(Integer id, Integer usuarioId, Integer numero, LocalDateTime data, String chaveNfe, Double valorTotal, Integer status) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.numero = numero;
        this.data = data;
        this.chaveNfe = chaveNfe;
        this.valorTotal = valorTotal;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getChaveNfe() {
        return chaveNfe;
    }

    public void setChaveNfe(String chaveNfe) {
        this.chaveNfe = chaveNfe;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
