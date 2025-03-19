package com.torreverde.ms_compra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

//    private Long id;
//    private Long usuarioId;
//    private Integer total;
//    private String status;
//    private LocalDateTime dataCriacao;

    private Long id;
    private Long usuarioId;
    private List<PedidoItemDTO> itens;  // Lista de produtos e doações
    private Integer total;
}
