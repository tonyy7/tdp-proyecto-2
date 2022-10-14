package ente.powerUp;

import Position.Position;
import ente.EnteGrafico;
import visitor.Visitor;

public class PowerUp3 extends PowerUp {
	public PowerUp3(Position p) {
		this.posicion=p;
		this.puntaje=100;	
		this.grafico= new EnteGrafico("assets/powerup/powerup3.png");
		this.urlCabeza = "assets/snake/cabeza3.png";
		this.urlCuerpo = "assets/snake/cuerpo3.png";
	}
	public void accept(Visitor v) {
		v.visitPowerUp3(this);
	}
}
