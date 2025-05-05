package com.senac.torreverde.ms_pagamento.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

// Entidade RegistroPagamento
@Entity
public class RegistroPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_pagamento_id")
    private Integer id;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "registro_pagamento_numero")
    private Integer numero;

    @Column(name = "registro_pagamento_data")
    private LocalDateTime data;

    @Column(name = "registro_pagamento_chave_nfe", length = 45)
    private String chaveNfe;

    @Column(name = "registro_pagamento_valor_total")
    private Double valorTotal;

    @Column(name = "registro_pagamento_status")
    private Integer status;

    @OneToOne(mappedBy = "registroPagamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private EntregaEndereco entregaEndereco;

    @OneToMany(mappedBy = "registroPagamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RegistroPagamentoItem> itens;

    public RegistroPagamento() {
    }

    public RegistroPagamento(Integer id, Integer usuarioId, Integer numero, LocalDateTime data, String chaveNfe, Double valorTotal, Integer status, EntregaEndereco entregaEndereco, List<RegistroPagamentoItem> itens) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.numero = numero;
        this.data = data;
        this.chaveNfe = chaveNfe;
        this.valorTotal = valorTotal;
        this.status = status;
        this.entregaEndereco = entregaEndereco;
        this.itens = itens;
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

    public EntregaEndereco getEntregaEndereco() {
        return entregaEndereco;
    }

    public void setEntregaEndereco(EntregaEndereco entregaEndereco) {
        this.entregaEndereco = entregaEndereco;
    }

    public List<RegistroPagamentoItem> getItens() {
        return itens;
    }

    public void setItens(List<RegistroPagamentoItem> itens) {
        this.itens = itens;
    }
}
