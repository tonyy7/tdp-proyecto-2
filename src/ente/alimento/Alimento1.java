package ente.alimento;
import Position.Position;
import ente.EnteGrafico;
import visitor.Visitor;

public class Alimento1 extends Alimento {
	public Alimento1(Position p) {
		this.bloque=2;
		this.puntaje=25;
		this.posicion=p;
		//Agregar ruta de la imagen de alimento 1
		this.grafico= new EnteGrafico(" reuta del alimento 1");
	}
	public void accept(Visitor v) {
		v.visitAlimento1(this);
	}
}
