package com.loja.arenajogos.service;

import com.loja.arenajogos.exceptions.EntidadeInexistenteExecption;
import com.loja.arenajogos.model.Telefone;
import com.loja.arenajogos.repository.TelefoneRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneService {

    @Autowired
    private TelefoneRepository repository;

    public Telefone salvaTelefone(Telefone novo){
        return repository.save(novo);
    }

    public List<Telefone> buscaTodosTelefone(){
        return repository.findAll();
    }

    public Telefone buscaTelefone(Long id){
        return repository.findById(id).orElseThrow(()->new EntidadeInexistenteExecption("entidade inesistente!"));
    }

    public void deletaTelefone(Long id){

        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new EntidadeInexistenteExecption("entidade nao existe");
        }

    }

    public Telefone atualizaTelefone(Telefone telefone, Long id){
        if(repository.existsById(id)){
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(telefone.getClass(),"id"));
            ReflectionUtils.setField(ReflectionUtils.findField(telefone.getClass(),"id"),telefone,id);

            return repository.save(telefone);

        }

        throw new EntidadeInexistenteExecption("entidade inexistente!");
    }


}
