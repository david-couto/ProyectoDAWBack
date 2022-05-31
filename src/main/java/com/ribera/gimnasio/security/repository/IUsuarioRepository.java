package com.ribera.gimnasio.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ribera.gimnasio.security.entity.Rol;
import com.ribera.gimnasio.security.entity.Usuario;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long>{

	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	List<Usuario> findByRoles(Rol rol);
	boolean existsByNombreUsuario(String nombreUsuario);
	boolean existsByEmail(String email);
}
