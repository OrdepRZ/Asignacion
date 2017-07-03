package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto;

import java.io.Serializable;

public class NombramientoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idDocumento;
	private byte[] blob;
	private String llaveMongo;
	private String coleccionMongo;
	
	public String getIdDocumento() 
	{
		return idDocumento;
	}
	public void setIdDocumento(String idDocumento) 
	{
		this.idDocumento = idDocumento;
	}
	public byte[] getBlob() 
	{
		return blob;
	}
	public void setBlob(byte[] blob) 
	{
		this.blob = blob;
	}
	public String getLlaveMongo() {
		return llaveMongo;
	}
	public void setLlaveMongo(String llaveMongo) {
		this.llaveMongo = llaveMongo;
	}
	public String getColeccionMongo() {
		return coleccionMongo;
	}
	public void setColeccionMongo(String coleccionMongo) {
		this.coleccionMongo = coleccionMongo;
	}
}
