package mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String id;
	private String cveUsuario;
	private String nombre;
	private String apePaterno;
	private String apeMaterno;
	private String nombreCompleto;
	private String email;
	private String telefono1;
	private String telefono2;
	private String pwdUsuario;
	private String usrAlta;
	private String usrUMO;
	private String fecUltAcceso;
	private String fecCmbPwd;
	private String sysAdmin;
	private String idSede;
	private String idSubsistema;
	private String idAsignatura;
	private String gpoAsignaturas;
	private String nombreSede;
	private List<String> lstPermiso;
	private List<PermisoDTO> lstPermisoAdmon;
	
	public UsuarioDTO() {
		lstPermiso = new ArrayList<String>();
		lstPermisoAdmon = new ArrayList<PermisoDTO>();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCveUsuario() {
		return cveUsuario;
	}

	public void setCveUsuario(String cveUsuario) {
		this.cveUsuario = cveUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApePaterno() {
		return apePaterno;
	}

	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	public String getApeMaterno() {
		return apeMaterno;
	}

	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getPwdUsuario() {
		return pwdUsuario;
	}

	public void setPwdUsuario(String pwdUsuario) {
		this.pwdUsuario = pwdUsuario;
	}

	public String getUsrAlta() {
		return usrAlta;
	}

	public void setUsrAlta(String usrAlta) {
		this.usrAlta = usrAlta;
	}

	public String getUsrUMO() {
		return usrUMO;
	}

	public void setUsrUMO(String usrUMO) {
		this.usrUMO = usrUMO;
	}

	public String getFecUltAcceso() {
		return fecUltAcceso;
	}

	public void setFecUltAcceso(String fecUltAcceso) {
		this.fecUltAcceso = fecUltAcceso;
	}

	public String getFecCmbPwd() {
		return fecCmbPwd;
	}

	public void setFecCmbPwd(String fecCmbPwd) {
		this.fecCmbPwd = fecCmbPwd;
	}

	public String getSysAdmin() {
		return sysAdmin;
	}

	public void setSysAdmin(String sysAdmin) {
		this.sysAdmin = sysAdmin;
	}

	public List<String> getLstPermiso() {
		return lstPermiso;
	}

	public void setLstPermiso(List<String> lstPermiso) {
		this.lstPermiso = lstPermiso;
	}

	public List<PermisoDTO> getLstPermisoAdmon() {
		return lstPermisoAdmon;
	}

	public void setLstPermisoAdmon(List<PermisoDTO> lstPermisoAdmon) {
		this.lstPermisoAdmon = lstPermisoAdmon;
	}

	public String getIdSede() {
		return idSede;
	}

	public void setIdSede(String idSede) {
		this.idSede = idSede;
	}

	public String getIdSubsistema() {
		return idSubsistema;
	}

	public void setIdSubsistema(String idSubsistema) {
		this.idSubsistema = idSubsistema;
	}

	public String getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(String idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	public String getGpoAsignaturas() {
		return gpoAsignaturas;
	}

	public void setGpoAsignaturas(String gpoAsignaturas) {
		this.gpoAsignaturas = gpoAsignaturas;
	}

	public String getNombreSede() {
		return nombreSede;
	}

	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}
	
}
