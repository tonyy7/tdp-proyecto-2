package ente.alimento;

import Position.Position;
import ente.EnteGrafico;
import visitor.Visitor;

public class Alimento5 extends Alimento{
	public Alimento5(Position p) {
		this.bloque=6;
		this.puntaje=100;
		this.posicion=p;
		//Agregar ruta de la imagen de alimento 5
		this.grafico= new EnteGrafico(p, "assets/alimento/alimento5.png");
	}
	public void accept(Visitor v) {
		v.visitAlimento5(this);
	}
}
