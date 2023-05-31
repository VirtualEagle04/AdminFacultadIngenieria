package co.edu.unbosque.util.exceptions;

/**
 * 
 * Excepcion para controlar el ingreso de los apellidos incompletos
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class ApellidosIncompletosException extends Exception {
/**
 * Muestra un mensaje de error predeterminado
 */
	public ApellidosIncompletosException() {

		super("Por favor ingrese ambos apellidos.");

	}

}
