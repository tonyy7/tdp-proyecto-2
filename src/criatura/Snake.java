package criatura;

import java.util.LinkedList;
import java.util.List;

import Position.Position;
import ente.Ente;
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
	protected String urlCabeza;
	protected String urlCuerpo;
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
		urlCabeza = "assets/snake/cabeza.png";
		urlCuerpo = "assets/snake/cuerpo.png";
		init(p);
	}
	
	/*
	 * Inicializa snake con un tamanio predefinido.
	 */
	private void init(Position p) {
		Position posDireccion = getDireccionP();
		Position posSiguiente;
		Cuerpo insert;
		cuerpo.addFirst(new Cuerpo(p));
		cuerpo.getFirst().setSkin(urlCabeza);
		grilla.setEnte(cuerpo.getFirst().getPosition(), cuerpo.getFirst());
		for(int i=1; i<TAMANIO; i++) {
			posSiguiente = cuerpo.get(i-1).getPosition();
			insert = new Cuerpo(new Position(posSiguiente.getX()-posDireccion.getX(), posSiguiente.getY()-posDireccion.getY()));
			cuerpo.addLast(insert);
			cuerpo.get(i).setSkin(urlCuerpo);
			grilla.setEnte(insert.getPosition(), insert);
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
	 * Crece el tama�o de snake n bloques en la direccion correspondiete.
	 */
	public void crecerNBloques(int n) {
		for(int i=0; i<n; i++) {
			crecer();
		}
	}
	
	/*
	 * Aumenta el tama�o de snake en 1 bloque en la direccion correspondiente.
	 */
	public void crecer() {
		Position posDireccion = getDireccionP();
		Position posCabeza = getPosicionCabeza();
		Position proxima = new Position(posCabeza.getX()+posDireccion.getX(), posCabeza.getY()+posDireccion.getY());
		Ente colision;
		if(grilla.getEnte(proxima) == null) {
			cuerpo.getFirst().setSkin(urlCuerpo);
			cuerpo.addFirst(new Cuerpo(proxima));
			cuerpo.getFirst().setSkin(urlCabeza);
			grilla.setEnte(proxima, cuerpo.getFirst());		
		}
		else {
			//codear colision con ente en la posicion proximna
			colision = grilla.getEnte(proxima);
			colision.accept(this);
		}
	}
	
	public LinkedList<Cuerpo> getCuerpo() {
        return cuerpo;
    }
	
	/*
	 * Cambia la skin correspondiente a snake
	 */
	private void cambiarSkin(String urlCabeza, String urlCuerpo) {
		this.urlCabeza = urlCabeza;
		this.urlCuerpo = urlCuerpo;
		cuerpo.getFirst().setSkin(this.urlCabeza);
		for(int i=2; i<cuerpo.size(); i++) {
			cuerpo.get(i).setSkin(this.urlCuerpo);
		}
	}
	
	/*
	 * @return Position correspondiente a la direccion
	 */
	public Position getDireccionP() {
		Position toReturn = null;
		switch (direccion) {
		case "arriba" : {
			toReturn = new Position(0,-1);
			break;
		}
		case "abajo" : {
			toReturn = new Position(0,1);
			break;
		}
		case "izquierda" : {
			toReturn = new Position(-1,0);
			break;
		}
		case "derecha" : {
			toReturn = new Position(1,0);
			break;
		}
		}
		return toReturn;
	}

	public void visitAlimento1(Alimento1 a) {
		crecerNBloques(a.getBloque());
		juego.sumarPuntos(a.getPuntaje());
		grilla.consumir(a);
	}

	public void visitAlimento2(Alimento2 a) {
		crecerNBloques(a.getBloque());
		juego.sumarPuntos(a.getPuntaje());
		grilla.consumir(a);
	}

	public void visitAlimento3(Alimento3 a) {
		crecerNBloques(a.getBloque());
		juego.sumarPuntos(a.getPuntaje());
		grilla.consumir(a);
	}

	public void visitAlimento4(Alimento4 a) {
		crecerNBloques(a.getBloque());
		juego.sumarPuntos(a.getPuntaje());
		grilla.consumir(a);
	}

	public void visitAlimento5(Alimento5 a) {
		crecerNBloques(a.getBloque());
		juego.sumarPuntos(a.getPuntaje());
		grilla.consumir(a);
	}

	/*
	 * VER EL ORDEN DE CAMBIO DE SKIN
	 */
	public void visitPowerUp1(PowerUp1 p) {
		cambiarSkin(p.getUrlCabeza(), p.getUrlCuerpo());
		crecerNBloques(3);
		juego.sumarPuntos(p.getPuntaje());
		grilla.consumir(p);
	}

	public void visitPowerUp2(PowerUp2 p) {
		cambiarSkin(p.getUrlCabeza(), p.getUrlCuerpo());
		crecerNBloques(3);
		juego.sumarPuntos(p.getPuntaje());
		grilla.consumir(p);
	}

	public void visitPowerUp3(PowerUp3 p) {
		cambiarSkin(p.getUrlCabeza(), p.getUrlCuerpo());
		crecerNBloques(3);
		juego.sumarPuntos(p.getPuntaje());
		grilla.consumir(p);
	}

	public void visitCuerpo(Cuerpo c) {
		juego.gameOver();
	}

	public void visitPared(Pared p) {
		juego.gameOver();
	}
}
