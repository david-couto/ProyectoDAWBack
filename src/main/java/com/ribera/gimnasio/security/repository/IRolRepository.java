package com.ribera.gimnasio.security.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ribera.gimnasio.security.entity.Rol;
import com.ribera.gimnasio.security.enums.RolNombre;

public interface IRolRepository extends CrudRepository<Rol, Long>{

	Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
