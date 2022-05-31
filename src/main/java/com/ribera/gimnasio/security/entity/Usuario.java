package com.ribera.gimnasio.security.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ribera.gimnasio.entity.Clase;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nombre;
	@NotNull
	@Column(unique = true)
	private String nombreUsuario;
	@NotNull
	@Column(unique = true)
	private String email;
	
	private Date fechaNacimiento;
	
	@NotNull
	private String dni;
	@NotNull
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name="id_usuario"),
	inverseJoinColumns = @JoinColumn(name = "id_rol"))
	@NotNull
	private Set<Rol> roles = new HashSet<>();

	@OneToMany(mappedBy = "monitor",fetch = FetchType.LAZY)
	private Set<Clase> clasesImpartidas = new HashSet<>();
	
	@ManyToMany(mappedBy = "usuarios")
	private Set<Clase> clasesRecibidas = new HashSet<>();
	
	public Usuario() {
	}

	public Usuario(@NotNull String nombre, @NotNull String nombreUsuario, @NotNull String email,@NotNull String dni,@NotNull Date fechaNacimiento,
			@NotNull String password) {
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.dni = dni;
		this.fechaNacimiento=fechaNacimiento;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Set<Clase> getClasesRecibidas() {
		return clasesRecibidas;
	}

	public void setClasesRecibidas(Set<Clase> clasesRecibidas) {
		this.clasesRecibidas = clasesRecibidas;
	}

	public Set<Clase> getClasesImpartidas() {
		return clasesImpartidas;
	}

	public void setClasesImpartidas(Set<Clase> clasesImpartidas) {
		this.clasesImpartidas = clasesImpartidas;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
