package com.senac.torreverde.ms_compra.controllers;

// Controller comentado, pois as movimentações se dão pelo PedidoController
/*
@RestController
@RequestMapping("/pedido-item-doacoes")
public class PedidoItemDoacaoController {

    @Autowired
    private PedidoItemDoacaoService pedidoItemDoacaoService;



    // Endpoint para listar todos os pedidosItensDoacao
    @GetMapping
    public ResponseEntity<List<PedidoItemDoacao>> buscarTodosPedidosItensDoacao() {
        return ResponseEntity.ok(pedidoItemDoacaoService.findAll());
    }

    // Enndpoint para listar um pedidoItemDoacao por id
    @GetMapping("/{id}")
    public ResponseEntity<PedidoItemDoacao> findById(@PathVariable Integer id) {
        return pedidoItemDoacaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para criar um pedidoItemDoacao
    @PostMapping
    public ResponseEntity<PedidoItemDoacao>  criar(@RequestBody PedidoItemDoacao doacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoItemDoacaoService.salvarDoacao(doacao));
    }

    // Endpoint para atualizar um pedidoItemDoacao
    @PutMapping("/{id}")
    public ResponseEntity<PedidoItemDoacao> atualizar(@PathVariable Integer id, @RequestBody PedidoItemDoacao doacao) {
        if (pedidoItemDoacaoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        doacao.setId(id);
        return ResponseEntity.ok(pedidoItemDoacaoService.salvarDoacao(doacao));
    }

    // Endpoint para deletar um pedidoItemDoacao
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (pedidoItemDoacaoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pedidoItemDoacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
*/

