package ente.powerUp;

import Position.Position;
import ente.EnteGrafico;
import visitor.Visitor;

public class PowerUp1 extends PowerUp {
	
	public PowerUp1(Position p) {
		this.posicion=p;
		this.puntaje=50;	
		this.grafico= new EnteGrafico("ruta");
	}
	public void accept(Visitor v) {
		v.visitPowerUp1(this);
	}
}
