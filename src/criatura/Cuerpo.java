package criatura;

import Position.Position;
import ente.Ente;
import ente.EnteGrafico;
import visitor.Visitor;

public class Cuerpo extends Ente {
	
	public Cuerpo(Position p) {
		this.posicion=p;
		this.grafico=new EnteGrafico("ruta");
	}
	
	public void setSkin(String url) {
		this.grafico.setSkin(url);
	}
	
	
	public void accept(Visitor v) {
		v.visitCuerpo(this);
	}
}