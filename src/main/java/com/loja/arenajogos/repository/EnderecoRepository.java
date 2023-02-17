package com.loja.arenajogos.repository;

import com.loja.arenajogos.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {
    public Boolean existsByCidadeAndBairroAndRuaAndNumero(String cidade, String bairro, String rua, String numero);
}
