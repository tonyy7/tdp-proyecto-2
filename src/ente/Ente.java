package ente;

import Position.Position;
import visitor.Visitor;

public abstract class Ente {
	protected Position posicion;
	protected EnteGrafico grafico;
	
	public Position getPosition() {
		return posicion;
	}
	public void setPosition(Position posicion) {
		grafico.setPosicion(posicion);
		this.posicion = posicion;
	}
	public EnteGrafico getGrafico() {
		return grafico;
	}
	public void setGrafico(EnteGrafico g) {
		this.grafico = g;
	}
	
	public String getSkin() {
		return grafico.getSkin();
	}
	
	public abstract void accept(Visitor v);
}
