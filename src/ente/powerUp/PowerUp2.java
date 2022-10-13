package ente.powerUp;

import Position.Position;
import ente.EnteGrafico;
import visitor.Visitor;

public class PowerUp2 extends PowerUp{
	public PowerUp2(Position p) {
		this.posicion=p;
		this.puntaje= 75;	
		this.grafico= new EnteGrafico("ruta");
	}
	public void accept(Visitor v) {
		v.visitPowerUp2(this);
	}
}
