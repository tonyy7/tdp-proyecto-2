package ente.powerUp;

import Position.Position;
import ente.EnteGrafico;

public class PowerUp2 extends PowerUp{
	public PowerUp2(Position p) {
		this.posicion=p;
		this.puntaje= 75;	
		this.grafico= new EnteGrafico("ruta");
	}
}
