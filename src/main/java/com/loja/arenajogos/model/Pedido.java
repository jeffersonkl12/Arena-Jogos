package com.loja.arenajogos.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Cliente comprador;

    @Column(name = "data_compra", nullable = false)
    @NotNull
    private LocalDate dataCompra;

    @Column(name = "data_entrega", nullable = false)
    @NotNull
    private LocalDate dataEntrega;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Endereco destino;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Telefone contato;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Status estatus;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id",nullable = false)
    @NotNull
    private FormaPagamento formaPagamento;

}

