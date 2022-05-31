package com.ribera.gimnasio.dto;


import java.sql.Date;
import java.util.Set;

import com.ribera.gimnasio.entity.Clase;

public class Monitor {

	private Long id;
	private String nombre;
	private String email;
	private Date fechaNacimiento;
	private Set<ClaseDto> clasesImpartidas;
	public Monitor(Long id, String nombre, String email, Date fechaNacimiento, Set<ClaseDto> clasesImpartidas) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.clasesImpartidas = clasesImpartidas;
	}
	
	public Monitor() {
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Set<ClaseDto> getClasesImpartidas() {
		return clasesImpartidas;
	}
	public void setClasesImpartidas(Set<ClaseDto> clasesImpartidas) {
		this.clasesImpartidas = clasesImpartidas;
	}
	
}
