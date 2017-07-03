package mx.gob.edomex.seduca.dgippe.sigdip.bean.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.AspiranteDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.AspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl.AspiranteDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.dao.AspiranteMSDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.impl.AspiranteMSDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dao.CatalogoDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dto.CatalogoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.impl.CatalogoDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.validador.Validador;

@ManagedBean
@ViewScoped
public class IdoneidadBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String opcion;
	private String nivel;
	private String idFuncion;
	private String folio;
	private String curp;
	private List<CatalogoDTO> lstFuncion;
	private List<AspiranteDTO> lstAspirante;
	private AspiranteDTO aspirante;
	
	public IdoneidadBean() {
		opcion = null;
		nivel = "0";
		idFuncion = "0";
		folio = null;
		curp  = null;
		lstFuncion = new ArrayList<CatalogoDTO>();
		lstAspirante = new ArrayList<AspiranteDTO>();
		aspirante = new AspiranteDTO();
		
		try {
			CatalogoDAO catalogoDAO = new CatalogoDaoImpl();
			lstFuncion = catalogoDAO.getCatalogoItems("ASGFUNASP");
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "IdoneidadBean() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void limpiar() {
		opcion = null;
		limpiarFiltros();
	}
	
	public void limpiarFiltros () {
		nivel = "0";
		idFuncion = "0";
		folio = null;
		curp  = null;
		lstAspirante = new ArrayList<AspiranteDTO>();
		aspirante = new AspiranteDTO();		
	}
	
	public String consultar() {
		String resultado = null;
		lstAspirante = new ArrayList<AspiranteDTO>();
		aspirante = new AspiranteDTO();
		
		try {
			
			if ( opcion != null && !opcion.trim().equals("") && !opcion.trim().equals("0") ) {
				if ( nivel != null && !nivel.trim().equals("") && !nivel.trim().equals("0") ) {

					Validador IValidador = new Validador();
					
					List<CampoRptDTO> lstFiltro = new ArrayList<CampoRptDTO>();					
					CampoRptDTO idFiltro;

					if (opcion.trim().equals("1")) {
						//Búsqueda por Folio federal
						if(idFuncion != null && !idFuncion.trim().equals("") && !idFuncion.trim().equals("0")) {
							idFiltro = new CampoRptDTO();
							idFiltro.setClave("ID_FUNCION");
							idFiltro.setValor(idFuncion.trim());
							lstFiltro.add(idFiltro);
							
						} else {
							FacesMessage msg = new FacesMessage("Función es un dato requerido para la consulta.");
							msg.setSeverity(FacesMessage.SEVERITY_ERROR);
							FacesContext.getCurrentInstance().addMessage(null, msg);
							return null;							
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
						} else {
							FacesMessage msg = new FacesMessage("Folio federal es un dato requerido para la consulta.");
							msg.setSeverity(FacesMessage.SEVERITY_ERROR);
							FacesContext.getCurrentInstance().addMessage(null, msg);
							return null;
						}
					}

					if (opcion.trim().equals("2")) {
						//Búsqueda por CURP
						if(curp != null && !curp.trim().equals("")) {
							if(curp.trim().length() == 18) {
								idFiltro = new CampoRptDTO();
								idFiltro.setClave("CURP");
								idFiltro.setValor(curp.trim().toUpperCase());
								lstFiltro.add(idFiltro);
							} else {
								FacesMessage msg = new FacesMessage("La CURP debe ser un dato alfanumérico de longitud 18 y estructura AAAA999999AAAAAA99.");
								msg.setSeverity(FacesMessage.SEVERITY_ERROR);
								FacesContext.getCurrentInstance().addMessage(null, msg);
								return null;
							}
						} else {
							FacesMessage msg = new FacesMessage("CURP es un dato requerido para la consulta.");
							msg.setSeverity(FacesMessage.SEVERITY_ERROR);
							FacesContext.getCurrentInstance().addMessage(null, msg);
							return null;							
						}
					}
											
					if (nivel.equals("1")) {
						//Educación Básica
						idFiltro = new CampoRptDTO();
						idFiltro.setClave("ID_CONVOCATORIA");
						idFiltro.setValor("3");
						lstFiltro.add(idFiltro);

						
						AspiranteDAO aspiranteDAO = new AspiranteDaoImpl();
						lstAspirante = aspiranteDAO.getAspirantes(lstFiltro);
						
						
					} else if (nivel.equals("2")) {
						//Educación Media Superior
						idFiltro = new CampoRptDTO();
						idFiltro.setClave("ID_CONVOCATORIA");
						idFiltro.setValor("2");
						lstFiltro.add(idFiltro);

						AspiranteMSDAO aspiranteMSDAO = new AspiranteMSDaoImpl();
						lstAspirante = aspiranteMSDAO.getAspirantes(lstFiltro); 
					}
					
					//Quitar los no presentados.
					Iterator <AspiranteDTO> it = lstAspirante.iterator();
					while (it.hasNext()) {
						AspiranteDTO aspiranteDTO = (AspiranteDTO) it.next();
						if( aspiranteDTO.getStatus() == null || aspiranteDTO.getStatus().equals("6")) {
							it.remove();
						}
					}
										
					if(lstAspirante.isEmpty()) {
						FacesMessage msg = new FacesMessage("No se localizarón registros con los filtros seleccionados.");
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					} else {
						//Mostrará únicamente el primer registro.
						aspirante = lstAspirante.get(0);
					}
						
				} else {
					FacesMessage msg = new FacesMessage("El Nivel Educativo es requerido para la consulta.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
				
			} else {
				FacesMessage msg = new FacesMessage("El Tipo de búsqueda es requerido para la consulta.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);				
			}
						
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "Error interno en el servidor, inténta nuevamente."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return resultado;
	}
	
	public void mayusCurp() {
		curp = curp.toUpperCase();
	}
	
	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(String idFuncion) {
		this.idFuncion = idFuncion;
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

	public List<CatalogoDTO> getLstFuncion() {
		return lstFuncion;
	}

	public void setLstFuncion(List<CatalogoDTO> lstFuncion) {
		this.lstFuncion = lstFuncion;
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
	
}
