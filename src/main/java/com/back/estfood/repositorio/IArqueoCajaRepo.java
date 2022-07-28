package com.back.estfood.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.estfood.modelos.ArqueoCaja;

@Repository
public interface IArqueoCajaRepo extends JpaRepository<ArqueoCaja, Long>{

}
