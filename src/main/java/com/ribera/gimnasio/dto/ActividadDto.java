package com.ribera.gimnasio.dto;

import com.ribera.gimnasio.enums.CategoriaActividad;
import com.ribera.gimnasio.enums.DificultadActividad;

public class ActividadDto {
	private Long id;
	private String nombre;
	private int duracion;
	private CategoriaActividad categoria;
	private DificultadActividad dificultad;
	private int numMaxAsistentes;
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
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public CategoriaActividad getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaActividad categoria) {
		this.categoria = categoria;
	}
	public DificultadActividad getDificultad() {
		return dificultad;
	}
	public void setDificultad(DificultadActividad dificultad) {
		this.dificultad = dificultad;
	}
	public int getNumMaxAsistentes() {
		return numMaxAsistentes;
	}
	public void setNumMaxAsistentes(int numMaxAsistentes) {
		this.numMaxAsistentes = numMaxAsistentes;
	}
	
}
