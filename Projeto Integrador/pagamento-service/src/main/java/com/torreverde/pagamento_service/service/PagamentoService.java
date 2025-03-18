package com.torreverde.pagamento_service.service;

import com.torreverde.pagamento_service.model.RegistroItemDoacao;
import com.torreverde.pagamento_service.repository.RegistroItemDoacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PagamentoService {

    private final RegistroItemDoacaoRepository registroItemDoacaoRepository;

    public PagamentoService(RegistroItemDoacaoRepository registroItemDoacaoRepository) {
        this.registroItemDoacaoRepository = registroItemDoacaoRepository;
    }

    public RegistroItemDoacao adicionarDoacao(RegistroItemDoacao registroItemDoacao) {
        // Se o pedidoId for nulo, é uma doação direta
        if (registroItemDoacao.getPedidoId() == null) {
            registroItemDoacao.setRegistroPagamentoItemId(null); // Não está vinculada a um item de pedido
        }
        registroItemDoacao.setData(LocalDateTime.now());
        return registroItemDoacaoRepository.save(registroItemDoacao);
    }
}
