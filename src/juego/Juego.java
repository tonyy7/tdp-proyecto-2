package juego;

import java.util.LinkedList;
import java.util.List;

import criatura.Snake;
import gui.GUI;
import puntaje.Puntaje;

public class Juego {
	protected GUI ventana;
	protected int maxPuntaje; //seteado por nivel
	protected int puntajeActual; //puntaje actual del juego
	protected Snake snake;
	
	protected Puntaje puntajes;
	
	public Juego(Puntaje puntajes) {
		this.puntajes = puntajes;
		snake = new Snake();
	}
	
	public void actualizarCriatura() {
		
	}
	
	public void moverCriatura(int x, int y) {
		
	}
	
	public void sumarPuntos() {
		
	}
	
	public void gameOver() {
		puntajes.setPuntaje(puntajeActual);
	}
	
	public void setPuntaje(Puntaje p) {
		
	}
	
	public void aumentarNivel() {
		
	}
	
	
}
