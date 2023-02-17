package com.loja.arenajogos.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 2,nullable = false)
    @NotNull
    @NotBlank
    private String dd;

    @Column(length = 11, nullable = false)
    @NotNull
    @NotBlank
    private String numero;

    @OneToMany(mappedBy = "telefone")
    private List<Cliente> clientes;
}
