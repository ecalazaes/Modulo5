package com.torreverde.ms_pagamento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "pagamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pagamento_id")
    private Long id;

    @Column(name = "pagamento_pedido_id")
    private Long pedidoId;

    @Column(name = "pagamento_usuario_id")
    private Long usuarioId;

    @Column(name = "pagamento_nome_doador")
    private String nomeDoador;

    @Column(name = "pagamento_documento_doador", length = 14)
    private String documentoDoador; // CPF ou CNPJ

    @Column(name = "pagamento_valor", nullable = false)
    private Integer valor;

    @Column(name = "pagamento_status", nullable = false)
    private String status; // "APROVADO" ou "RECUSADO"

    @Column(name = "pagamento_data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "pagamento_contem_doacao", nullable = false)
    private Boolean doacao;  // Novo campo para indicar se o pagamento é de uma doação
}
