package com.loja.arenajogos.controller;

import com.loja.arenajogos.model.FormaPagamento;
import com.loja.arenajogos.service.FormaPagamentoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/formaPagamento")
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FormaPagamento> salvaFormaPagamento(@RequestBody FormaPagamento novo){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvaFormaPagamento(novo));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FormaPagamento>> buscaTodos(){
        return ResponseEntity.ok(service.buscaTodos());
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<FormaPagamento> buscaFormaPagamento(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.buscaFormaPagamento(id));
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaFormaPagamento(@PathVariable(name = "id") Long id){
        service.deletaFormaPagamento(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FormaPagamento> atualizaFormaPagamento(@RequestBody FormaPagamento novo,@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.atualizaFormaPagamento(novo,id));
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.PATCH)
    public ResponseEntity<FormaPagamento> atualizaParcialFormaPagamento(@RequestBody Map<String,Object> campos,@PathVariable(name = "id") Long id){
        FormaPagamento response = service.buscaFormaPagamento(id);
        FormaPagamento finalResponse = response;

        campos.forEach((chave,valor)->{
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(FormaPagamento.class,chave));
            ReflectionUtils.setField(ReflectionUtils.findField(FormaPagamento.class,chave),finalResponse,valor);
        });

        response = service.atualizaFormaPagamento(finalResponse,id);
        return ResponseEntity.ok(response);
    }

}
