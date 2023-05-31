package co.edu.unbosque.util.exceptions;

/**
 * 
 * Excepcion para controlar el ingreso de caracteres invalidos
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class CaracteresInvalidosException extends Exception {
	/**
	 * Muestra un mensaje de error predeterminado
	 */
	public CaracteresInvalidosException() {

		super("Ha ingresado caracteres invalidos en la siguiente casilla: ");

	}
}
