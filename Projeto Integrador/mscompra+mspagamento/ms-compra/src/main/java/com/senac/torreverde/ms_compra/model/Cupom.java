package com.senac.torreverde.ms_compra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cupom_id")
    private Integer id;

    @Column(name = "cupom_codigo", length = 45, unique = true)
    private String codigo;

    @Column(name = "cupom_descricao", length = 300)
    private String descricao;

    @Column(name = "cupom_tipo_desconto")
    private Integer tipoDesconto;

    @Column(name = "cupom_valor_desconto", precision = 10)
    private Double valorDesconto;

    @Column(name = "cupom_validade")
    private LocalDate validade;

    @Column(name = "cupom_quantidade_uso")
    private Integer quantidadeUso;

    @Column(name = "cupom_status")
    private Integer status;
}

