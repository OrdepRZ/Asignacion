package mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dto;

import java.io.Serializable;

public class CatalogoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idCatalogo;
	private String nomCatalogo;
	private String idItem;
	private String descItem;
	private String descripcion;
	private String bDeshabilitado;
	private String bBaja;
	
	public String getIdCatalogo() {
		return idCatalogo;
	}
	public void setIdCatalogo(String idCatalogo) {
		this.idCatalogo = idCatalogo;
	}
	public String getNomCatalogo() {
		return nomCatalogo;
	}
	public void setNomCatalogo(String nomCatalogo) {
		this.nomCatalogo = nomCatalogo;
	}
	public String getIdItem() {
		return idItem;
	}
	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}
	public String getDescItem() {
		return descItem;
	}
	public void setDescItem(String descItem) {
		this.descItem = descItem;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getbDeshabilitado() {
		return bDeshabilitado;
	}
	public void setbDeshabilitado(String bDeshabilitado) {
		this.bDeshabilitado = bDeshabilitado;
	}
	public String getbBaja() {
		return bBaja;
	}
	public void setbBaja(String bBaja) {
		this.bBaja = bBaja;
	}
	
}
