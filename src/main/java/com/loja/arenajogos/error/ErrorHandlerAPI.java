package com.loja.arenajogos.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ErrorHandlerAPI {

    private HttpStatus status;
    private LocalDate data;
    private String message;
    private String debugMessage;
    private List<ApiSubError> erros;

    ErrorHandlerAPI(){
        this.data = LocalDate.now();
    }
    public ErrorHandlerAPI(HttpStatus status){
        this();
        this.status = status;
    }
    public ErrorHandlerAPI(HttpStatus status,Throwable e){
        this();
        this.status = status;
        this.message = "error inesperado";
        this.debugMessage = e.getLocalizedMessage();
    }
    public ErrorHandlerAPI(HttpStatus status, String message, Throwable e){
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = e.getLocalizedMessage();
    }


}
