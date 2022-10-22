package ente;

import Position.Position;

public class EnteGrafico{
	protected String url;
	protected Position posicion;
	
	public EnteGrafico(Position posicion, String url){
		this.posicion = posicion;
		this.url=url;
	}
	
	public void setSkin(String s) {
		this.url=s;
	}
	
	public String getSkin() {
		return this.url;
	}

	public Position getPosicion() {
		return posicion;
	}

	public void setPosicion(Position posicion) {
		this.posicion = posicion;
	}
}
