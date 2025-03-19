package com.torreverde.ms_compra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO implements Serializable {

    private Long id;
    private Long usuarioId;
    private List<PedidoItemDTO> itensPedido;  // Lista de produtos e doações
    private Integer total;
}
