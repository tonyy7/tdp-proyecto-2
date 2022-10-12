package juego;

import java.util.LinkedList;
import java.util.List;

import gui.GUI;
import puntaje.Puntaje;

public class Juego {
	protected GUI ventana;
	protected int maxPuntaje; //seteado por nivel
	protected int puntajeActual; //puntaje actual del juego	
	
	protected Puntaje puntajes;
	
	public Juego(Puntaje puntajes) {
		this.puntajes = puntajes;
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
