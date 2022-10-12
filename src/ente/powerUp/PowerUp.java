package ente.powerUp;

import ente.Ente;
import ente.EnteGrafico;

public abstract class PowerUp extends Ente {
	protected int puntaje;
	protected EnteGrafico grafico;
	public int GetPuntaje() {
		return this.puntaje;
	}
	public EnteGrafico getEnteGrafico() {
		return this.grafico;
	}
}