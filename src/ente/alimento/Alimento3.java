package ente.alimento;

import Position.Position;
import ente.EnteGrafico;

public class Alimento3 extends Alimento {
	public Alimento3(Position p) {
		this.bloque=4;
		this.puntaje=50;
		this.posicion=p;
		//Agregar ruta de la imagen de alimento 3
		this.grafico= new EnteGrafico(" reuta del alimento 3");
	}
}
