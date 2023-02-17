package com.loja.arenajogos.repository;

import com.loja.arenajogos.model.Produtora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoraRepository extends JpaRepository<Produtora,Long> {
}
