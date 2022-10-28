package launcher;

import java.io.*;
import juego.Juego;
import splashScreen.Ranking;

public class Launcher {
	
	private static String file = "ranking.r";

	public static void main(String[] args) {		
		//new SplashScreen(3,"assets/splashScreen/SplashScreen.png");
		
		Ranking r;
		try {
			r = leerPuntaje();
			new Juego(r);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Ranking leerPuntaje() throws Exception {
	    FileInputStream file = new FileInputStream(Launcher.file);
	    ObjectInputStream in = new ObjectInputStream(file);
	    Ranking book = (Ranking) in.readObject();
	    in.close();
	    file.close();
	    return book;
	}

}
