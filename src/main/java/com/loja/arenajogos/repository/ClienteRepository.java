package com.loja.arenajogos.repository;

import com.loja.arenajogos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    public Boolean existsBycpf(String cpf);
}
