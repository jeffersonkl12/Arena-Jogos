package com.loja.arenajogos.repository;

import com.loja.arenajogos.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoRepository extends JpaRepository<Jogo,Long> {
}
