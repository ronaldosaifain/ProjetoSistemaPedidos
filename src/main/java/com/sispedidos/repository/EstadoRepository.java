package com.sispedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sispedidos.domain.Estado;

@Repository
public interface  EstadoRepository extends JpaRepository<Estado, Integer> {

}
