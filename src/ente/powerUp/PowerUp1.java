package ente.powerUp;

import Position.Position;
import ente.EnteGrafico;
import visitor.Visitor;

public class PowerUp1 extends PowerUp {
	
	public PowerUp1(Position p) {
		this.posicion=p;
		this.puntaje=50;	
		this.grafico= new EnteGrafico("assets/powerup/powerup1.png");
		this.urlCabeza = "assets/snake/cabeza1.png";
		this.urlCuerpo = "assets/snake/cuerpo1.png";
	}
	public void accept(Visitor v) {
		v.visitPowerUp1(this);
	}
}
