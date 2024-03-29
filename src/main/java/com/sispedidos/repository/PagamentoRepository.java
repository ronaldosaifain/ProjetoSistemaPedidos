package com.sispedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sispedidos.domain.Pagamento;

@Repository
public interface  PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
