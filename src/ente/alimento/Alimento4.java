package ente.alimento;

import Position.Position;
import ente.EnteGrafico;

public class Alimento4 extends Alimento{
	public Alimento4(Position p) {
		this.bloque=5;
		this.puntaje=75;
		this.posicion=p;
		//Agregar ruta de la imagen de alimento 4
		this.grafico= new EnteGrafico(" reuta del alimento 4");
	}
}
