package mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dto;

import java.io.Serializable;

public class PermisoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String clave;
	private String descripcion;
	private String ruta;
	private String idPadre;
	
	public PermisoDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(String idPadre) {
		this.idPadre = idPadre;
	}
	
}
