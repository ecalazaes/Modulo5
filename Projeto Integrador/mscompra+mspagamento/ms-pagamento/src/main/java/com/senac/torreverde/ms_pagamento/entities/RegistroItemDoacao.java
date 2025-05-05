package com.senac.torreverde.ms_pagamento.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// Entidade RegistroItemDoacao
@Entity
public class RegistroItemDoacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_pagamento_item_doacao_id")
    private Integer id;

    @Column(name = "registro_pagamento_item_doacao_valor")
    private Double valor;

    @Column(name = "registro_item_doacao_data")
    private LocalDateTime data;

    @Column(name = "registro_item_doacao_doador_nome", length = 300)
    private String doadorNome;

    @Column(name = "registro_item_doacao_doador_identificacao", length = 20)
    private String doadorIdentificacao;

    @Column(name = "registro_item_doacao_doador_tipo")
    private Integer doadorTipo;

    @Column(name = "registro_item_doacao_doador_pais_origem", length = 45)
    private String doadorPaisOrigem;

    @Column(name = "registro_item_doacao_forma_transferencia", length = 45)
    private String formaTransferencia;

    @Column(name = "registro_item_doacao_id_transacao", length = 200)
    private String idTransacao;

    @OneToOne
    @JoinColumn(name = "registro_pagamento_item_id")
    private RegistroPagamentoItem registroPagamentoItem;

    public RegistroItemDoacao() {
    }

    public RegistroItemDoacao(Integer id, Double valor, LocalDateTime data, String doadorNome, String doadorIdentificacao, Integer doadorTipo, String doadorPaisOrigem, String formaTransferencia, String idTransacao, RegistroPagamentoItem registroPagamentoItem) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.doadorNome = doadorNome;
        this.doadorIdentificacao = doadorIdentificacao;
        this.doadorTipo = doadorTipo;
        this.doadorPaisOrigem = doadorPaisOrigem;
        this.formaTransferencia = formaTransferencia;
        this.idTransacao = idTransacao;
        this.registroPagamentoItem = registroPagamentoItem;
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getDoadorNome() {
        return doadorNome;
    }

    public void setDoadorNome(String doadorNome) {
        this.doadorNome = doadorNome;
    }

    public String getDoadorIdentificacao() {
        return doadorIdentificacao;
    }

    public void setDoadorIdentificacao(String doadorIdentificacao) {
        this.doadorIdentificacao = doadorIdentificacao;
    }

    public Integer getDoadorTipo() {
        return doadorTipo;
    }

    public void setDoadorTipo(Integer doadorTipo) {
        this.doadorTipo = doadorTipo;
    }

    public String getDoadorPaisOrigem() {
        return doadorPaisOrigem;
    }

    public void setDoadorPaisOrigem(String doadorPaisOrigem) {
        this.doadorPaisOrigem = doadorPaisOrigem;
    }

    public String getFormaTransferencia() {
        return formaTransferencia;
    }

    public void setFormaTransferencia(String formaTransferencia) {
        this.formaTransferencia = formaTransferencia;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }

    public RegistroPagamentoItem getRegistroPagamentoItem() {
        return registroPagamentoItem;
    }

    public void setRegistroPagamentoItem(RegistroPagamentoItem registroPagamentoItem) {
        this.registroPagamentoItem = registroPagamentoItem;
    }
}
