package com.loja.arenajogos.repository;

import com.loja.arenajogos.model.PedidoItem;
import com.loja.arenajogos.model.PedidoItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, PedidoItemId> {
}
