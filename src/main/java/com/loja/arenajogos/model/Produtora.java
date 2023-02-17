package com.loja.arenajogos.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produtora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 30,nullable = false)
    @NotNull
    @NotBlank
    private String nome;

    private LocalDate aberto;

    @OneToMany(mappedBy = "produtora",fetch = FetchType.LAZY)
    private List<Jogo> jogos;
}
