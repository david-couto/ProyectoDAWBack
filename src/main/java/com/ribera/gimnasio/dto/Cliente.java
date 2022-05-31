package com.ribera.gimnasio.dto;

import java.util.Date;
import java.util.Set;

import com.ribera.gimnasio.entity.Clase;

public class Cliente {


	private Long id;
	private String nombre;
	private String email;
	private String dni;
	private Date fechaNacimiento;
	private Set<Clase> clasesRecibidas;
	public Cliente(Long id, String nombre, String email, String dni, Date fechaNacimiento, Set<Clase> clasesRecibidas) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.clasesRecibidas = clasesRecibidas;
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Set<Clase> getClasesRecibidas() {
		return clasesRecibidas;
	}
	public void setClasesRecibidas(Set<Clase> clasesRecibidas) {
		this.clasesRecibidas = clasesRecibidas;
	}
	
}
