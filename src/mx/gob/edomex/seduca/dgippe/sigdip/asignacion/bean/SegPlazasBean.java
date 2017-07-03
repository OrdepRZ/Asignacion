package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.VacanteDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.InformacionCctDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.VacanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl.VacanteDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.bean.login.LoginBean;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dao.CatalogoDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dto.CatalogoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.impl.CatalogoDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;

@ManagedBean
@ViewScoped
public class SegPlazasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idSubsistema;
	private String idFuncion;
	private String idAsignatura;
	private String idMunicipio;
	private String cct;
	private String idDisponibilidad;
	private List<CatalogoDTO> lstSubsistema;
	private List<CatalogoDTO> lstFuncion;
	private List<CatalogoDTO> lstAsignatura;
	private List<CatalogoDTO> lstMunicipio;
	private List<VacanteDTO> lstVacante;
	private LoginBean loginBean;

	private String centerMap;
	private MapModel simpleModel;
	private InformacionCctDTO informacionCct;
	private Marker marker;
	
	public SegPlazasBean() {
		cct = null;
		idDisponibilidad = "1";
		lstSubsistema = new ArrayList<CatalogoDTO>();
		lstFuncion = new ArrayList<CatalogoDTO>();
		lstAsignatura = new ArrayList<CatalogoDTO>();
		lstMunicipio = new ArrayList<CatalogoDTO>();
		
		try {
			CatalogoDAO catalogoDAO = new CatalogoDaoImpl();
			lstSubsistema = catalogoDAO.getCatalogoItems("ASGSUBSIS");
			lstFuncion = catalogoDAO.getCatalogoItems("ASGFUNASP");
			lstAsignatura = catalogoDAO.getCatalogoItems("ASGTPOEVAL");
			lstMunicipio = catalogoDAO.getCatalogoItems("ASGMPO");
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			loginBean = (LoginBean) session.getAttribute("loginBean");	
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "SegPlazasBean() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void limpiar() {
		idSubsistema = "0";
		idFuncion = "0";
		idAsignatura = "0";
		idMunicipio = "0";
		cct = null;
		idDisponibilidad = "1";
		lstVacante = new ArrayList<VacanteDTO>();
	}
	
	public String consultar() {
		String resultado = null;
		
		try {
			List<CampoRptDTO> lstFiltro = new ArrayList<CampoRptDTO>();
			CampoRptDTO idFiltro = null;

			//Filtros disponibles.
			if (loginBean.getUsuario() != null && loginBean.getUsuario().getIdSubsistema() != null 
					&& !loginBean.getUsuario().getIdSubsistema().trim().equals("") && !loginBean.getUsuario().getIdSubsistema().trim().equals("0")) {
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("SUBSISTEMA");
				idFiltro.setValor(loginBean.getUsuario().getIdSubsistema());
				lstFiltro.add(idFiltro);				
			} else if(idSubsistema != null && !idSubsistema.trim().equals("") && !idSubsistema.trim().equals("0")) {
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("SUBSISTEMA");
				idFiltro.setValor(idSubsistema);
				lstFiltro.add(idFiltro);
			}

			if (loginBean.getUsuario() != null && loginBean.getUsuario().getGpoAsignaturas() != null 
					&& !loginBean.getUsuario().getGpoAsignaturas().trim().equals("")) {
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("GPO_ASIGNATURAS");
				idFiltro.setValor(loginBean.getUsuario().getGpoAsignaturas().trim());
				lstFiltro.add(idFiltro);
			} else if (loginBean.getUsuario() != null && loginBean.getUsuario().getIdAsignatura() != null 
					&& !loginBean.getUsuario().getIdAsignatura().trim().equals("") && !loginBean.getUsuario().getIdAsignatura().trim().equals("0")) {
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("CVE_ASIGNATURA");
				idFiltro.setValor(loginBean.getUsuario().getIdAsignatura());
				lstFiltro.add(idFiltro);				
			} else if(idAsignatura != null && !idAsignatura.trim().equals("") && !idAsignatura.trim().equals("0")) {
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("CVE_ASIGNATURA");
				idFiltro.setValor(idAsignatura);
				lstFiltro.add(idFiltro);
			}

			if(idFuncion != null && !idFuncion.trim().equals("") && !idFuncion.trim().equals("0")) {
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("ID_FUNCION");
				idFiltro.setValor(idFuncion);
				lstFiltro.add(idFiltro);
			}

			if(idMunicipio != null && !idMunicipio.trim().equals("") && !idMunicipio.trim().equals("0")) {
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("CVE_MUNICIPIO");
				idFiltro.setValor(idMunicipio);
				lstFiltro.add(idFiltro);
			}

			if (cct != null && !cct.trim().equals("") ) {
				idFiltro = new CampoRptDTO();
				idFiltro.setClave("CCT");
				idFiltro.setValor(cct.trim().toUpperCase());
				lstFiltro.add(idFiltro);				
			}
			
			if(idDisponibilidad != null && !idDisponibilidad.trim().equals("") && !idDisponibilidad.trim().equals("0")) {
				if(idDisponibilidad.trim().equals("1")) {
					//Disponibles
					idFiltro = new CampoRptDTO();
					idFiltro.setClave("DISPONIBLE");
					idFiltro.setValor("0");
					lstFiltro.add(idFiltro);
				} else if (idDisponibilidad.trim().equals("2")) {
					//No Disponibles
					idFiltro = new CampoRptDTO();
					idFiltro.setClave("NODISPONIBLE");
					idFiltro.setValor("0");
					lstFiltro.add(idFiltro);
				}					
			}
			
			VacanteDAO vacanteDAO = new VacanteDaoImpl();
			lstVacante = vacanteDAO.getVacantes(lstFiltro);
			
			if (lstVacante.isEmpty()) {
				FacesMessage msg = new FacesMessage("No se localizarón vacantes disponibles para los filtros seleccionados.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
			}

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "consultar() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return resultado;
	}
	
	public void marcarCCT(VacanteDTO vacanteCCT) {
		try {
		
			if (vacanteCCT != null && vacanteCCT.getCmpCCT() != null) {
				
				simpleModel = new DefaultMapModel();
				LatLng coordenada = null;
				String etiqueta = null;
				
				if (vacanteCCT.getLongitudMpo() != null && !vacanteCCT.getLongitudMpo().equals("0")
						&& vacanteCCT.getLatitudMpo() != null && !vacanteCCT.getLatitudMpo().equals("0")) {
					
					centerMap = vacanteCCT.getLatitudMpo() + "," + vacanteCCT.getLongitudMpo();
					
					//para la informacion que se mostrara en la marca
					//AspiranteDAO aspiranteDAO = new AspiranteDaoImpl();
					//informacionCct = aspiranteDAO.getInformacionCct(vacanteCCT.getCmpCCT());
					informacionCct = new InformacionCctDTO();
					informacionCct.setCct(vacanteCCT.getCmpCCT());
					informacionCct.setNombre(vacanteCCT.getNomCCT());
					informacionCct.setDomicilio(vacanteCCT.getDomicilio());
					informacionCct.setLocalidad(vacanteCCT.getLocalidad());
					informacionCct.setIdMunicipio(vacanteCCT.getCveMun());
					informacionCct.setNombreMunicipio(vacanteCCT.getMunicipio());
					
					if(vacanteCCT.getLongitudCct() != null && !vacanteCCT.getLongitudCct().equals("0")
							&& vacanteCCT.getLatitudCct() != null && !vacanteCCT.getLatitudCct().equals("0")) {
						coordenada = new LatLng(Double.parseDouble(vacanteCCT.getLatitudCct()), Double.parseDouble(vacanteCCT.getLongitudCct()));
						etiqueta = vacanteCCT.getCmpCCT() + "- " + vacanteCCT.getNomCCT();
					} else {
						//Posicionar en Cabecera municipal
						coordenada = new LatLng(Double.parseDouble(vacanteCCT.getLatitudMpo()), Double.parseDouble(vacanteCCT.getLongitudMpo()));
						etiqueta = "UBICACIÓN NO DISPONIBLE";
					}
					
				} else {
					//Posicionar en Toluca
					centerMap = "19.2922222,-99.6538889";
					coordenada = new LatLng(19.2922222, -99.6538889);
					etiqueta = "UBICACIÓN NO DISPONIBLE";
				}
				
				simpleModel.addOverlay(new Marker(coordenada, etiqueta));

			} else {
				FacesMessage msg = new FacesMessage("Ocurrió un error al recuperar la información de la vacante.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);				
			}
						
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "marcarCCT() - Se generó un error interno en el servidor.") );
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void onMarkerSelect(OverlaySelectEvent event) {  
        marker = (Marker) event.getOverlay();  
    } 

	public String getIdSubsistema() {
		return idSubsistema;
	}

	public void setIdSubsistema(String idSubsistema) {
		this.idSubsistema = idSubsistema;
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

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public List<CatalogoDTO> getLstSubsistema() {
		return lstSubsistema;
	}

	public void setLstSubsistema(List<CatalogoDTO> lstSubsistema) {
		this.lstSubsistema = lstSubsistema;
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

	public List<CatalogoDTO> getLstMunicipio() {
		return lstMunicipio;
	}

	public void setLstMunicipio(List<CatalogoDTO> lstMunicipio) {
		this.lstMunicipio = lstMunicipio;
	}

	public String getIdDisponibilidad() {
		return idDisponibilidad;
	}

	public void setIdDisponibilidad(String idDisponibilidad) {
		this.idDisponibilidad = idDisponibilidad;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public List<VacanteDTO> getLstVacante() {
		return lstVacante;
	}

	public void setLstVacante(List<VacanteDTO> lstVacante) {
		this.lstVacante = lstVacante;
	}

	public String getCenterMap() {
		return centerMap;
	}

	public void setCenterMap(String centerMap) {
		this.centerMap = centerMap;
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}

	public InformacionCctDTO getInformacionCct() {
		return informacionCct;
	}

	public void setInformacionCct(InformacionCctDTO informacionCct) {
		this.informacionCct = informacionCct;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public String getCct() {
		return cct;
	}

	public void setCct(String cct) {
		this.cct = cct;
	}

}
