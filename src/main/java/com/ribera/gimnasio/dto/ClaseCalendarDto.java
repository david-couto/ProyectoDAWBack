package com.ribera.gimnasio.dto;

import java.sql.Date;
import java.sql.Time;

public class ClaseCalendarDto {
	private Long id;
	private String title;
	private String start;
	private String endStr;
	private boolean fullday = false;
	private String  backgroundColor;
	private String textColor;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(Date fechaClase,  Time  horaInicio) {
		this.start = fechaClase.toString()+"T"+horaInicio.toString();
	}
	public String getEndStr() {
		return endStr;
	}
	public void setEndStr(Date fechaClase,  Time  horaFin) {
		this.endStr = fechaClase.toString()+"T"+horaFin.toString();
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getTextColor() {
		return textColor;
	}
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}
	public boolean isFullday() {
		return fullday;
	}
	public void setFullday(boolean fullday) {
		this.fullday = fullday;
	}
	
	
}
