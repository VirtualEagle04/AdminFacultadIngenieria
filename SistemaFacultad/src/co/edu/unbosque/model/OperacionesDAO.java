package co.edu.unbosque.model;

/**
 * 
 * Interfaz encargada de instanciar los metodos para las clases que
 * posteriormente la implemente
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public interface OperacionesDAO {

	/**
	 * Metodo encargado de crear un objeto necesario y añadirlo a su respectivo
	 * ArrayList
	 * 
	 * @param obj Object que almacenara un objeto de la clase que se necesite
	 */
	public void crear(Object obj);

	/**
	 * 
	 * Metodo encargado de eliminar un objeto en especifico de un ArrayList
	 * 
	 * @param index Int utilizado para buscar el objeto a eliminar
	 * @return un booleano que verificara si se elimino correcatamente
	 */
	public boolean eliminar(int index);

	/**
	 * Metodo encargado de actualizar un objeto en especifico del ArrayList
	 * 
	 * @param index Int utilizado para buscar el objeto a actualizar
	 * @param obj   Objeto a actualizar
	 * @return booleano que verificara si se actualizo correctamente
	 */
	public boolean actualizar(int index, Object obj);

	/**
	 * Metodo encargado de mostrar todo en el arraylist
	 * 
	 * @return String que contiene el toString de cada elemento de el ArrayList
	 */
	public String mostrarTodo();

}
