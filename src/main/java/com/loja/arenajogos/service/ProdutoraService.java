package com.loja.arenajogos.service;

import com.loja.arenajogos.exceptions.EntidadeExistenteException;
import com.loja.arenajogos.exceptions.EntidadeInexistenteExecption;
import com.loja.arenajogos.model.Produtora;
import com.loja.arenajogos.repository.ProdutoraRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoraService {

    @Autowired
    private ProdutoraRepository repository;

    public Produtora salvaProdutora(Produtora novo){
        if(novo.getId() != null && !repository.existsById(novo.getId())){
            return repository.save(novo);
        }

        throw new EntidadeExistenteException("entidade ja existe!");
    }

    public List<Produtora> buscaTodos(){
        return repository.findAll();
    }

    public Produtora buscaProdutora(Long id){
        return repository.findById(id).orElseThrow(()->new EntidadeInexistenteExecption("entidade inexistente!"));
    }

    public void deletaProdutora(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new EntidadeInexistenteExecption("entidade inexistente!");
        }
    }

    public Produtora atualizaProdutora(Produtora novo,Long id){
        if(repository.existsById(id)){
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(novo.getClass(),"id"));
            ReflectionUtils.setField(ReflectionUtils.findField(novo.getClass(),"id"),novo,id);
            return repository.save(novo);
        }

        throw new EntidadeInexistenteExecption("entidade inexistente!");
    }
}
