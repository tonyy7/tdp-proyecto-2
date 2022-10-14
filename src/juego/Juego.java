package juego;

import Position.Position;
import criatura.Snake;
import grilla.Grilla;
import gui.GUI;
import puntaje.Puntaje;
import reloj.GameTimer;
import reloj.Reloj;
import splashScreen.SplashScreen;

public class Juego extends Thread{
	protected GUI ventana;
	protected int maxPuntaje; //seteado por nivel
	protected int puntajeActual; //puntaje actual del juego
	protected Snake snake;
	protected Grilla grilla;
	protected Reloj timer;
	protected GameTimer gameTimer;
	
	protected Puntaje puntajes;
	
	public Juego(Puntaje puntajes) {
		/*this.puntajes = puntajes;
		grilla = new Grilla(this);
		initSnake();
		grilla.setSnake(snake);
		*/
		timer = new Reloj(this);
		gameTimer = new GameTimer(snake);
		ventana = new GUI(this, timer);
		//Inicia reloj que controla el timepo en ventana
		timer.start();
		//Inicia reloj que controla el movimiento automatico de snake
		gameTimer.start();
	}
	
	public void run() {
		while (true) {
			actualizarCriatura();
		}
	}
	
	private void initSnake() {
		String dir[] = {"arriba", "abajo", "izquierda", "derecha"};
		String direccionSnake = dir[(int)(Math.random()*3)];
		Position rand = new Position((int) ((Math.random() *13) + 4), (int) ((Math.random() *13) + 4));
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
