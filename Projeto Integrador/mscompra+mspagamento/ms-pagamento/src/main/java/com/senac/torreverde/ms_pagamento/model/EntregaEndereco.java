package com.senac.torreverde.ms_pagamento.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EntregaEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrega_endereco_id")
    private Integer id;

    @Column(name = "entrega_endereco", length = 300)
    private String endereco;

    @Column(name = "entrega_endereco_cep", length = 7)
    private String cep;

    @Column(name = "registro_pagamento_id", insertable = false, updatable = false)
    private Integer registroPagamentoId;

    @OneToOne
    @JoinColumn(name = "registro_pagamento_id")
    private RegistroPagamento registroPagamento;
}
