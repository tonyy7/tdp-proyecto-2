package reloj;

import juego.Juego;

public class Reloj extends Thread {
	protected int time;
	protected Juego juego;
	protected boolean control;
	
	public Reloj(Juego juego) {
		time = 0;
		this.juego = juego;
		control = false;
	}
	
	public void run() {
		while(true) {
			System.out.print("");
			while(control) {
				try {
					Thread.sleep(1000);
					time++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				juego.actualizarTiempo();
			}
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
	
	public void pause() {
		control = false;
	}
	
	public void iniciar() {
		control = true;
	}
}
