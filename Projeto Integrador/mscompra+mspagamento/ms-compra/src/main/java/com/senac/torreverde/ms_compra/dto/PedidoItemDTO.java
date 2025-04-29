package com.senac.torreverde.ms_compra.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoItemDTO {

    private Integer estoqueId;
    private Integer quantidade;
    private Double precoUnitario;
    private Double subTotal;

}
