package ente.powerUp;

import ente.Ente;
import ente.EnteGrafico;

public abstract class PowerUp extends Ente {
	protected int puntaje;
	protected EnteGrafico grafico;
	private static final int bloque=3;
	protected String urlCabeza;
	protected String urlCuerpo;
	
	public String getUrlCuerpo() {
		return urlCuerpo;
	}
	public void setUrlCuerpo(String urlCuerpo) {
		this.urlCuerpo = urlCuerpo;
	}
	public String getUrlCabeza() {
		return urlCabeza;
	}
	public void setUrlCabeza(String urlCabeza) {
		this.urlCabeza = urlCabeza;
	}
	public int getPuntaje() {
		return this.puntaje;
	}
	public EnteGrafico getEnteGrafico() {
		return this.grafico;
	}
	public int getBloque() {return bloque;}
	
}
