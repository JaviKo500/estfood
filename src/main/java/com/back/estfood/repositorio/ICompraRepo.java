package com.back.estfood.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.back.estfood.modelos.Compra;

@Repository
public interface ICompraRepo extends JpaRepository<Compra, Long>{
	
	public Compra findByCodigoCompra(String codigoCompra);
	
	@Query (value = "select c from Compra c where lower(c.codigoCompra)   like %?1%")
	public List<Compra> findAllComprasByTermino(String termino);
}
