package co.edu.unbosque.model;

import java.util.ArrayList;

import co.edu.unbosque.model.persistance.FileHandler;

public class AdminDAO implements OperacionesDAO{

	private ArrayList<AdminDTO> lista;
	
	public AdminDAO() {
		lista = (ArrayList<AdminDTO>) FileHandler.leerSerializado("admins.txt");
		if (lista == null) {
			lista = new ArrayList<AdminDTO>();
		}
		
		//crear(new AdminDTO("admin", "admin"));
	}

	public ArrayList<AdminDTO> getLista() {
		return lista;
	}

	public void setLista(ArrayList<AdminDTO> lista) {
		this.lista = lista;
	}
	
	//ARCHIVOS
	
	public void escribirArchivo() {
		FileHandler.escribirSerializado("admins.txt", lista);
	}
	
	//CRUD
	
	@Override
	public void crear(Object obj) {
		lista.add((AdminDTO) obj);
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
			lista.set(index, (AdminDTO) obj);
			escribirArchivo();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String mostrarTodo() {
		StringBuilder sb = new StringBuilder();
		sb.append("Administradores: \n");
		for(AdminDTO admins : lista) {
			sb.append(admins.toString());
			sb.append("<---------------------------->\n");
		}
		return sb.toString();
	}
	
	
}
