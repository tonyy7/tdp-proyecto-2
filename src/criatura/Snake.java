package criatura;

import java.util.LinkedList;
import java.util.List;

import Position.Position;

public class Snake {
	protected String direccion;
	protected LinkedList<Cuerpo> cuerpo;
	
	public Snake() {
		cuerpo = new LinkedList<Cuerpo>();
		direccion = "derecha";
		init();
	}
	
	private void init() {
		for(int i=0; i<3; i++) {
			cuerpo.add(new Cuerpo());
		}
	}
	
	public Position getPosicionCabeza() {
		return cuerpo.getFirst().getPosition();

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void moverSnake() {
		switch (direccion) {
			case "arriba" : {
				moverSnake(new Position(0,-1));
				break;
			}
			case "abajo" : {
				moverSnake(new Position(0,1));
				break;
			}
			case "izquierda" : {
				moverSnake(new Position(-1,0));
				break;
			}
			case "derecha" : {
				moverSnake(new Position(1,0));
				break;
			}
		}
	}
	
	public void accept(Visitor v) {
		v.visitSnake(this);
	}
	
	private void moverSnake(Position p) { 
		
		cuerpo.add(2, new Cuerpo());
		cuerpo.removeLast();
	}
}
