package com.torrev.ms_compra.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Integer pedidoId;

    @Column(name = "pedido_data", updatable = false)
    @CreationTimestamp
    private LocalDateTime pedidoData;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "pedido_valor_total")
    private Double pedidoValorTotal;

    @Column(name = "pedido_status")
    private Integer pedidoStatus; // 0: pendente, outros status conforme necessidade

    @Column(name = "cupom_id")
    private Integer cupomId;

    @Column(name = "pedido_desconto_aplicado", length = 50)
    private String pedidoDescontoAplicado; // Pode armazenar o valor ou código do cupom aplicado

    @Column(name = "pedido_observacoes", columnDefinition = "TEXT")
    private String pedidoObservacoes;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> itens = new ArrayList<>();

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItemDoacao> itensDoacao = new ArrayList<>();

}
