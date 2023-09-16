package com.dimdimapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "movimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "clienteFez")
    @JsonIgnore
    private Cliente clienteFez;
    @ManyToOne
    @JoinColumn(name = "clienteRecebe")
    @JsonIgnore
    private Cliente clienteRecebe;


}
