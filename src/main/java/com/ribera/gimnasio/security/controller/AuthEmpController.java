package com.ribera.gimnasio.security.controller;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribera.gimnasio.dto.Mensaje;
import com.ribera.gimnasio.security.dto.NuevoUsuario;
import com.ribera.gimnasio.security.entity.Rol;
import com.ribera.gimnasio.security.entity.Usuario;
import com.ribera.gimnasio.security.enums.RolNombre;
import com.ribera.gimnasio.security.service.RolService;
import com.ribera.gimnasio.security.service.UsuarioService;

@RestController
@RequestMapping("/emp/auth")
@CrossOrigin
public class AuthEmpController {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RolService rolService;
	
	@PostMapping("/nuevo")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(new Mensaje("Campos mal puestos"),HttpStatus.BAD_REQUEST);
		if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
			return new ResponseEntity<>(new Mensaje("Ese nombre de usuario ya existe"),HttpStatus.BAD_REQUEST);
		if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
			return new ResponseEntity<>(new Mensaje("Ese email ya existe"),HttpStatus.BAD_REQUEST);
		Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),nuevoUsuario.getDni(),
				new Date(nuevoUsuario.getFechaNacimiento().getTime()),passwordEncoder.encode(nuevoUsuario.getPassword()));
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		if(nuevoUsuario.getRoles().contains("emp"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_EMP).get());
		if(nuevoUsuario.getRoles().contains("admin"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		usuario.setRoles(roles);
		usuarioService.save(usuario);
		return new ResponseEntity<>(new Mensaje("Usuario creado correctamente"),HttpStatus.CREATED);
	}
}
