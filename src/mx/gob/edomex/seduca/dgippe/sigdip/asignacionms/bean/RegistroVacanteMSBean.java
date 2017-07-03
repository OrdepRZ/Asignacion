package mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.CctDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.CctDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.VacanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl.CctDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.dao.VacanteMSDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.impl.VacanteMSDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.bean.login.LoginBean;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dao.CatalogoDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dto.CatalogoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.impl.CatalogoDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.validador.Validador;

@ManagedBean
@ViewScoped
public class RegistroVacanteMSBean implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private String idCCT;
	private CctDTO cct;
	
	private String idSubsistema;
	private String idTipoVac;
	private String idMotivoVac;
	private String idTipoPlaza;
	private String idAsignatura;
	private String idFuncion;
	private String colonia;
	private String subDirRegional;
	private String zonaEscolar;
	private String horas;
	private Date dFechaInicio;
	private Date dFechaTermino;	
	private String cvePresupuestal;
	
	private boolean bndConsulta;
	private boolean bndRegistro;
	private boolean bndSubsistema;
	private boolean bndPresupuestal;
	
	private List<VacanteDTO> listaVacantes;
	private VacanteDTO vacante;
	
	private List<CatalogoDTO> subsistemas;
	private List<CatalogoDTO> catPzaJornada;
	private List<CatalogoDTO> asignaturas;
	private List<CatalogoDTO> municipios;
	private List<CatalogoDTO> motivosVacante;
	private List<CatalogoDTO> tiposVacante;
	private List<CatalogoDTO> funcionesVacante;
	
	private LoginBean loginBean;
	
	public RegistroVacanteMSBean()
	{
		idCCT = "";
		idSubsistema = "";
		idTipoVac = "";
		idMotivoVac = "";
		idTipoPlaza = "";
		idAsignatura = "";
		idFuncion = "";
		subDirRegional = "";
		zonaEscolar = "";
		horas = "";
		dFechaInicio = new Date();
		dFechaTermino = new Date();	
		cvePresupuestal = "";
		
		bndConsulta = true;
		bndRegistro = false;
		bndSubsistema = false;
		bndPresupuestal = false;
		
		cct = new CctDTO();
		
		verificarSubsistema();		

	}
	
	public void verificarSubsistema()
	{
		try 
		{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			loginBean = (LoginBean) session.getAttribute("loginBean");	
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}	
		
		String idSubsistema = loginBean.getUsuario().getIdSubsistema().trim();

		if (idSubsistema != null && !idSubsistema.equals("") && !idSubsistema.equals("0")) 
		{
			bndSubsistema=true;
			this.idSubsistema=idSubsistema;
		}		
	}	
	
	public void consultar(){
		
		try
		{
			CatalogoDAO catalogoDAO = new CatalogoDaoImpl();
			
			subsistemas = catalogoDAO.getCatalogoItems("ASGMSSUBSIS");
			catPzaJornada = catalogoDAO.getCatalogoItems("ASGTPOVACA");
			municipios = catalogoDAO.getCatalogoItems("ASGMPO");
			funcionesVacante = catalogoDAO.getCatalogoItems("ASGMSFUNASP");
			tiposVacante = catalogoDAO.getCatalogoItems("ASGTPOPLZ");	
			
			motivosVacante = catalogoDAO.getCatalogoFormatoAsign();
			
			idCCT=idCCT.trim();
			
			if(idCCT != null && !idCCT.equals(""))
			{
				Validador IValidador = new Validador();
				if(IValidador.isLetrasNumeros(idCCT) && !IValidador.isNumeros(idCCT) && idCCT.length() == 10) 
				{				
					vacante = new VacanteDTO();
					CctDAO cctDAO = new CctDaoImpl();
					
					String educacion = "MS";
					
					cct = cctDAO.getCCT(idCCT,educacion);

					String idSubCCT=cct.getIdSubsistema().trim();
					String cveCCT=cct.getCmpCCT().trim();
					
					if(cveCCT != null && !cveCCT.equals("") && bndSubsistema && !idSubCCT.equals(idSubsistema))
					{
						FacesMessage message = new FacesMessage("El CCT ingresado no corresponde a su subsistema.");
						message.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null,message);	
					}						
					else if(cveCCT != null && !cveCCT.equals(""))
					{
						bndConsulta = false;
						bndRegistro = true;							
					}	
					else if(idSubCCT == null || idSubCCT.equals(""))
					{
						FacesMessage message = new FacesMessage("El subsistema del CCT ingresado no corresponde a Media Superior.");
						message.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null,message);							
					}						
					else
					{	
						FacesMessage message = new FacesMessage("No se localizó el CCT ingresado.");
						message.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null,message);							
					}	
				}
				else 
				{
					FacesMessage msg = new FacesMessage("CCT debe ser un dato conformado por números y letras de longitud 10.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);									
				}				
			}
			else
			{
				FacesMessage message = new FacesMessage("El campo CCT es requerido, favor de verificar.");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null,message);
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "consultar() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
	
	}
	
	public void consultarVacantes()
	{

		try
		{
			cvePresupuestal=cvePresupuestal.trim();
			
			if(cvePresupuestal != null && !cvePresupuestal.equals(""))
			{	
				List<CampoRptDTO> lstFiltro = new ArrayList<CampoRptDTO>();
				CampoRptDTO idFiltro = new CampoRptDTO();	
				
				idFiltro.setClave("CCT");
				idFiltro.setValor(idCCT.toUpperCase());
				lstFiltro.add(idFiltro);	
				
				idFiltro = new CampoRptDTO();
				
				idFiltro.setClave("CVE_PRESUPUESTAL");
				idFiltro.setValor(cvePresupuestal.trim());//REVISAR DESPÚES SI DEBE LLEVAR MAYÚSCULAS
				lstFiltro.add(idFiltro);				
				
				VacanteMSDAO vacanteMSDAO = new VacanteMSDaoImpl();
				
				listaVacantes = vacanteMSDAO.getVacantes(lstFiltro);		
			}
			if(listaVacantes.isEmpty())
			{
				bndPresupuestal = false;
			}	
			else
			{
				bndPresupuestal = true;	
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				VacanteDTO vacanteAux = null;
		        Iterator<VacanteDTO> itVac = listaVacantes.iterator();	
		        int i=0;	
		        
		        while (itVac.hasNext()) 
		        {	
		        	vacanteAux=itVac.next();

		        	
		            if(vacanteAux.getFechaInicio() != null && !vacanteAux.getFechaInicio().equals(""))
		            {
		            	listaVacantes.get(i).setdFechaInicio(sdf.parse(vacanteAux.getFechaInicio()));
		            }
		            else
		            {
		            	listaVacantes.get(i).setdFechaInicio(null);
		            }
		        
		            if(vacanteAux.getFechaTermino() != null && !vacanteAux.getFechaTermino().equals(""))
		            {
		            	listaVacantes.get(i).setdFechaTermino(sdf.parse(vacanteAux.getFechaTermino()));
		            	if(vacanteAux.getFechaTermino().equals("31/12/2100")) 
		            	{
		            		listaVacantes.get(i).setFechaTermino("DEFINITIVO");
		            	} 
		            } 
		            else 
		            {
		            	listaVacantes.get(i).setdFechaTermino(null);
		            }			        	
		        	
			        i++;
		        }					
							
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "consultarVacantes() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
	
	}	
	
	public void cargarTipoVacante()
	{
		//PARA TIPO DE VACANTE TEMPORAL
		if(idMotivoVac.equals("11") || idMotivoVac.equals("12") || idMotivoVac.equals("13") || idMotivoVac.equals("14") ||
		   idMotivoVac.equals("15") || idMotivoVac.equals("16") || idMotivoVac.equals("17") || idMotivoVac.equals("18") || 
		   idMotivoVac.equals("19") || idMotivoVac.equals("26") || idMotivoVac.equals("88")	|| idMotivoVac.equals("91") || 
		   idMotivoVac.equals("92") || idMotivoVac.equals("93") || idMotivoVac.equals("94") || idMotivoVac.equals("FR") ||
		   idMotivoVac.equals("FS"))
		{
			idTipoVac="2";
			
		}
		//PARA TIPO DE VACANTE DEFINITIVA
		else if(idMotivoVac.equals("10") || idMotivoVac.equals("27") || idMotivoVac.equals("28") || idMotivoVac.equals("29") ||
				idMotivoVac.equals("30") || idMotivoVac.equals("89") || idMotivoVac.equals("90") || idMotivoVac.equals("FQ") ||
				idMotivoVac.equals("FU") || idMotivoVac.equals("FV") || idMotivoVac.equals("FW") || idMotivoVac.equals("FY") || 
				idMotivoVac.equals("FZ") || idMotivoVac.equals("FT") || idMotivoVac.equals("FK") || idMotivoVac.equals("FJ") || 
				idMotivoVac.equals("FI") )
		{
			idTipoVac="3";
		}
	}	
	
	public void cargarAsignaturasMS()
	{
		try
		{
			CatalogoDAO catalogoDAO = new CatalogoDaoImpl();
			CatalogoDTO asignatura = null;
			int idAsignAux=0;
			int idFunAux =  Integer.parseInt(idFuncion.trim());	
			
			List<CatalogoDTO> asignaturasAux = new ArrayList<CatalogoDTO>();
			asignaturas = catalogoDAO.getCatalogoItems("ASGMSASIGN");
	        Iterator<CatalogoDTO> it = asignaturas.iterator();
	        
	        while (it.hasNext()) 
	        {		
	        	asignatura = it.next();
	        	idAsignAux=Integer.parseInt(asignatura.getIdItem().trim());
	        	
	        	//PARA ASIGNATURAS DE DOCENTE
	        	if(idFunAux == 1 && idAsignAux < 28) 
	        	{
	        		asignaturasAux.add(asignatura);
	        	}
	        	//PARA ASIGNATURAS DE ATP
	        	else if(idFunAux == 2 && idAsignAux ==113 ) 
	        	{
	        		asignaturasAux.add(asignatura);
	        	}
	        	//PARA ASIGNATURAS DE DIRECTOR
	        	else if(idFunAux == 3 && idAsignAux > 100 && idAsignAux < 112) 
	        	{
	        		asignaturasAux.add(asignatura);
	        	}
	        	//PARA ASIGNATURAS DE SUPERVISOR
	        	else if(idFunAux == 4 && idAsignAux == 112) 
	        	{
	        		asignaturasAux.add(asignatura);
	        	}	        			        	
			}
	        asignaturas = asignaturasAux;

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "cargarAsignaturasMS() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
	}
		
	
	public String registrarVacante()
	{
		String outcome = "";
		String resultado = "";
		String msjError = "";
		Validador validador = new Validador();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try 
		{
			if(cvePresupuestal != null && !cvePresupuestal.trim().equals(""))
			{			
				if(dFechaInicio != null){
					if(dFechaTermino != null){
						if(validador.isFecha(sdf.format(dFechaInicio), "dd/MM/yyyy")){
							if(validador.isFecha(sdf.format(dFechaTermino), "dd/MM/yyyy")){
								if(dFechaInicio.after(dFechaTermino)){
									msjError = "La fecha de inicio no puede ser posterior a la fecha de término, favor de verificar.\n";
								}
							}else{
								msjError = "Fecha de término incorrecta, favor de verificar.\n";
							}
						}else{
							msjError = "Fecha de inicio incorrecta, favor de verificar.\n";
						}
					}else{
						msjError = "El campo fecha de térrmino no puede estar vacío, favor de verificar.";
					}
				}else{
					msjError = "El campo fecha de inicio no puede estar vacío, favor de verificar.";
				}
				
				if(msjError.equals(""))
				{
					vacante.setCmpCCT(cct.getCmpCCT().trim());
					vacante.setNomCCT(cct.getNomCCT());
					vacante.setTurno(cct.getTurno());
					vacante.setCveMun(cct.getIdMunicipio());
					vacante.setMunicipio(cct.getMunicipio());	
					vacante.setLocalidad(cct.getLocalidad());
					vacante.setDomicilio(cct.getDomicilio());
					
					vacante.setColonia(cct.getColonia());
					vacante.setZonaEscolar(cct.getZonaEscolar());
					vacante.setSubDirRegional(cct.getSubdirRegional());
					vacante.setHoras(horas.trim());
					vacante.setCvePresupuestal(cvePresupuestal.trim());
					
					vacante.setFechaInicio(sdf.format(dFechaInicio));
					vacante.setFechaTermino(sdf.format(dFechaTermino));
					
					vacante.setIdSubsistema(cct.getIdSubsistema());
					vacante.setTpoVacante(idTipoVac);
					vacante.setMotivoVacante(idMotivoVac);
					vacante.setPzajornada(idTipoPlaza);
			
					vacante.setCveAsignatura(idAsignatura);
					vacante.setIdFuncion(idFuncion);
					
					VacanteMSDAO vacanteDAO = new VacanteMSDaoImpl();
					
					String cveUsuario = loginBean.getUsuario().getCveUsuario();
					
					resultado = vacanteDAO.registraVacante(vacante, cveUsuario);
					
					if(resultado.startsWith("00"))
					{
						outcome = "/sigdip/asignacionms/administracion/registroVacanteMS.xhtml?faces-redirect=true";
						
						consultarVacantes();//SE ACTUALIZA LAS VACANTES REGISTRADAS CON LA MISMA CVE_PRESUPUESTAL
						
						FacesMessage message = new FacesMessage(resultado.substring(3));
						message.setSeverity(FacesMessage.SEVERITY_INFO);
						FacesContext.getCurrentInstance().addMessage(null,message);
					}else
					{
						FacesMessage message = new FacesMessage(resultado);
						message.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null,message);
					}
					
				}else
				{
					FacesMessage message = new FacesMessage(msjError);
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null,message);
				}
			}
			else
			{
				FacesMessage message = new FacesMessage("Favor de ingresar la Clave Presupuestal.");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null,message);				
			}			
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "registrarVacante() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
		
		return outcome;
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

	public List<CatalogoDTO> getTiposVacante() {
		return tiposVacante;
	}

	public void setTiposVacante(List<CatalogoDTO> tiposVacante) {
		this.tiposVacante = tiposVacante;
	}

	public List<CatalogoDTO> getFuncionesVacante() {
		return funcionesVacante;
	}

	public void setFuncionesVacante(List<CatalogoDTO> funcionesVacante) {
		this.funcionesVacante = funcionesVacante;
	}

	public String getIdCCT() {
		return idCCT;
	}

	public void setIdCCT(String idCCT) {
		this.idCCT = idCCT;
	}

	public String getIdSubsistema() {
		return idSubsistema;
	}

	public void setIdSubsistema(String idSubsistema) {
		this.idSubsistema = idSubsistema;
	}

	public String getIdTipoVac() {
		return idTipoVac;
	}

	public void setIdTipoVac(String idTipoVac) {
		this.idTipoVac = idTipoVac;
	}

	public String getIdMotivoVac() {
		return idMotivoVac;
	}

	public void setIdMotivoVac(String idMotivoVac) {
		this.idMotivoVac = idMotivoVac;
	}

	public String getIdTipoPlaza() {
		return idTipoPlaza;
	}

	public void setIdTipoPlaza(String idTipoPlaza) {
		this.idTipoPlaza = idTipoPlaza;
	}

	public String getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(String idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	public String getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(String idFuncion) {
		this.idFuncion = idFuncion;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getSubDirRegional() {
		return subDirRegional;
	}

	public void setSubDirRegional(String subDirRegional) {
		this.subDirRegional = subDirRegional;
	}

	public String getZonaEscolar() {
		return zonaEscolar;
	}

	public void setZonaEscolar(String zonaEscolar) {
		this.zonaEscolar = zonaEscolar;
	}

	public String getHoras() {
		return horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}

	public Date getdFechaInicio() {
		return dFechaInicio;
	}

	public void setdFechaInicio(Date dFechaInicio) {
		this.dFechaInicio = dFechaInicio;
	}

	public Date getdFechaTermino() {
		return dFechaTermino;
	}

	public void setdFechaTermino(Date dFechaTermino) {
		this.dFechaTermino = dFechaTermino;
	}

	public String getCvePresupuestal() {
		return cvePresupuestal;
	}

	public void setCvePresupuestal(String cvePresupuestal) {
		this.cvePresupuestal = cvePresupuestal;
	}
	
	public boolean isBndConsulta() {
		return bndConsulta;
	}

	public void setBndConsulta(boolean bndConsulta) {
		this.bndConsulta = bndConsulta;
	}

	public boolean isBndRegistro() {
		return bndRegistro;
	}

	public void setBndRegistro(boolean bndRegistro) {
		this.bndRegistro = bndRegistro;
	}

	public boolean isBndSubsistema() {
		return bndSubsistema;
	}

	public void setBndSubsistema(boolean bndSubsistema) {
		this.bndSubsistema = bndSubsistema;
	}

	public boolean isBndPresupuestal() {
		return bndPresupuestal;
	}

	public void setBndPresupuestal(boolean bndPresupuestal) {
		this.bndPresupuestal = bndPresupuestal;
	}

	public CctDTO getCct() {
		return cct;
	}

	public void setCct(CctDTO cct) {
		this.cct = cct;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	

}