package br.com.udemy.vendas.domain.controller;

import br.com.udemy.vendas.domain.entity.Produto;
import br.com.udemy.vendas.domain.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id) {
        return produtoRepository.findById(id)
                .map(produto -> ResponseEntity.ok(produto))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/listar")
    public ResponseEntity listar(){
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Produto> cadastrar(@Valid @RequestBody Produto produto) {
        return ResponseEntity.ok(produtoRepository.save(produto));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Produto> atualizar(@Valid @PathVariable Integer id, @RequestBody Produto produto) {
        return produtoRepository.findById(id)
                .map(produtoExistente -> {
                    produto.setId(produtoExistente.getId());
                    produtoRepository.save(produto);
                    return ResponseEntity.ok(produto);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produtoRepository.delete(produto);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
