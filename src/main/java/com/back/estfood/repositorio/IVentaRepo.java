package com.back.estfood.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.estfood.modelos.Venta;

@Repository
public interface IVentaRepo extends JpaRepository<Venta, Long>{

}
