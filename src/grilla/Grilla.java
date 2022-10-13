package grilla;

import java.util.LinkedList;

import Position.Position;
import criatura.Snake;
import ente.Ente;
import factory.FactoryLevel;
import grilla.nivel.Nivel;

public class Grilla {
	protected LinkedList<Ente> grid[][];
	protected int nivelN;
	protected FactoryLevel generadorNivel;
	protected Nivel nivelActual;
	protected static int MAX_NIVEL = 5;
	protected Snake snake;
	
	public Grilla() {
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
		nivelActual = generadorNivel.generarNivel("niveles/nivel"+nivelN+".txt");
		grid = nivelActual.getGrilla();
	}
	
	private void spawnConsumible() {
		Ente e = nivelActual.getconsumible();
		grid[e.getPosition().getX()][e.getPosition().getY()].addFirst(e);
		
	}
	
	public boolean estaVacia(Position pos) {
		return grid[pos.getX()][pos.getY()].isEmpty();
	}
}
