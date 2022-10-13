package ente.pared;

import Position.Position;
import ente.Ente;
import ente.EnteGrafico;
import visitor.Visitor;

public class Pared extends Ente {
	public Pared (Position p) {
		this.posicion=p;
		grafico = new EnteGrafico("assets/pared/pared.png");
	}
	public void accept(Visitor v) {
		v.visitPared(this);
	}
}
