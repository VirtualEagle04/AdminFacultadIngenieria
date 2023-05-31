package co.edu.unbosque.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Clase Data transfer Object de los administradores
 * 
 * @param documento           Long encargado de almacenar un atributo
 * @param nombres             String encargado de almacenar un atributo
 * @param apellidos           String encargado de almacenar un atributo
 * @param genero              char encargado de almacenar un atributo
 * @param usuario             String encargado de almacenar un atributo
 * @param correo              String encargado de almacenar un atributo
 * @param fecha_nacimiento    Date encargado de almacenar un atributo
 * @param esta_activo         boolean encargado de almacenar un atributo
 * @param programa            String encargado de almacenar un atributo
 * @param jornada             String encargado de almacenar un atributo
 * @param lugar_nacimiento    String encargado de almacenar un atributo
 * @param fecha_registro      Date encargado de almacenar un atributo
 * @param nacional_extranjero String encargado de almacenar un atributo
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class EstudianteDTO implements Serializable {

	private static final long serialVersionUID = 765794194752650816L;
	private long documento;
	private String nombres;
	private String apellidos;
	private char genero;
	private String usuario;
	private String correo;
	private Date fecha_nacimiento;
	private boolean esta_activo;
	private String programa;
	private String jornada;
	private String lugar_nacimiento;
	private Date fecha_registro;
	private String nacional_extranjero;

	public EstudianteDTO() {
		// TODO Auto-generated constructor stub
	}

	public EstudianteDTO(long documento, String nombres, String apellidos, char genero, String usuario, String correo,
			Date fecha_nacimiento, boolean esta_activo, String programa, String jornada, String lugar_nacimiento,
			Date fecha_registro, String nacional_extranjero) {
		super();
		this.documento = documento;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.genero = genero;
		this.usuario = usuario;
		this.correo = correo;
		this.fecha_nacimiento = fecha_nacimiento;
		this.esta_activo = esta_activo;
		this.programa = programa;
		this.jornada = jornada;
		this.lugar_nacimiento = lugar_nacimiento;
		this.fecha_registro = fecha_registro;
		this.nacional_extranjero = nacional_extranjero;
	}

	public long getDocumento() {
		return documento;
	}

	public void setDocumento(long documento) {
		this.documento = documento;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public boolean isEsta_activo() {
		return esta_activo;
	}

	public void setEsta_activo(boolean esta_activo) {
		this.esta_activo = esta_activo;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getJornada() {
		return jornada;
	}

	public void setJornada(String jornada) {
		this.jornada = jornada;
	}

	public String getLugar_nacimiento() {
		return lugar_nacimiento;
	}

	public void setLugar_nacimiento(String lugar_nacimiento) {
		this.lugar_nacimiento = lugar_nacimiento;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getNacional_extranjero() {
		return nacional_extranjero;
	}

	public void setNacional_extranjero(String nacional_extranjero) {
		this.nacional_extranjero = nacional_extranjero;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		sb.append("Documento: " + getDocumento() + "\n");
		sb.append("Nombres: " + getNombres() + "\n");
		sb.append("Apellidos: " + getApellidos() + "\n");
		sb.append("Genero: " + getGenero() + "\n");
		sb.append("Usuario: " + getUsuario() + "\n");
		sb.append("Correo: " + getCorreo() + "\n");
		sb.append("Fecha de Nacimiento: " + dateFormat.format(getFecha_nacimiento()) + "\n");
		sb.append("Estado: " + (isEsta_activo() ? "ACTIVO" : "INACTIVO") + "\n");
		sb.append("Programa Academico: " + getPrograma() + "\n");
		sb.append("Jornada Academica: " + getJornada() + "\n");
		sb.append("Lugar de Nacimiento: " + getLugar_nacimiento() + "\n");
		sb.append("Fecha de Registro: " + dateFormat.format(getFecha_registro()) + "\n");
		sb.append("Nacional/Extranjero: " + getNacional_extranjero() + "\n");
		return sb.toString();
	}

}
