package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto;

import java.io.Serializable;

public class MunicipioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idMunicipio;
	private String nomMunicipio;
	private String latitud;
	private String longitud;
	
	public String getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getNomMunicipio() {
		return nomMunicipio;
	}
	public void setNomMunicipio(String nomMunicipio) {
		this.nomMunicipio = nomMunicipio;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
}
