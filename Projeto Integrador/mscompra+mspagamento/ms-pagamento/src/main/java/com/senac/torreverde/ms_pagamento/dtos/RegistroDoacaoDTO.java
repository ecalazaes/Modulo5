package com.senac.torreverde.ms_pagamento.dtos;

public class RegistroDoacaoDTO {

    private Integer usuarioId;
    private RegistroItemDoacaoDTO detalhesDoacao;

    public RegistroDoacaoDTO() {
    }

    public RegistroDoacaoDTO(Integer usuarioId, RegistroItemDoacaoDTO detalhesDoacao) {
        this.usuarioId = usuarioId;
        this.detalhesDoacao = detalhesDoacao;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public RegistroItemDoacaoDTO getDetalhesDoacao() {
        return detalhesDoacao;
    }

    public void setDetalhesDoacao(RegistroItemDoacaoDTO detalhesDoacao) {
        this.detalhesDoacao = detalhesDoacao;
    }
}
