package com.ribera.gimnasio.dto;

import java.sql.Date;
import java.sql.Time;

public class ClaseDto {

	private Long id;
	private Date fechaClase;
	private java.util.Date horaInicio;
	private java.util.Date horaFin;
	private int numAsistentes;
	private String monitor;
	private ActividadDto actividad;
	
	public ClaseDto() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaClase() {
		return fechaClase;
	}
	public void setFechaClase(Date fechaClase) {
		this.fechaClase = fechaClase;
	}
	public java.util.Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(java.util.Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public java.util.Date getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(java.util.Date horaFin) {
		this.horaFin = horaFin;
	}
	public int getNumAsistentes() {
		return numAsistentes;
	}
	public void setNumAsistentes(int numAsistentes) {
		this.numAsistentes = numAsistentes;
	}
	public String getMonitor() {
		return monitor;
	}
	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}
	public ActividadDto getActividad() {
		return actividad;
	}
	public void setActividad(ActividadDto actividad) {
		this.actividad = actividad;
	}
	
	
}
