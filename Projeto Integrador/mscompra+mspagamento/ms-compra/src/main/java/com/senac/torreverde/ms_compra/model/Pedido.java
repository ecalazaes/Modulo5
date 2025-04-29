package com.senac.torreverde.ms_compra.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

}
