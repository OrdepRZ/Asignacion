package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto;

import java.io.Serializable;

public class ArchivoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String folioFeder;
	public String idDocumento;
	public String idConvocatoria;
	public String idFuncion;
	public String idVacante;
	public String nombreArchivo;
	public byte[] arhivo;
	public byte[] propiedadesNombramiento;
	public String secuencia;
	public String coleccionMongo;
	public String llaveMongo;
	public String fecAlta;
	public String usuAlta;
	public String url;

	public ArchivoDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getFolioFeder() {
		return folioFeder;
	}

	public void setFolioFeder(String folioFeder) {
		this.folioFeder = folioFeder;
	}

	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getIdConvocatoria() {
		return idConvocatoria;
	}

	public void setIdConvocatoria(String idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}

	public String getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(String idFuncion) {
		this.idFuncion = idFuncion;
	}

	public String getIdVacante() {
		return idVacante;
	}

	public void setIdVacante(String idVacante) {
		this.idVacante = idVacante;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public byte[] getArhivo() {
		return arhivo;
	}

	public void setArhivo(byte[] arhivo) {
		this.arhivo = arhivo;
	}

	public byte[] getPropiedadesNombramiento() {
		return propiedadesNombramiento;
	}

	public void setPropiedadesNombramiento(byte[] propiedadesNombramiento) {
		this.propiedadesNombramiento = propiedadesNombramiento;
	}

	public String getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	public String getColeccionMongo() {
		return coleccionMongo;
	}

	public void setColeccionMongo(String coleccionMongo) {
		this.coleccionMongo = coleccionMongo;
	}

	public String getLlaveMongo() {
		return llaveMongo;
	}

	public void setLlaveMongo(String llaveMongo) {
		this.llaveMongo = llaveMongo;
	}

	public String getFecAlta() {
		return fecAlta;
	}

	public void setFecAlta(String fecAlta) {
		this.fecAlta = fecAlta;
	}

	public String getUsuAlta() {
		return usuAlta;
	}

	public void setUsuAlta(String usuAlta) {
		this.usuAlta = usuAlta;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
