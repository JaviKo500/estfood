package com.back.estfood.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.estfood.modelos.Proveedor;

@Repository
public interface IProveedorRepo extends JpaRepository<Proveedor, Long>{

}
