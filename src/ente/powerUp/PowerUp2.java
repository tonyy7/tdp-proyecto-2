package ente.powerUp;

import Position.Position;
import ente.EnteGrafico;
import visitor.Visitor;

public class PowerUp2 extends PowerUp{
	public PowerUp2(Position p) {
		this.posicion=p;
		this.puntaje= 75;	
		this.grafico= new EnteGrafico(p, "assets/powerup/powerup2.png");
		this.urlCabeza = "assets/snake/cabeza2.png";
		this.urlCuerpo = "assets/snake/cuerpo2.png";
	}
	public void accept(Visitor v) {
		v.visitPowerUp2(this);
	}
}
