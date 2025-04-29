package com.senac.torreverde.ms_pagamento.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class RegistroPagamentoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_pagamento_item_id")
    private Integer id;

    @Column(name = "registro_pagamento_id", insertable = false, updatable = false)
    private Integer registroPagamentoId;

    @Column(name = "estoque_id")
    private Integer estoqueId;

    @Column(name = "registro_pagamento_item_quantidade")
    private Integer quantidade;

    @Column(name = "registro_pagamento_item_preco_unitario")
    private Double precoUnitario;

    @Column(name = "registro_pagamento_item_sub_total")
    private Double subTotal;

    @ManyToOne
    @JoinColumn(name = "registro_pagamento_id")
    private RegistroPagamento registroPagamento;

    @OneToOne(mappedBy = "registroPagamentoItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private RegistroItemDoacao doacao;

}
