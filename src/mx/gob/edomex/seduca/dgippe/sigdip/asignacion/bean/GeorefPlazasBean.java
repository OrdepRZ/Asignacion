package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.VacanteDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.MunicipioDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.VacanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl.VacanteDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@ViewScoped
public class GeorefPlazasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MapModel mapModel;
	private List<MunicipioDTO> municipios;
	private List<VacanteDTO> vacantes;
	private List<VacanteDTO> asignaturas;
	private String centroMapa;
	private Integer indiceMunicipio;
	private String nomMunicipio;
	private String icono;
	
	public GeorefPlazasBean(){
		mapModel = new DefaultMapModel();
		indiceMunicipio = 0;
		try {
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			
			VacanteDAO vacanteDAO = new VacanteDaoImpl();
			
			municipios = vacanteDAO.getMunicipiosVacantes();
			
			if(municipios.size() > 0){
				centroMapa = municipios.get(indiceMunicipio).getLatitud() +","+ municipios.get(indiceMunicipio).getLongitud();
				nomMunicipio = municipios.get(indiceMunicipio).getNomMunicipio();
				vacantes = vacanteDAO.getVacantesGeoref(municipios.get(indiceMunicipio).getIdMunicipio());
				asignaturas = vacanteDAO.getAsignaturasGeoref(municipios.get(indiceMunicipio).getIdMunicipio());
				
				if(vacantes.size() > 0){
					for(VacanteDTO vacante: vacantes){
						String icono =  url + "/resources/img/marcas_mapa/" + vacante.getNombreIcono();
						LatLng coord = new LatLng(Double.parseDouble(vacante.getLatitudCct()),Double.parseDouble(vacante.getLongitudCct()));						
						Marker marca = new Marker(coord, vacante.getCmpCCT(),null,icono);

						mapModel.addOverlay(marca);
					}
				}else{
					FacesMessage message = new FacesMessage("No se encontró información de vacantes.");
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null,message);
				}
			}else{
				FacesMessage message = new FacesMessage("No se encontró información de municipios.");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null,message);
			}
			
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
	}
	
	public void getPlazas(){
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		
		mapModel = new DefaultMapModel();
		indiceMunicipio++;
		
		if(indiceMunicipio >= municipios.size()){
			indiceMunicipio = 0;
		}
		
		try {
			VacanteDAO vacanteDAO = new VacanteDaoImpl();
			centroMapa = municipios.get(indiceMunicipio).getLatitud() +","+ municipios.get(indiceMunicipio).getLongitud();
			nomMunicipio = municipios.get(indiceMunicipio).getNomMunicipio();
			vacantes = vacanteDAO.getVacantesGeoref(municipios.get(indiceMunicipio).getIdMunicipio());
			asignaturas = vacanteDAO.getAsignaturasGeoref(municipios.get(indiceMunicipio).getIdMunicipio());
			
			if(vacantes.size() > 0){
				for(VacanteDTO vacante: vacantes){
					/*LatLng coord = new LatLng(Double.parseDouble(vacante.getLatitudCct()),Double.parseDouble(vacante.getLongitudCct()));
					mapModel.addOverlay(new Marker(coord, vacante.getCmpCCT()));*/
					
					String icono =  url + "/resources/img/marcas_mapa/" + vacante.getNombreIcono();
					LatLng coord = new LatLng(Double.parseDouble(vacante.getLatitudCct()),Double.parseDouble(vacante.getLongitudCct()));						
					Marker marca = new Marker(coord, vacante.getCmpCCT(),null,icono);

					mapModel.addOverlay(marca);
				}
			}
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
	}

	public MapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
	}
	public List<VacanteDTO> getVacantes() {
		return vacantes;
	}
	public void setVacantes(List<VacanteDTO> vacantes) {
		this.vacantes = vacantes;
	}

	public List<MunicipioDTO> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<MunicipioDTO> municipios) {
		this.municipios = municipios;
	}

	public String getCentroMapa() {
		return centroMapa;
	}

	public void setCentroMapa(String centroMapa) {
		this.centroMapa = centroMapa;
	}

	public Integer getIndiceMunicipio() {
		return indiceMunicipio;
	}

	public void setIndiceMunicipio(Integer indiceMunicipio) {
		this.indiceMunicipio = indiceMunicipio;
	}

	public String getNomMunicipio() {
		return nomMunicipio;
	}

	public void setNomMunicipio(String nomMunicipio) {
		this.nomMunicipio = nomMunicipio;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public List<VacanteDTO> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<VacanteDTO> asignaturas) {
		this.asignaturas = asignaturas;
	}

}
