package criatura;

import Position.Position;
import ente.Ente;
import ente.EnteGrafico;

public class Cuerpo extends Ente {
	
	public Cuerpo(Position p) {
		this.posicion=p;
		//Agregar ruta de la imagen de cuerpo
		this.grafico=new EnteGrafico("ruta");
	}
}
