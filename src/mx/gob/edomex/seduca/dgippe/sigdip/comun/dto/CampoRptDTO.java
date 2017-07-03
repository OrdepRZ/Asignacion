package mx.gob.edomex.seduca.dgippe.sigdip.comun.dto;

import java.io.Serializable;

public class CampoRptDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String clave;
	private String descripcion;
	private String valor;
	private String propiedadObj;
	private boolean cadena = false;
	private boolean numero = false;
	private boolean fecha = false;
	private boolean bLike = false;

	public CampoRptDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getPropiedadObj() {
		return propiedadObj;
	}

	public void setPropiedadObj(String propiedadObj) {
		this.propiedadObj = propiedadObj;
	}

	public boolean isCadena() {
		return cadena;
	}

	public void setCadena(boolean cadena) {
		this.cadena = cadena;
	}

	public boolean isNumero() {
		return numero;
	}

	public void setNumero(boolean numero) {
		this.numero = numero;
	}

	public boolean isFecha() {
		return fecha;
	}

	public void setFecha(boolean fecha) {
		this.fecha = fecha;
	}

	public boolean isbLike() {
		return bLike;
	}

	public void setbLike(boolean bLike) {
		this.bLike = bLike;
	}

}
