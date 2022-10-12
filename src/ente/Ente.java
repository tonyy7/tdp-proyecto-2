package ente;

import Position.Position;

public abstract class Ente {
	protected Position posicion;
	protected EnteGrafico grafico;
	
	public Position getPosicion() {
		return posicion;
	}
	public void setPosicion(Position posicion) {
		this.posicion = posicion;
	}
	public EnteGrafico getGrafico() {
		return grafico;
	}
	public void setGrafico(EnteGrafico g) {
		this.grafico = g;
	}
}
