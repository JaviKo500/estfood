package com.back.estfood.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Caja implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCaja;
	
	private String nombreCaja;
	private Boolean estadoCaja;
	
	@Temporal(TemporalType.DATE)
	private Date fechaCaja;
	
	@PrePersist
	public void prePersist() {
		this.fechaCaja = new Date();
	}
	
}
