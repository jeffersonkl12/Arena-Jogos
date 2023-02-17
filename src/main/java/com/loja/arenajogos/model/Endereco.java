package com.loja.arenajogos.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 20,nullable = false)
    @NotNull
    @NotBlank
    private String estado;

    @Column(length = 30,nullable = false)
    @NotBlank
    @NotNull
    private String cidade;

    @Column(length = 30,nullable = false)
    @NotNull
    @NotBlank
    private String bairro;

    @Column(length = 40,nullable = false)
    @NotNull
    @NotBlank
    private String rua;

    @Column(length = 4,nullable = false)
    @NotNull
    @NotBlank
    private String numero;

    @Column(length = 15)
    private String complemento;

    @OneToMany(mappedBy = "endereco")
    private List<Cliente> clientes;
}
