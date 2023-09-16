package com.dimdimapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    private String cpf;
    @NotNull
    private Double saldo;
    @OneToMany(mappedBy = "clienteFez")
    private List<Movimentacao> movimentacoesFez;
    @OneToMany(mappedBy = "clienteRecebe")
    private List<Movimentacao> movimentacoesRecebe;

}
