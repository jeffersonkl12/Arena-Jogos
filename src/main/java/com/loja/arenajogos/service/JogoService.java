package com.loja.arenajogos.service;

import com.loja.arenajogos.exceptions.EntidadeExistenteException;
import com.loja.arenajogos.exceptions.EntidadeInexistenteExecption;
import com.loja.arenajogos.model.Jogo;
import com.loja.arenajogos.repository.JogoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class JogoService {

    @Autowired
    private JogoRepository repository;

    public Jogo salvaJogo(Jogo novo){
        if(novo.getId() != null && !repository.existsById(novo.getId())){
            return repository.save(novo);
        }
        throw new EntidadeExistenteException("entidade ja existente!");
    }

    public List<Jogo> buscaTodos(){
        return repository.findAll();
    }

    public Jogo buscaJogo(Long id){
        return repository.findById(id).orElseThrow(()->new EntidadeInexistenteExecption("entidade inexistente!"));
    }

    public void deletaJogo(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new EntidadeInexistenteExecption("entidade inexistente!");
        }
    }

    public Jogo atualizaJogo(Jogo novo, Long id){
        if(repository.existsById(id)){
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(novo.getClass(),"id"));
            ReflectionUtils.setField(ReflectionUtils.findField(novo.getClass(),"id"),novo,id);
            return repository.save(novo);
        }else{
            throw new EntidadeInexistenteExecption("entidade inexistente!");
        }
    }

}
