package com.senac.torreverde.ms_pagamento.service;

import com.senac.torreverde.ms_pagamento.model.RegistroItemDoacao;
import com.senac.torreverde.ms_pagamento.repository.RegistroItemDoacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistroItemDoacaoService {

    private final RegistroItemDoacaoRepository registroItemDoacaoRepository;

    public RegistroItemDoacaoService(RegistroItemDoacaoRepository registroItemDoacaoRepository) {
        this.registroItemDoacaoRepository = registroItemDoacaoRepository;
    }

    public RegistroItemDoacao save(RegistroItemDoacao registroItemDoacao) {
        return registroItemDoacaoRepository.save(registroItemDoacao);
    }
}
