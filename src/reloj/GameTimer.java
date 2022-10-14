package reloj;

import criatura.Snake;

public class GameTimer extends Thread {
	protected Snake snake;
	private static int FRAME = 150;
	
	public GameTimer(Snake snake) {
		this.snake = snake;
	}
	public void run() {
		try {			
			while(true) {
				Thread.sleep(FRAME);
				snake.moverSnake();
			}			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}
