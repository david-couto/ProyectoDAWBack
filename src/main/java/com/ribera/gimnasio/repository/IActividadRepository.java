package com.ribera.gimnasio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ribera.gimnasio.entity.Actividad;

public interface IActividadRepository extends CrudRepository<Actividad, Long> {
		
	public List<Actividad> findAll();
	public Optional<Actividad> findByNombre(String nombre);
	@SuppressWarnings("unchecked")
	public Actividad save(Actividad actividad);
	public boolean existsByNombre(String nombre);
}
