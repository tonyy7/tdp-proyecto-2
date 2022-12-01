package sonido;

public class Sonidos {
	private boolean playOn;
	private SClip move_song;
	private SClip consumible_song;
	private SClip pause_song;
	private SClip winner_song;
	private SClip gameOver_song;
	
	public Sonidos() {
		playOn = true;
		move_song = new SClip("assets/sonido/move_song.wav");
		consumible_song = new SClip("assets/sonido/consumible_song.wav");
		pause_song = new SClip("assets/sonido/pause_song.wav");
		winner_song = new SClip("assets/sonido/winner_song.wav");
		gameOver_song = new SClip("assets/sonido/gameOver_song.wav");
	}
	
	public void playOn() {
		playOn = true;
	}
	
	public void playOff() {
		playOn = false;
	}
	
	public boolean playPausaSong() {
		if(playOn)
			playOn = false;
		else
			playOn = true;
		return playOn;
	}
	
	public void playMoveSong() {
		if(playOn)
			move_song.play();
	}
	
	public void playConsumibleSong() {
		if(playOn)
			consumible_song.play();
	}
	
	public void playPauseSong() {
		if(playOn)
			pause_song.play();
	}
	
	public void playWinnerSong() {
		if(playOn)
			winner_song.play();
	}
	
	public void playGameOverSong() {
		if(playOn)
			gameOver_song.play();
	}
}
