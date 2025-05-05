package com.senac.torreverde.ms_pagamento.dtos;

// Classe DTO para referenciar a RegistroItemDoacao
public class RegistroItemDoacaoDTO {

    private Double valorDoacao;
    private String doadorNome;
    private String doadorIdentificacao;
    private Integer doadorTipo;
    private String doadorPaisOrigem;
    private String formaTransferencia;
    private String idTransacao;

    public RegistroItemDoacaoDTO() {
    }

    public RegistroItemDoacaoDTO(Double valorDoacao, String doadorNome, String doadorIdentificacao, Integer doadorTipo, String doadorPaisOrigem, String formaTransferencia, String idTransacao) {
        this.valorDoacao = valorDoacao;
        this.doadorNome = doadorNome;
        this.doadorIdentificacao = doadorIdentificacao;
        this.doadorTipo = doadorTipo;
        this.doadorPaisOrigem = doadorPaisOrigem;
        this.formaTransferencia = formaTransferencia;
        this.idTransacao = idTransacao;
    }

    public Double getValorDoacao() {
        return valorDoacao;
    }

    public void setValorDoacao(Double valorDoacao) {
        this.valorDoacao = valorDoacao;
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
}
