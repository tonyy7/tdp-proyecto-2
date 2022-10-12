package ente.pared;

import Position.Position;
import ente.Ente;
import ente.EnteGrafico;

public class Pared extends Ente {
	public Pared (Position p) {
		this.posicion=p;
		grafico= new EnteGrafico("ruta");
	}
}
