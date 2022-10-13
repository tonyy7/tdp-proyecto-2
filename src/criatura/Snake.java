package criatura;

import java.util.LinkedList;
import java.util.List;

import Position.Position;
import ente.alimento.Alimento1;
import ente.alimento.Alimento2;
import ente.alimento.Alimento3;
import ente.alimento.Alimento4;
import ente.alimento.Alimento5;
import ente.pared.Pared;
import ente.powerUp.PowerUp1;
import ente.powerUp.PowerUp2;
import ente.powerUp.PowerUp3;
import grilla.Grilla;
import juego.Juego;
import visitor.Visitor;

public class Snake implements Visitor{
	protected String direccion;
	protected Juego juego;
	protected Grilla grilla;
	protected LinkedList<Cuerpo> cuerpo;
	protected static int TAMANIO = 3;
	
	/*
	 * Crea una instancia de Snake en la posicion p y en la direccion
	 * pasada por parametro.
	 */
	public Snake(Position p, String direccion, Juego juego, Grilla grilla) {
		cuerpo = new LinkedList<Cuerpo>();
		this.direccion = direccion;
		this.juego = juego;
		this.grilla = grilla;
		init(p);
	}
	
	/*
	 * Inicializa snake con un tamanio predefinido.
	 */
	private void init(Position p) {
		Position posDireccion = getDireccionP();
		Position posSiguiente;
		Cuerpo insert;
		cuerpo.add(new Cuerpo(p));
		for(int i=1; i<TAMANIO; i++) {
			posSiguiente = cuerpo.get(i).getPosition();
			insert = new Cuerpo(new Position(posSiguiente.getX()-posDireccion.getX(), posSiguiente.getY()-posDireccion.getY()));
			cuerpo.addLast(insert);
		}
	}
	
	/*
	 * @return posicion de la cabeza en la grilla.
	 */
	public Position getPosicionCabeza() {
		return cuerpo.getFirst().getPosition();
	}

	/*
	 * @return direccion de snake.
	 */
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/*
	 * Mueve snake 1 bloque en la direccion correspondiente.
	 */
	public void moverSnake() {
		crecer();
		cuerpo.removeLast();
	}
	
	/*
	 * Crece el tamaño de snake n bloques en la direccion correspondiete.
	 */
	public void crecerNBloques(int n) {
		for(int i=0; i<n; i++) {
			crecer();
		}
	}
	
	/*
	 * Aumenta el tamaño de snake en 1 bloque en la direccion correspondiente.
	 */
	public void crecer() {
		Position posDireccion = getDireccionP();
		Position posCabeza = getPosicionCabeza();
		cuerpo.getFirst().setPosition(new Position(posCabeza.getX()+posDireccion.getX(), posCabeza.getY()+posDireccion.getY()));
		cuerpo.add(2, new Cuerpo(new Position(posCabeza.getX()-posDireccion.getX(), posCabeza.getY()-posDireccion.getY())));
	}
	
	
	/*
	 * @return Position correspondiente a la direccion
	 */
	private Position getDireccionP() {
		Position toReturn = null;
		switch (direccion) {
		case "arriba" : {
			toReturn = new Position(-1,0);
			break;
		}
		case "abajo" : {
			toReturn = new Position(1,0);
			break;
		}
		case "izquierda" : {
			toReturn = new Position(0,-1);
			break;
		}
		case "derecha" : {
			toReturn = new Position(0,1);
			break;
		}
		}
		return toReturn;
	}

	public int visitAlimento1(Alimento1 a) {
		crecerNBloques(a.getBloque());
		return a.getPuntaje();
	}

	public int visitAlimento2(Alimento2 a) {
		crecerNBloques(a.getBloque());
		return a.getPuntaje();
	}

	public int visitAlimento3(Alimento3 a) {
		crecerNBloques(a.getBloque());
		return a.getPuntaje();
	}

	public int visitAlimento4(Alimento4 a) {
		crecerNBloques(a.getBloque());
		return a.getPuntaje();
	}

	public int visitAlimento5(Alimento5 a) {
		crecerNBloques(a.getBloque());
		return a.getPuntaje();
	}

	public int visitPowerUp1(PowerUp1 p) {
		crecerNBloques(3);
		return p.GetPuntaje();
	}

	public int visitPowerUp2(PowerUp2 p) {
		crecerNBloques(3);
		return p.GetPuntaje();
	}

	public int visitPowerUp3(PowerUp3 p) {
		crecerNBloques(3);
		return p.GetPuntaje();
	}

	public void visitCuerpo(Cuerpo c) {
		juego.gameOver();
	}

	public void visitPared(Pared p) {
		juego.gameOver();
	}
}
