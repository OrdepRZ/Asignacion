package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto;

import java.io.Serializable;
import java.util.Date;

public class VacanteDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cve;
	private String cmpCCT;
	private String nomCCT;
	private String funcion;
	private String tpoVacante;
	private String cveMun;
	private String municipio;
	private String localidad;
	private String domicilio;
	private String folio;
	private String horas;
	private String cveAsignatura;
	private String asginatura;
	private String pzajornada;
	private String fechaInicio;
	private String fechaTermino;
	private String turno;
	private String subDirRegional;
	private String zonaEscolar;
	
	private String latitudCct;
	private String longitudCct;
	private String latitudMpo;
	private String longitudMpo;
	private String descTpoVacante;
	private String colonia;
	private String cp;
	private String idSubsistema;
	private String descSubsistema;
	
	private String fecAsignacion;
	private String idConvocatoria;
	private String motivoVacante;
	private String cvePresupuestal;
	private String nombreIcono;
	
	private Date dFechaInicio;
	private Date dFechaTermino;
	private String usuarioAsignacion;
	private String idFuncion;
	private String descConvocatoria;

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
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getHoras() {
		return horas;
	}
	public void setHoras(String horas) {
		this.horas = horas;
	}
	public String getAsginatura() {
		return asginatura;
	}
	public void setAsginatura(String asginatura) {
		this.asginatura = asginatura;
	}
	public String getPzajornada() {
		return pzajornada;
	}
	public void setPzajornada(String pzajornada) {
		this.pzajornada = pzajornada;
	}
	public String getCve() {
		return cve;
	}
	public void setCve(String cve) {
		this.cve = cve;
	}
	public String getCveMun() {
		return cveMun;
	}
	public void setCveMun(String cveMun) {
		this.cveMun = cveMun;
	}
	public String getCveAsignatura() {
		return cveAsignatura;
	}
	public void setCveAsignatura(String cveAsignatura) {
		this.cveAsignatura = cveAsignatura;
	}
	public String getFuncion() {
		return funcion;
	}
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}
	public String getTpoVacante() {
		return tpoVacante;
	}
	public void setTpoVacante(String tpoVacante) {
		this.tpoVacante = tpoVacante;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaTermino() {
		return fechaTermino;
	}
	public void setFechaTermino(String fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getSubDirRegional() {
		return subDirRegional;
	}
	public void setSubDirRegional(String subDirRegional) {
		this.subDirRegional = subDirRegional;
	}
	public String getZonaEscolar() {
		return zonaEscolar;
	}
	public void setZonaEscolar(String zonaEscolar) {
		this.zonaEscolar = zonaEscolar;
	}
	public String getLatitudCct() {
		return latitudCct;
	}
	public void setLatitudCct(String latitudCct) {
		this.latitudCct = latitudCct;
	}
	public String getLongitudCct() {
		return longitudCct;
	}
	public void setLongitudCct(String longitudCct) {
		this.longitudCct = longitudCct;
	}
	public String getLatitudMpo() {
		return latitudMpo;
	}
	public void setLatitudMpo(String latitudMpo) {
		this.latitudMpo = latitudMpo;
	}
	public String getLongitudMpo() {
		return longitudMpo;
	}
	public void setLongitudMpo(String longitudMpo) {
		this.longitudMpo = longitudMpo;
	}
	public String getDescTpoVacante() {
		return descTpoVacante;
	}
	public void setDescTpoVacante(String descTpoVacante) {
		this.descTpoVacante = descTpoVacante;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getDescSubsistema() {
		return descSubsistema;
	}
	public void setDescSubsistema(String descSubsistema) {
		this.descSubsistema = descSubsistema;
	}
	public String getIdSubsistema() {
		return idSubsistema;
	}
	public void setIdSubsistema(String idSubsistema) {
		this.idSubsistema = idSubsistema;
	}
	public String getFecAsignacion() {
		return fecAsignacion;
	}
	public void setFecAsignacion(String fecAsignacion) {
		this.fecAsignacion = fecAsignacion;
	}
	public String getIdConvocatoria() {
		return idConvocatoria;
	}
	public void setIdConvocatoria(String idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}
	public String getMotivoVacante() {
		return motivoVacante;
	}
	public void setMotivoVacante(String motivoVacante) {
		this.motivoVacante = motivoVacante;
	}
	public String getCvePresupuestal() {
		return cvePresupuestal;
	}
	public void setCvePresupuestal(String cvePresupuestal) {
		this.cvePresupuestal = cvePresupuestal;
	}
	public String getNombreIcono() {
		return nombreIcono;
	}
	public void setNombreIcono(String nombreIcono) {
		this.nombreIcono = nombreIcono;
	}
	public Date getdFechaInicio() {
		return dFechaInicio;
	}
	public void setdFechaInicio(Date dFechaInicio) {
		this.dFechaInicio = dFechaInicio;
	}
	public Date getdFechaTermino() {
		return dFechaTermino;
	}
	public void setdFechaTermino(Date dFechaTermino) {
		this.dFechaTermino = dFechaTermino;
	}
	public String getUsuarioAsignacion() {
		return usuarioAsignacion;
	}
	public void setUsuarioAsignacion(String usuarioAsignacion) {
		this.usuarioAsignacion = usuarioAsignacion;
	}
	public String getIdFuncion() {
		return idFuncion;
	}
	public void setIdFuncion(String idFuncion) {
		this.idFuncion = idFuncion;
	}
	public String getDescConvocatoria() {
		return descConvocatoria;
	}
	public void setDescConvocatoria(String descConvocatoria) {
		this.descConvocatoria = descConvocatoria;
	}

}