package puntaje;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Puntaje {
	protected String nombre;
	protected int puntos;
	protected List<Integer> puntajeHistorico;
	
	public Puntaje( ) {
		puntajeHistorico = new LinkedList<Integer>();
	}
	
	public void setPuntaje(int puntaje) {
		puntajeHistorico.add(puntaje);
	}
	
	public Iterator<Integer> getTopPuntajes() {
		puntajeHistorico.sort(Comparator.naturalOrder());
		return null;

	}
}
