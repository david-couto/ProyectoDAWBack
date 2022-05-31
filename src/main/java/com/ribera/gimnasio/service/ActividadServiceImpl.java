package com.ribera.gimnasio.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribera.gimnasio.entity.Actividad;
import com.ribera.gimnasio.repository.IActividadRepository;

@Service
@Transactional
public class ActividadServiceImpl implements IActividadService {

	@Autowired
	IActividadRepository actividadRespository;
	@Override
	public List<Actividad> getActividades() {
		// TODO Auto-generated method stub
		return (List<Actividad>) actividadRespository.findAll();
	}

	@Override
	public Actividad getActividadById(Long Id) {
		// TODO Auto-generated method stub
		return actividadRespository.findById(Id).get();
	}

	@Override
	public Actividad save(Actividad actividad) {
		// TODO Auto-generated method stub
		return actividadRespository.save(actividad);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		actividadRespository.deleteById(id);
	}

	@Override
	public Actividad getActividadByNombre(String nombre) {
		// TODO Auto-generated method stub
		return actividadRespository.findByNombre(nombre).get();
	}

	@Override
	public boolean existsByNombre(String nombre) {
		// TODO Auto-generated method stub
		return actividadRespository.existsByNombre(nombre);
	}
	
}
