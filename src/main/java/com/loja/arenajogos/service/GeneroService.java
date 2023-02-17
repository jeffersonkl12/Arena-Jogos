package com.loja.arenajogos.service;

import com.loja.arenajogos.exceptions.EntidadeExistenteException;
import com.loja.arenajogos.exceptions.EntidadeInexistenteExecption;
import com.loja.arenajogos.model.Genero;
import com.loja.arenajogos.repository.GeneroRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class GeneroService {

    @Autowired
    private GeneroRepository repository;

    public Genero salvaGenero(Genero novo){
        if(novo.getId() != null && !repository.existsById(novo.getId())){
            return repository.save(novo);
        }

        throw new EntidadeExistenteException("entidade existente!");
    }

    public List<Genero> buscaTodos(){
        return repository.findAll();
    }

    public Genero buscaGenero(Long id){
        return repository.findById(id).orElseThrow(()->new EntidadeInexistenteExecption("entidade inexistente!"));
    }

    public void deletaGenero(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new EntidadeInexistenteExecption("entidade inexistente!");
        }
    }

    public Genero atualizaGenero(Genero novo, Long id){
        if(repository.existsById(id)){
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(novo.getClass(),"id"));
            ReflectionUtils.setField(ReflectionUtils.findField(novo.getClass(),"id"),novo,id);
            return repository.save(novo);
        }else{
            throw new EntidadeInexistenteExecption("entidade inexistente!");
        }
    }

}
