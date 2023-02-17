package com.loja.arenajogos.service;

import com.loja.arenajogos.exceptions.EntidadeInexistenteExecption;
import com.loja.arenajogos.model.Cliente;
import com.loja.arenajogos.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private TelefoneService telefoneService;

    public Cliente salvaCliente(Cliente novo){
        try{
            if(novo.getEndereco() != null && novo.getEndereco().getId() != null){
              novo.setEndereco(enderecoService.salvaEndereco(novo.getEndereco()));
            }
            if(novo.getTelefone() != null && novo.getTelefone().getId() != null){
                novo.setTelefone(telefoneService.salvaTelefone(novo.getTelefone()));
            }
            return repository.save(novo);
        }catch (IllegalArgumentException e){
            throw new EntidadeInexistenteExecption(e.getMessage());
        }
    }

    public List<Cliente> buscaTodos(){
        return repository.findAll();
    }

    public Cliente buscaCliente(Long id){
      return repository.findById(id).orElse(null);
    }

    public void deletaCliente(Long id){
       if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new EntidadeInexistenteExecption("entidade nao existe!");
        }
    }

    public Cliente atualizaCliente(Cliente novo,Long id) throws NoSuchFieldException {
        if(repository.existsById(id)){
            ReflectionUtils.makeAccessible(ReflectionUtils.findField(Cliente.class,"id"));
            ReflectionUtils.setField(ReflectionUtils.findField(Cliente.class,"id"),novo,id);
            return repository.save(novo);
        }
        throw new EntidadeInexistenteExecption("Entidade nao existe!");
    }

}
