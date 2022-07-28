package com.back.estfood.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.estfood.modelos.Ciudad;

@Repository
public interface ICiudadRepo extends JpaRepository<Ciudad, Long> {

}
