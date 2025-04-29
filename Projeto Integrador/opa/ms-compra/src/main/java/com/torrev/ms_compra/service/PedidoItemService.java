package com.torrev.ms_compra.service;

import com.torrev.ms_compra.model.PedidoItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PedidoItemService {

    public boolean existeDoacao(PedidoItem pedidoItem) {
        return pedidoItem.getPedidoItemDoacao() != null &&
                pedidoItem.getPedidoItemDoacao().getPedidoItemDoacaoValor() != null &&
                pedidoItem.getPedidoItemDoacao().getPedidoItemDoacaoValor() > 0;
    }
}
