package reloj;

import criatura.Snake;

public class GameTimer extends Thread {
	protected Snake snake;
	private static int FRAME = 95;
	
	public GameTimer(Snake snake) {
		this.snake = snake;
	}
	public void run() {
		while(true) {
			try {
				Thread.sleep(FRAME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			snake.moverSnake();
		}
	}
}
