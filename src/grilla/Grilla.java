package grilla;

import java.util.LinkedList;

import Position.Position;
import criatura.Snake;
import ente.Ente;
import factory.FactoryLevel;
import grilla.nivel.Nivel;
import juego.Juego;

public class Grilla {
	protected LinkedList<Ente> grid[][];
	protected int nivelN;
	protected FactoryLevel generadorNivel;
	protected Nivel nivelActual;
	protected static int MAX_NIVEL = 5;
	protected Snake snake;
	protected Juego juego;
	
	public Grilla(Juego juego) {
		this.juego = juego;
		nivelActual = generadorNivel.generarNivel("niveles/nivel1.txt");
		nivelN = 1;
		grid = nivelActual.getGrilla();
	}
		
	public void setSnake(Snake snake) { 
		this.snake = snake;
	}
	
	public Ente getEnte(Position p) {
		return grid[p.getX()][p.getY()].getFirst();
	}
	
	public void cargarNivel() {
		nivelN++;
		if(nivelN++ <= MAX_NIVEL) {
			nivelActual = generadorNivel.generarNivel("niveles/nivel"+nivelN+".txt");
			grid = nivelActual.getGrilla();
		}
		else
			juego.gameOver();
	}
	
	public void spawnConsumible() {
		Ente e = nivelActual.getconsumible();
		if(e == null) {
			juego.aumentarNivel();
		}
		grid[e.getPosition().getX()][e.getPosition().getY()].addFirst(e);		
	}
	
	public boolean estaVacia(Position pos) {
		return grid[pos.getX()][pos.getY()] == null;
	}
}
