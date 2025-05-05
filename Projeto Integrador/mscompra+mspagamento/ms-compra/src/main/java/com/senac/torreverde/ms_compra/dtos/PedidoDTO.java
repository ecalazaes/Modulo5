package com.senac.torreverde.ms_compra.dtos;

import java.io.Serializable;
import java.util.List;

public class PedidoDTO implements Serializable {

    private Integer pedidoId;
    private Integer usuarioId;
    private Double pedidoValorTotal;
    private List<PedidoItemDTO> itens;

    public PedidoDTO() {
    }

    public PedidoDTO(Integer pedidoId, Integer usuarioId, Double pedidoValorTotal, List<PedidoItemDTO> itens) {
        this.pedidoId = pedidoId;
        this.usuarioId = usuarioId;
        this.pedidoValorTotal = pedidoValorTotal;
        this.itens = itens;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Double getPedidoValorTotal() {
        return pedidoValorTotal;
    }

    public void setPedidoValorTotal(Double pedidoValorTotal) {
        this.pedidoValorTotal = pedidoValorTotal;
    }

    public List<PedidoItemDTO> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItemDTO> itens) {
        this.itens = itens;
    }
}
