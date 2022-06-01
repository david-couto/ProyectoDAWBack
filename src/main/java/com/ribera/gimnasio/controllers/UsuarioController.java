package com.ribera.gimnasio.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribera.gimnasio.dto.ClaseDto;
import com.ribera.gimnasio.dto.Cliente;
import com.ribera.gimnasio.dto.Mensaje;
import com.ribera.gimnasio.dto.Monitor;
import com.ribera.gimnasio.entity.Clase;
import com.ribera.gimnasio.security.entity.Rol;
import com.ribera.gimnasio.security.entity.Usuario;
import com.ribera.gimnasio.security.enums.RolNombre;
import com.ribera.gimnasio.security.service.RolService;
import com.ribera.gimnasio.security.service.UsuarioService;
import com.ribera.gimnasio.service.ClaseServiceImpl;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	RolService rolService;
	@Autowired
	ClaseServiceImpl claseService;
	@Autowired
    private ModelMapper modelMapper;
	@GetMapping("/monitores/{nombreUsuario}")
	@PreAuthorize("hasRole('ROLE_EMP')")
	public Monitor getClienteByNombreUsuario(@PathVariable String nombreUsuario){
		
		  return this.convertToDto(usuarioService.getByNombreUsuario(nombreUsuario).get()) ;
	
		
	}
	
	
	@GetMapping("/monitores")
	@PreAuthorize("hasRole('ROLE_EMP')")
	public List<Monitor> getMonitores(){
		Rol rol = rolService.getByRolNombre(RolNombre.ROLE_EMP).get();
		  return usuarioService.getByRol(rol).stream().map(this::convertToDto).collect(Collectors.toList());
	
		
	}
	
	@GetMapping()
	@PreAuthorize("hasRole('ROLE_EMP')")
	public List<Cliente> getClientes(){
		List<Usuario> usuarios = usuarioService.getUsuarios();
		List<Cliente> clientes= new ArrayList<Cliente>();
		usuarios.forEach(t -> {
			Cliente cliente = new Cliente(t.getId(),t.getNombre(),t.getEmail(),t.getDni(),t.getFechaNacimiento(),t.getClasesRecibidas());
			clientes.add(cliente);
		});
		return clientes;
	}
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) throws  ConstraintViolationException, Exception{
		
		Usuario usuario= usuarioService.getById(id);
		
		if(claseService.getClasesFuturasByMonitor(usuario).isEmpty()) {
			claseService.deleteByMonitor(usuario);		
		}
		usuarioService.delete(id);
		return new ResponseEntity<>(new Mensaje("Usuario eliminado"),HttpStatus.OK);
		
	}
	 private Monitor convertToDto(Usuario usuario) {
	        Monitor monitor = modelMapper.map(usuario, Monitor.class);
	       
	        return monitor;
	    }
}
