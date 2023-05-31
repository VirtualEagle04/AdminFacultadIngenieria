package co.edu.unbosque.util.exceptions;
/**
 * 
 * Excepcion para controlar que no haya seleccionado algo en las listas de pdf
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class PdfNoSeleccionadoException extends Exception {
	/**
	 * Muestra un mensaje de error predeterminado
	 */
	public PdfNoSeleccionadoException() {
		super("PDF no seleccionado");
	}
}
