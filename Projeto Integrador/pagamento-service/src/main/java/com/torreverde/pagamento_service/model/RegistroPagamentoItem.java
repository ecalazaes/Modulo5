package com.torreverde.pagamento_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegistroPagamentoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_pagamento_item_id")
    private Long id;

    @Column(name = "registro_pagamento_item_quantidade")
    private Integer quantidade;

    @Column(name = "registro_pagamento_item_estoque_id")
    private Long estoqueId;

    @ManyToOne
    @JoinColumn(name = "registro_pagamento_id", nullable = false)
    private RegistroPagamento registroPagamento;
}
