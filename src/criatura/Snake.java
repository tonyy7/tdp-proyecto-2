package criatura;

import java.util.LinkedList;

import Position.Position;
import ente.pared.Pared;
import ente.alimento.*;
import ente.powerUp.*;
import juego.Juego;
import visitor.Visitor;

public class Snake implements Visitor{
	protected Juego juego;
	protected Position direccion;
	protected LinkedList<Cuerpo> cuerpo;
	protected String urlCabeza;
	protected String urlCuerpo;
	protected static int TAMANIO = 3;
	protected int acarreo;
	protected boolean hayCarry;
	
	/*
	 * Crea una instancia de Snake en la posicion cabeza y en la direccion
	 * pasada por parametro.
	 * cabeza y direccion corresponden a una grilla 60x60;
	 */
	public Snake(Position cabeza, Position direccion, Juego juego) {
		cuerpo = new LinkedList<Cuerpo>();
		this.juego = juego;
		this.direccion = direccion;
		acarreo = 0;
		urlCabeza = "assets/snake/cabeza.png";
		urlCuerpo = "assets/snake/cuerpo.png";
		initSnake(cabeza);
	}
	
	/*
	 * Inicializa snake con un tamanio predefinido.
	 */
	private void initSnake(Position cabeza) {
		Cuerpo insert;
		Position posSiguiente;
		cabeza.setX(cabeza.getX());
		cabeza.setY(cabeza.getY());
		cuerpo.addFirst(new Cuerpo(cabeza, urlCabeza));
		for (int i=1; i<TAMANIO; i++) {
			posSiguiente = cuerpo.get(i-1).getPosition();
			insert = new Cuerpo(new Position(posSiguiente.getX() - direccion.getX(), posSiguiente.getY() - direccion.getY()), urlCuerpo);
			cuerpo.add(i, insert);
		}
	}

	public Position moverSnake() {
		Position posCabeza = getPosicionCabeza();
		Position proxima = new Position(posCabeza.getX()+direccion.getX(), posCabeza.getY()+direccion.getY());
		cuerpo.getFirst().setSkin(urlCuerpo);
		cuerpo.addFirst(new Cuerpo(proxima, urlCabeza));
		hayCarry = false;
		if(acarreo == 0)
			cuerpo.removeLast();
		else {
			acarreo--;
			hayCarry = true;
		}
		return cuerpo.getFirst().getPosition();
	}
	
	/*
	 * @return posicion de la cabeza.
	 */
	public Position getPosicionCabeza() {
		return cuerpo.getFirst().getPosition();
	}
	
	/*
	 * @return direccion de snake.
	 */
	public Position getDireccion() {
		return direccion;
	}
	
	public boolean hayCarry() {
		return hayCarry;
	}
	

	public void setDireccion(Position direccion) {
		this.direccion = direccion;
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
		for(int i=1; i<cuerpo.size(); i++) {
			cuerpo.get(i).setSkin(this.urlCuerpo);
		}
	}
	

	public void visitAlimento1(Alimento1 a) {
		juego.sumarPuntos(a.getPuntaje());
		acarreo += a.getBloque();
	}

	public void visitAlimento2(Alimento2 a) {
		juego.sumarPuntos(a.getPuntaje());
		acarreo += a.getBloque();
	}

	public void visitAlimento3(Alimento3 a) {
		juego.sumarPuntos(a.getPuntaje());
		acarreo += a.getBloque();
	}

	public void visitAlimento4(Alimento4 a) {
		juego.sumarPuntos(a.getPuntaje());
		acarreo += a.getBloque();
	}

	public void visitAlimento5(Alimento5 a) {
		juego.sumarPuntos(a.getPuntaje());
		acarreo += a.getBloque();
	}

	public void visitPowerUp1(PowerUp1 p) {
		juego.sumarPuntos(p.getPuntaje());
		cambiarSkin(p.getUrlCabeza(),p.getUrlCuerpo());
		acarreo += p.getBloque();	
	}

	public void visitPowerUp2(PowerUp2 p) {
		juego.sumarPuntos(p.getPuntaje());
		cambiarSkin(p.getUrlCabeza(),p.getUrlCuerpo());
		acarreo += p.getBloque();
	}

	public void visitPowerUp3(PowerUp3 p) {
		juego.sumarPuntos(p.getPuntaje());
		cambiarSkin(p.getUrlCabeza(),p.getUrlCuerpo());
		acarreo += p.getBloque();
	}

	public void visitCuerpo(Cuerpo c) {
		juego.gameOver(false);
	}

	public void visitPared(Pared p) {
		juego.gameOver(false);
	}
}
