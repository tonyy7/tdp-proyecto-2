package ente.powerUp;

import Position.Position;
import ente.EnteGrafico;
import visitor.Visitor;

public class PowerUp3 extends PowerUp {
	public PowerUp3(Position p) {
		this.posicion=p;
		this.puntaje=100;	
		this.grafico= new EnteGrafico("ruta");
	}
	public void accept(Visitor v) {
		v.visitPowerUp3(this);
	}
}
