package com.torrev.ms_compra.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NovoPedidoEvent {

    private Integer pedidoId;
    private Double valorTotal;
    private Integer usuarioId;

}
