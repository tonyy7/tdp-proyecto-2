package launcher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gui.GUI;
import juego.Juego;
import puntaje.Puntaje;
import reloj.Reloj;
import splashScreen.SplashScreen;

public class Launcher {
	
	private static String file = "puntaje/puntaje.p";

	public static void main(String[] args) {		
		//new SplashScreen(3,"assets/splashScreen/SplashScreen.png");
		
		new Juego(null);
		//juego.start();
		
	}
	
	public static void guardarPuntaje(Puntaje p) throws Exception {
		FileOutputStream file = new FileOutputStream(Launcher.file);
	    ObjectOutputStream out = new ObjectOutputStream(file);
	    out.writeObject(p);
	    out.close();
	    file.close();
	}
	
	public static Puntaje leerPuntaje() throws Exception {
	    FileInputStream file = new FileInputStream(Launcher.file);
	    ObjectInputStream in = new ObjectInputStream(file);
	    Puntaje book = (Puntaje) in.readObject();
	    in.close();
	    file.close();
	    return book;
	}

}
