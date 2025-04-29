package com.senac.torreverde.ms_pagamento.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
public class RegistroItemDoacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_pagamento_item_doacao_id")
    private Integer id;

    @Column(name = "registro_pagamento_item_doacao_valor", length = 45)
    private String valor;

    @Column(name = "registro_pagamento_item_doacao_data")
    private LocalDateTime data;

    @Column(name = "registro_item_doacao_doador_nome", length = 300)
    private String doadorNome;

    @Column(name = "registro_item_doacao_doador_identificacao", length = 20)
    private String doadorIdentificacao;

    @Column(name = "registro_item_doacao_doador_tipo")
    private Integer doadorTipo;

    @Column(name = "registro_item_doacao_doador_pais_origem", length = 45)
    private String doadorPaisOrigem;

    @Column(name = "registro_item_doacao_forma_transferencia", length = 45)
    private String formaTransferencia;

    @Column(name = "registro_item_doacao_id_transacao", length = 200)
    private String idTransacao;

    @OneToOne
    @JoinColumn(name = "registro_pagamento_item_id")
    private RegistroPagamentoItem registroPagamentoItem;

}
