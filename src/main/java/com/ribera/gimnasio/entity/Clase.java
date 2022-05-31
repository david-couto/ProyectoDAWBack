package com.ribera.gimnasio.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ribera.gimnasio.security.entity.Rol;
import com.ribera.gimnasio.security.entity.Usuario;

@Entity
public class Clase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date fechaClase;
	
	private Time horaInicio;
	
	private Time horaFin;
	
	private int numAsistentes = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_actividad")
	private Actividad actividad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_monitor")
	@JsonBackReference
	private Usuario monitor;
	
	@ManyToMany
	@JoinTable(name = "usuarios_clases", joinColumns = @JoinColumn(name="id_clase"),
	inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	@NotNull
	private Set<Usuario> usuarios = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaClase() {
		return fechaClase;
	}

	public void setFechaClase(Date fechaClase) {
		this.fechaClase = fechaClase;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	public int getNumAsistentes() {
		return numAsistentes;
	}

	public void setNumAsistentes(int numAsistentes) {
		this.numAsistentes = numAsistentes;
	}
	public void anadirAsistente() {
		this.numAsistentes++;
	}
	public void quitarAsistente() {
		this.numAsistentes--;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Usuario getMonitor() {
		return monitor;
	}

	public void setMonitor(Usuario profesor) {
		this.monitor = profesor;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	 public Time getAddSubtractTime(Time time,int minutes)
	    {
	        Calendar cal = new GregorianCalendar();
	        cal.setTimeInMillis(time.getTime());
	        cal.add(Calendar.MINUTE, minutes);
	        return new Time(cal.getTimeInMillis());
	    }
	
}
