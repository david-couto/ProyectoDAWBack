package com.ribera.gimnasio.enums;

public enum DificultadActividad {
	BAJA("baja"), MEDIA("media"), ALTA("alta");
	private String texto;

	private DificultadActividad(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}
	public static DificultadActividad fromText(String text) {
		for(DificultadActividad c : DificultadActividad.values()) {
			if(c.getTexto().equals(text)) {
				return c;
			}
		}
		 throw new IllegalArgumentException();
	}
}
