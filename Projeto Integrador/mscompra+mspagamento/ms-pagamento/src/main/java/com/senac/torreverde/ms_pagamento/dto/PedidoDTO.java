package com.senac.torreverde.ms_pagamento.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class PedidoDTO implements Serializable {

    private Integer pedidoId;
    private Integer usuarioId;
    private Double pedidoValorTotal;
    private List<PedidoItemDTO> itens;
}