package com.loja.arenajogos.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class ExceptionManagerHandler extends ResponseEntityExceptionHandler {


   @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorHandlerAPI errorHandlerAPI = new ErrorHandlerAPI();
        errorHandlerAPI.setStatus(status);
        errorHandlerAPI.setMessage("argumentos invalidos, por favor verifique os parametros corretamente.");
        errorHandlerAPI.setDebugMessage("total de campos invalidos: "+ ex.getFieldErrorCount());
        List<ApiSubError> apiSubErrorList = new ArrayList<>();
        for(FieldError error: ex.getFieldErrors()){
            apiSubErrorList.add(new ApiValidationError(error.getObjectName(),error.getDefaultMessage(),
                    error.getField(),error.getRejectedValue()));
        }
        errorHandlerAPI.setErros(apiSubErrorList);
        return this.handleExceptionInternal(ex,errorHandlerAPI,headers,status,request);
        //return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

}
