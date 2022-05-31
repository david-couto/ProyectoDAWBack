package com.ribera.gimnasio.enums;

public enum CategoriaActividad {
	CARDIO("cardio"), HIT("hit"), TORSO("torso"), CORE("core"), PIERNAS("piernas");
	private String text;

	private CategoriaActividad(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

	public static CategoriaActividad fromText(String text) {
		for(CategoriaActividad c : CategoriaActividad.values()) {
			if(c.getText().equals(text)) {
				return c;
			}
		}
		 throw new IllegalArgumentException();
	}
}
