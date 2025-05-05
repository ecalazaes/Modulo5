package com.senac.torreverde.ms_pagamento.entities;

import jakarta.persistence.*;

// Entidade EntregaEndereco
@Entity
public class EntregaEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrega_endereco_id")
    private Integer id;

    @Column(name = "entrega_endereco", length = 300)
    private String endereco;

    @Column(name = "entrega_endereco_cep", length = 7)
    private String cep;

    @Column(name = "registro_pagamento_id", insertable = false, updatable = false)
    private Integer registroPagamentoId;

    @OneToOne
    @JoinColumn(name = "registro_pagamento_id")
    private RegistroPagamento registroPagamento;

    public EntregaEndereco() {
    }

    public EntregaEndereco(Integer id, String endereco, String cep, Integer registroPagamentoId, RegistroPagamento registroPagamento) {
        this.id = id;
        this.endereco = endereco;
        this.cep = cep;
        this.registroPagamentoId = registroPagamentoId;
        this.registroPagamento = registroPagamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getRegistroPagamentoId() {
        return registroPagamentoId;
    }

    public void setRegistroPagamentoId(Integer registroPagamentoId) {
        this.registroPagamentoId = registroPagamentoId;
    }

    public RegistroPagamento getRegistroPagamento() {
        return registroPagamento;
    }

    public void setRegistroPagamento(RegistroPagamento registroPagamento) {
        this.registroPagamento = registroPagamento;
    }
}
