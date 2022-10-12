package launcher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import juego.Juego;
import puntaje.Puntaje;
import splashScreen.SplashScreen;

public class Launcher {
	
	private static String file = "puntaje/puntaje.p";

	public static void main(String[] args) {
		
		//new SplashScreen(3);
		
		
		
		
		
		/* PARA GENERAR PUNTAJE
		try {
			Launcher.guardarPuntaje(puntajeHistorico);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		*/
		
		
		/* PARA LEER PUNTAJE
		try {
			puntajeHistorico = leerPuntaje();
			juego = new Juego(puntajeHistorico);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		*/
		
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
