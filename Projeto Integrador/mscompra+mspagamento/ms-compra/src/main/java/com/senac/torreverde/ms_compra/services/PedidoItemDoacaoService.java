package com.senac.torreverde.ms_compra.services;

import com.senac.torreverde.ms_compra.entities.PedidoItemDoacao;
import com.senac.torreverde.ms_compra.repositories.PedidoItemDoacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoItemDoacaoService {

    @Autowired
    private PedidoItemDoacaoRepository pedidoItemDoacaoRepository;

    // Metodo para listar todos
    public List<PedidoItemDoacao> findAll() {
        return pedidoItemDoacaoRepository.findAll();
    }

    // Metodo para salvar pedidoItemDoacao
    @Transactional
    public PedidoItemDoacao salvarDoacao(PedidoItemDoacao doacao) {
        if (doacao != null) {
            pedidoItemDoacaoRepository.save(doacao);
        }
        return doacao;
    }

    // Metodo para buscar um pedidoItemDoacao por id
    public Optional<PedidoItemDoacao> findById(Integer id) {
        return pedidoItemDoacaoRepository.findById(id);
    }

    // Metodo para deletar um pedidoItemDoacao
    public void delete(Integer id) {
        pedidoItemDoacaoRepository.deleteById(id);
    }
}
