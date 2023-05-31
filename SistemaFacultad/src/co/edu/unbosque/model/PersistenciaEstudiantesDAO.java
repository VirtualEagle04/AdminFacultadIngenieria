package co.edu.unbosque.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import co.edu.unbosque.model.persistance.FileHandler;

/**
 * Clase Data Acces Object de los informes
 * 
 * @param lista ArrayList que almacena todos los objetos creados de la clase
 *              PersistenciaEstudiantesDTO
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class PersistenciaEstudiantesDAO {

	private ArrayList<PersistenciaEstudiantesDTO> lista_pdfs;

	public PersistenciaEstudiantesDAO() {
		lista_pdfs = (ArrayList<PersistenciaEstudiantesDTO>) FileHandler.leerSerializado("serializadoListas.txt");
		if (lista_pdfs == null) {
			lista_pdfs = new ArrayList<PersistenciaEstudiantesDTO>();
		}
	}

	public ArrayList<PersistenciaEstudiantesDTO> getLista_pdfs() {
		return lista_pdfs;
	}

	public void setLista_pdfs(ArrayList<PersistenciaEstudiantesDTO> lista_pdfs) {
		this.lista_pdfs = lista_pdfs;
	}

	// ARCHIVOS
	/**
	 * Metodo encargado de actualizar el serializado reespectivo de la clase
	 */
	public void escribirArchivo() {
		FileHandler.escribirSerializado("serializadoListas.txt", lista_pdfs);
	}

	// CREAR
	public void crear(Object obj) {

		lista_pdfs.add((PersistenciaEstudiantesDTO) obj);
		escribirArchivo();

	}

	/**
	 * Metodo encargado de mostrar los informes generados
	 * 
	 * @return String que almacena una informacion reducida de los pdfs como
	 *         indicador
	 */
	public String mostrarPDFs() {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy - HH:mm:ss");
		sb.append("Lista de PDFs generados: \n");
		for (int i = 0; i < lista_pdfs.size(); i++) {
			sb.append(i + ") " + sdf.format(lista_pdfs.get(i).getFecha_generacion()) + "\n");
		}

		return sb.toString();

	}

}
