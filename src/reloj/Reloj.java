package reloj;

import gui.GUI;
import juego.Juego;

public class Reloj extends Thread {
	protected int time;
	protected GUI gui;
	protected Juego juego;
	
	public Reloj(Juego juego) {
		time = 0;
		this.juego = juego;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				time++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			juego.actualizarTiempo();
		}
	}
	
	public String getTime() {
		int m = time/60;
		int s = time%60; 	
		return (String.format("%02d", m)+":"+String.format("%02d", s));
	}
	
	public int getSegundos() {
		return time;
	}
}
