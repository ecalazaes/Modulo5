package com.torreverde.ms_compra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoItemDTO implements Serializable {

    private String nome;
    private Integer preco;
    private Integer quantidade;
    private Boolean doacao;  // Se for uma doação, esse campo será true

}
