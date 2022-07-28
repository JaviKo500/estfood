package com.back.estfood.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.estfood.modelos.Compra;

@Repository
public interface ICompraRepo extends JpaRepository<Compra, Long>{

}
