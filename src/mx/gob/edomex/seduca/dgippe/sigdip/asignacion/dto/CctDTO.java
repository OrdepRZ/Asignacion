package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto;

import java.io.Serializable;

public class CctDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String cmpCCT;
	private String nomCCT;
	private String domicilio;
	private String colonia;
	private String localidad;
	private String turno;
	private String idMunicipio;
	private String municipio;
	private String zonaEscolar;
	private String subdirRegional;
	private String region;
	private String idSubsistema;
	private String subsistema;
	private String longitud;
	private String latitud;
	
	public String getCmpCCT() {
		return cmpCCT;
	}
	public void setCmpCCT(String cmpCCT) {
		this.cmpCCT = cmpCCT;
	}
	public String getNomCCT() {
		return nomCCT;
	}
	public void setNomCCT(String nomCCT) {
		this.nomCCT = nomCCT;
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
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getZonaEscolar() {
		return zonaEscolar;
	}
	public void setZonaEscolar(String zonaEscolar) {
		this.zonaEscolar = zonaEscolar;
	}
	public String getSubdirRegional() {
		return subdirRegional;
	}
	public void setSubdirRegional(String subdirRegional) {
		this.subdirRegional = subdirRegional;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getIdSubsistema() {
		return idSubsistema;
	}
	public void setIdSubsistema(String idSubsistema) {
		this.idSubsistema = idSubsistema;
	}
	public String getSubsistema() {
		return subsistema;
	}
	public void setSubsistema(String subsistema) {
		this.subsistema = subsistema;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
}
