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
	protected Ente elemento;
	
	
	public Grilla(Juego juego) {
		this.juego = juego;
		generadorNivel = new GeneradorNivel();
		nivelN = 1;
		nivelActual = generadorNivel.generarNivel("assets/niveles/nivel"+nivelN+".txt");
		grid = nivelActual.getGrilla();		
	}
	
	public void setSnake(Snake snake) { 
		this.snake = snake;
	}
	
	public void removerEnte(Position p) {
		if(p.getX()<60 && p.getX()>=0 && p.getY()<60 && p.getY()>=0) {
			if(!grid[p.getX()][p.getY()].isEmpty())
				grid[p.getX()][p.getY()].removeFirst();
		}
	}
	
	public void setEnte(Position pos, Ente e) {
		if(pos.getX()<60 && pos.getX()>=0 && pos.getY()<60 && pos.getY()>=0) {
			grid[pos.getX()][pos.getY()].addFirst(e);
		}
	}
	
	public LinkedList<Ente>[][] getGrilla(){
		return grid;
	}
	
	public Ente getEnte(Position p) {
		if(p.getX()>59 || p.getX()<0 || p.getY()>59 || p.getY()<0 || grid[p.getX()][p.getY()].isEmpty())
            return null;
        else
            return grid[p.getX()][p.getY()].getFirst();
	}
	
	public void cargarNivel() {
		nivelN++;
		if(nivelN <= MAX_NIVEL) {
			generadorNivel.iniciarNivel();
			nivelActual = generadorNivel.generarNivel("assets/niveles/nivel"+nivelN+".txt");
			grid = nivelActual.getGrilla();		
			juego.cambiarNivel();
		}
		else
			juego.gameOver();
	}
	
	public Ente consumir(Ente a) {
		Ente toReturn = grid[a.getPosition().getX()][a.getPosition().getY()].removeFirst();
		spawnConsumible();
		return toReturn;
	}
	
	public void spawnConsumible() {		
		Ente e = nivelActual.getconsumible();
		if(e == null) {
			cargarNivel();
		}
		else {
			if(grid[e.getPosition().getX()][e.getPosition().getY()] == null)
				grid[e.getPosition().getX()][e.getPosition().getY()].addFirst(e);
			else 
				grid[e.getPosition().getX()][e.getPosition().getY()].add(0, e);
			elemento = e;
		}	
	}
	
	public boolean estaVacia(Position pos) {
		return grid[pos.getX()][pos.getY()] == null;
	}
	
	public Ente getConsumible() {
		return elemento;
	}

	public int getNivelN() {
		return nivelN;
	}
}
