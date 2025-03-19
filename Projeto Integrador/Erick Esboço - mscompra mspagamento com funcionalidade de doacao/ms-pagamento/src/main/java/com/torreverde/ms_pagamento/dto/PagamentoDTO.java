package com.torreverde.ms_pagamento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagamentoDTO implements Serializable {

    private Long pedidoId;
    private Long usuarioId;
    private String nomeDoador;
    private String documento;
    private Integer valor;
    private String status;
    private Boolean doacao;  // Indica se o pagamento contém uma doação
    private LocalDateTime dataCriacao;

}
