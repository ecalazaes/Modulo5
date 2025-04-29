package com.senac.torreverde.ms_pagamento.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
public class RegistroPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_pagamento_id")
    private Integer id;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "registro_pagamento_numero")
    private Integer numero;

    @Column(name = "registro_pagamento_data")
    private LocalDateTime data;

    @Column(name = "registro_pagamento_chave_nfe", length = 45)
    private String chaveNfe;

    @Column(name = "registro_pagamento_valor_total")
    private Double valorTotal;

    @Column(name = "registro_pagamento_status")
    private Integer status;

    @OneToOne(mappedBy = "registroPagamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private EntregaEndereco entregaEndereco;

    @OneToMany(mappedBy = "registroPagamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RegistroPagamentoItem> itens;
}
