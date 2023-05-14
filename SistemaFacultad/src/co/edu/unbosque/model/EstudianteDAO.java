package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.Date;

import co.edu.unbosque.model.persistance.FileHandler;

public class EstudianteDAO implements OperacionesDAO{
	
	private ArrayList<EstudianteDTO> lista;
	
	public EstudianteDAO() {
		//lista = (ArrayList<EstudianteDTO>) FileHandler.leerSerializado("estudiantes.txt");
		lista = cargarDesdeArchivo();
//		if (lista == null) {
//			lista = new ArrayList<EstudianteDTO>();
//		}
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
	
	private ArrayList<EstudianteDTO> cargarDesdeArchivo() {
		ArrayList<EstudianteDTO> desde_archivo = new ArrayList<>();
		String contenido = FileHandler.abrirArchivoText("datosEstSobreescrito.csv");
		String[] lineas = contenido.split("\n");
		for (String linea : lineas) {
			String[] attr = linea.split(",");
			long documento = Long.parseLong(attr[0]);
			String nombres = attr[1];
			String apellidos = attr[2];
			char genero = attr[3].charAt(0);
			String usuario = attr[4];
			String correo = attr[5];
			Date fecha_nacimiento = new Date(attr[6]);
			boolean esta_activo = attr[7].equalsIgnoreCase("ACTIVO")? true: false;
			String programa = attr[8];
			String jornada = attr[9];
			String lugar_nacimiento = attr[10];
			Date fecha_registro = new Date(attr[11]);
			String nacional_extranjero = attr[12];
			desde_archivo.add(new EstudianteDTO(documento, nombres, apellidos, genero, usuario, correo, fecha_nacimiento, esta_activo, programa, jornada, lugar_nacimiento, fecha_registro, nacional_extranjero));
		}
		return desde_archivo;

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
