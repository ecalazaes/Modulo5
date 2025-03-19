package com.torreverde.ms_compra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long id;

    @Column(name = "pedido_usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "pedido_valor_total", nullable = false)
    private Integer total;

    @Column(name = "pedido_status", nullable = false)
    private String status;

    @Column(name = "pedido_data_criacao", nullable = false)
    private LocalDateTime dataCriacao;
}
