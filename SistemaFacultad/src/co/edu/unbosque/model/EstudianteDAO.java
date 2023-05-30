package co.edu.unbosque.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import co.edu.unbosque.model.persistance.FileHandler;

public class EstudianteDAO implements OperacionesDAO{
	
	private ArrayList<EstudianteDTO> lista;
	
	public EstudianteDAO() {
		lista = (ArrayList<EstudianteDTO>) FileHandler.leerSerializado("estudiantes.txt");
		if (lista == null) {
			lista = new ArrayList<EstudianteDTO>();
		}
	}

	public ArrayList<EstudianteDTO> getLista() {
		return lista;
	}

	public void setLista(ArrayList<EstudianteDTO> lista) {
		this.lista = lista;
	}
	
	//ARCHIVOS
	
	public void escribirArchivo() {
		FileHandler.escribirSerializado("estudiantes.txt", lista);
	}
	
	//CRUD

	@Override
	public void crear(Object obj) {
		lista.add((EstudianteDTO) obj);
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
			lista.set(index, (EstudianteDTO) obj);
			escribirArchivo();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String mostrarTodo() {
		StringBuilder sb = new StringBuilder();
		sb.append("Estudiantes: \n");
		for(EstudianteDTO estudiante : lista) {
			sb.append(estudiante.toString());
			sb.append("<---------------------------->\n");
		}
		return sb.toString();
	}
	
}
