package ente.alimento;

import ente.Ente;

public abstract class Alimento extends Ente {
	protected final int maxPuntaje=100;
	protected int bloque;
	protected int puntaje;
	
	
	public int getBloque() {
		return bloque;
	}
	public void setBloque(int bloque) {
		this.bloque = bloque;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public int getPuntaje() {
		return  this.puntaje;}

}
