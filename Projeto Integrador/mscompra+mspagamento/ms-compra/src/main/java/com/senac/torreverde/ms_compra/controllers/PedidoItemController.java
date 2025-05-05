package com.senac.torreverde.ms_compra.controllers;

// Controller comentado, pois as movimentações se dão pelo PedidoController
/*
@RestController
@RequestMapping("/pedido-itens")
public class PedidoItemController {

    @Autowired
    private PedidoItemService pedidoItemService;

    // Endpoint para listar todos os pedidosItens
    @GetMapping
    public ResponseEntity<List<PedidoItem>> buscarTodosPedidosItens() {
        return ResponseEntity.ok(pedidoItemService.findAll());
    }

    // Endpoint para listar um pedidoItens por id
    @GetMapping("/{id}")
    public ResponseEntity<PedidoItem> buscarPedidoItemPorId(@PathVariable Integer id) {
        return pedidoItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para criar um pedidoItem
    @PostMapping
    public ResponseEntity<PedidoItem> criarPedidoItem(@RequestBody PedidoItem pedidoItem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoItemService.save(pedidoItem));
    }

    // Endpoint para atualizar um pedidoItem
    @PutMapping("/{id}")
    public ResponseEntity<PedidoItem> atualizarPedidoItem(@PathVariable Integer id, @RequestBody PedidoItem pedidoItem) {
       if (pedidoItemService.findById(id).isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       pedidoItem.setId(id);
       return ResponseEntity.ok(pedidoItemService.save(pedidoItem));
    }

    // Endpoint para deletar um pedidoItem
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (pedidoItemService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pedidoItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
*/
