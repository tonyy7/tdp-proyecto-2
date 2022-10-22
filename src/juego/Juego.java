package juego;

import java.util.LinkedList;

import Position.Position;
import criatura.Cuerpo;
import criatura.Snake;
import ente.Ente;
import ente.EnteGrafico;
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
		generateConsumible();
		
		timerVentana.start();
		this.start();
	}
	
	public void run() {
		while (true) {
			moverCriatura();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void initSnake() {
		String dir[] = {"arriba", "abajo", "izquierda", "derecha"};
		String direccionSnake = dir[(int)(Math.random()*3)];
		Position rand = new Position((int) ((Math.random() *53) + 4), (int) ((Math.random() *53) + 4));
		snake = new Snake(rand, generatePosition(direccionSnake), this);
		setSnakeGrilla();
	}
	
	private void generateConsumible() {
		grilla.spawnConsumible();
		if(grilla.getConsumible().getGrafico() != null)
			ventana.setConsumible(grilla.getConsumible().getGrafico());
	}

	public void updateCriatura(Position pos) {
		if(posValida(pos)) {
			snake.setDireccion(pos);
			moverCriatura();
		}
	}
	
	public void moverCriatura() {
		Position pos = snake.moverSnake();		
		if(grilla.getEnte(pos) != null) {
			grilla.getEnte(pos).accept(snake);
			ventana.eliminarConsumible();
			generateConsumible();
		}
		setSnakeGrilla();		
	}
	
	private void setSnakeGrilla() {
		Position aux;
		Position enteGrafico;
		EnteGrafico insert;
		LinkedList<EnteGrafico> cuerpoSnakeGrafico = new LinkedList<EnteGrafico>();
		LinkedList<Cuerpo> cuerpoSnake = snake.getCuerpo();
		grilla.setEnte(cuerpoSnake.getFirst().getPosition(), cuerpoSnake.getFirst());
		grilla.removerEnte(cuerpoSnake.getLast().getPosition());
		for(Cuerpo c : cuerpoSnake) {
			aux = c.getPosition();
			enteGrafico = new Position(aux.getX()*10, aux.getY()*10);
			insert = c.getGrafico();
			insert.setPosicion(enteGrafico);
			cuerpoSnakeGrafico.addLast(insert);		
		} 
		ventana.actualizarCriatura(cuerpoSnakeGrafico);
	}
	
	public void sumarPuntos(int p) {
		puntajeActual += p;
		ventana.setPuntaje(puntajeActual);
	}
	
	public void gameOver() {
		//puntajes.setPuntaje(puntajeActual);
		//timerVentana.stop(); NO USAR
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
	
	private Position generatePosition(String dir) {
		Position toReturn = null;
		switch (dir) {
		case "arriba" : {
			toReturn = new Position(0,-1);
			break;
		}
		case "abajo" : {
			toReturn = new Position(0,1);
			break;
		}
		case "izquierda" : {
			toReturn = new Position(-1,0);
			break;
		}
		case "derecha" : {
			toReturn = new Position(1,0);
			break;
		}
		}
		return toReturn;
	}
	
	private boolean posValida(Position pos) {
		Position dirSnake = snake.getDireccion();
		boolean toReturn;
		if(dirSnake.getX() != 0 && pos.getY() != 0) {
			toReturn = true;
		}
		else {
			if(dirSnake.getY() != 0 && pos.getX() != 0)
				toReturn = true;
			else
				toReturn = false;
		}
		return toReturn;
	}
}
