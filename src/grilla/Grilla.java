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
		generadorNivel = new GeneradorNivel();
		nivelN = 1;
		nivelActual = generadorNivel.generarNivel("assets/niveles/nivel"+nivelN+".txt");
		grid = nivelActual.getGrilla();
		//spawnConsumible();
	}
	
	public void setSnake(Snake snake) { 
		this.snake = snake;
	}
	
	public void setEnte(Position pos, Ente e) {
		grid[pos.getX()][pos.getY()].addFirst(e);
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
			nivelActual = generadorNivel.generarNivel("assets/niveles/nivel"+nivelN+".txt");
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
			cargarNivel();
		}
		else {
			if(grid[e.getPosition().getX()][e.getPosition().getY()] == null)
				grid[e.getPosition().getX()][e.getPosition().getY()].addFirst(e);
			else 
				grid[e.getPosition().getX()][e.getPosition().getY()].add(2, e);						
		}
	}
	
	public boolean estaVacia(Position pos) {
		return grid[pos.getX()][pos.getY()] == null;
	}
}
