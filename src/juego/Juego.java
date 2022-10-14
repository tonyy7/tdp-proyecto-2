package juego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import Position.Position;
import criatura.Snake;
import grilla.Grilla;
import gui.GUI;
import puntaje.Puntaje;
import reloj.Reloj;

public class Juego extends Thread{
	protected GUI ventana;
	protected int maxPuntaje; //seteado por nivel
	protected int puntajeActual; //puntaje actual del juego
	protected Snake snake;
	protected Grilla grilla;
	protected Reloj timer;
	
	protected Puntaje puntajes;
	
	public Juego(Puntaje puntajes) {
<<<<<<< HEAD
		/*this.puntajes = puntajes;
		grilla = new Grilla(this);
		initSnake();
		grilla.setSnake(snake);
		*/
		ventana= new GUI(this);
		
		this.start();
=======
		//this.puntajes = puntajes;
		//grilla = new Grilla(this);
		//initSnake();
		//grilla.setSnake(snake);
		timer = new Reloj(this);
		ventana = new GUI(this, timer);
		timer.start();
>>>>>>> branch 'master' of https://github.com/tonyy7/tdp-proyecto-2
	}
	
	public void run() {
		while (true) {
			actualizarCriatura();
		}
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
		Position posSnake = snake.getDireccionP();
		//Si x no es cero, snake corre verticalmente
		if(posSnake.getX() == 0) {
			if( x == 1)
				snake.setDireccion("derecha");
			else
				snake.setDireccion("izquierda");
			snake.moverSnake();
		}
		else {
			if(y == 1) {
				snake.setDireccion("abajo");
			}
			else
				snake.setDireccion("arriba");
			snake.moverSnake();
		}
	}
	
	public void sumarPuntos(int p) {
		puntajeActual += p;
	}
	
	public void gameOver() {
		puntajes.setPuntaje(puntajeActual);
		cerrar();
	}
	
	public void setPuntaje(Puntaje p) {
		
	}
	
	public void aumentarNivel() {
		
	}
	
	public int getPuntajeActaul() {
		return puntajeActual;
	}
	
	public void actualizarTiempo() {
		ventana.setTiempo();
	}
	
	public void cerrar() {
		System.exit(0);
	}
	
	
	
}
