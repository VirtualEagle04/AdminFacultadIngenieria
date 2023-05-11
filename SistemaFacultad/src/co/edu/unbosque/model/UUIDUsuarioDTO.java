package co.edu.unbosque.model;

import java.io.Serializable;

public class UUIDUsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = 8817131621514665080L;
	private String usuario;
	private String uuid;
	
	public UUIDUsuarioDTO() {
		// TODO Auto-generated constructor stub
	}

	public UUIDUsuarioDTO(String usuario, String uuid) {
		super();
		this.usuario = usuario;
		this.uuid = uuid;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Usuario: "+getUsuario()+"\n");
		sb.append("UUID: "+getUuid()+"\n");
		return sb.toString();
	}
	
}
