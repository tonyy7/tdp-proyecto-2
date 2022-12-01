package juego;

import java.util.LinkedList;

import Position.Position;
import criatura.Cuerpo;
import criatura.Snake;
import ente.Ente;
import ente.EnteGrafico;
import grilla.Grilla;
import gui.GUI;
import ranking.*;
import reloj.GameTimer;
import reloj.Reloj;
import sonido.Sonidos;

public class Juego {
	protected GUI ventana;
	protected Snake snake;
	protected Grilla grilla;
	protected Reloj timerVentana;
	protected Ranking ranking;
	protected Sonidos sonidos_juego;
	protected GameTimer gameTimer;
	protected boolean controlTeclado;
	protected int puntajeActual;
	
	public Juego(Ranking puntaje) {
		puntajeActual = 0;			
		ranking = puntaje;		
		sonidos_juego = new Sonidos();
		controlTeclado = true;
		grilla = new Grilla(this); 
		timerVentana = new Reloj(this);
		gameTimer = new GameTimer(this);
		ventana = new GUI(this, timerVentana);			
		ventana.setNivel(grilla.getNivelN());
		ventana.generarGrilla(); 
		initSnake();
		grilla.setSnake(snake);
		generateConsumible();		
		timerVentana.start();
		gameTimer.start();		
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
		if(grilla.getConsumible() != null && ventana.consumiblesEmpty()) {
			ventana.setConsumible(grilla.getConsumible().getGrafico());					
		}
	}

	public void updateCriatura(Position pos) {
		gameTimer.play();
		if(posValida(pos) && controlTeclado) {
			snake.setDireccion(pos);
			sonidos_juego.playMoveSong();
			controlTeclado = false;
		}
	}
	
	public void moverCriatura() {		
		Position pos = snake.moverSnake();
		if(grilla.getEnte(pos) != null) {			
			grilla.getEnte(pos).accept(snake);
			ventana.eliminarConsumible();
			sonidos_juego.playConsumibleSong();
			grilla.removerEnte(pos);			
			generateConsumible();
		}
		controlTeclado = true;
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
		gameTimer.pause();
		gameTimer.setDefaultSpeed();
		ventana.cambiarNivel();			
		initSnake();		
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
		sonidos_juego.playPauseSong();
		gameTimer.playPause();
	}
	
	public void pauseTimerVentana() {
		timerVentana.pause();
	}
	
	public boolean playPauseMusic() {
		return sonidos_juego.playPausaSong();
	}
	
	public void playTimerVentana() {
		timerVentana.iniciar();
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
	
	public void updateSpeed() {
		gameTimer.updateSpeed();
	}
	
	public void gameOver(boolean estado) {
		if(estado) 
			sonidos_juego.playWinnerSong();
		else
			sonidos_juego.playGameOverSong();
		timerVentana.pause();
		gameTimer.pause();
		ventana.close();
		new RankingGameOver(this, ranking, estado);
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