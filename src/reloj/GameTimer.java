package reloj;

import juego.Juego;

public class GameTimer extends Thread {
	private Juego juego;
	private int DEFAULT_SPEED = 150;
	private int DEFAULT_MAX_SPEED = 30;
	private boolean run;
	private int speed;
	
	
	public GameTimer(Juego juego) {
		this.juego = juego;
		speed = DEFAULT_SPEED;
		run = false;
	}
	
	public void run() {
		while (true) {
			System.out.print("");
			if(!run) {
				juego.pauseTimerVentana();
			}
			else {
				juego.playTimerVentana();
			}
			if(run) {
				juego.moverCriatura();
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void play() {
		run = true;
	}
	
	public void pause() {
		run = false;
	}
	
	public void playPause() {		
		if(this.run)
			this.run = false;
		else
			this.run = true;
	}
	
	public void updateSpeed() {
		if(speed > DEFAULT_MAX_SPEED) {
			speed -= 2;
		}
	}
	
	public void setDefaultSpeed() {
		speed = DEFAULT_SPEED;
	}
}
