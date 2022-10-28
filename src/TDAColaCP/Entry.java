package TDAColaCP;

/**
 * Interfaz Entry.
 * @author Franco Leon y Agustin Vera.
 */
public interface Entry<K,V> {
	
	/**
	 * Retorna la clave de la Entry.
	 * @return K clave.
	 */
    public K getKey();
    
    /**
     * Retorna el valor de Entry.
     * @return V valor.
     */
    public V getValue();
}
