package com.torreverde.ms_pagamento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoacaoDTO {

    private String nomeDoador;
    private String documentoDoador; // CPF ou CNPJ
    private Integer valor;
}
