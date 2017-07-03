package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto;

import java.io.Serializable;

public class HistoricoEstatusDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idHistorico;
	private String folioFederal;
	private String idConvocatoria;
	private String idFuncion;
	private String idEstatus;
	private String descEstatus;
	private String detalleEstatus;
	private String observacion;
	private String fechaEstatus;
	private String usuarioEstatus;
	
	public HistoricoEstatusDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(String idHistorico) {
		this.idHistorico = idHistorico;
	}

	public String getFolioFederal() {
		return folioFederal;
	}

	public void setFolioFederal(String folioFederal) {
		this.folioFederal = folioFederal;
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

	public String getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(String idEstatus) {
		this.idEstatus = idEstatus;
	}

	public String getDescEstatus() {
		return descEstatus;
	}

	public void setDescEstatus(String descEstatus) {
		this.descEstatus = descEstatus;
	}

	public String getDetalleEstatus() {
		return detalleEstatus;
	}

	public void setDetalleEstatus(String detalleEstatus) {
		this.detalleEstatus = detalleEstatus;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getFechaEstatus() {
		return fechaEstatus;
	}

	public void setFechaEstatus(String fechaEstatus) {
		this.fechaEstatus = fechaEstatus;
	}

	public String getUsuarioEstatus() {
		return usuarioEstatus;
	}

	public void setUsuarioEstatus(String usuarioEstatus) {
		this.usuarioEstatus = usuarioEstatus;
	}

}
