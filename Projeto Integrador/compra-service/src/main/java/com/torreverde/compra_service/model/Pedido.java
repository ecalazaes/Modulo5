package com.torreverde.compra_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long id;

    @Column(name = "pedido_numero")
    private Integer numero;

    @Column(name = "pedido_data")
    private LocalDateTime data;

    @Column(name = "pedido_valor_total")
    private Integer valorTotal;

    @Column(name = "pedido_usuario_id")
    private Long usuarioId;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PedidoItem> itens;

//    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<PedidoItemDoacao> itensDoacao;
}
