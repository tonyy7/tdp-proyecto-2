package juego;

import java.util.LinkedList;

import Position.Position;
import criatura.Cuerpo;
import criatura.Snake;
import ente.Ente;
import ente.EnteGrafico;
import grilla.Grilla;
import gui.GUI;
import reloj.Reloj;
import splashScreen.Ranking;
import splashScreen.RankingGameOver;

public class Juego extends Thread{
	protected GUI ventana;
	protected int puntajeActual;
	protected Snake snake;
	protected Grilla grilla;
	protected Reloj timerVentana;
	protected Ranking ranking;
	protected boolean run;
	
	public Juego(Ranking puntaje) {
		try {
			puntajeActual = 0;			
			ranking = puntaje;		
			run = false;
			grilla = new Grilla(this); 
			timerVentana = new Reloj(this);			
			ventana = new GUI(this, timerVentana);			
			ventana.setNivel(grilla.getNivelN());
			ventana.generarGrilla(); 
			initSnake();
			grilla.setSnake(snake);
			generateConsumible();		
			timerVentana.start();
			this.start();
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			System.out.print("");
			if(!run) {
				timerVentana.pause();
			}
			else {
				timerVentana.iniciar();
			}
			while(this.run) {
				moverCriatura();
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
		this.run = true;
		if(posValida(pos)) {
			snake.setDireccion(pos);
		}
	}
	
	public void moverCriatura() {		
		Position pos = snake.moverSnake();
		if(grilla.getEnte(pos) != null) {
			grilla.getEnte(pos).accept(snake);
			ventana.eliminarConsumible();
			grilla.removerEnte(pos);
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
		if(!snake.hayCarry()) {
			grilla.removerEnte(cuerpoSnake.getLast().getPosition());
		}
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
	
	public void cambiarNivel() {
		pausa();		
		ventana.cambiarNivel();			
		initSnake();
		grilla.setSnake(snake);
		actualizarNivel();
		generateConsumible();
	}
	
	public int getPuntajeActaul() {
		return puntajeActual;
	}
	
	public void actualizarTiempo() {
		ventana.setTiempo();
	}
	
	public void pausa() {
		if(this.run)
			this.run = false;
		else
			this.run = true;
	}
	
	public void actualizarNivel() {
		ventana.setNivel(grilla.getNivelN());
	}	

	public EnteGrafico[][] getGrilla(){
		EnteGrafico grillaGrafica[][] = new EnteGrafico[60][60];
		LinkedList<Ente>[][] grillaLogica = grilla.getGrilla();
		for(int i=0; i<grillaLogica.length; i++) {
			for(int j=0; j<grillaLogica[0].length; j++) {
				if(grillaLogica[i][j].getFirst() != null) 
					grillaGrafica[i][j] = grillaLogica[i][j].getFirst().getGrafico();
				else 
					grillaGrafica[i][j] = null;							
			}
		}	
		return grillaGrafica;
	}
	
	public void gameOver(boolean b) {
		timerVentana.pause();
		run = false;
		ventana.close();
		new RankingGameOver(this, ranking, b);
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