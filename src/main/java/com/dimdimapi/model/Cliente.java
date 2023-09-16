package com.dimdimapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private Double saldo;
    @OneToMany(mappedBy = "clienteFez")
    private List<Movimentacao> movimentacoesFez;
    @OneToMany(mappedBy = "clienteRecebe")
    private List<Movimentacao> movimentacoesRecebe;

}
