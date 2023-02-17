package com.loja.arenajogos.controller;

import com.loja.arenajogos.model.Telefone;
import com.loja.arenajogos.service.TelefoneService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/telefone")
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneController {

    @Autowired
    private TelefoneService service;

    @RequestMapping
    public ResponseEntity<List<Telefone>> pegaTodos(){
        return ResponseEntity.ok(service.buscaTodosTelefone());
    }
    @RequestMapping(path = "/{id}")
    public ResponseEntity<Telefone> pegaUm(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.buscaTelefone(id));
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Telefone> salvaTelefone(@RequestBody Telefone novo){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvaTelefone(novo));
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaTelefone(@PathVariable(name = "id") Long id){
        service.deletaTelefone(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Telefone> atualizaTelefone(@RequestBody Telefone novo,@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.atualizaTelefone(novo,id));
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.PATCH)
    public ResponseEntity<Telefone> atualizaParcialTelefone(@RequestBody Map<String,Object> novo, @PathVariable(name = "id") Long id){
        Telefone atual = service.buscaTelefone(id);
        novo.forEach((chave,valor)->{
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Telefone.class,chave));
            ReflectionUtils.setField(ReflectionUtils.findField(Telefone.class,chave),atual,valor);
        });
        return ResponseEntity.ok(service.atualizaTelefone(atual,id));
    }
}
