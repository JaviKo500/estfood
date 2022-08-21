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
import com.back.estfood.modelos.Venta;
import com.back.estfood.servicios.ProductoServicio;
import com.back.estfood.servicios.VentaServicio;
import com.back.estfood.validaciones.RespuestaAccion;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class VentaControlador {
	@Autowired
	private VentaServicio ventaServicio;
	@Autowired 
	private ProductoServicio productoServicio;
	@Autowired
	private RespuestaAccion respuestaAccion;
	
	@GetMapping("venta")
	public ResponseEntity<?> listar(){
		
		List<Venta> listaVentas= ventaServicio.listar();
		
		if (listaVentas.size() == 0) {
			return respuestaAccion.listaDatosVacia(false, "No existe ventas", "tabla vacía");
		}
		
		return respuestaAccion.accionCumplida(true, "ventas", listaVentas);
	}
	
	@GetMapping("venta/{id}")
	public ResponseEntity<?> buscarVentaPorId(@PathVariable Long id) {

		Venta venta = ventaServicio.buscarPorId(id);
		
		if (venta == null) {
			return respuestaAccion.datoNulo(false, "No existe ese venta", "id invalido");
		}
		
		return respuestaAccion.accionCumplida(true, "Datos de la venta", venta);
	}
	
	@GetMapping("venta/buscar/{termino}")
	public ResponseEntity<?> listar(@PathVariable String termino){
		
		List<Venta> listaVentas= ventaServicio.listarVentasPorTermino(termino);
		
		if (listaVentas.size() == 0) {
			return respuestaAccion.listaDatosVacia(false, "No existe ventas", "tabla vacía");
		}
		
		return respuestaAccion.accionCumplida(true, "ventas", listaVentas);
	}
	
	@PostMapping("venta")
	public ResponseEntity<?> guardarVenta(@RequestBody Venta venta) {
		Venta nuevaVenta = null;
		
		// validamos que no exista el mismo codigo
		Venta ventaCodigo = ventaServicio.buscarPorCodigoVenta(venta.getCodigoVenta());
		if (ventaCodigo != null) {
			return respuestaAccion.datoDuplicado(false, "Hay una venta con ese código", "Código inexistente");
		}
		// creamos venta
		try {
			Producto producto = null;
			for (int j = 0; j < venta.getItems().size(); j++) {
				producto = productoServicio.buscarPorId(venta.getItems().get(j).getProducto().getIdProducto());
				int stockAct = producto.getStockProducto();
				int nuevoStock = stockAct - venta.getItems().get(j).getCantidadDetalleProductoVenta();
				producto.setStockProducto(nuevoStock);
				productoServicio.guardar(producto);
			}
			nuevaVenta = ventaServicio.guardar(venta);
		} catch (DataAccessException e) {
			return respuestaAccion.errorBD(false, "Error al guardar la venta",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}

		return respuestaAccion.accionCumplida(true, "Venta guardada", nuevaVenta);
	}
	
	@PutMapping("venta/{id}")
	public ResponseEntity<?> actualizarVenta(@RequestBody Venta venta, @PathVariable Long id) {
		
		Venta ventaActual = ventaServicio.buscarPorId(id);
		Venta ventaActualizado = null;
		
		// si la venta no existe
		if (ventaActual == null) {
			return respuestaAccion.datoNulo(false, "No existe en la Base de Datos", "id inválido");
		}
		// si existe otro producto con el mismo codigo
		Venta ventaCodigo = ventaServicio.buscarPorCodigoVenta(venta.getCodigoVenta());
		if (ventaCodigo != null && ventaCodigo.getIdVenta() != id) {
			return respuestaAccion.datoDuplicado(false, "Ya existe una venta con ese código", "Código existente");
		}
		
		try {
			
			//actualizarStockProductoEditar(venta, ventaActual);
			 
			
			ventaActual.setCodigoVenta(venta.getCodigoVenta());
			ventaActual.setFechaVenta(venta.getFechaVenta());
			ventaActual.setCliente(venta.getCliente());
			ventaActual.setTotalVenta(venta.getTotalVenta());
			ventaActual.setFormaPago(venta.getFormaPago());
			ventaActual.setItems(venta.getItems());
			ventaActualizado = ventaServicio.guardar(ventaActual);
		} catch (DataAccessException e) {
			return respuestaAccion.errorBD(false, "Error al actualizar la venta",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		return respuestaAccion.accionCumplida(true, "Venta actualizada", ventaActualizado);
	}
	
	@PutMapping("/venta/estado/{id}")
	public ResponseEntity<?> actualizarEstadoVenta(@PathVariable Long id) {
		
		Venta ventaActual = ventaServicio.buscarPorId(id);
		Venta ventaActualizado = null;
		
		// si la Venta no existe
		if (ventaActual == null) {
			return respuestaAccion.datoNulo(false, "No existe en la Base de Datos", "id inválido");
		}

		try {
			ventaActual.setEstadoVenta(!ventaActual.getEstadoVenta());
			ventaActualizado = ventaServicio.guardar(ventaActual);
			
		} catch (DataAccessException e) {
			return respuestaAccion.errorBD(false, "Error al actualizar la venta",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		return respuestaAccion.accionCumplida(true, "Estado venta", ventaActualizado);
	}
	
	@DeleteMapping("venta/{id}")
	public ResponseEntity<?> borrarVenta(@PathVariable Long id){
		//Venta venta = ventaServicio.buscarPorId(id);
		try {
			//List<Producto> productos = verificarStockProducto(venta);
			//if (productos.size() > 0) {
				//return respuestaAccion.accionIncumplida(false, "error", productos);
			//}
			//actualizarStockEliminarProducto(venta);
			ventaServicio.eliminar(id);
		} catch (DataAccessException e) {
			return respuestaAccion.errorBD(false, "Error al borrar el venta",
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

		}
		return respuestaAccion.accionCumplida(true, "Venta borrada", "borrada");
	}
	
}
