package TDAColaCP;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Comparador<E> implements java.util.Comparator<E>, Serializable {
	
	/**
	 * Compara dos elementos del mismo tipo.
	 * @return si o1 es menor a o2 == -1, si o1 es igual a o2 == 0, si o1 es mayor a o2 == 1.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int compare(E o1, E o2) {
		return ((Comparable<E>) o2).compareTo(o1);
	}
}