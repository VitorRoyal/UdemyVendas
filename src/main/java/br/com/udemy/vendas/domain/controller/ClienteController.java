package br.com.udemy.vendas.domain.controller;

import br.com.udemy.vendas.domain.entity.Cliente;
import br.com.udemy.vendas.domain.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@ApiResponse(description = "API de Clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastrar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar cliente")
    })
    public ResponseEntity<Cliente> cadastrar(@Valid @RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @PutMapping("/atualizar/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Integer id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente clienteAtualizado = clienteOptional.get();
            clienteAtualizado.setNome(cliente.getNome());
            clienteRepository.save(clienteAtualizado);
            return ResponseEntity.ok(clienteAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletar/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity deletar(@PathVariable Integer id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes listados")
    })
    public ResponseEntity listar() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }


}
