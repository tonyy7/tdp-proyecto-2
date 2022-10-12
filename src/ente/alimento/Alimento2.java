package ente.alimento;

import Position.Position;
import ente.EnteGrafico;

public class Alimento2 extends Alimento {
	public Alimento2(Position p) {
		this.bloque=3;
		this.puntaje=35;
		this.posicion=p;
		//Agregar ruta de la imagen de alimento 2
		this.grafico= new EnteGrafico(" reuta del alimento 2");
	}
}
