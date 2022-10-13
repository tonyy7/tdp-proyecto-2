package ente.alimento;

import Position.Position;
import ente.EnteGrafico;
import visitor.Visitor;

public class Alimento3 extends Alimento {
	public Alimento3(Position p) {
		this.bloque=4;
		this.puntaje=50;
		this.posicion=p;
		//Agregar ruta de la imagen de alimento 3
		this.grafico= new EnteGrafico("assets/alimento/alimento3.png");
	}
	public void accept(Visitor v) {
		v.visitAlimento3(this);
	}
}
