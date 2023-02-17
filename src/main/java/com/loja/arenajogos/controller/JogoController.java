package com.loja.arenajogos.controller;

import com.loja.arenajogos.model.Jogo;
import com.loja.arenajogos.service.JogoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/jogo")
@AllArgsConstructor
@NoArgsConstructor
public class JogoController {

    @Autowired
    private JogoService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Jogo> salvaJogo(@Valid @RequestBody Jogo novo){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvaJogo(novo));
    }

    @RequestMapping
    public ResponseEntity<List<Jogo>> buscaTodos(){
        return ResponseEntity.ok().body(service.buscaTodos());
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<Jogo> buscaJogo(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.buscaJogo(id));
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaJogo(@PathVariable(name = "id") Long id){
        service.deletaJogo(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Jogo> atualizaJogo(@Valid @RequestBody Jogo novo,@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(service.atualizaJogo(novo,id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Jogo> atualizaParcialJogo(@RequestBody Map<String,Object> campos, @PathVariable(name = "id") Long id){
        Jogo response = service.buscaJogo(id);
        Jogo finalResponse = response;

        campos.forEach((chave,valor)->{
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Jogo.class,chave));
            ReflectionUtils.setField(ReflectionUtils.findField(Jogo.class,chave),finalResponse,valor);
        });

        response = service.atualizaJogo(finalResponse,id);
        return ResponseEntity.ok(response);
    }

}
