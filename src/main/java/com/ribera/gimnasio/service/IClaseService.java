package com.ribera.gimnasio.service;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.List;

import com.ribera.gimnasio.dto.ClaseDto;
import com.ribera.gimnasio.entity.Actividad;
import com.ribera.gimnasio.entity.Clase;
import com.ribera.gimnasio.security.entity.Usuario;

public interface IClaseService {

	public List<Clase> getClases();
	public Clase getClaseById(Long Id);
	
	public Clase save(Clase clase);
	public void delete(Long id);
	//public Clase getClaseByNombre(String nombre);
	public List<Clase> getByUser(Usuario usuario);
	List<Clase> getClasesFuturas();
	List<Clase> getClasesPasadas();
	public List<Clase> getByMonitor(Usuario usuario);
	List<Clase> getAll();
	List<Clase> getClasesFuturasByMonitor(Usuario usuario);
	public void deleteByMonitor(Usuario usuario);
	List<Clase> getClasesFuturasByActividad(Actividad actividad);
	public void deleteByActividad(Actividad actividad);
	public List<Clase> getClasesByMonitorBetweenDates(Date fechaClase, Time horaInicio, Time horaFin, Usuario monitor);
	public List<Clase> getClasesBetweenDates(Date fechaClase, Time horaInicio, Time horaFin);
}
