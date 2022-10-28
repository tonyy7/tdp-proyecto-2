package TDAColaCP;

/**
 * Excepcion de clave K incorrecta.
 * @author Franco Leon y Agustin Vera.
 *
 */
public class InvalidKeyException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidKeyException(String msg) {
		super(msg);
	}

}
