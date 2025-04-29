package com.senac.torreverde.ms_compra.service;

import com.senac.torreverde.ms_compra.model.PedidoItemDoacao;
import com.senac.torreverde.ms_compra.repository.PedidoItemDoacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoItemDoacaoService {

    private final PedidoItemDoacaoRepository pedidoItemDoacaoRepository;

    public PedidoItemDoacaoService(PedidoItemDoacaoRepository pedidoItemDoacaoRepository) {
        this.pedidoItemDoacaoRepository = pedidoItemDoacaoRepository;
    }

    public List<PedidoItemDoacao> findAll() {
        return pedidoItemDoacaoRepository.findAll();
    }

    @Transactional
    public PedidoItemDoacao salvarDoacao(PedidoItemDoacao doacao) {
        if (doacao != null) {
            pedidoItemDoacaoRepository.save(doacao);
        }
        return doacao;
    }

    public Optional<PedidoItemDoacao> findById(Integer id) {
        return pedidoItemDoacaoRepository.findById(id);
    }

    public void delete(Integer id) {
        pedidoItemDoacaoRepository.deleteById(id);
    }
}
