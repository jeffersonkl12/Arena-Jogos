package com.loja.arenajogos.controller;

import com.loja.arenajogos.model.Endereco;
import com.loja.arenajogos.service.EnderecoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/endereco")
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Endereco> salvaEndereco(@RequestBody Endereco novo){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvaEndereco(novo));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Endereco>> buscaTodos(){
        return ResponseEntity.ok(service.buscaTodos());
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Endereco> buscaEndereco(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.buscaEndereco(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaEndereco(@PathVariable(name = "id") Long id){
        service.deletaEndereco(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Endereco> atualizaEndereco(@RequestBody Endereco novo,@PathVariable(name = "id") Long id) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.ok(service.atualizaEndereco(novo,id));
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.PATCH)
    public ResponseEntity<Endereco> atualizaParcialEndereco(@RequestBody Map<String,Object> campos,@PathVariable(name = "id") Long id) throws InvocationTargetException, IllegalAccessException {
        Endereco response = service.buscaEndereco(id);
        Endereco finalResponse = response;
        campos.forEach((chave,valor)->{
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Endereco.class,chave));
            ReflectionUtils.setField(ReflectionUtils.findField(Endereco.class,chave),finalResponse,valor);
        });

        response = service.atualizaEndereco(finalResponse,id);
        return ResponseEntity.ok(response);
    }
}
