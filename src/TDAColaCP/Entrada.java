package TDAColaCP;

import java.io.Serializable;

/**
 * Clase Entrada, implementa interfaz Entry.
 * @author Franco Leon y Agustin Vera.
 */
public class Entrada<K,V> implements Entry<K,V>, Serializable{
	private K clave;
	private V valor;
	
	/**
	 * Crea una entrada con un par K,V (clave, valor).
	 * @param clave K.
	 * @param valor V.
	 */
	public Entrada(K clave, V valor) {
		this.clave= clave; this.valor= valor;
	}
	
	/**
	 * Analiza una clave K de una Entrada.
	 * @return clave K.
	 */
	public K getKey() {
		return clave;
	}
	
	/**
	 * Analiza un valor V de una Entrada.
	 * @return valor V. 
	 */
	public V getValue() {
		return valor;
	}
	
	/**
	 * Asigna una clave K a una Entrada.
	 * @param clave K.
	 */
	public void setKey(K clave) {
		this.clave = clave;
	}

	/**
	 * Asigna un valor V a una Entrada.
	 * @param valor V.
	 */
	public void setValue(V valor) {
		this.valor= valor;
	}
	
	/**
	 * Retorna  un String con el par clave, valor.
	 * @return String par (K,V). 
	 */
	public String toString() {
		return "("+ getKey() + "," + getValue() +")";
	}
}
