package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto;

import java.io.Serializable;

public class FirmanteDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String idSubsistema;
	private String usuario;
	private String password;
	private String keyId;
		
	public FirmanteDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdSubsistema() {
		return idSubsistema;
	}

	public void setIdSubsistema(String idSubsistema) {
		this.idSubsistema = idSubsistema;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	
}
