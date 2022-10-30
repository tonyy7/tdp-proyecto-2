package ranking;

import java.io.Serializable;

import TDAColaCP.ColaCP;
import TDAColaCP.Comparador;
import TDAColaCP.InvalidKeyException;

@SuppressWarnings("serial")
public class Ranking implements Serializable {
	private ColaCP<Integer,String> puntaje;
	
	public Ranking() {
		puntaje = new ColaCP<Integer, String>(10, new Comparador<Integer>());
	}
	
	public void insert(int k, String v) throws InvalidKeyException {
		puntaje.insert(k,v);
	}
	
	public ColaCP<Integer, String> cola() {
		return puntaje;
	}
	
	public void setCola(ColaCP<Integer, String> cola) {
		puntaje = cola;
	}
}
