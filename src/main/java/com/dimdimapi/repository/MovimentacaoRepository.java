package com.dimdimapi.repository;

import com.dimdimapi.model.Cliente;
import com.dimdimapi.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
