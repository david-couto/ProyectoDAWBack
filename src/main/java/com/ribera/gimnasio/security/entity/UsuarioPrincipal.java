package com.ribera.gimnasio.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class UsuarioPrincipal implements UserDetails {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private String nombreUsuario;
	
	private String email;
	
	private String password;
	
	
	private String dni;
	
	private Collection<? extends GrantedAuthority> authorities ;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nombreUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	
	public String getDni() {
		return dni;
	}

	public UsuarioPrincipal(String nombre, String nombreUsuario, String email,String dni, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.dni=dni;
		this.password = password;
		this.authorities = authorities;
	}
	public static UsuarioPrincipal build(Usuario usuario) {
		System.out.println(usuario);
		List<GrantedAuthority> authorities = 
					usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());

		return new UsuarioPrincipal(usuario.getNombre(), usuario.getNombreUsuario(), usuario.getEmail(),usuario.getDni(),
				usuario.getPassword(),authorities);
	}
}
