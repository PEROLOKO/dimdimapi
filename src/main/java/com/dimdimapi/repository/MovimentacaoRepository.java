package com.dimdimapi.repository;

import com.dimdimapi.model.Cliente;
import com.dimdimapi.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByClienteFez(Cliente clienteFez);

    List<Movimentacao> findByClienteRecebe(Cliente clienteRecebe);

}
