package com.ribera.gimnasio.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ribera.gimnasio.security.entity.Usuario;
import com.ribera.gimnasio.security.entity.UsuarioPrincipal;
import com.ribera.gimnasio.security.repository.IUsuarioRepository;
import com.ribera.gimnasio.service.IClaseService;

@Service
public class AuthenticatedEmpService {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	UserDetailsServiceImpl userDetails;
	@Autowired
	IClaseService claseService;
	public boolean isMonitor(Long id) {
		UsuarioPrincipal usuarioPrincipal  = (UsuarioPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = usuarioPrincipal.getUsername();
		Long idUser= usuarioService.getByNombreUsuario(username).get().getId();
		
		return idUser==claseService.getClaseById(id).getMonitor().getId();
	}
	
}
