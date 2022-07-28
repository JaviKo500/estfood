package com.back.estfood.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.estfood.modelos.Condiciones;

@Repository
public interface ICondicionesRepo extends JpaRepository<Condiciones, Long>{
	
}
