package grilla;

import java.util.LinkedList;

import Position.Position;
import criatura.Snake;
import ente.Ente;
import factory.FactoryLevel;

public class Grilla {
	protected LinkedList<Ente> grid[][];
	protected int nivel;
	protected FactoryLevel generadorNivel;
	protected static int MAX_NIVEL;
	protected Snake snake;
	
	public Grilla() {
		grid = generadorNivel.generarNivel("niveles/nivel1.txt");
		nivel = 1;
	}
		
	public void setSnake(Snake snake) { 
		this.snake = snake;
	}
	
	public Ente getEnte(Position p) {
		return grid[p.getX()][p.getY()].getFirst();
	}
	
	public void cargarNivel() {
		nivel++;
		grid = generadorNivel.generarNivel("niveles/nivel"+nivel+".txt");
	}
	
	public boolean estaVacia(Position pos) {
		return grid[pos.getX()][pos.getY()].isEmpty();
	}
}
