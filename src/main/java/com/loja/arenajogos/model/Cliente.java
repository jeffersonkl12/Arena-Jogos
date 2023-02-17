package com.loja.arenajogos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @JsonIgnore
    private Long id;

    @Column(length = 50,nullable = false)
    @Size(max = 50)
    @NotBlank
    @NotNull
    private String nome;

    @Column(nullable = false)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate idade;

    @Enumerated(value = EnumType.STRING)
    private SEXO sexo;

    @Column(length = 11,nullable = false,unique = true)
    @NotNull
    @NotBlank
    @CPF
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;


    @ManyToOne
    @JoinColumn(name = "telefone_id")
    private Telefone telefone;
    public enum SEXO{
        MASCULINO("MASCULINO"),
        FEMININO("FEMININO");

        private String genero;
        SEXO(String genero){
            this.genero = genero;
        }
    }
}
