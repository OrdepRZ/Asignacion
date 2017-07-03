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
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.validador.Validador;

@ManagedBean
@ViewScoped
public class ReversaFolioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean bndConsulta;
	private boolean bndReversa;
	private String folio;
	private List<AspiranteDTO> lstAspirante;
	private AspiranteDTO aspirante;
	private AspiranteDTO aspiranteFormato;
	private String observacionReversa;
	private LoginBean loginBean;
	
	public ReversaFolioBean() {
		mostrarConsulta();
		
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			loginBean = (LoginBean) session.getAttribute("loginBean");	
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "ReversaFolioBean() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public String consultarFolio() {
		String resultado = null;
		
		lstAspirante = new ArrayList<AspiranteDTO>();
		
		try {
			if(folio != null && !folio.trim().equals("")) {
				Validador IValidador = new Validador();
				if(IValidador.isNumeros(folio) && folio.trim().length() == 12) {
					List<CampoRptDTO> lstFiltro = new ArrayList<CampoRptDTO>();					
					CampoRptDTO idFiltro;
										
					idFiltro = new CampoRptDTO();
					idFiltro.setClave("FOLIO");
					idFiltro.setValor(folio.trim());
					lstFiltro.add(idFiltro);

					String usuCveAsignatura = loginBean.getUsuario().getIdAsignatura();
					if (usuCveAsignatura != null && !usuCveAsignatura.trim().equals("") && !usuCveAsignatura.trim().equals("0")) {
						idFiltro = new CampoRptDTO();
						idFiltro.setClave("CVEASIGNATURA");
						idFiltro.setValor(usuCveAsignatura.trim());
						lstFiltro.add(idFiltro);						
					}

					AspiranteDAO aspiranteDAO = new AspiranteDaoImpl();
					lstAspirante = aspiranteDAO.getAspirantes(lstFiltro);
					
					if(lstAspirante.isEmpty()) {
						FacesMessage msg = new FacesMessage("No se localizarón registros con los filtros seleccionados.");
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					} else {
						aspirante = lstAspirante.get(0);
					}
				} else {
					FacesMessage msg = new FacesMessage("Folio debe ser un dato numérico de longitud 12.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);									
				}
			} else {
				FacesMessage msg = new FacesMessage("Folio es un campo requerido, por favor verifica.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);				
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "consultarFolio() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return resultado;
	}

	public String inicializarReversa() {
		String resultado = null;
		
		observacionReversa = "";
		
		try {			
			mostrarReversa();
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "inicializarReversa() - Se generó un error interno en el servidor.") );
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return resultado;
	}

	public String ejecutarReversa() {
		String resultado = null;
		
		try {
			if (aspirante != null && aspirante.getFolioFederal() != null && !aspirante.getFolioFederal().trim().equals("")) {
				if (observacionReversa != null && !observacionReversa.trim().equals("")) {
					String folioFederal = aspirante.getFolioFederal();
					String idConvocatoria = aspirante.getIdConvocatoria();
					String idFuncion = aspirante.getIdFuncion();
					String usuario = loginBean.getUsuario().getCveUsuario();
					
					AspiranteDAO aspiranteDAO = new AspiranteDaoImpl();
					String respuesta = aspiranteDAO.ejecutarReversaStatus(folioFederal, idConvocatoria, idFuncion, observacionReversa, usuario);
					
					if (respuesta.startsWith("00")) {
						this.folio = aspirante.getFolioFederal();
												
						consultarFolio();
						
						mostrarConsulta();
						
						respuesta = respuesta.substring(3); //Eliminar "00|"
						FacesMessage msg = new FacesMessage(respuesta);
						msg.setSeverity(FacesMessage.SEVERITY_INFO);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					} else {
						FacesMessage msg = new FacesMessage(respuesta);
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
					
				} else {
					FacesMessage msg = new FacesMessage("Observación de reversa es requerido, por favor selecciona uno.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} else {
				FacesMessage msg = new FacesMessage("asignarPlaza() : Ocurrió un error al recuperar la información del aspirante.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "registrarRenuncia() - Se generó un error interno en el servidor.") );
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return resultado;
	}

	public void regresarConsulta() {
		folio = null;
		mostrarConsulta();
	}

	public void limpiar() {
		folio = null;
		lstAspirante = new ArrayList<AspiranteDTO>();
	}

	private void mostrarConsulta() {
		bndConsulta = true;
		bndReversa = false;
	}

	private void mostrarReversa() {
		bndConsulta = false;
		bndReversa = true;
	}

	public boolean isBndConsulta() {
		return bndConsulta;
	}

	public void setBndConsulta(boolean bndConsulta) {
		this.bndConsulta = bndConsulta;
	}

	public boolean isBndReversa() {
		return bndReversa;
	}

	public void setBndReversa(boolean bndReversa) {
		this.bndReversa = bndReversa;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public List<AspiranteDTO> getLstAspirante() {
		return lstAspirante;
	}

	public void setLstAspirante(List<AspiranteDTO> lstAspirante) {
		this.lstAspirante = lstAspirante;
	}

	public AspiranteDTO getAspirante() {
		return aspirante;
	}

	public void setAspirante(AspiranteDTO aspirante) {
		this.aspirante = aspirante;
	}

	public AspiranteDTO getAspiranteFormato() {
		return aspiranteFormato;
	}

	public void setAspiranteFormato(AspiranteDTO aspiranteFormato) {
		this.aspiranteFormato = aspiranteFormato;
	}

	public String getObservacionReversa() {
		return observacionReversa;
	}

	public void setObservacionReversa(String observacionReversa) {
		this.observacionReversa = observacionReversa;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

}
