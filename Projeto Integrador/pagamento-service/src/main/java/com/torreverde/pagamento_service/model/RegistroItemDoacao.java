package com.torreverde.pagamento_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegistroItemDoacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_item_doacao_id")
    private Long id;

    @Column(name = "registro_item_doacao_valor")
    private Integer valor;

    @Column(name = "registro_item_doacao_data")
    private LocalDateTime data;

    @Column(name = "registro_item_doacao_doador_identificacao")
    private String identificacao;

    @Column(name = "registro_item_doacao_doador_tipo")
    private Integer doadorTipo;

    @Column(name = "registro_item_doacao_transacao_id")
    private String transacaoId;

    @ManyToOne
    @JoinColumn(name = "registro_pagamento_id")
    private RegistroPagamento registroPagamento;

    @Column(nullable = true)
    private Long registroPagamentoItemId;

    @Column(nullable = true)
    private Long pedidoId;
}
