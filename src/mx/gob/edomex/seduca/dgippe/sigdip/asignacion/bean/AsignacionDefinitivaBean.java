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
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.VacanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl.AspiranteDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl.VacanteDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.bean.login.LoginBean;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.validador.Validador;

@ManagedBean
@ViewScoped
public class AsignacionDefinitivaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String folio;
	private String idMovimiento;
	private String idVacante;
	private String cct;
	private String observacion;
	private List<AspiranteDTO> lstAspirante;
	private List<VacanteDTO> lstVacante;
	private VacanteDTO vacanteSeleccionada;
	private AspiranteDTO aspirante;
	private AspiranteDTO aspiranteFormato;
	private String formato;	
	private boolean bndConsulta;
	private boolean bndAsignacion;
	private LoginBean loginBean;
	
	public AsignacionDefinitivaBean() {
		mostrarConsulta();
		
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			loginBean = (LoginBean) session.getAttribute("loginBean");	
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "AsignacionDefinitivaBean() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void limpiar() {
		folio = null;
		lstAspirante = new ArrayList<AspiranteDTO>();
	}
	
	public void limpiarPlazas() {
		idMovimiento = null;
		idVacante = null;
		cct = null;
		lstVacante = new ArrayList<VacanteDTO>();
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
	
	public String inicializarAsignacion() {
		String resultado = null;
		
		lstVacante = new ArrayList<VacanteDTO>();
		
		try {
			if (aspirante != null) {
				
				if (aspirante.getStatus() != null && aspirante.getStatus().equals("2")) {
					mostrarAsignacion();
				} else {
					FacesMessage msg = new FacesMessage("El aspirante " + aspirante.getNombre() + " con folio " + aspirante.getFolioFederal() + " debió haber sido ASIGNADO previamente y tiene estatus " + aspirante.getDescStatus() + ".");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
				
			} else {
				FacesMessage msg = new FacesMessage("buscarPlazas() : Ocurrió un error al recuperar la información del aspirante.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}			
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "buscarPlazas() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return resultado;
	}

	public String consultarPlazas() {
		String resultado = null;
		
		try {
						
			if (aspirante != null && aspirante.getFolioFederal() != null && !aspirante.getFolioFederal().trim().equals("")) {
				//AspiranteDTO aspirante = lstAspirante.get(0);
				
				if (aspirante.getStatus() != null && aspirante.getStatus().equals("2")) {
					if (aspirante.getCveAsignatura() != null && !aspirante.getCveAsignatura().trim().equals("")) {

						if (idMovimiento != null && !idMovimiento.equals("") && !idMovimiento.equals("0")) {
							List<CampoRptDTO> lstFiltro = new ArrayList<CampoRptDTO>();
							CampoRptDTO idFiltro = null;
							
							idFiltro = new CampoRptDTO();
							idFiltro.setClave("CVE_ASIGNATURA");
							idFiltro.setValor(aspirante.getCveAsignatura());
							lstFiltro.add(idFiltro);					
							
							idFiltro = new CampoRptDTO();
							idFiltro.setClave("ID_FUNCION");
							idFiltro.setValor(aspirante.getIdFuncion());
							lstFiltro.add(idFiltro);					

							idFiltro = new CampoRptDTO();
							idFiltro.setClave("DISPONIBLE");
							idFiltro.setValor("0");
							lstFiltro.add(idFiltro);

							if (idMovimiento != null && idMovimiento.trim().equals("1")) {
								//Tipo de movimiento: Cambio de Temporal a Definitiva
								idFiltro = new CampoRptDTO();
								idFiltro.setClave("TIPOVACANTE");
								idFiltro.setValor("DEFINITIVA");
								lstFiltro.add(idFiltro);
							}
							
							if (idVacante != null && !idVacante.trim().equals("")) {
								idFiltro = new CampoRptDTO();
								idFiltro.setClave("ID");
								idFiltro.setValor(idVacante);
								lstFiltro.add(idFiltro);								
							}

							if (cct != null && !cct.trim().equals("")) {
								idFiltro = new CampoRptDTO();
								idFiltro.setClave("CCT");
								idFiltro.setValor(cct);
								lstFiltro.add(idFiltro);								
							}

							
							String idSubsistema = loginBean.getUsuario().getIdSubsistema();
							if (idSubsistema != null && !idSubsistema.trim().equals("") && !idSubsistema.trim().equals("0")) {
								idFiltro = new CampoRptDTO();
								idFiltro.setClave("SUBSISTEMA");
								idFiltro.setValor(idSubsistema);
								lstFiltro.add(idFiltro);								
							}

							VacanteDAO vacanteDAO = new VacanteDaoImpl();
							lstVacante = vacanteDAO.getVacantes(lstFiltro);
							
							if (lstVacante.isEmpty()) {
								FacesMessage msg = new FacesMessage("No se localizarón vacantes definitivas disponibles para los criterios establecidos.");
								msg.setSeverity(FacesMessage.SEVERITY_ERROR);
								FacesContext.getCurrentInstance().addMessage(null, msg);
							}

						} else {
							FacesMessage msg = new FacesMessage("Tipo de Movimiento es requerido.");
							msg.setSeverity(FacesMessage.SEVERITY_ERROR);
							FacesContext.getCurrentInstance().addMessage(null, msg);										
						}
						
					} else {
						FacesMessage msg = new FacesMessage("Ocurrió un error al recuperar la clave de asignatura del aspirante.");
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null, msg);										
					}
				} else {
					FacesMessage msg = new FacesMessage("El aspirante " + aspirante.getNombre() + " con folio " + aspirante.getFolioFederal() + " tiene estatus " + aspirante.getDescStatus());
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
				
			} else {
				FacesMessage msg = new FacesMessage("consultarPlazas() : Ocurrió un error al recuperar la información del aspirante.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);				
			}		
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "buscarPlazas() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return resultado;
	}
	
	public void prepararAsignacion(VacanteDTO vacante) {
		//Inicializa vacanate seleccionada.
		observacion = null;
		vacanteSeleccionada = vacante;
	}
	
	public String asignarPlaza() {
		String resultado = null;
		
		try {
			if (aspirante != null && aspirante.getFolioFederal() != null && !aspirante.getFolioFederal().trim().equals("")
					&& aspirante.getVacanteAsignada() != null && aspirante.getVacanteAsignada().getCve() != null && !aspirante.getVacanteAsignada().getCve().equals("") ) {
				//AspiranteDTO aspirante = lstAspirante.get(0);
				
				if (vacanteSeleccionada != null && vacanteSeleccionada.getCve() != null && !vacanteSeleccionada.getCve().equals("")) {
					if (observacion != null && !observacion.trim().equals("")) {
						//Invocar procedimiento de asignación. (Envíar foliofeder, id_convocatoria, idVacante, subsistema, usuario)
						String folio = aspirante.getFolioFederal();
						String idConvocatoria = aspirante.getIdConvocatoria();
						String idFuncion = aspirante.getIdFuncion();
						String idVacanteDefinitiva = vacanteSeleccionada.getCve();
						String idSubsistema = vacanteSeleccionada.getIdSubsistema();
						String cveUsuario = loginBean.getUsuario().getCveUsuario();
						String idVacanteLiberar = aspirante.getVacanteAsignada().getCve();
						
						AspiranteDAO aspiranteDAO = new AspiranteDaoImpl();
						String respuesta = aspiranteDAO.asignarVacanteDefinitiva(folio, idConvocatoria, idFuncion, idVacanteDefinitiva, idSubsistema, cveUsuario, idVacanteLiberar, idMovimiento, observacion);
						
						aspiranteFormato = null;
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
						FacesMessage msg = new FacesMessage("Observación es un campo requerido.");
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null, msg);						
					}
				} else {
					FacesMessage msg = new FacesMessage("asignarPlaza() : Ocurrió un error al recuperar la información de la vacante.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} else {
				FacesMessage msg = new FacesMessage("asignarPlaza() : Ocurrió un error al recuperar la información del aspirante.");
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

	public void cancelarAsignacion() {
		aspirante = null;
		mostrarConsulta();
	}

	public void regresarAsignacion() {
		mostrarAsignacion();
	}

	public void cancelarNombramiento() {
		aspirante = null;
		mostrarConsulta();
	}

	private void mostrarConsulta() {
		bndConsulta = true;
		bndAsignacion = false;
	}

	private void mostrarAsignacion() {
		bndConsulta = false;
		bndAsignacion = true;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(String idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public String getIdVacante() {
		return idVacante;
	}

	public void setIdVacante(String idVacante) {
		this.idVacante = idVacante;
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

	public List<AspiranteDTO> getLstAspirante() {
		return lstAspirante;
	}

	public void setLstAspirante(List<AspiranteDTO> lstAspirante) {
		this.lstAspirante = lstAspirante;
	}

	public List<VacanteDTO> getLstVacante() {
		return lstVacante;
	}

	public void setLstVacante(List<VacanteDTO> lstVacante) {
		this.lstVacante = lstVacante;
	}

	public VacanteDTO getVacanteSeleccionada() {
		return vacanteSeleccionada;
	}

	public void setVacanteSeleccionada(VacanteDTO vacanteSeleccionada) {
		this.vacanteSeleccionada = vacanteSeleccionada;
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

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public boolean isBndConsulta() {
		return bndConsulta;
	}

	public void setBndConsulta(boolean bndConsulta) {
		this.bndConsulta = bndConsulta;
	}

	public boolean isBndAsignacion() {
		return bndAsignacion;
	}

	public void setBndAsignacion(boolean bndAsignacion) {
		this.bndAsignacion = bndAsignacion;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

}
