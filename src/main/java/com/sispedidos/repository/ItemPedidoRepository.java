package com.sispedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sispedidos.domain.ItemPedido;

@Repository
public interface  ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
