package juego;

import java.util.LinkedList;

import Position.Position;
import criatura.Snake;
import ente.Ente;
import grilla.Grilla;
import gui.GUI;
import puntaje.Puntaje;
import reloj.GameTimer;
import reloj.Reloj;
import splashScreen.SplashScreen;
import splashScreen.SplashScreenGameOver;

public class Juego extends Thread{
	protected GUI ventana;
	protected int puntajeActual; //puntaje actual del juego
	protected Snake snake;
	protected Grilla grilla;
	protected Reloj timerVentana;
	protected GameTimer gameTimer;
	
	protected Puntaje puntajes;
	
	public Juego(Puntaje puntajes) {
		puntajeActual = 0;
		this.puntajes = puntajes;
		grilla = new Grilla(this);
		timerVentana = new Reloj(this);
		
		
		ventana = new GUI(this, timerVentana);
		ventana.generarGrilla();
		
		
		initSnake();
		grilla.setSnake(snake);
		
		
		gameTimer = new GameTimer(snake);
		timerVentana.start();
		gameTimer.start();
		this.start();
	}
	
	public void run() {
		while (true) {
			actualizarCriatura();
			getConsumible();
			getElementoConsumido();			
		}
	}
	
	private void initSnake() {
		String dir[] = {"arriba", "abajo", "izquierda", "derecha"};
		String direccionSnake = dir[(int)(Math.random()*3)];
		Position rand = new Position((int) ((Math.random() *13) + 4), (int) ((Math.random() *13) + 4));
		snake = new Snake(rand, direccionSnake, this, grilla);
	}
	
	public void actualizarCriatura() {
		ventana.actualizarVentana(snake.getCuerpo());
	}
	
	public void moverCriatura(int x, int y) {
		Position posSnake = snake.getDireccionP();
		//Si x no es cero, snake corre verticalmente
		if(posSnake.getX() == 0) {
			if( x == 1)
				snake.setDireccion("derecha");
			else
				snake.setDireccion("izquierda");
		}
		else {
			if(y == 1) {
				snake.setDireccion("abajo");
			}
			else
				snake.setDireccion("arriba");
		}
	}
	
	public void sumarPuntos(int p) {
		puntajeActual += p;
	}
	
	public void gameOver() {
		puntajes.setPuntaje(puntajeActual);
		timerVentana.stop();
		cerrar();
	}
	
	public int getPuntajeActaul() {
		return puntajeActual;
	}
	
	public void actualizarTiempo() {
		ventana.setTiempo();
	}
	
	public void cerrar() {
		new SplashScreen(4,"assets/gameover/gameover.png");
		System.exit(0);
	}
	
	public LinkedList<Ente>[][] getGrilla(){
		return grilla.getGrilla();
	}
	
	public void getConsumible() {
		if(grilla.getConsumible() != null)
			ventana.setEnte(grilla.getConsumible());
	}
	
	public void getElementoConsumido() {
		if(grilla.getElementoConsumido() != null)
			ventana.removerEnte(grilla.getElementoConsumido());
	}
}
