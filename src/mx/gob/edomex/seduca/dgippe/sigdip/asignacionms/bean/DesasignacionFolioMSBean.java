package mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.AspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.VacanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.dao.AspiranteMSDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.dao.VacanteMSDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.impl.AspiranteMSDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.impl.VacanteMSDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.bean.login.LoginBean;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.validador.Validador;

@ManagedBean
@ViewScoped
public class DesasignacionFolioMSBean implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private String folio;
	private AspiranteDTO aspirante;
	private VacanteDTO vacanteSeleccionada;
	private List<VacanteDTO> lstVacante;
	private List<AspiranteDTO> lstAspirante;
	
	private boolean bndConsulta;
	private boolean bndNombramientos;

	private LoginBean loginBean;
	
	public DesasignacionFolioMSBean() {
		mostrarConsulta();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			loginBean = (LoginBean) session.getAttribute("loginBean");	
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "DesasignacionFolioMSBean() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void limpiar() {
		folio = null;
		lstAspirante = new ArrayList<AspiranteDTO>();
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
					
					/*
					idFiltro = new CampoRptDTO();
					idFiltro.setClave("ID_CONVOCATORIA");
					idFiltro.setValor(idConvocatoria.trim());
					lstFiltro.add(idFiltro);
					*/					
					
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

					String idSubsistemaUsu = loginBean.getUsuario().getIdSubsistema();
					if (idSubsistemaUsu != null && !idSubsistemaUsu.trim().equals("") && !idSubsistemaUsu.trim().equals("0")) {
						idFiltro = new CampoRptDTO();
						idFiltro.setClave("ID_SUBSISTEMA");
						idFiltro.setValor(idSubsistemaUsu);
						lstFiltro.add(idFiltro);
					}
					
					AspiranteMSDAO aspiranteDAO = new AspiranteMSDaoImpl();
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

	public String buscarNombramientos() {
		String resultado = null;
		
		lstVacante = new ArrayList<VacanteDTO>();
		
		try {
			if (aspirante != null) {
				if (aspirante.getStatus() != null && aspirante.getStatus().equals("2")) {
					List<CampoRptDTO> lstFiltro = new ArrayList<CampoRptDTO>();
					CampoRptDTO idFiltro = new CampoRptDTO();
					
					idFiltro = new CampoRptDTO();
					idFiltro.setClave("FOLIO");
					idFiltro.setValor(aspirante.getFolioFederal().trim());
					lstFiltro.add(idFiltro);
					
					idFiltro = new CampoRptDTO();
					idFiltro.setClave("ID_CONVOCATORIA");
					idFiltro.setValor(aspirante.getIdConvocatoria().trim());
					lstFiltro.add(idFiltro);								

					idFiltro = new CampoRptDTO();
					idFiltro.setClave("ID_FUNCION");
					idFiltro.setValor(aspirante.getIdFuncion());
					lstFiltro.add(idFiltro);								
					
					VacanteMSDAO vacanteDAO  = new VacanteMSDaoImpl();
					lstVacante = vacanteDAO.getVacantes(lstFiltro);
					
					if (lstVacante.isEmpty()) {
						FacesMessage msg = new FacesMessage("No se localizarón vacantes asignadas a " + aspirante.getNombre() + " con folio " + aspirante.getFolioFederal() + "." );
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					} else {
						mostrarNombramientos();
					}

				} else {
					FacesMessage msg = new FacesMessage("El aspirante " + aspirante.getNombre() + " con folio " + aspirante.getFolioFederal() + " tiene estatus " + aspirante.getDescStatus());
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
				
			} else {
				FacesMessage msg = new FacesMessage("buscarNombramientos() : Ocurrió un error al recuperar la información del aspirante.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}			
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "buscarNombramientos() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return resultado;
	}

	public String desasignarPlaza() {
		String resultado = null;
		
		try {
			if (aspirante != null && aspirante.getFolioFederal() != null && !aspirante.getFolioFederal().trim().equals("")) {
				//AspiranteDTO aspirante = lstAspirante.get(0);
				
				if (vacanteSeleccionada != null && vacanteSeleccionada.getCve() != null && !vacanteSeleccionada.getCve().equals("")) {
					//Invocar procedimiento de asignación. (Envíar foliofeder, id_convocatoria, idVacante, subsistema, usuario)
					String folio = aspirante.getFolioFederal();
					String idConvocatoria = aspirante.getIdConvocatoria();
					String idFuncion = aspirante.getIdFuncion();
					String idVacante = vacanteSeleccionada.getCve();
					String idSubsistema = vacanteSeleccionada.getIdSubsistema();
					String cveUsuario = loginBean.getUsuario().getCveUsuario();
					
					AspiranteMSDAO aspiranteDAO = new AspiranteMSDaoImpl();
					String respuesta = aspiranteDAO.desasignarVacante(folio, idConvocatoria, idFuncion, idVacante, idSubsistema, cveUsuario);
					
					if (respuesta.startsWith("00")) {				
						this.folio = aspirante.getFolioFederal();
						consultarFolio();
						
						//Emitir mensaje de asignación.
						respuesta = respuesta.substring(3); //Eliminar "00|"
						FacesMessage msg = new FacesMessage(respuesta);
						msg.setSeverity(FacesMessage.SEVERITY_INFO);
						FacesContext.getCurrentInstance().addMessage(null, msg);
						
						mostrarConsulta();
					} else {
						FacesMessage msg = new FacesMessage(respuesta);
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}						
				} else {
					FacesMessage msg = new FacesMessage("desasignarPlaza() : Ocurrió un error al recuperar la información de la vacante.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} else {
				FacesMessage msg = new FacesMessage("desasignarPlaza() : Ocurrió un error al recuperar la información del aspirante.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);				
			}		

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "asignarPlaza() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return resultado;
	}
	
	public void prepararDesasignacion(VacanteDTO vacante) {
		//Inicializa vacanate seleccionada.
		vacanteSeleccionada = vacante;
	}
	
	public void cancelarNombramiento() {
		aspirante = null;
		mostrarConsulta();
	}
	
	private void mostrarConsulta() {
		bndConsulta = true;
		bndNombramientos = false;
	}

	private void mostrarNombramientos() {
		bndConsulta = false;
		bndNombramientos = true;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public AspiranteDTO getAspirante() {
		return aspirante;
	}

	public void setAspirante(AspiranteDTO aspirante) {
		this.aspirante = aspirante;
	}

	public VacanteDTO getVacanteSeleccionada() {
		return vacanteSeleccionada;
	}

	public void setVacanteSeleccionada(VacanteDTO vacanteSeleccionada) {
		this.vacanteSeleccionada = vacanteSeleccionada;
	}

	public List<VacanteDTO> getLstVacante() {
		return lstVacante;
	}

	public void setLstVacante(List<VacanteDTO> lstVacante) {
		this.lstVacante = lstVacante;
	}

	public List<AspiranteDTO> getLstAspirante() {
		return lstAspirante;
	}

	public void setLstAspirante(List<AspiranteDTO> lstAspirante) {
		this.lstAspirante = lstAspirante;
	}

	public boolean isBndConsulta() {
		return bndConsulta;
	}

	public void setBndConsulta(boolean bndConsulta) {
		this.bndConsulta = bndConsulta;
	}

	public boolean isBndNombramientos() {
		return bndNombramientos;
	}

	public void setBndNombramientos(boolean bndNombramientos) {
		this.bndNombramientos = bndNombramientos;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
}
