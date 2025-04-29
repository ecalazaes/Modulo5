package com.torrev.ms_compra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cupom_id")
    private Integer cupomId;

    @Column(name = "cupom_codigo", length = 45)
    private String cupomCodigo;

    @Column(name = "cupom_descricao", length = 300)
    private String cupomDescricao;

    @Column(name = "cupom_tipo_desconto")
    private String cupomTipoDesconto;

    @Column(name = "cupom_valor_desconto")
    private Double cupomValorDesconto;

    @Column(name = "cupom_validade")
    private LocalDateTime cupomValidade;

    @Column(name = "cupom_quantidade_uso")
    private Integer cupomQuantidadeUso;

    @Column(name = "cupom_status")
    private Integer cupomStatus;
}
