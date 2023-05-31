package co.edu.unbosque.util.exceptions;

/**
 * 
 * // * Excepcion para controlar el ingreso de un documento invalido
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class DocumentoMuyLargoException extends Exception {
	/**
	 * Muestra un mensaje de error predeterminado
	 */
	public DocumentoMuyLargoException() {

		super("El documento ingresado debe contener 10 digitos.");

	}
}
