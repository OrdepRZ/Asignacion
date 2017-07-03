package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto;

import java.io.Serializable;

public class AspiranteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idConvocatoria;
	private String idAspirante;
	private String folioFederal;
	private String curp;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String ent;
	private String plaza;
	private String tipoConvocatoria;
	private String nomTexn;
	private String gpoDesmo;
	private String prelac;
	private String cveAsignatura;
	private String asignatura;
	private String base;
	private String subsistema;
	private String status;
	private String cveOpoUsu;
	private boolean bndAsistencia;
	
	private String idFuncion;
	private String funcion;
	private String idGrupo;
	private String fechaAplicacion;
	
	private String descStatus;
	private String cct;
	private String nombreCCT;
	private String bndAsignar;
	private String descSubsistema;
	private String cvePresupuestal;
	private String descConvocatoria;
	
	private VacanteDTO vacanteAsignada;
	private RechazoDTO motivoRechazo;
	private String bndRegSisFed; 
	
	public AspiranteDTO() {
		// TODO Auto-generated constructor stub
		vacanteAsignada = new VacanteDTO();
	}
	public String getIdConvocatoria() {
		return idConvocatoria;
	}
	public void setIdConvocatoria(String idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}
	public String getFolioFederal() {
		return folioFederal;
	}
	public void setFolioFederal(String folioFederal) {
		this.folioFederal = folioFederal;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEnt() {
		return ent;
	}
	public void setEnt(String ent) {
		this.ent = ent;
	}
	public String getPlaza() {
		return plaza;
	}
	public void setPlaza(String plaza) {
		this.plaza = plaza;
	}
	public String getTipoConvocatoria() {
		return tipoConvocatoria;
	}
	public void setTipoConvocatoria(String tipoConvocatoria) {
		this.tipoConvocatoria = tipoConvocatoria;
	}
	public String getNomTexn() {
		return nomTexn;
	}
	public void setNomTexn(String nomTexn) {
		this.nomTexn = nomTexn;
	}
	public String getGpoDesmo() {
		return gpoDesmo;
	}
	public void setGpoDesmo(String gpoDesmo) {
		this.gpoDesmo = gpoDesmo;
	}
	public String getPrelac() {
		return prelac;
	}
	public void setPrelac(String prelac) {
		this.prelac = prelac;
	}
	public String getCveAsignatura() {
		return cveAsignatura;
	}
	public void setCveAsignatura(String cveAsignatura) {
		this.cveAsignatura = cveAsignatura;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getSubsistema() {
		return subsistema;
	}
	public void setSubsistema(String subsistema) {
		this.subsistema = subsistema;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCveOpoUsu() {
		return cveOpoUsu;
	}
	public void setCveOpoUsu(String cveOpoUsu) {
		this.cveOpoUsu = cveOpoUsu;
	}
	public String getDescStatus() {
		return descStatus;
	}
	public void setDescStatus(String descStatus) {
		this.descStatus = descStatus;
	}
	public boolean isBndAsistencia() {
		return bndAsistencia;
	}
	public void setBndAsistencia(boolean bndAsistencia) {
		this.bndAsistencia = bndAsistencia;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getIdAspirante() {
		return idAspirante;
	}
	public void setIdAspirante(String idAspirante) {
		this.idAspirante = idAspirante;
	}
	public String getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getFechaAplicacion() {
		return fechaAplicacion;
	}
	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	public String getCct() {
		return cct;
	}
	public void setCct(String cct) {
		this.cct = cct;
	}
	public String getNombreCCT() {
		return nombreCCT;
	}
	public void setNombreCCT(String nombreCCT) {
		this.nombreCCT = nombreCCT;
	}
	public String getBndAsignar() {
		return bndAsignar;
	}
	public void setBndAsignar(String bndAsignar) {
		this.bndAsignar = bndAsignar;
	}
	public String getIdFuncion() {
		return idFuncion;
	}
	public void setIdFuncion(String idFuncion) {
		this.idFuncion = idFuncion;
	}
	public String getFuncion() {
		return funcion;
	}
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}
	public String getDescSubsistema() {
		return descSubsistema;
	}
	public void setDescSubsistema(String descSubsistema) {
		this.descSubsistema = descSubsistema;
	}
	public String getCvePresupuestal() {
		return cvePresupuestal;
	}
	public void setCvePresupuestal(String cvePresupuestal) {
		this.cvePresupuestal = cvePresupuestal;
	}
	public VacanteDTO getVacanteAsignada() {
		return vacanteAsignada;
	}
	public void setVacanteAsignada(VacanteDTO vacanteAsignada) {
		this.vacanteAsignada = vacanteAsignada;
	}
	public RechazoDTO getMotivoRechazo() {
		return motivoRechazo;
	}
	public void setMotivoRechazo(RechazoDTO motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}
	public String getBndRegSisFed() {
		return bndRegSisFed;
	}
	public void setBndRegSisFed(String bndRegSisFed) {
		this.bndRegSisFed = bndRegSisFed;
	}
	public String getDescConvocatoria() {
		return descConvocatoria;
	}
	public void setDescConvocatoria(String descConvocatoria) {
		this.descConvocatoria = descConvocatoria;
	}

}
