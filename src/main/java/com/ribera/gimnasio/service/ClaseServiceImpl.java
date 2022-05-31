package com.ribera.gimnasio.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribera.gimnasio.dto.Monitor;
import com.ribera.gimnasio.entity.Actividad;
import com.ribera.gimnasio.entity.Clase;
import com.ribera.gimnasio.repository.IClaseRepository;
import com.ribera.gimnasio.security.entity.Usuario;

@Service
@Transactional
public class ClaseServiceImpl implements IClaseService{
	@Autowired
	IClaseRepository claseRepository;

	@Override
	public List<Clase> getClases() {
		// TODO Auto-generated method stub
		return (List<Clase>) claseRepository.findAll();
	}

	@Override
	public Clase getClaseById(Long Id) {
		// TODO Auto-generated method stub
		return claseRepository.findById(Id).get();
	}

	@Override
	public Clase save(Clase clase) {
		// TODO Auto-generated method stub
		return claseRepository.save(clase);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		claseRepository.deleteById(id);
	}
	@Override
	public List<Clase> getByUser(Usuario usuario){
		return claseRepository.findByUsuarios(usuario);
	}
	@Override
	public List<Clase> getClasesFuturas(){
		java.util.Date hoy = new java.util.Date();
		System.out.println(new Date(hoy.getTime()));
		System.out.println(new Time(hoy.getTime()));
		return claseRepository.findByFechaClaseAndHoraInicioAfter(new Date(hoy.getTime()), new Time(hoy.getTime()));
	}
	@Override
	public List<Clase> getClasesFuturasByMonitor(Usuario usuario){
		java.util.Date hoy = new java.util.Date();
		System.out.println(new Date(hoy.getTime()));
		System.out.println(new Time(hoy.getTime()));
		return claseRepository.findByMonitorWithFechaClaseAndHoraInicioAfter(new Date(hoy.getTime()), new Time(hoy.getTime()),usuario);
	}
	@Override
	public List<Clase> getClasesPasadas(){
		java.util.Date hoy = new java.util.Date();
		return claseRepository.findByFechaClaseAndHoraInicioBefore(new Date(hoy.getTime()), new Time(hoy.getTime()));
	}

	@Override
	public List<Clase> getByMonitor(Usuario usuario) {
		// TODO Auto-generated method stub
		return claseRepository.findByMonitor(usuario);
	}
	@Override
	public List<Clase> getAll(){
		return (List<Clase>) claseRepository.findAll();
	}

	@Override
	public void deleteByMonitor(Usuario usuario) {
		// TODO Auto-generated method stub
		claseRepository.deleteByMonitor(usuario);
	}

	@Override
	public List<Clase> getClasesFuturasByActividad(Actividad actividad) {
		// TODO Auto-generated method stub
		java.util.Date hoy = new java.util.Date();
		System.out.println(new Date(hoy.getTime()));
		System.out.println(new Time(hoy.getTime()));
		return claseRepository.findByActividadWithFechaClaseAndHoraInicioAfter(new Date(hoy.getTime()), new Time(hoy.getTime()), actividad);
	}

	@Override
	public void deleteByActividad(Actividad actividad) {
		// TODO Auto-generated method stub
		claseRepository.deleteByActividad(actividad);
	}

	@Override
	public List<Clase> getClasesByMonitorBetweenDates(Date fechaClase, Time horaInicio, Time horaFin, Usuario monitor) {
		// TODO Auto-generated method stub
		return claseRepository.findByMonitorBetween(fechaClase, horaInicio, horaFin, monitor);
	}

	@Override
	public List<Clase> getClasesBetweenDates(Date fechaClase, Time horaInicio, Time horaFin) {
		// TODO Auto-generated method stub
		return claseRepository.findAllBetween(fechaClase, horaInicio, horaFin);
	}

	//@Override
	/*public Clase getClaseByNombre(String nombre) {
		// TODO Auto-generated method stub
		return claseRepository.findByNombre(nombre).get();
	}*/

}
