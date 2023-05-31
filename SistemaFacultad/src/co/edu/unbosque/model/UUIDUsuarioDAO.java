package co.edu.unbosque.model;

import java.util.ArrayList;

import co.edu.unbosque.model.persistance.FileHandler;

/**
 * Clase Data Acces Object de los identificadores unicos universales
 * 
 * @param lista ArrayList que almacena todos los objetos creados de la clase
 *              UUIDUsuarioDTO
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class UUIDUsuarioDAO implements OperacionesDAO {

	private ArrayList<UUIDUsuarioDTO> lista;

	public UUIDUsuarioDAO() {
		lista = (ArrayList<UUIDUsuarioDTO>) FileHandler.leerSerializado("UUIDusuario.txt");
		if (lista == null) {
			lista = new ArrayList<UUIDUsuarioDTO>();
		}
	}

	public ArrayList<UUIDUsuarioDTO> getLista() {
		return lista;
	}

	public void setLista(ArrayList<UUIDUsuarioDTO> lista) {
		this.lista = lista;
	}

	// ARCHIVOS
	/**
	 * Metodo encargado de actualizar el serializado reespectivo de la clase
	 */
	public void escribirArchivo() {
		FileHandler.escribirSerializado("UUIDusuario.txt", lista);
	}

	// CRUD

	@Override
	public void crear(Object obj) {
		lista.add((UUIDUsuarioDTO) obj);
		escribirArchivo();
	}

	@Override
	public boolean eliminar(int index) {
		try {
			lista.remove(index);
			escribirArchivo();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean actualizar(int index, Object obj) {
		try {
			lista.set(index, (UUIDUsuarioDTO) obj);
			escribirArchivo();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String mostrarTodo() {
		StringBuilder sb = new StringBuilder();
		sb.append("UUIDs y Usuarios: \n");
		for (UUIDUsuarioDTO uuid_usuario : lista) {
			sb.append(uuid_usuario.toString());
			sb.append("<---------------------------->\n");
		}
		return sb.toString();
	}

}
