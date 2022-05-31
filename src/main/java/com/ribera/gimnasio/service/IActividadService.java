package com.ribera.gimnasio.service;

import java.util.List;

import com.ribera.gimnasio.entity.Actividad;

public interface IActividadService {

	public List<Actividad> getActividades();
	public Actividad getActividadById(Long Id);
	public Actividad getActividadByNombre(String nombre);
	public Actividad save(Actividad actividad);
	public void delete(Long id);
	public boolean existsByNombre(String nombre);
}
