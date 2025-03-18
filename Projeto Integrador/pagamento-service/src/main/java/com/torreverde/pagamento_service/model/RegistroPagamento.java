package com.torreverde.pagamento_service.model;

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
public class RegistroPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_pagamento_id")
    private Long id;

    @Column(name = "registro_pagamento_numero")
    private Integer numero;

    @Column(name = "registro_pagamento_data")
    private LocalDateTime data;

    @Column(name = "registro_pagamento_chaveNFE")
    private String chaveNFE;

    @Column(name = "registro_pagamento_usuario_id")
    private Long usuarioId;

    @OneToMany(mappedBy = "registroPagamento", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<RegistroItemDoacao> itensDoacao;
}
