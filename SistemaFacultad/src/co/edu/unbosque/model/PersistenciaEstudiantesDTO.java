package co.edu.unbosque.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class PersistenciaEstudiantesDTO implements Serializable{
	
	private static final long serialVersionUID = -8331248926046685238L;
	private ArrayList<EstudianteDTO> lista_individual;
	private Date fecha_generacion;
	
	public PersistenciaEstudiantesDTO() {
		lista_individual = new ArrayList<>();
		fecha_generacion = new Date();
	}

	public PersistenciaEstudiantesDTO(ArrayList<EstudianteDTO> lista_individual) {
		super();
		this.lista_individual = lista_individual;
		fecha_generacion = new Date();
	}

	public ArrayList<EstudianteDTO> getLista_individual() {
		return lista_individual;
	}

	public void setLista_individual(ArrayList<EstudianteDTO> lista_individual) {
		this.lista_individual = lista_individual;
	}

	public Date getFecha_generacion() {
		return fecha_generacion;
	}

	public void setFecha_generacion(Date fecha_generacion) {
		this.fecha_generacion = fecha_generacion;
	}
	
	
	
}
