package com.torreverde.compra_service.service;

import com.torreverde.compra_service.model.PedidoItemDoacao;
import com.torreverde.compra_service.repository.PedidoItemDoacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    private final PedidoItemDoacaoRepository pedidoItemDoacaoRepository;

    public CompraService(PedidoItemDoacaoRepository pedidoItemDoacaoRepository) {
        this.pedidoItemDoacaoRepository = pedidoItemDoacaoRepository;
    }

    public PedidoItemDoacao adicionarDoacao(PedidoItemDoacao pedidoItemDoacao) {
        return pedidoItemDoacaoRepository.save(pedidoItemDoacao);
    }

}
