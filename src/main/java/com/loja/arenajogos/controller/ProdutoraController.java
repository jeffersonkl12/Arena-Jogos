package com.loja.arenajogos.controller;

import com.loja.arenajogos.model.Produtora;
import com.loja.arenajogos.service.ProdutoraService;
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
@RequestMapping(path = "/produtora")
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoraController {

    @Autowired
    private ProdutoraService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Produtora>> pegaTodos(){
        return ResponseEntity.ok(service.buscaTodos());
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Produtora> pegaUm(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.buscaProdutora(id));
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Produtora> salvaUm(Produtora nova){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvaProdutora(nova));
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Produtora> deletaUm(@PathVariable(name = "id") Long id){
        service.deletaProdutora(id);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Produtora> atualizaProdutora(@RequestBody Produtora nova,@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.atualizaProdutora(nova,id));
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.PATCH)
    public ResponseEntity<Produtora> atualizaParcialProdutora(@RequestBody Map<String,Object> nova, @PathVariable(name = "id") Long id){
        Produtora produtora = service.buscaProdutora(id);
        nova.forEach((chave,valor)->{
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Produtora.class,chave));
            ReflectionUtils.setField(ReflectionUtils.findField(Produtora.class,chave),produtora,valor);
        });
        return ResponseEntity.ok(service.atualizaProdutora(produtora,id));
    }
}
