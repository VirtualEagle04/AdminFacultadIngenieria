package co.edu.unbosque.model;

import java.io.Serializable;

public class AdminDTO implements Serializable{
	
	private static final long serialVersionUID = -7133709986467525024L;
	private String usuario_admin;
	private String contrasena_admin;
	
	public AdminDTO() {
		// TODO Auto-generated constructor stub
	}

	public AdminDTO(String usuario_admin, String contrasena_admin) {
		super();
		this.usuario_admin = usuario_admin;
		this.contrasena_admin = contrasena_admin;
	}

	public String getUsuario_admin() {
		return usuario_admin;
	}

	public void setUsuario_admin(String usuario_admin) {
		this.usuario_admin = usuario_admin;
	}

	public String getContrasena_admin() {
		return contrasena_admin;
	}

	public void setContrasena_admin(String contrasena_admin) {
		this.contrasena_admin = contrasena_admin;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Usuario: "+getUsuario_admin()+"\n");
		sb.append("Contraseña: "+getContrasena_admin()+"\n");
		return sb.toString();
	}
	
}
