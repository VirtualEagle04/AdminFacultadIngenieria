package co.edu.unbosque.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import co.edu.unbosque.model.persistance.FileHandler;

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
	
	//ARCHIVOS
	public void escribirArchivo() {
		FileHandler.escribirSerializado("serializadoListas.txt", lista_pdfs);
	}
	
	//CREAR
	public void crear(Object obj) {
		
		lista_pdfs.add((PersistenciaEstudiantesDTO) obj);
		escribirArchivo();
		
	}
	
	public String mostrarPDFs() {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy - HH:mm:ss");
		sb.append("Lista de PDFs generados: \n");
		for(int i = 0; i < lista_pdfs.size(); i++) {
			sb.append(i+") "+sdf.format(lista_pdfs.get(i).getFecha_generacion())+"\n");
		}
		
		return sb.toString();
		
	}
	
	
	
	
}
