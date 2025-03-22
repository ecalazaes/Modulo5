package com.torreverde.ms_compra.service;

import com.torreverde.ms_compra.dto.PedidoDTO;
import com.torreverde.ms_compra.dto.PedidoItemDTO;
import com.torreverde.ms_compra.model.Pedido;
import com.torreverde.ms_compra.model.PedidoItem;
import com.torreverde.ms_compra.repository.PedidoItemRepository;
import com.torreverde.ms_compra.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoItemRepository pedidoItemRepository;
    private final PedidoPublisher pedidoPublisher;

    public PedidoService(PedidoRepository pedidoRepository, PedidoItemRepository pedidoItemRepository, PedidoPublisher pedidoPublisher) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoItemRepository = pedidoItemRepository;
        this.pedidoPublisher = pedidoPublisher;
    }

    @Transactional
    public Pedido criarPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setUsuarioId(pedidoDTO.getUsuarioId());
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setStatus("PENDENTE");
        pedido.setDataCriacao(LocalDateTime.now());

        pedido = pedidoRepository.save(pedido);

        for (PedidoItemDTO itemDTO : pedidoDTO.getItensPedido()) {
            PedidoItem item = PedidoItem.builder()
                    .pedido(pedido)
                    .nome(itemDTO.getNome())
                    .preco(itemDTO.getPreco())
                    .quantidade(itemDTO.getQuantidade())
                    .doacao(itemDTO.getDoacao())
                    .build();

            pedidoItemRepository.save(item);
        }

        // Enviar evento para RabbitMQ
        PedidoDTO pedidoCriado = PedidoDTO.builder()
                .usuarioId(pedido.getUsuarioId())
                .id(pedido.getId())
                .total(pedido.getTotal())
                .itensPedido(pedidoDTO.getItensPedido())
                .build();

        pedidoPublisher.enviarPedidoCriado(pedidoCriado);

        return pedido;
    }
}
