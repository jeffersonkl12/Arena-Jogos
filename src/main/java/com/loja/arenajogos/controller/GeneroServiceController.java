package com.loja.arenajogos.controller;

import com.loja.arenajogos.model.Genero;
import com.loja.arenajogos.service.GeneroService;
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
@RequestMapping(path = "/generoService")
@AllArgsConstructor
@NoArgsConstructor
public class GeneroServiceController {

    @Autowired
    private GeneroService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Genero> salvaGenero(@RequestBody Genero novo){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvaGenero(novo));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Genero>> buscaTodos(){
        return ResponseEntity.ok(service.buscaTodos());
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Genero> buscaGenero(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.buscaGenero(id));
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaGenero(@PathVariable(name = "id") Long id){
        service.deletaGenero(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Genero> atualizaGenero(@RequestBody Genero novo,@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.atualizaGenero(novo,id));
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.PATCH)
    public ResponseEntity<Genero> atualizaParcialGenero(@RequestBody Map<String,Object> campos,@PathVariable(name = "id") Long id){
        Genero response = service.buscaGenero(id);
        Genero finalResponse = response;
        campos.forEach((chave,valor)->{
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Genero.class,chave));
            ReflectionUtils.setField(ReflectionUtils.findField(Genero.class,chave),finalResponse,valor);
        });
        response = service.atualizaGenero(finalResponse,id);
        return ResponseEntity.ok(response);
    }

}
