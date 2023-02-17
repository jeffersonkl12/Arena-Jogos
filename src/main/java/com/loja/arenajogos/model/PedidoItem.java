package com.loja.arenajogos.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class PedidoItem {

    @EqualsAndHashCode.Include
    @EmbeddedId
    private PedidoItemId id;

    @Column(nullable = false)
    @NotNull
    @Positive
    private Integer qtd;


}
