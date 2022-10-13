package juego;

import java.util.LinkedList;
import java.util.List;

import Position.Position;
import criatura.Snake;
import grilla.Grilla;
import gui.GUI;
import puntaje.Puntaje;

public class Juego {
	protected GUI ventana;
	protected int maxPuntaje; //seteado por nivel
	protected int puntajeActual; //puntaje actual del juego
	protected Snake snake;
	protected Grilla grilla;
	
	protected Puntaje puntajes;
	
	public Juego(Puntaje puntajes) {
		this.puntajes = puntajes;
		grilla = new Grilla();
		initSnake();
		grilla.setSnake(snake);
	}
	
	private void initSnake() {
		String dir[] = {"arriba", "abajo", "izquierda", "derecha"};
		String direccionSnake = dir[(int)(Math.random()*3)];
		Position rand = new Position((int)(Math.random()*20), (int)(Math.random()*20));
		while(grilla.getEnte(rand) != null) {
			rand = new Position((int)(Math.random()*20), (int)(Math.random()*20));
		}
		snake = new Snake(rand, direccionSnake, this, grilla);
	}
	
	public void actualizarCriatura() {
		
	}
	
	public void moverCriatura(int x, int y) {
		
	}
	
	public void sumarPuntos() {
		
	}
	
	public void gameOver() {
		puntajes.setPuntaje(puntajeActual);
		cerrar();
	}
	
	public void setPuntaje(Puntaje p) {
		
	}
	
	public void aumentarNivel() {
		
	}
	
	public void cerrar() {
		System.exit(0);
	}
	
	
}
