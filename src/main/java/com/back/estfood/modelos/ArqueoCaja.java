package com.back.estfood.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class ArqueoCaja implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idArCaja;
	
	private Double montoInicialArCaja;
	private Double montoFinalArCaja;
	private Double ingresoArCaja;
	private Double efectivoFinalArCaja;
	private Double diferenciaArCaja;
	private String descripcionArCaja;
	private String descripcionCierreArCaja;
	
	@Temporal(TemporalType.DATE)
	private Date fechaArCaja;
	@Temporal(TemporalType.DATE)
	private Date fechaAperturaArCaja;
	@Temporal(TemporalType.DATE)
	private Date fechaCierreArCaja;
	
	private Boolean isOpen;
	
	/* Relaciones */
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_caja")
	private Caja caja;
	
	@PrePersist
	public void prePersist() {
		this.fechaArCaja = new Date();
	}
	
	
	
	
}
