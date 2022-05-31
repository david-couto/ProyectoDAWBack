package com.ribera.gimnasio.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribera.gimnasio.security.entity.Rol;
import com.ribera.gimnasio.security.enums.RolNombre;
import com.ribera.gimnasio.security.repository.IRolRepository;

@Service
@Transactional
public class RolService {

	@Autowired
	IRolRepository rolRepository;
	public Optional<Rol> getByRolNombre(RolNombre rolNombre){
		return rolRepository.findByRolNombre(rolNombre);
	}
}
