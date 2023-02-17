package com.loja.arenajogos.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ApiValidationError extends ApiSubError{

    private String objeto;
    private String message;
    private String campo;
    private Object valor;

    public ApiValidationError(String objeto, String message){
        this.objeto = objeto;
        this.message = message;
    }
}
