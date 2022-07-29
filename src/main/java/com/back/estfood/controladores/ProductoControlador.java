package com.back.estfood.controladores;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.estfood.modelos.Producto;
import com.back.estfood.servicios.ProductoServicio;
import com.back.estfood.validaciones.RespuestaAccion;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductoControlador {
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
	private RespuestaAccion respuestaAccion;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		
		List<Producto> listaProductos= productoServicio.listar();
		
		if (listaProductos.size() == 0) {
			return respuestaAccion.listaDatosVacia(false, "No existe productos", "tabla vacía");
		}
		
		return respuestaAccion.accionCumplida(true, "productos", listaProductos);
	}
	
	@GetMapping("/producto/{id}")
	public ResponseEntity<?> buscarProductoPorId(@PathVariable Long id) {

		Producto producto = productoServicio.buscarPorId(id);
		
		if (producto == null) {
			return respuestaAccion.datoNulo(false, "No existe ese producto", "id invalido");
		}
		
		return respuestaAccion.accionCumplida(true, "Datos del producto", producto);
	}
	
	@PostMapping("/producto")
	public ResponseEntity<?> guardarProducto(@RequestBody Producto producto) {
		Producto nuevoProdcto = null;
		
		// validamos que no exista el mismo codigo
		Producto prodCodigo = productoServicio.buscarPorCodigo(producto.getCodigoProducto());
		if (prodCodigo != null) {
			return respuestaAccion.datoDuplicado(false, "Hay un producto con ese código", "Código existente");
		}
		// creamos producto
		try {
			nuevoProdcto = productoServicio.guardar(producto);
		} catch (DataAccessException e) {
			return respuestaAccion.errorBD(false, "Error al guardar el producto",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}

		return respuestaAccion.accionCumplida(true, "Producto guardado", nuevoProdcto);
	}
	
	@PutMapping("/producto/{id}")
	public ResponseEntity<?> actualizarProducto(@RequestBody Producto producto, @PathVariable Long id) {
		
		Producto prodActual = productoServicio.buscarPorId(id);
		Producto prodActualizado = null;
		
		// si el prod no existe
		if (prodActual == null) {
			return respuestaAccion.datoNulo(false, "No existe en la Base de Datos", "id inválido");
		}
		// si existe otro producto con el mismo codigo
		Producto prodCodigo = productoServicio.buscarPorCodigo(producto.getCodigoProducto());
		if (prodCodigo != null && prodCodigo.getIdProducto() != id) {
			return respuestaAccion.errorBD(false, "Ya existe un producto con ese código", "Código existente");
		}
		
		try {
			
			prodActual.setCodigoProducto(producto.getCodigoProducto());
			prodActual.setDescripcionProducto(producto.getDescripcionProducto());
			prodActual.setEstadoProducto(producto.getEstadoProducto());
			prodActual.setFechaIngreso(producto.getFechaIngreso());
			prodActual.setIvaProducto(producto.getIvaProducto());
			prodActual.setPrecioVentaProducto(producto.getPrecioVentaProducto());
			prodActual.setStockProducto(producto.getStockProducto());
			prodActual.setMarca(producto.getMarca());
			prodActual.setPresentacion(producto.getPresentacion());
			prodActual.setZona(producto.getZona());
			prodActualizado = productoServicio.guardar(prodActual);
			
		} catch (DataAccessException e) {
			return respuestaAccion.errorBD(false, "Error al actualizar el producto",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		return respuestaAccion.accionCumplida(true, "Producto actualizado", prodActualizado);
	}
	
	@PutMapping("/producto/estado/{id}")
	public ResponseEntity<?> actualizarEstadoProducto(@PathVariable Long id) {
		
		Producto prodActual = productoServicio.buscarPorId(id);
		Producto prodActualizado = null;
		
		// si el prod no existe
		if (prodActual == null) {
			return respuestaAccion.datoNulo(false, "No existe en la Base de Datos", "id inválido");
		}

		try {
			
			prodActual.setEstadoProducto(!prodActual.getEstadoProducto());
			prodActualizado = productoServicio.guardar(prodActual);
			
		} catch (DataAccessException e) {
			return respuestaAccion.errorBD(false, "Error al actualizar el producto",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		return respuestaAccion.accionCumplida(true, "Estado actualizado", prodActualizado);
	}
	
	// para desactivar la tarjeta como inactivo
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<?> borrarProducto(@PathVariable Long id){
		
		try {
			productoServicio.eliminar(id);
		} catch (DataAccessException e) {
			return respuestaAccion.errorBD(false, "Error al borrar el producto",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

		}
		return respuestaAccion.accionCumplida(true, "Producto borrado", "borrada");
	}
	
	
	
}
