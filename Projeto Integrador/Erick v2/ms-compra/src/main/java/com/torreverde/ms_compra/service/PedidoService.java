package com.torreverde.ms_compra.service;

import com.torreverde.ms_compra.dto.PedidoDTO;
import com.torreverde.ms_compra.model.Pedido;
import com.torreverde.ms_compra.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoPublisher pedidoPublisher;

    public PedidoService(PedidoRepository pedidoRepository, PedidoPublisher pedidoPublisher) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoPublisher = pedidoPublisher;
    }

    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus("PENDENTE");
        pedido.setDataCriacao(LocalDateTime.now());
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        // Criar DTO para enviar ao RabbitMQ
        PedidoDTO pedidoDTO = PedidoDTO.builder()
                .id(pedidoSalvo.getId())
                .usuarioId(pedidoSalvo.getUsuarioId())
                .total(pedidoSalvo.getTotal())
                .status(pedidoSalvo.getStatus())
                .dataCriacao(pedidoSalvo.getDataCriacao())
                .build();

        // Publica evento no RabbitMQ
        pedidoPublisher.enviarPedidoCriado(pedidoDTO);

        return pedidoSalvo;
    }
}
