package com.dimdimapi.controller;

import com.dimdimapi.model.Cliente;
import com.dimdimapi.model.Movimentacao;
import com.dimdimapi.repository.ClienteRepository;
import com.dimdimapi.repository.MovimentacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/dimdim/api/movimentacao")
public class MovimentacaoController {

    Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping
    public List<Movimentacao> index() { return movimentacaoRepository.findAll(); }

    @PostMapping("{idFaz}/{idRecebe}/{valor}")
    public ResponseEntity<Object> fazerMovimentacao(@PathVariable Long idFaz, @PathVariable Long idRecebe, @PathVariable Double valor) {
        log.info("fazendo movimentação de "+idFaz+" para "+idRecebe+" de R$"+valor);
        var clienteFaz = clienteRepository.findById(idFaz)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado"));
        var clienteRecebe = clienteRepository.findById(idRecebe)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado"));
        if (clienteFaz.getSaldo() < valor) { throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Saldo insuficiente"); }
        Movimentacao movimentacao = Movimentacao.builder()
                .valor(valor)
                .clienteFez(clienteFaz)
                .clienteRecebe(clienteRecebe)
                .build();
        movimentacaoRepository.save(movimentacao);
        clienteFaz.setSaldo(clienteFaz.getSaldo()-valor);
        clienteRepository.save(clienteFaz);
        log.info("novo saldo para "+idFaz+": R$"+clienteFaz.getSaldo());
        clienteRecebe.setSaldo(clienteRecebe.getSaldo()+valor);
        clienteRepository.save(clienteRecebe);
        log.info("novo saldo para "+idRecebe+": R$"+clienteRecebe.getSaldo());
        return ResponseEntity.ok(movimentacao);
    }

    @GetMapping("fez/{idCliente}")
    public List<Movimentacao> listarClienteFez(@PathVariable Long idCliente) {
        var cliente = clienteRepository.findById(idCliente)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado"));
        return movimentacaoRepository.findByClienteFez(cliente);
    }

    @GetMapping("recebeu/{idCliente}")
    public List<Movimentacao> listarClienteRecebeu(@PathVariable Long idCliente) {
        var cliente = clienteRepository.findById(idCliente)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado"));
        return movimentacaoRepository.findByClienteRecebe(cliente);
    }

}
