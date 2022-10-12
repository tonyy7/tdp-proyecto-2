package reloj;

import java.util.TimerTask;

public class Reloj extends TimerTask{
	protected int time;
	
	public Reloj() {
		time = 0;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				time++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public String getTime() {
		int m = time/60;
		int s = time%60; 
		
		return (String.format("%02d", m)+":"+String.format("%02d", s));
	}
	
}
