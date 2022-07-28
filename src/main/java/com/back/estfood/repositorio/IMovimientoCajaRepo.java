package com.back.estfood.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.estfood.modelos.MovimientoCaja;

@Repository
public interface IMovimientoCajaRepo extends JpaRepository<MovimientoCaja, Long>{

}
