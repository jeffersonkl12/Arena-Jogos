package com.loja.arenajogos.model;

public enum Status {
    PEDENTE("pedente"),
    CANCELADO("cancelado"),
    APROVADO("aprovado"),
    ENTREGUE("entregue"),
    EM_ROTA("em rota");

    private String estatus;
    Status(String estatus){
        this.estatus = estatus;
    }

}
