package com.senac.torreverde.ms_compra.services;

import com.senac.torreverde.ms_compra.entities.Pedido;
import com.senac.torreverde.ms_compra.entities.PedidoItem;
import com.senac.torreverde.ms_compra.repositories.PedidoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoItemService {

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Autowired
    private PedidoItemDoacaoService pedidoItemDoacaoService;

    // Metodo para buscar todos os pedidosItens
    public List<PedidoItem> findAll() {
        return pedidoItemRepository.findAll();
    }

    // Metodo para buscar um pedidoItem por id
    public Optional<PedidoItem> findById(Integer id) {
        return pedidoItemRepository.findById(id);
    }

    // Metodo para salvar um pedidoItem
    public PedidoItem save(PedidoItem pedidoItem) {
        // Primeiro, salva o PedidoItem para garantir que ele tenha um id gerado
        PedidoItem itemSalvo = pedidoItemRepository.save(pedidoItem);

        // Verifica se há uma doação para associar ao PedidoItem
        if (itemSalvo.getDoacao() != null) {
            // Atribui o PedidoItem ao PedidoItemDoacao
            itemSalvo.getDoacao().setPedidoItem(itemSalvo);

            // Agora salva a doação
            pedidoItemDoacaoService.salvarDoacao(itemSalvo.getDoacao());
        }

        return itemSalvo;
    }

    // Metodo para deletar um pedidoItem
    public void delete(Integer id) {
        pedidoItemRepository.deleteById(id);
    }

    // Metodo para vincular um pedidoItem a um pedido
    public void vincularPedidoItemAoPedido(PedidoItem item, Pedido pedido){
        item.setPedido(pedido); // Garante o vínculo
        item.setSubTotal(item.getPrecoUnitario() * item.getQuantidade());
        item.setStatus(1);

        // Se houver doação, garante a associação com o PedidoItem
        if (item.getDoacao() != null) {
            item.getDoacao().setPedidoItem(item); // Associa o PedidoItem à doação
        }
    }
}
