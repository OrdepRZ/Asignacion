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
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.VacanteDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.AspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.HistoricoEstatusDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.RechazoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.VacanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl.AspiranteDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl.VacanteDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.bean.login.LoginBean;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dao.CatalogoDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dto.CatalogoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.impl.CatalogoDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.validador.Validador;

@ManagedBean
@ViewScoped
public class SegFoliosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idConvocatoria;
	private String idFuncion;
	private String idAsignatura;
	private String idStatus;
	private String folio;
	private String curp;
	private String nombre;
	private String cct;
	private String observacion;
	private List<CatalogoDTO> lstConvocatoria;
	private List<CatalogoDTO> lstFuncion;
	private List<CatalogoDTO> lstAsignatura;
	private List<CatalogoDTO> lstStatus;
	private List<AspiranteDTO> lstAspirante;
	private List<HistoricoEstatusDTO> lstHistoricoEstatus;
	private VacanteDTO vacanteAspiranteDTO;
	private RechazoDTO rechazoAspiranteDTO;
	private AspiranteDTO aspiranteSeleccionado;
	private LoginBean loginBean;
	
	public SegFoliosBean() {
		lstConvocatoria = new ArrayList<CatalogoDTO>();
		lstFuncion = new ArrayList<CatalogoDTO>();
		lstAsignatura = new ArrayList<CatalogoDTO>();
		lstStatus = new ArrayList<CatalogoDTO>();
		lstAspirante = new ArrayList<AspiranteDTO>();
		lstHistoricoEstatus = new ArrayList<HistoricoEstatusDTO>();
		folio = null;
		curp = null;
		nombre = null;
		cct = null;
		observacion = null;
		vacanteAspiranteDTO = null;
		rechazoAspiranteDTO = null;
		aspiranteSeleccionado = null;
		
		try {
			//Cargar lista de Convocatorias.
			CatalogoDAO catalogoDAO = new CatalogoDaoImpl();
			lstConvocatoria = catalogoDAO.getCatalogoItems("ASGCONVOC");
			lstFuncion = catalogoDAO.getCatalogoItems("ASGFUNASP");
			lstAsignatura = catalogoDAO.getCatalogoItems("ASGTPOEVAL");
			lstStatus = catalogoDAO.getCatalogoItems("ASGTPOSTAT"); 
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			loginBean = (LoginBean) session.getAttribute("loginBean");	
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "SegFoliosBean() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public String consultar() {
		String resultado = null;
		
		lstAspirante = new ArrayList<AspiranteDTO>();
		
		if(idConvocatoria == null || idConvocatoria.trim().equals("") || idConvocatoria.trim().equals("0")) {
			FacesMessage msg = new FacesMessage("Por favor selecciona la Convocatoria.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return resultado;
		}

		
		try {
			Validador IValidador = new Validador();
			
			List<CampoRptDTO> lstFiltro = new ArrayList<CampoRptDTO>();					
			CampoRptDTO idFiltro;
			
			if(idConvocatoria != null && !idConvocatoria.trim().equals("") && !idConvocatoria.trim().equals("0")) {
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("ID_CONVOCATORIA");
				idFiltro.setValor(idConvocatoria.trim());
				lstFiltro.add(idFiltro);
			}

			if(idFuncion != null && !idFuncion.trim().equals("") && !idFuncion.trim().equals("0")) {
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("ID_FUNCION");
				idFiltro.setValor(idFuncion.trim());
				lstFiltro.add(idFiltro);
			}

			if(idAsignatura != null && !idAsignatura.trim().equals("") && !idAsignatura.trim().equals("0")) {
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("CVEASIGNATURA");
				idFiltro.setValor(idAsignatura.trim());
				lstFiltro.add(idFiltro);
			}

			if(idStatus != null && !idStatus.trim().equals("") && !idStatus.trim().equals("0")) {
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("STATUS");
				idFiltro.setValor(idStatus.trim());
				lstFiltro.add(idFiltro);
			}

			if(folio != null && !folio.trim().equals("")) {
				if(IValidador.isNumeros(folio) && folio.trim().length() == 12) {
					idFiltro = new CampoRptDTO();
					idFiltro.setClave("FOLIO");
					idFiltro.setValor(folio.trim());
					lstFiltro.add(idFiltro);
				} else {
					FacesMessage msg = new FacesMessage("Folio debe ser un dato numérico de longitud 12.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return null;
				}
			}

			if(curp != null && !curp.trim().equals("")) {
				//if(IValidador.isNumeros(folio) && folio.trim().length() == 18) {
					idFiltro = new CampoRptDTO();
					idFiltro.setClave("CURP");
					idFiltro.setValor(curp.trim());
					lstFiltro.add(idFiltro);
					/*
				} else {
					FacesMessage msg = new FacesMessage("Curp debe ser un dato alfanumérico de longitud 18 y estructura AAAA999999AAAAAA99.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return null;
				}
				*/
			}

			if(nombre != null && !nombre.trim().equals("")) {
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("NOMBRE");
				idFiltro.setValor(nombre.trim());
				lstFiltro.add(idFiltro);
			}			

			if(cct != null && !cct.trim().equals("")) {
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("CCT");
				idFiltro.setValor(cct.trim().toUpperCase());
				lstFiltro.add(idFiltro);
			}
			
			AspiranteDAO aspiranteDAO = new AspiranteDaoImpl();
			lstAspirante = aspiranteDAO.getAspirantes(lstFiltro);
			
			if(lstAspirante.isEmpty()) {
				FacesMessage msg = new FacesMessage("No se localizarón registros con los filtros seleccionados.");
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
	
	public void obtenerInfoEstatus(AspiranteDTO paramAspirante) {
		aspiranteSeleccionado = null;
		vacanteAspiranteDTO = null;
		rechazoAspiranteDTO = null;
		
		try {
			
			if (paramAspirante != null ) {
				aspiranteSeleccionado = paramAspirante;
				
				if(paramAspirante.getStatus().equals("2")) {
					vacanteAspiranteDTO = paramAspirante.getVacanteAsignada();
				} else if(paramAspirante.getStatus().equals("3")) {
					rechazoAspiranteDTO = paramAspirante.getMotivoRechazo();
				}
				
			} else {
				FacesMessage msg = new FacesMessage("Imposible recuperar la información del estatus.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);			
			}			
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "obtenerInfoEstatus() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	} 
	
	public void obtenerInfoVacante(AspiranteDTO paramAspirante) {
		vacanteAspiranteDTO = null;
		
		try {
			if (paramAspirante != null 
					&& paramAspirante.getFolioFederal() != null && !paramAspirante.getFolioFederal().trim().equals("")
					&& paramAspirante.getIdConvocatoria() != null && !paramAspirante.getIdConvocatoria().trim().equals("")) {

				List<CampoRptDTO> lstFiltro = new ArrayList<CampoRptDTO>();
				
				CampoRptDTO idFiltro = new CampoRptDTO();
				idFiltro.setClave("FOLIO");
				idFiltro.setValor(paramAspirante.getFolioFederal().trim());
				lstFiltro.add(idFiltro);					
				
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("ID_CONVOCATORIA");
				idFiltro.setValor(paramAspirante.getIdConvocatoria().trim());
				lstFiltro.add(idFiltro);					

				idFiltro = new CampoRptDTO();
				idFiltro.setClave("ID_FUNCION");
				idFiltro.setValor(paramAspirante.getIdFuncion().trim());
				lstFiltro.add(idFiltro);
				
				VacanteDAO vacanteDAO = new VacanteDaoImpl();
				List<VacanteDTO> lstVacante = vacanteDAO.getVacantes(lstFiltro);
				
				if (lstVacante.isEmpty()) {
					FacesMessage msg = new FacesMessage("No se localizarón vacantes asignadas al Aspirante seleccionado.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					vacanteAspiranteDTO = lstVacante.get(0);
				}
				
			} else {
				FacesMessage msg = new FacesMessage("Imposible recuperar la información del Aspirante.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);			
			}			
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "obtenerInfoVacante() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
 		
	}

	public void obtenerHistorico(AspiranteDTO paramAspirante) {
		lstHistoricoEstatus = new ArrayList<HistoricoEstatusDTO>();
		
		try {
			if (paramAspirante != null 
					&& paramAspirante.getFolioFederal() != null && !paramAspirante.getFolioFederal().trim().equals("")
					&& paramAspirante.getIdConvocatoria() != null && !paramAspirante.getIdConvocatoria().trim().equals("")
					&& paramAspirante.getIdFuncion() != null && !paramAspirante.getIdFuncion().trim().equals("")) {

				List<CampoRptDTO> lstFiltro = new ArrayList<CampoRptDTO>();
				
				CampoRptDTO idFiltro = new CampoRptDTO();
				idFiltro.setClave("FOLIO");
				idFiltro.setValor(paramAspirante.getFolioFederal().trim());
				lstFiltro.add(idFiltro);					
				
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("ID_CONVOCATORIA");
				idFiltro.setValor(paramAspirante.getIdConvocatoria().trim());
				lstFiltro.add(idFiltro);					

				idFiltro = new CampoRptDTO();
				idFiltro.setClave("ID_FUNCION");
				idFiltro.setValor(paramAspirante.getIdFuncion().trim());
				lstFiltro.add(idFiltro);					
				
				AspiranteDAO aspiranteDAO = new AspiranteDaoImpl();
				lstHistoricoEstatus = aspiranteDAO.getHistoricoEstatus(lstFiltro);
				aspiranteSeleccionado = paramAspirante;

			} else {
				FacesMessage msg = new FacesMessage("Imposible recuperar la información del Aspirante.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);			
			}			
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "obtenerHistorico() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
 		
	}

	public void prepararRegistro(AspiranteDTO aspirante) {
		//Inicializa vacanate seleccionada.
		aspiranteSeleccionado = aspirante;
		observacion = null;
	}

	public String confirmarRegistro() {
		String resultado = null;
		
		try {
			if (aspiranteSeleccionado != null 
					&& aspiranteSeleccionado.getFolioFederal() != null && !aspiranteSeleccionado.getFolioFederal().trim().equals("")
					&& aspiranteSeleccionado.getIdConvocatoria() != null && !aspiranteSeleccionado.getIdConvocatoria().trim().equals("")
					&& aspiranteSeleccionado.getIdFuncion() != null && !aspiranteSeleccionado.getIdFuncion().trim().equals("")) {

				String cveUsuario = loginBean.getUsuario().getCveUsuario();
				
				AspiranteDAO aspiranteDAO = new AspiranteDaoImpl();
				String respuesta = aspiranteDAO.registroFederal(aspiranteSeleccionado, observacion.trim().toUpperCase(), cveUsuario);
				
				if (respuesta.startsWith("00")) {
					//this.folio = aspiranteSeleccionado.getFolioFederal();
					consultar();
					
					//Emitir mensaje de asignación.
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
				FacesMessage msg = new FacesMessage("confirmarRegistro() : Ocurrió un error al recuperar la información del aspirante.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);				
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "confirmarRegistro() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return resultado;
	}
	
	public void limpiar () {
		idConvocatoria = "0";
		idFuncion = "0";
		idAsignatura = "0";
		idStatus = "0";		
		folio = null;
		curp = null;
		nombre = null;
		cct = null;
		observacion = null;
		lstAspirante = new ArrayList<AspiranteDTO>();
		vacanteAspiranteDTO = null;
		rechazoAspiranteDTO = null;
		aspiranteSeleccionado = null;
	}
	
	public String getIdConvocatoria() {
		return idConvocatoria;
	}

	public void setIdConvocatoria(String idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}

	public String getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(String idFuncion) {
		this.idFuncion = idFuncion;
	}

	public String getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(String idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	public String getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(String idStatus) {
		this.idStatus = idStatus;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
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

	public String getCct() {
		return cct;
	}

	public void setCct(String cct) {
		this.cct = cct;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public List<CatalogoDTO> getLstConvocatoria() {
		return lstConvocatoria;
	}

	public void setLstConvocatoria(List<CatalogoDTO> lstConvocatoria) {
		this.lstConvocatoria = lstConvocatoria;
	}

	public List<CatalogoDTO> getLstFuncion() {
		return lstFuncion;
	}

	public void setLstFuncion(List<CatalogoDTO> lstFuncion) {
		this.lstFuncion = lstFuncion;
	}

	public List<CatalogoDTO> getLstAsignatura() {
		return lstAsignatura;
	}

	public void setLstAsignatura(List<CatalogoDTO> lstAsignatura) {
		this.lstAsignatura = lstAsignatura;
	}

	public List<CatalogoDTO> getLstStatus() {
		return lstStatus;
	}

	public void setLstStatus(List<CatalogoDTO> lstStatus) {
		this.lstStatus = lstStatus;
	}

	public RechazoDTO getRechazoAspiranteDTO() {
		return rechazoAspiranteDTO;
	}

	public void setRechazoAspiranteDTO(RechazoDTO rechazoAspiranteDTO) {
		this.rechazoAspiranteDTO = rechazoAspiranteDTO;
	}

	public List<AspiranteDTO> getLstAspirante() {
		return lstAspirante;
	}

	public void setLstAspirante(List<AspiranteDTO> lstAspirante) {
		this.lstAspirante = lstAspirante;
	}

	public List<HistoricoEstatusDTO> getLstHistoricoEstatus() {
		return lstHistoricoEstatus;
	}

	public void setLstHistoricoEstatus(List<HistoricoEstatusDTO> lstHistoricoEstatus) {
		this.lstHistoricoEstatus = lstHistoricoEstatus;
	}

	public VacanteDTO getVacanteAspiranteDTO() {
		return vacanteAspiranteDTO;
	}

	public void setVacanteAspiranteDTO(VacanteDTO vacanteAspiranteDTO) {
		this.vacanteAspiranteDTO = vacanteAspiranteDTO;
	}

	public AspiranteDTO getAspiranteSeleccionado() {
		return aspiranteSeleccionado;
	}

	public void setAspiranteSeleccionado(AspiranteDTO aspiranteSeleccionado) {
		this.aspiranteSeleccionado = aspiranteSeleccionado;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
}
