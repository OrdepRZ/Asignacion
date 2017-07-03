package mx.gob.edomex.seduca.dgippe.sigdip.bean.login;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;



@ManagedBean
@SessionScoped
public class NavegacionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public String redireccionarAAcceso() {
		return "/acceso.xhtml?faces-redirect=true";
	}
	
	public String redireccionarAInicio() {
		return "/sigdip/inicio.xhtml?faces-redirect=true";
	}
	
	/* ---------------------------------- CATÁLOGOS --------------------------------*/

	/* ---------------------------------- APLICACION --------------------------------*/

    
	//Ingreso
	
    
	//Promocion
    
    
    //Desempeno
	
    
	/* ---------------------------------- ASIGNACION --------------------------------*/
	public String asgFolio() {
		return "/sigdip/asignacion/folio/asignacionFolio.xhtml?faces-redirect=true";
	}

	public String asgDefinitiva() {
		return "/sigdip/asignacion/folio/asignacionDefinitiva.xhtml?faces-redirect=true";
	}
	
	public String rechazoFolio() {
		return "/sigdip/asignacion/rechazo/rechazoFolio.xhtml?faces-redirect=true";
	}
	
	public String asgSegPlazas() {
		return "/sigdip/asignacion/seguimiento/segPlazas.xhtml?faces-redirect=true";
	}

	public String asgSegFolios() {
		return "/sigdip/asignacion/seguimiento/segFolios.xhtml?faces-redirect=true";
	}

	public String asgSegGeoref(){
		return "/sigdip/asignacion/seguimiento/georefPlazas.xhtml?faces-redirect=true";
	}

	public String asgCargaDoc(){
		return "/sigdip/asignacion/carga/cargaDocumentos.xhtml?faces-redirect=true";
	}
	
	public String asgAdminReversaStatus(){
		return "/sigdip/asignacion/administracion/reversaFolio.xhtml?faces-redirect=true";
	}

	public String asgEdiPlazas(){
		return "/sigdip/asignacion/edicion/edicionPlaza.xhtml?faces-redirect=true";
	}
	
	public String asgRptAsignacion(){
		return "/sigdip/asignacion/reportes/rptAsignacion.xhtml?faces-redirect=true";
	}

	public String asgRptRechazo(){
		return "/sigdip/asignacion/reportes/rptRechazo.xhtml?faces-redirect=true";
	}
	
	public String respaldoNomb() {
		return "/sigdip/asignacion/administracion/respaldoNomb.xhtml?faces-redirect=true";
	}
	
	public String asgAdminTestFirmDig4() {
		return "/sigdip/asignacion/administracion/testFirmaDigital4.xhtml?faces-redirect=true";
	}
	
	public String asgEdiPlazasNivel(){	//AGREGADO
		return "/sigdip/asignacion/seguimiento/edicionPlazaNivel.xhtml?faces-redirect=true";
	}
	
	public String asgRegVacante(){	//AGREGADO
		return "/sigdip/asignacion/seguimiento/registroVacante.xhtml?faces-redirect=true";
	}	

	public String asgRegCita() {		//AGREGADO
		return "/sigdip/asignacion/seguimiento/citas.xhtml?faces-redirect=true";
	}	

	public String asgRptCita() {	//AGREGADO
		return "/sigdip/asignacion/reportes/rptCitas.xhtml?faces-redirect=true";
	}	

	public String asgAdminRespNombMongo() {
		return "/sigdip/asignacion/administracion/respNomMongo.xhtml?faces-redirect=true";
	}

	public String validacionCurp() {	
		return "/sigdip/asignacion/administracion/validacionCurp.xhtml?faces-redirect=true";
	}		

	/* ---------------------------------- ASIGNACION  MS --------------------------*/

	public String asgFolioMS() {
		return "/sigdip/asignacionms/folio/asignacionFolioMS.xhtml?faces-redirect=true";
	} 

	public String asgNombrMSFolio() {
		return "/sigdip/asignacionms/nombramiento/NombramientoFolioMS.xhtml?faces-redirect=true";
	} 
	
	public String rechazoFolioMS() {
		return "/sigdip/asignacionms/rechazo/rechazoFolioMS.xhtml?faces-redirect=true";
	}
	
	public String asgSegFoliosMS() {
		return "/sigdip/asignacionms/seguimiento/segFoliosMS.xhtml?faces-redirect=true";
	}

	public String asgSegPlazasMS() {
		return "/sigdip/asignacionms/seguimiento/segPlazasMS.xhtml?faces-redirect=true";
	}

	public String asgAdminReversaStatusMS(){
		return "/sigdip/asignacionms/administracion/reversaFolioMS.xhtml?faces-redirect=true";
	}

	public String asgAdminDesasignarMS(){
		return "/sigdip/asignacionms/administracion/desasignarFolioMS.xhtml?faces-redirect=true";
	}
	
	public String asgSegGeorefMS(){
		return "/sigdip/asignacionms/seguimiento/georefPlazasMS.xhtml?faces-redirect=true";
	}

	public String asgRptAsignacionMS(){
		return "/sigdip/asignacionms/reportes/rptAsignacionMS.xhtml?faces-redirect=true";
	}

	public String asgRptRechazoMS(){
		return "/sigdip/asignacionms/reportes/rptRechazoMS.xhtml?faces-redirect=true";
	}
	
	public String respaldoNombMS() {
		return "/sigdip/asignacionms/administracion/respaldoNombMS.xhtml?faces-redirect=true";
	}

	public String asgEdiPlazasNivelMS(){	
		return "/sigdip/asignacionms/seguimiento/edicionPlazaNivelMS.xhtml?faces-redirect=true";
	}

	public String asgRegVacanteMS(){	
		return "/sigdip/asignacionms/seguimiento/registroVacanteMS.xhtml?faces-redirect=true";
	}	

	public String asgRegCitaMS() {		
		return "/sigdip/asignacionms/seguimiento/citasMS.xhtml?faces-redirect=true";
	}	

	public String asgRptCitaMS()	
	{
		return "/sigdip/asignacionms/reportes/rptCitasMS.xhtml?faces-redirect=true";
	}	
	
	/* --------------------------------- ASIGNACIÓN EXTRAORDINARIA ---------------------------*/

	/* ---------------------------------- EVALUACION --------------------------------*/	//AGREGADO
	
	
	/* ---------------------------------- TUTORIA --------------------------------*/
	
	/* ------------------------------------ ATP ----------------------------------*/

	/* ---------------------------------- CARGA ARCHIVOS --------------------------------*/

	/* ---------------------------------- USUARIO --------------------------------*/

	/* ---------------------------------- DOCUMENTOS --------------------------------*/

}
