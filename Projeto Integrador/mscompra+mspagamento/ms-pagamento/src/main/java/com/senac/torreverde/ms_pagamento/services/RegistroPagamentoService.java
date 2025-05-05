package com.senac.torreverde.ms_pagamento.services;

import com.senac.torreverde.ms_pagamento.dtos.*;
import com.senac.torreverde.ms_pagamento.entities.RegistroItemDoacao;
import com.senac.torreverde.ms_pagamento.entities.RegistroPagamento;
import com.senac.torreverde.ms_pagamento.entities.RegistroPagamentoItem;
import com.senac.torreverde.ms_pagamento.repositories.RegistroItemDoacaoRepository;
import com.senac.torreverde.ms_pagamento.repositories.RegistroPagamentoItemRepository;
import com.senac.torreverde.ms_pagamento.repositories.RegistroPagamentoRepository;
import com.senac.torreverde.ms_pagamento.services.rabbitmq.PagamentoPublisher;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

// Service de Registro Pagamento
@Service
public class RegistroPagamentoService {

    @Autowired
    private RegistroPagamentoRepository registroPagamentoRepository;
    @Autowired
    private RegistroPagamentoItemRepository registroPagamentoItemRepository;
    @Autowired
    private RegistroItemDoacaoRepository registroItemDoacaoRepository;
    @Autowired
    private PagamentoPublisher pagamentoPublisher;

    // Metodo que processa o pagamento de um pedido enviado do ms-compra
    @Transactional
    public RegistroPagamento processarPagamento(PedidoDTO pedidoDTO) {

        // Mock de status pagamento, Exemplo: 1 = Pagamento Confirmado, 2 = Pagamento Recusado
        int statusPagamento = new Random().nextInt(1,3);

        // Criando o registro de Pagamento
        RegistroPagamento registroPagamento = new RegistroPagamento();
        registroPagamento.setUsuarioId(pedidoDTO.getUsuarioId());
        registroPagamento.setNumero(pedidoDTO.getPedidoId());
        registroPagamento.setData(LocalDateTime.now());
        registroPagamento.setChaveNfe(new Random().nextInt(100000000) + "");
        registroPagamento.setValorTotal(pedidoDTO.getPedidoValorTotal());
        registroPagamento.setStatus(statusPagamento);
        RegistroPagamento registroPagamentoSalvo = registroPagamentoRepository.save(registroPagamento);

        // Criando o registro de Item de Pagamento
        if (pedidoDTO.getItens() != null && !pedidoDTO.getItens().isEmpty()) {
            for (PedidoItemDTO itemDTO : pedidoDTO.getItens()) {
                RegistroPagamentoItem registroPagamentoItem = new RegistroPagamentoItem();
                registroPagamentoItem.setRegistroPagamentoId(registroPagamentoSalvo.getId());
                registroPagamentoItem.setEstoqueId(itemDTO.getEstoqueId());
                registroPagamentoItem.setQuantidade(itemDTO.getQuantidade());
                registroPagamentoItem.setPrecoUnitario(itemDTO.getPrecoUnitario());
                registroPagamentoItem.setSubTotal(itemDTO.getSubTotal());
                registroPagamentoItem.setRegistroPagamento(registroPagamentoSalvo);

                registroPagamentoItemRepository.save(registroPagamentoItem);
            }
            System.out.println("Registro de Pagamento e seus itens criados para o pedido " + pedidoDTO.getPedidoId());
        } else {
            System.out.println("Pedido ID: " + pedidoDTO.getPedidoId() + " não possui itens.");
        }

        // Criando DTO de pagamento concluido para enviar via Rabbitmq
        RegistroPagamentoDTO registroPagamentoDTO = new RegistroPagamentoDTO();
        registroPagamentoDTO.setId(registroPagamentoSalvo.getId());
        registroPagamentoDTO.setUsuarioId(registroPagamentoSalvo.getUsuarioId());
        registroPagamentoDTO.setNumero(pedidoDTO.getPedidoId());
        registroPagamentoDTO.setData(registroPagamentoSalvo.getData());
        registroPagamentoDTO.setChaveNfe(registroPagamentoSalvo.getChaveNfe());
        registroPagamentoDTO.setValorTotal(registroPagamentoSalvo.getValorTotal());
        registroPagamentoDTO.setStatus(registroPagamentoSalvo.getStatus());

        pagamentoPublisher.enviarPagamentoConcluido(registroPagamentoDTO);

        return registroPagamentoSalvo;
    }

    // Metodo que criar o registro de pagamento de uma doação direta pelo ms-pagameanto
    @Transactional
    public RegistroPagamento criarRegistroDoacao(RegistroDoacaoDTO request) {

        RegistroItemDoacaoDTO registroItemDoacaoDTO = request.getDetalhesDoacao();

        int statusPagamento = new Random().nextInt(2);

        // Criando o registro de Pagamento
        RegistroPagamento registroPagamento = new RegistroPagamento();
        registroPagamento.setUsuarioId(request.getUsuarioId());
        registroPagamento.setValorTotal(request.getDetalhesDoacao().getValorDoacao());
        registroPagamento.setNumero(new Random().nextInt(999));
        registroPagamento.setChaveNfe(new Random().nextInt(100000000) + "");
        registroPagamento.setData(LocalDateTime.now());
        registroPagamento.setStatus(statusPagamento);
        RegistroPagamento registroPagamentoSalvo = registroPagamentoRepository.save(registroPagamento);

        // Criando o registro de Item de Pagamento
        RegistroPagamentoItem registroPagamentoItem = new RegistroPagamentoItem();
        registroPagamentoItem.setRegistroPagamentoId(registroPagamentoSalvo.getId());
        registroPagamentoItem.setQuantidade(1);
        registroPagamentoItem.setPrecoUnitario(registroItemDoacaoDTO.getValorDoacao());
        registroPagamentoItem.setSubTotal(registroItemDoacaoDTO.getValorDoacao());
        registroPagamentoItem.setRegistroPagamento(registroPagamentoSalvo);
        registroPagamentoItem.setEstoqueId(125); // Qualquer ID específico para doação no estoque, se fizer sentido
        RegistroPagamentoItem itemSalvo = registroPagamentoItemRepository.save(registroPagamentoItem);

        // Criando o registro de Doacao
        RegistroItemDoacao registroItemDoacao = new RegistroItemDoacao();
        registroItemDoacao.setRegistroPagamentoItem(itemSalvo);
        registroItemDoacao.setValor(registroItemDoacaoDTO.getValorDoacao());
        registroItemDoacao.setData(LocalDateTime.now());
        registroItemDoacao.setDoadorNome(registroItemDoacaoDTO.getDoadorNome());
        registroItemDoacao.setDoadorIdentificacao(registroItemDoacaoDTO.getDoadorIdentificacao());
        registroItemDoacao.setDoadorTipo(registroItemDoacaoDTO.getDoadorTipo());
        registroItemDoacao.setDoadorPaisOrigem(registroItemDoacaoDTO.getDoadorPaisOrigem());
        registroItemDoacao.setFormaTransferencia(registroItemDoacaoDTO.getFormaTransferencia());
        registroItemDoacao.setIdTransacao(registroItemDoacaoDTO.getIdTransacao());
        registroItemDoacaoRepository.save(registroItemDoacao);

        return registroPagamentoSalvo;
    }
}
