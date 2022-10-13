package gui;

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
			juego.moverCriatura(0, -1);
			break;
		}
		case "derecha":
		{
			juego.moverCriatura(0, 1);
			break;
		}
		case "arriba":
		{
			juego.moverCriatura(-1, 0);
			break;
		}
		case "abajo":
		{
			juego.moverCriatura(1, 0);
			break;
		}
		case "salir":
		{
			juego.cerrar();
			break;
		}
		}
	}
}
