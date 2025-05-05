package com.senac.torreverde.ms_compra.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Entidade Pedido
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Integer id;

    @Column(name = "pedido_data")
    private LocalDateTime data;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "pedido_valor_total")
    private Double valorTotal;

    @Column(name = "pedido_status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "cupom_id")
    private Cupom cupom;

    @Column(name = "pedido_desconto_aplicado")
    private Double descontoAplicado;

    @Column(name = "pedido_observacoes", columnDefinition = "TEXT")
    private String observacoes;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> itens = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(Integer id, LocalDateTime data, Integer usuarioId, Double valorTotal, Integer status, Cupom cupom, Double descontoAplicado, String observacoes, List<PedidoItem> itens) {
        this.id = id;
        this.data = data;
        this.usuarioId = usuarioId;
        this.valorTotal = valorTotal;
        this.status = status;
        this.cupom = cupom;
        this.descontoAplicado = descontoAplicado;
        this.observacoes = observacoes;
        this.itens = itens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Cupom getCupom() {
        return cupom;
    }

    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
    }

    public Double getDescontoAplicado() {
        return descontoAplicado;
    }

    public void setDescontoAplicado(Double descontoAplicado) {
        this.descontoAplicado = descontoAplicado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public List<PedidoItem> getItens() {
        return itens;
    }
}
