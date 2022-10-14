package grilla;

import java.util.LinkedList;

import Position.Position;
import criatura.Snake;
import ente.Ente;
import factory.FactoryLevel;
import factory.GeneradorNivel;
import grilla.nivel.Nivel;
import juego.Juego;

public class Grilla {
	protected LinkedList<Ente> grid[][];
	protected int nivelN;
	protected GeneradorNivel generadorNivel;
	protected Nivel nivelActual;
	protected static int MAX_NIVEL = 5;
	protected Snake snake;
	protected Juego juego;
	
	public Grilla(Juego juego) {
		this.juego = juego;
<<<<<<< HEAD
		//nivelActual = generadorNivel.generarNivel("niveles/nivel1.txt");
=======
		generadorNivel = new GeneradorNivel();
		nivelActual = generadorNivel.generarNivel("nivel1.txt");
>>>>>>> branch 'master' of https://github.com/tonyy7/tdp-proyecto-2
		nivelN = 1;
		grid = nivelActual.getGrilla();
		spawnConsumible();
	}
	
	public void setSnake(Snake snake) { 
		this.snake = snake;
	}
	
	public LinkedList<Ente>[][] getGrilla(){
		return grid;
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
	
	public void consumir(Ente a) {
		grid[a.getPosition().getX()][a.getPosition().getY()].removeFirst();
		spawnConsumible();
	}
	
	private void spawnConsumible() {
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
