package com.ribera.gimnasio.dto;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NuevaClase {

	private Date fechaClase;
	private Date horaInicio;
	private Long monitor;
	private Long actividad;
	private int numAsistentes;
	 public Time getAddSubtractTime(Time time,int minutes)
	    {
	        Calendar cal = new GregorianCalendar();
	        cal.setTimeInMillis(time.getTime());
	        cal.add(Calendar.MINUTE, minutes);
	        return new Time(cal.getTimeInMillis());
	    }
	public NuevaClase(Date fechaClase, Date horaInicio, Long idMonitor, Long idActividad, int numAsistentes) {
		this.fechaClase = fechaClase;
		this.horaInicio = horaInicio;
		this.monitor = idMonitor;
		this.actividad = idActividad;
		this.numAsistentes = numAsistentes;
	}
	public Date getFechaClase() {
		return fechaClase;
	}
	public void setFechaClase(Date fechaClase) {
		this.fechaClase = fechaClase;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Long getMonitor() {
		return monitor;
	}
	public void setMonitor(Long idMonitor) {
		this.monitor = idMonitor;
	}
	public Long getActividad() {
		return actividad;
	}
	public void setActividad(Long idActividad) {
		this.actividad = idActividad;
	}
	public int getNumAsistentes() {
		return numAsistentes;
	}
	public void setNumAsistentes(int numAsistentes) {
		this.numAsistentes = numAsistentes;
	}
	@Override
	public String toString() {
		return "NuevaClase [fechaClase=" + fechaClase + ", horaInicio=" + horaInicio + ", idMonitor=" + monitor
				+ ", idActividad=" + actividad + ", numAsistentes=" + numAsistentes + "]";
	}
	
	
}
