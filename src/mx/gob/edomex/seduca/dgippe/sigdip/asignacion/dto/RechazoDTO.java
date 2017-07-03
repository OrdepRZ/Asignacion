package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto;

import java.io.Serializable;

public class RechazoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String folioFeder;
	private String idConvocatoria;
	private String idFuncion;
	private String idMotivo;
	private String descMotivo;
	private String fecRechazo;
	private String observacion;

	public String getFolioFeder() {
		return folioFeder;
	}

	public void setFolioFeder(String folioFeder) {
		this.folioFeder = folioFeder;
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

	public String getIdMotivo() {
		return idMotivo;
	}

	public void setIdMotivo(String idMotivo) {
		this.idMotivo = idMotivo;
	}

	public String getDescMotivo() {
		return descMotivo;
	}

	public void setDescMotivo(String descMotivo) {
		this.descMotivo = descMotivo;
	}

	public String getFecRechazo() {
		return fecRechazo;
	}

	public void setFecRechazo(String fecRechazo) {
		this.fecRechazo = fecRechazo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
