package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.AspiranteDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.AspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl.AspiranteDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.bean.login.LoginBean;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class CargaDocumentosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String curp;
	private List<UploadedFile> lstfile;
	private boolean showCargaArchivo;
	private AspiranteDTO aspirante;
	private LoginBean loginBean;
	
	public CargaDocumentosBean(){
		lstfile =  new ArrayList<UploadedFile>();
		curp = "";
		showCargaArchivo = false;
		aspirante = new AspiranteDTO();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		loginBean = (LoginBean) session.getAttribute("loginBean");
	}
	
	public void uploadfile(FileUploadEvent event){
		UploadedFile uploadedFile = null;
		
		uploadedFile = event.getFile();
		
		this.lstfile.add(uploadedFile);
	}
	
	public void consultarCurp(){
		try {
			if(!curp.equals("")){
				AspiranteDAO aspiranteDAO = new AspiranteDaoImpl();
				aspirante = aspiranteDAO.consultarAspCurp(curp);
				
				if(curp.equals(aspirante.getCurp())){
					showCargaArchivo = true;
				}else{
					showCargaArchivo = false;
					FacesMessage msg = new FacesMessage("No se localizarón registros con la CURP proporcionada.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}else{
				showCargaArchivo = false;
				FacesMessage msg = new FacesMessage("Favor de capturar la CURP.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		
	}
	
	public void guardarDocumentos(){
		
		if(lstfile.size() <= 0){
			FacesMessage msg = new FacesMessage("Debe seleccionar al menos una archivo, favor de verificar.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			String resultado = "";
			AspiranteDAO aspiranteDAO = new AspiranteDaoImpl();
			try {
				resultado = aspiranteDAO.cargarArchivosCurp(lstfile, aspirante.getCurp(), loginBean.getUsuario().getCveUsuario());
				
				if(resultado.equals("correcto")){
					FacesMessage msg = new FacesMessage("Se guardaron correctamente los documentos para la CURP "+ aspirante.getCurp() +".");
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}else{
					FacesMessage msg = new FacesMessage("Ocurrió un error al guardar los documentos.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
				
				curp = "";
				showCargaArchivo = false;
			} catch (DBExcepcion e) {
				e.printStackTrace();
			} catch (SistemaExcepcion e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<UploadedFile> getLstfile() {
		return lstfile;
	}

	public void setLstfile(List<UploadedFile> lstfile) {
		this.lstfile = lstfile;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public boolean isShowCargaArchivo() {
		return showCargaArchivo;
	}

	public void setShowCargaArchivo(boolean showCargaArchivo) {
		this.showCargaArchivo = showCargaArchivo;
	}

	public AspiranteDTO getAspirante() {
		return aspirante;
	}

	public void setAspirante(AspiranteDTO aspirante) {
		this.aspirante = aspirante;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
}
