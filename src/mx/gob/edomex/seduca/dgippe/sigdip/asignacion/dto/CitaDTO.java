package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto;

import java.io.Serializable;
import java.util.Date;

public class CitaDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String folio;
	private Date fecha;
	
	public String getFolio() 
	{
		return folio;
	}
	public void setFolio(String folio)
	{
		this.folio = folio;
	}
	public Date getFecha() 
	{
		return fecha;
	}
	public void setFecha(Date fecha) 
	{
		this.fecha = fecha;
	}

}