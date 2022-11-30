package grilla;

import java.util.LinkedList;

import Position.Position;
import criatura.Snake;
import ente.Ente;
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
			juego.gameOver(true);
	}
	
	public Ente consumir(Ente a) {
		Ente toReturn = grid[a.getPosition().getX()][a.getPosition().getY()].removeFirst();
		spawnConsumible();
		return toReturn;
	}
	
	public void spawnConsumible() {		
		Ente e = nivelActual.getconsumible();
		Position aux;
		if(e == null) {
			cargarNivel();
		}
		else {
			if(grid[e.getPosition().getX()][e.getPosition().getY()] == null)
				grid[e.getPosition().getX()][e.getPosition().getY()].addFirst(e);
			else {				
				if(grid[e.getPosition().getX()][e.getPosition().getY()].size() > 1) {
					aux = generarPosicionLibre();
					while(grid[aux.getX()][aux.getY()].size() > 1) {
						aux = generarPosicionLibre();
					}
					e.setPosition(aux);
					grid[e.getPosition().getX()][e.getPosition().getY()].addFirst(e);
				}
				else
					grid[e.getPosition().getX()][e.getPosition().getY()].addFirst(e);
			}
			elemento = e;
		}	
	}
	
	public boolean estaVacia(Position pos) {
		return grid[pos.getX()][pos.getY()] == null;
	}
	
	public Ente getConsumible() {
		juego.updateSpeed();
		return elemento;
	}

	public int getNivelN() {
		return nivelN;
	}
	
	private Position generarPosicionLibre() {
		Position toReturn;
		int x = (int) (Math.random()*59+1);
		int y = (int) (Math.random()*59+1);
		toReturn = new Position(x,y);
		return toReturn;
	}
}
