package co.edu.unbosque.model;

public interface OperacionesDAO {
	
	public void crear(Object obj);
	
	public boolean eliminar(int index);
	
	public boolean actualizar(int index, Object obj);
	
	public String mostrarTodo();
	
}
