package com.senac.torreverde.ms_compra.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

// Entidade Cupom
@Entity
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

    public Cupom() {
    }

    public Cupom(Integer id, String codigo, String descricao, Integer tipoDesconto, Double valorDesconto, LocalDate validade, Integer quantidadeUso, Integer status) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.tipoDesconto = tipoDesconto;
        this.valorDesconto = valorDesconto;
        this.validade = validade;
        this.quantidadeUso = quantidadeUso;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(Integer tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Integer getQuantidadeUso() {
        return quantidadeUso;
    }

    public void setQuantidadeUso(Integer quantidadeUso) {
        this.quantidadeUso = quantidadeUso;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

