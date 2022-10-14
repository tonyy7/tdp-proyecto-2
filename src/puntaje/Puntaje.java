package puntaje;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Puntaje implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected List<Integer> puntajeHistorico;
	
	public Puntaje( ) {
		puntajeHistorico = new LinkedList<Integer>();
	}
	
	/*
	 *	Agrega el puntaje a la lista 
	 */
	public void setPuntaje(int puntaje) {
		puntajeHistorico.add(puntaje);
	}
	
	/*
	 * Retorna una lista iterable ordenada de mayor a menor
	 * con un top 10 de puntajes.
	 * @return lista iterable de enteros
	 */
	public Iterable<Integer> getTopPuntajes() {
		ordenarPuntajes();
		return puntajeHistorico;
	}
	
	/*
	 * Ordena los puntajes en forma descendente y quita los que no entran en el top 10.
	 */
	private void ordenarPuntajes() {
		List<Integer> aux = new LinkedList<Integer>();
		int estado = 0;
		Collections.sort(puntajeHistorico, Collections.reverseOrder());
		for(int a : puntajeHistorico) {
			if(estado < 5) {
				aux.add(a);
				estado++;
			}
			else
				break;
		}
		puntajeHistorico = aux;
	}
	
	public String getTopString() {
		String toReturn = "";
		for(int a: puntajeHistorico) {
			toReturn += a+"\n";
		}
		return toReturn;
	}

}
