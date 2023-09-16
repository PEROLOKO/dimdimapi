package com.dimdimapi.controller;

import com.dimdimapi.model.Cliente;
import com.dimdimapi.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/dimdim/api/cliente")
public class ClienteController {

    Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    ClienteRepository repository;

    @GetMapping
    public List<Cliente> index() { return repository.findAll(); }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Cliente cliente) {
        log.info("cadastrando cliente: " + cliente);
        repository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> show(@PathVariable Long id) {
        log.info("buscar cliente " + id);
        var clienteEncontrado = repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado"));
        return ResponseEntity.ok(clienteEncontrado);
    }

    @DeleteMapping("{id}")
    @CrossOrigin
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        var clienteEncontrado = repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel deletar o cliente"));
        repository.delete(clienteEncontrado);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
        var clienteEncontrado = repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado"));
        cliente.setId(id);
        cliente.setMovimentacoesFez(clienteEncontrado.getMovimentacoesFez());
        cliente.setMovimentacoesRecebe(clienteEncontrado.getMovimentacoesRecebe());
        repository.save(cliente);
        return ResponseEntity.ok(cliente);
    }

}
