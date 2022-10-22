package gui;

import Position.Position;
import juego.Juego;

public class ControlTeclado {
	protected Juego juego;
	public ControlTeclado(Juego juego) {
		this.juego = juego;
	}
	
	public void generadorPosition(String dir) {
		switch (dir) {
		case "izquierda": 
		{
			juego.updateCriatura(new Position(-1,0));
			break;
		}
		case "derecha":
		{
			juego.updateCriatura(new Position(1,0));
			break;
		}
		case "arriba":
		{
			juego.updateCriatura(new Position(0,-1));
			break;
		}
		case "abajo":
		{
			juego.updateCriatura(new Position(0,1));
			break;
		}
		case "espacio":
		{
			juego.moverCriatura();
			break;
		}
		case "salir":
		{
			//juego.cerrar();
			break;
		}
		}
	}
}
