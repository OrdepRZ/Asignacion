package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.VacanteDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.AspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.VacanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl.VacanteDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dao.CatalogoDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dto.CatalogoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.impl.CatalogoDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.validador.Validador;

@ManagedBean
@ViewScoped
public class EdicionPlazasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cct;
	private List<VacanteDTO> listaVacantes;
	private VacanteDTO vacante;
	private AspiranteDTO aspirantePlaza;
	private boolean muestraPlaza;
	private boolean muestraPrelado;
	private List<CatalogoDTO> subsistemas;
	private List<CatalogoDTO> catPzaJornada;
	private List<CatalogoDTO> asignaturas;
	private List<CatalogoDTO> municipios;
	private List<CatalogoDTO> motivosVacante;
	
	
	public EdicionPlazasBean(){
		cct = "";
		muestraPlaza = false;
		muestraPrelado = false;
		aspirantePlaza = new AspiranteDTO();
	}
	
	public void consultar(){
		
		try{
			if(cct != null && cct != ""){
				List<CampoRptDTO> lstFiltro = new ArrayList<CampoRptDTO>();
				CampoRptDTO idFiltro = new CampoRptDTO();
				
				idFiltro.setClave("CCT");
				idFiltro.setValor(cct.trim().toUpperCase());
				lstFiltro.add(idFiltro);
				
				VacanteDAO vacanteDAO = new VacanteDaoImpl();
				
				listaVacantes = vacanteDAO.getVacantes(lstFiltro);
				
			}else{
				FacesMessage message = new FacesMessage("El campo CCT es requerido, favor de verificar.");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null,message);
			}
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void consultaAsignacion(){
		muestraPlaza = true;
		listaVacantes = new ArrayList<VacanteDTO>();
		
		try {
			CatalogoDAO catalogoDAO = new CatalogoDaoImpl();
			subsistemas = catalogoDAO.getCatalogoItems("ASGSUBSIS");
			catPzaJornada = catalogoDAO.getCatalogoItems("ASGTPOVACA");
			asignaturas = catalogoDAO.getCatalogoItems("ASGTPOEVAL");
			municipios = catalogoDAO.getCatalogoItems("ASGMPO");
			motivosVacante = catalogoDAO.getCatalogoItems("ASGMOTNOMB");
			
			if(vacante.getFolio() != null && vacante.getFolio() != "" && !vacante.getFolio().equals("0")){
				VacanteDAO vacanteDAO = new VacanteDaoImpl();
				
				aspirantePlaza = vacanteDAO.getVacanteAspirante(vacante.getFolio());
				
				muestraPrelado = true;
			}else{
				aspirantePlaza = new AspiranteDTO();
				muestraPrelado = false;
			}
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
	}
	
	public String editaPlaza(){
		String outcome = "";
		String resultado = "";
		String msjError = "";
		Validador validador = new Validador();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			if(vacante.getdFechaInicio() != null){
				if(vacante.getdFechaTermino() != null){
					if(validador.isFecha(sdf.format(vacante.getdFechaInicio()), "dd/MM/yyyy")){
						if(validador.isFecha(sdf.format(vacante.getdFechaTermino()), "dd/MM/yyyy")){
							if(vacante.getdFechaInicio().after(vacante.getdFechaTermino())){
								msjError = "La fecha de inicio no puede ser posterior a la fecha de término, favor de verificar.\n";
							}
						}else{
							msjError = "Fecha de término incorrecta, favor de verificar.\n";
						}
					}else{
						msjError = "Fecha de inicio incorrecta, favor de verificar.\n";
					}
				}else{
					msjError = "El campo fecha de término no puede estar vacío, favor de verificar.";
				}
			}else{
				msjError = "El campo fecha de inicio no puede estar vacío, favor de verificar.";
			}
			
			
			
			if(msjError.equals("")){
				vacante.setFechaInicio(sdf.format(vacante.getdFechaInicio()));
				vacante.setFechaTermino(sdf.format(vacante.getdFechaTermino()));
				
				VacanteDAO vacanteDAO = new VacanteDaoImpl();
				
				resultado = vacanteDAO.actualizaVacante(vacante);
				
				if(resultado.startsWith("00")){
					outcome = "/sigdip/asignacion/edicion/edicionPlaza.xhtml?faces-redirect=true";
					
					FacesMessage message = new FacesMessage(resultado.substring(3));
					message.setSeverity(FacesMessage.SEVERITY_INFO);
					FacesContext.getCurrentInstance().addMessage(null,message);
				}else{
					FacesMessage message = new FacesMessage(resultado.substring(3));
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null,message);
				}
				
			}else{
				FacesMessage message = new FacesMessage(msjError);
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null,message);
			}
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return outcome;
	}
	
	public String getCct() {
		return cct;
	}

	public void setCct(String cct) {
		this.cct = cct;
	}

	public List<VacanteDTO> getListaVacantes() {
		return listaVacantes;
	}

	public void setListaVacantes(List<VacanteDTO> listaVacantes) {
		this.listaVacantes = listaVacantes;
	}

	public VacanteDTO getVacante() {
		return vacante;
	}

	public void setVacante(VacanteDTO vacante) {
		this.vacante = vacante;
	}

	public boolean isMuestraPlaza() {
		return muestraPlaza;
	}

	public void setMuestraPlaza(boolean muestraPlaza) {
		this.muestraPlaza = muestraPlaza;
	}

	public AspiranteDTO getAspirantePlaza() {
		return aspirantePlaza;
	}

	public void setAspirantePlaza(AspiranteDTO aspirantePlaza) {
		this.aspirantePlaza = aspirantePlaza;
	}

	public boolean isMuestraPrelado() {
		return muestraPrelado;
	}

	public void setMuestraPrelado(boolean muestraPrelado) {
		this.muestraPrelado = muestraPrelado;
	}

	public List<CatalogoDTO> getSubsistemas() {
		return subsistemas;
	}

	public void setSubsistemas(List<CatalogoDTO> subsistemas) {
		this.subsistemas = subsistemas;
	}

	public List<CatalogoDTO> getCatPzaJornada() {
		return catPzaJornada;
	}

	public void setCatPzaJornada(List<CatalogoDTO> catPzaJornada) {
		this.catPzaJornada = catPzaJornada;
	}

	public List<CatalogoDTO> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<CatalogoDTO> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public List<CatalogoDTO> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<CatalogoDTO> municipios) {
		this.municipios = municipios;
	}

	public List<CatalogoDTO> getMotivosVacante() {
		return motivosVacante;
	}

	public void setMotivosVacante(List<CatalogoDTO> motivosVacante) {
		this.motivosVacante = motivosVacante;
	}
}
