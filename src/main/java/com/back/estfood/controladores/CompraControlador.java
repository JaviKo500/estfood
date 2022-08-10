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

import com.back.estfood.modelos.Compra;
import com.back.estfood.servicios.CompraServicio;
import com.back.estfood.validaciones.RespuestaAccion;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CompraControlador {
	
	@Autowired
	private CompraServicio compraServicio;
	@Autowired
	private RespuestaAccion respuestaAccion;
	
	@GetMapping("compra")
	public ResponseEntity<?> listar(){
		
		List<Compra> listaCompras= compraServicio.listar();
		
		if (listaCompras.size() == 0) {
			return respuestaAccion.listaDatosVacia(false, "No existe compras", "tabla vacía");
		}
		
		return respuestaAccion.accionCumplida(true, "compras", listaCompras);
	}
	
	@GetMapping("compra/{id}")
	public ResponseEntity<?> buscarCompraPorId(@PathVariable Long id) {

		Compra compra = compraServicio.buscarPorId(id);
		
		if (compra == null) {
			return respuestaAccion.datoNulo(false, "No existe ese compra", "id invalido");
		}
		
		return respuestaAccion.accionCumplida(true, "Datos de la compra", compra);
	}
	
	@GetMapping("compra/buscar/{termino}")
	public ResponseEntity<?> listar(@PathVariable String termino){
		
		List<Compra> listaCompras= compraServicio.listarComprasPorTermino(termino);
		
		if (listaCompras.size() == 0) {
			return respuestaAccion.listaDatosVacia(false, "No existe compras", "tabla vacía");
		}
		
		return respuestaAccion.accionCumplida(true, "compras", listaCompras);
	}
	
	@PostMapping("compra")
	public ResponseEntity<?> guardarCompra(@RequestBody Compra compra) {
		Compra nuevaCompra = null;
		
		// validamos que no exista el mismo codigo
		Compra compraCodigo = compraServicio.buscarPorCodigoCompra(compra.getCodigoCompra());
		if (compraCodigo != null) {
			return respuestaAccion.datoDuplicado(false, "Hay una compra con ese código", "Código existente");
		}
		// creamos producto
		try {
			nuevaCompra = compraServicio.guardar(compra);
		} catch (DataAccessException e) {
			return respuestaAccion.errorBD(false, "Error al guardar la compra",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}

		return respuestaAccion.accionCumplida(true, "Compra guardada", nuevaCompra);
	}
	
	@PutMapping("compra/{id}")
	public ResponseEntity<?> actualizarCompra(@RequestBody Compra compra, @PathVariable Long id) {
		
		Compra compraActual = compraServicio.buscarPorId(id);
		Compra compraActualizado = null;
		
		// si el prov no existe
		if (compraActual == null) {
			return respuestaAccion.datoNulo(false, "No existe en la Base de Datos", "id inválido");
		}
		// si existe otro producto con el mismo codigo
		Compra compraCodigo = compraServicio.buscarPorCodigoCompra(compra.getCodigoCompra());
		if (compraCodigo != null && compraCodigo.getIdCompra() != id) {
			return respuestaAccion.datoDuplicado(false, "Ya existe una compra con ese código", "Código existente");
		}
		
		try {
			
			compraActual.setCodigoCompra(compra.getCodigoCompra());
			compraActual.setFechaCompra(compra.getFechaCompra());
			compraActual.setFormaPago(compra.getFormaPago());
			compraActual.setTotalCompra(compra.getTotalCompra());
			compraActual.setItems(compra.getItems());
			compraActualizado = compraServicio.guardar(compraActual);
			
		} catch (DataAccessException e) {
			return respuestaAccion.errorBD(false, "Error al actualizar la compra",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		return respuestaAccion.accionCumplida(true, "Compra actualizada", compraActualizado);
	}
	
	@PutMapping("/compra/estado/{id}")
	public ResponseEntity<?> actualizarEstadoCompra(@PathVariable Long id) {
		
		Compra compraActual = compraServicio.buscarPorId(id);
		Compra compraActualizado = null;
		
		// si la compra no existe
		if (compraActual == null) {
			return respuestaAccion.datoNulo(false, "No existe en la Base de Datos", "id inválido");
		}

		try {
			compraActual.setEstadoCompra(!compraActual.getEstadoCompra());
			compraActualizado = compraServicio.guardar(compraActual);
			
		} catch (DataAccessException e) {
			return respuestaAccion.errorBD(false, "Error al actualizar la compra",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		return respuestaAccion.accionCumplida(true, "Estado compra", compraActualizado);
	}
	
	@DeleteMapping("compra/{id}")
	public ResponseEntity<?> borrarCompra(@PathVariable Long id){
		try {
			compraServicio.eliminar(id);
		} catch (DataAccessException e) {
			return respuestaAccion.errorBD(false, "Error al borrar el compra",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

		}
		return respuestaAccion.accionCumplida(true, "Compra borrada", "borrada");
	}
}