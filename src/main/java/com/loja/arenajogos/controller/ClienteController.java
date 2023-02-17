package com.loja.arenajogos.controller;


import com.loja.arenajogos.model.Cliente;
import com.loja.arenajogos.service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/cliente")
@AllArgsConstructor
@NoArgsConstructor
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Cliente> salvaCliente(@Valid @RequestBody Cliente novo){
        Cliente reponse = service.salvaCliente(novo);

        return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> buscaTodos(){
        return ResponseEntity.ok(service.buscaTodos());
    }

    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    public ResponseEntity<Cliente> buscaCliente(@PathVariable(name = "id",required = true) Long id){
        Cliente response = service.buscaCliente(id);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "/{id}")
    public ResponseEntity<?> deletaCliente(@PathVariable(name = "id", required = true) Long id){
        service.deletaCliente(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Cliente> atualizaCliente(@Valid @RequestBody Cliente novo, @PathVariable(name = "id", required = true) Long id) throws NoSuchFieldException {
        Cliente response = service.atualizaCliente(novo,id);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/{id}")
    public ResponseEntity<Cliente> atualizaParcialCliente(@RequestBody Map<String,Object> novo, @PathVariable(name = "id", required = true) Long id) throws IllegalAccessException, NoSuchFieldException {
        Cliente response = service.buscaCliente(id);
        Cliente finalResponse = response;
        novo.forEach((chave,valor)->{
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Cliente.class,chave));
            ReflectionUtils.setField(ReflectionUtils.findField(Cliente.class,chave),finalResponse,valor);
        });

        response = service.atualizaCliente(finalResponse,id);
        return ResponseEntity.ok(response);
    }


}
