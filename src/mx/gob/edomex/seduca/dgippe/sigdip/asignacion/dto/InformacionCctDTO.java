package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto;

import java.io.Serializable;

public class InformacionCctDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cct;
	private String nombre;
	private String domicilio;
	private String colonia;
	private String localidad;
	private String idMunicipio;
	private String nombreMunicipio;
	private String codigoPostal;
	
	public String getCct() {
		return cct;
	}
	public void setCct(String cct) {
		this.cct = cct;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getNombreMunicipio() {
		return nombreMunicipio;
	}
	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

}
