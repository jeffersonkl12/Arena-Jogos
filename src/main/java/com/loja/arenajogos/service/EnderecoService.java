package com.loja.arenajogos.service;

import com.loja.arenajogos.exceptions.EntidadeExistenteException;
import com.loja.arenajogos.exceptions.EntidadeInexistenteExecption;
import com.loja.arenajogos.model.Endereco;
import com.loja.arenajogos.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoService {

    private EnderecoRepository repository;

    public Endereco salvaEndereco(Endereco novo){

            if(repository.existsByCidadeAndBairroAndRuaAndNumero(
                    novo.getCidade(),
                    novo.getBairro(),
                    novo.getRua(),
                    novo.getNumero())
            ){
                throw new EntidadeExistenteException("Entidade já existe!");
            }
            return repository.save(novo);
    }

    public List<Endereco> buscaTodos(){
        return repository.findAll();
    }

    public Endereco buscaEndereco(Long id){
        return repository.findById(id).orElse(null);
    }

    public void deletaEndereco(Long id){

        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new EntidadeInexistenteExecption("entidade nao existe!");
        }

    }

    public Endereco atualizaEndereco(Endereco novo, Long id) throws InvocationTargetException, IllegalAccessException {

        if(repository.existsById(id)){
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Endereco.class,"id"));
            ReflectionUtils.setField(ReflectionUtils.findField(Endereco.class,"id"),novo,id);
            return repository.save(novo);
        }
        throw new EntidadeInexistenteExecption("entidade nao existe!");
    }
}
