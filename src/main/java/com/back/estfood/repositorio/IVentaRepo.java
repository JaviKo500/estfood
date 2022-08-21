package com.back.estfood.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.back.estfood.modelos.Venta;

@Repository
public interface IVentaRepo extends JpaRepository<Venta, Long>{
	
	public Venta findByCodigoVenta(String codigoVenta);
	
	@Query (value = "select v from Venta v where lower(v.codigoVenta)   like %?1%")
	public List<Venta> findAllVentasByTermino(String termino);
}
