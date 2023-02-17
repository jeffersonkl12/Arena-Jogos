package com.loja.arenajogos.service;

import com.loja.arenajogos.exceptions.EntidadeExistenteException;
import com.loja.arenajogos.exceptions.EntidadeInexistenteExecption;
import com.loja.arenajogos.model.PedidoItem;
import com.loja.arenajogos.model.PedidoItemId;
import com.loja.arenajogos.repository.PedidoItemRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PedidoItemService {

    @Autowired
    private PedidoItemRepository repository;

    public PedidoItem salvaPedidoItem(PedidoItem novo){
        if(novo.getId() != null && !repository.existsById(novo.getId())){
            return repository.save(novo);
        }
        throw new EntidadeExistenteException("entidade existente!");
    }

    public List<PedidoItem> buscaTodos(){
        return repository.findAll();
    }

    public PedidoItem buscaPedidoItem(PedidoItemId id){
        return repository.findById(id).orElseThrow(()->new EntidadeInexistenteExecption("entidade inexsitente"));
    }

    public void delePedidoItem(PedidoItemId id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new EntidadeInexistenteExecption("entidade inexistente!");
        }
    }

    public PedidoItem atualizaPedidoItem(PedidoItem novo, PedidoItemId id){
        if(repository.existsById(id)){
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(novo.getClass(),"id"));
            ReflectionUtils.setField(ReflectionUtils.findField(novo.getClass(),"id"),novo,id);
            return repository.save(novo);
        }

        throw new EntidadeInexistenteExecption("entidade inexistente!");
    }

}
