package TDAColaCP;

/**
 * Excepcion de cola con prioridad vacia.
 * @author Franco Leon y Agustin Vera.
 */
public class EmptyPriorityQueueException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyPriorityQueueException(String s) {
    	super(s);
    }
}
