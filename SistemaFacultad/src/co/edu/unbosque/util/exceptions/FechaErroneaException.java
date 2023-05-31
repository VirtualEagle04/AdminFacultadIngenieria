package co.edu.unbosque.util.exceptions;

/**
 * 
 * Excepcion para controlar el ingreso de una fecha erronea
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class FechaErroneaException extends Exception {
	/**
	 * Muestra un mensaje de error predeterminado
	 */
	public FechaErroneaException() {
		super("La fecha ingresada no es valida");
	}

}
