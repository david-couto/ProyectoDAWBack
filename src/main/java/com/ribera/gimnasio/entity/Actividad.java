package com.ribera.gimnasio.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ribera.gimnasio.enums.CategoriaActividad;

import com.ribera.gimnasio.enums.DificultadActividad;

import com.ribera.gimnasio.security.enums.RolNombre;

@Entity
@Table(name="actividades")
public class Actividad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	@Column(columnDefinition = "LONGTEXT")
	private String icono;
	private int duracion;
	
	@Enumerated(EnumType.STRING)
	private CategoriaActividad categoria;
	
	@Enumerated(EnumType.STRING)
	private DificultadActividad dificultad;
	
	@OneToMany(mappedBy = "actividad",fetch = FetchType.LAZY)
	private List<Clase> clases ;
	
	private int numMaxAsistentes;

	public Actividad() {
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

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}

	public int getNumMaxAsistentes() {
		return numMaxAsistentes;
	}

	public void setNumMaxAsistentes(int numMaxAsistentes) {
		this.numMaxAsistentes = numMaxAsistentes;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}
	
	
	
}
