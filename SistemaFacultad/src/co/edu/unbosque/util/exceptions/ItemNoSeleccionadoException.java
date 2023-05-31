package co.edu.unbosque.util.exceptions;

/**
 * 
 * Excepcion para controlar que no haya seleccionado algo en las listas
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class ItemNoSeleccionadoException extends Exception {
	/**
	 * Muestra un mensaje de error predeterminado
	 */
	public ItemNoSeleccionadoException() {
		super("Item no seleccionado");
	}

}
