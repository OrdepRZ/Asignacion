package mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dto;

import java.io.Serializable;

public class AvisoDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String texto;
	private String urlImagen;
	private String nombreImagen;
	private String extensionImagen;
	private String llaveMongoImagen;
	private byte [] imagen;
	private String urlArchivo;
	private String nombreArchivo;
	private String extensionArchivo;
	private String llaveMongoArchivo;
	private byte [] archivo;
	private String coleccionMongo;
	private String usuUMO;

	public AvisoDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	public String getExtensionImagen() {
		return extensionImagen;
	}

	public void setExtensionImagen(String extensionImagen) {
		this.extensionImagen = extensionImagen;
	}

	public String getLlaveMongoImagen() {
		return llaveMongoImagen;
	}

	public void setLlaveMongoImagen(String llaveMongoImagen) {
		this.llaveMongoImagen = llaveMongoImagen;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getUrlArchivo() {
		return urlArchivo;
	}

	public void setUrlArchivo(String urlArchivo) {
		this.urlArchivo = urlArchivo;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getExtensionArchivo() {
		return extensionArchivo;
	}

	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}

	public String getLlaveMongoArchivo() {
		return llaveMongoArchivo;
	}

	public void setLlaveMongoArchivo(String llaveMongoArchivo) {
		this.llaveMongoArchivo = llaveMongoArchivo;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getColeccionMongo() {
		return coleccionMongo;
	}

	public void setColeccionMongo(String coleccionMongo) {
		this.coleccionMongo = coleccionMongo;
	}

	public String getUsuUMO() {
		return usuUMO;
	}

	public void setUsuUMO(String usuUMO) {
		this.usuUMO = usuUMO;
	}

}
