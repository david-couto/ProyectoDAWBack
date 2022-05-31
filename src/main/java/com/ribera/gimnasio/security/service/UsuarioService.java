package com.ribera.gimnasio.security.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribera.gimnasio.security.entity.Rol;
import com.ribera.gimnasio.security.entity.Usuario;
import com.ribera.gimnasio.security.repository.IUsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	IUsuarioRepository usuarioRepository;
	public Usuario getById(Long id) {
		return usuarioRepository.findById(id).get();
	}
	public List<Usuario> getUsuarios(){
		return (List<Usuario>) usuarioRepository.findAll();
	}
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}
	public boolean existsByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.existsByNombreUsuario(nombreUsuario);
	}
	public boolean existsByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	public List<Usuario> getByRol(Rol rol){
		return usuarioRepository.findByRoles(rol);
	}
	public void delete(Long id) {
		usuarioRepository.deleteById(id);
	}
	
}
