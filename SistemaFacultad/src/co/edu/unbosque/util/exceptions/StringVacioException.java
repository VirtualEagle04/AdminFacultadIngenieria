package co.edu.unbosque.util.exceptions;

/**
 * 
 * Excepcion para controlar el no ingreso de datos
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class StringVacioException extends Exception {
	/**
	 * Muestra un mensaje de error predeterminado
	 */
	public StringVacioException() {

		super("La siguiente casilla no contiene informacion: ");

	}

}
