package com.loja.arenajogos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 40,nullable = false)
    @NotBlank
    @NotNull
    private String nome;

    @Column(name = "data_lanca")
    private LocalDate lanca;

    @Column(nullable = false)
    @Positive
    private Integer qtd;

    @Column(nullable = false)
    @Positive
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "produtora_id",nullable = false)
    @NotNull
    private Produtora produtora;

    @ManyToOne
    @JoinColumn(name = "genero_id",nullable = false)
    @NotNull
    private Genero genero;
}
