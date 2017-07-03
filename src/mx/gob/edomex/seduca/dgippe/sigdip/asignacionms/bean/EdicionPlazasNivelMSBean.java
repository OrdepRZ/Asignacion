package mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.CctDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.AspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.CctDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.VacanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl.CctDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.dao.AspiranteMSDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.dao.VacanteMSDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.impl.AspiranteMSDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.impl.VacanteMSDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.bean.login.LoginBean;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dao.CatalogoDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dto.CatalogoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.impl.CatalogoDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.validador.Validador;

@ManagedBean
@ViewScoped
public class EdicionPlazasNivelMSBean implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private String idCCT;
	private List<VacanteDTO> listaVacantes;
	private List<VacanteDTO> listaVacantesReg;	
	private List<AspiranteDTO> listaAspirante;	
	private VacanteDTO vacante;
	private boolean bndConsulta;
	private boolean bndPlazaAsign;
	private boolean bndPlazaNoAsign;
	private boolean bndSubsistema;
	private boolean bndPresupuestal;	
	
	private AspiranteDTO aspirantePlaza;
	private CctDTO cct;
	
	private List<CatalogoDTO> subsistemas;
	private List<CatalogoDTO> catPzaJornada;
	private List<CatalogoDTO> asignaturas;
	private List<CatalogoDTO> municipios;
	private List<CatalogoDTO> motivosVacante;	
	private List<CatalogoDTO> tiposVacante;
	private List<CatalogoDTO> funcionesVacante;
	
	private String motivoVacante;
	private String idSubsistema;
	private String subsistema;	
	private String cvePresupuestal;
	private String cvePresuOriginal;	
	
	private LoginBean loginBean;
	
	public EdicionPlazasNivelMSBean()
	{
		idCCT = "";
		bndConsulta = true;
		bndPlazaAsign = false;
		bndPlazaNoAsign = false;
		bndSubsistema = false;
		bndPresupuestal = false;		
		
		motivoVacante = "";
		idSubsistema = "";
		subsistema = "";	
		cvePresupuestal = "";
		cvePresuOriginal = "";		
		
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
			bndSubsistema = true;
			this.idSubsistema=idSubsistema;
		}
	}
	
	public void consultarVacantes()
	{
		try
		{
			cvePresupuestal=vacante.getCvePresupuestal();
			
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
				
				listaVacantesReg = vacanteMSDAO.getVacantes(lstFiltro);		
			}
			if(listaVacantesReg.isEmpty())
			{
				bndPresupuestal = false;
			}	
			else
			{
				bndPresupuestal = true;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				VacanteDTO vacanteAux = null;
		        Iterator<VacanteDTO> itVac = listaVacantesReg.iterator();	
		        int i=0;	
		        
		        while (itVac.hasNext()) 
		        {	
		        	vacanteAux=itVac.next();

		        	
		            if(vacanteAux.getFechaInicio() != null && !vacanteAux.getFechaInicio().equals(""))
		            {
		            	listaVacantesReg.get(i).setdFechaInicio(sdf.parse(vacanteAux.getFechaInicio()));
		            }
		            else
		            {
		            	listaVacantesReg.get(i).setdFechaInicio(null);
		            }
		        
		            if(vacanteAux.getFechaTermino() != null && !vacanteAux.getFechaTermino().equals(""))
		            {
		            	listaVacantesReg.get(i).setdFechaTermino(sdf.parse(vacanteAux.getFechaTermino()));
		            	if(vacanteAux.getFechaTermino().equals("31/12/2100")) 
		            	{
		            		listaVacantesReg.get(i).setFechaTermino("DEFINITIVO");
		            	} 
		            } 
		            else 
		            {
		            	listaVacantesReg.get(i).setdFechaTermino(null);
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
	
	public void consultar()
	{
		try
		{
			idCCT=idCCT.trim();
			
			if(idCCT != null && !idCCT.equals(""))
			{
				Validador IValidador = new Validador();
				if(IValidador.isLetrasNumeros(idCCT) && !IValidador.isNumeros(idCCT) && idCCT.length() == 10) 
				{					
					CctDAO cctDAO = new CctDaoImpl();
					
					String educacion = "MS";
					
					cct = cctDAO.getCCT(idCCT.toUpperCase(), educacion);				
					
					List<CampoRptDTO> lstFiltro = new ArrayList<CampoRptDTO>();
					CampoRptDTO idFiltro = new CampoRptDTO();
					
					idFiltro.setClave("CCT");
					idFiltro.setValor(idCCT.toUpperCase());
					lstFiltro.add(idFiltro);
					
					if(bndSubsistema)
					{
						idFiltro = new CampoRptDTO();
						
						idFiltro.setClave("SUBSISTEMA");
						idFiltro.setValor(idSubsistema.trim());
						lstFiltro.add(idFiltro);					
					}
					
					VacanteMSDAO vacanteMSDAO = new VacanteMSDaoImpl();
					
					listaVacantes = vacanteMSDAO.getVacantes(lstFiltro);
					
					String idSubCCT=cct.getIdSubsistema().trim();
					
					if(listaVacantes.isEmpty() && bndSubsistema && !idSubCCT.equals(idSubsistema))
					{
						FacesMessage message = new FacesMessage("El CCT ingresado no corresponde a su subsistema.");
						message.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null,message);						
					}									
					else if(listaVacantes.isEmpty())
					{
						FacesMessage message = new FacesMessage("No se localizaron registros con el CCT ingresado.");
						message.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null,message);						
					}
					else if(idSubCCT == null || idSubCCT.equals(""))
					{
						listaVacantes = new ArrayList<VacanteDTO>();
						FacesMessage message = new FacesMessage("El subsistema del CCT ingresado no corresponde a Media Superior.");
						message.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null,message);							
					}				
					else
					{
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
	
	public void consultaAsignacion()
	{
		bndConsulta = false;
		listaVacantes = new ArrayList<VacanteDTO>();
		
		try 
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			CatalogoDAO catalogoDAO = new CatalogoDaoImpl();
			subsistemas = catalogoDAO.getCatalogoItems("ASGMSSUBSIS");
			catPzaJornada = catalogoDAO.getCatalogoItems("ASGTPOVACA");
			asignaturas = catalogoDAO.getCatalogoItems("ASGMSASIGN");
			municipios = catalogoDAO.getCatalogoItems("ASGMPO");
			funcionesVacante = catalogoDAO.getCatalogoItems("ASGMSFUNASP");
			tiposVacante = catalogoDAO.getCatalogoItems("ASGTPOPLZ");
			motivosVacante = catalogoDAO.getCatalogoFormatoAsign();
			
			CatalogoDTO motivoAux = null;
			
			String idMotivoCat="";
			
			String idMotivoVac=vacante.getMotivoVacante().trim();
	        			
			Iterator<CatalogoDTO> itMun = motivosVacante.iterator();
	        
	        while (itMun.hasNext()) 
	        {	
	        	motivoAux=itMun.next();
	        	idMotivoCat=motivoAux.getIdItem().trim();
	        	if(idMotivoCat.equals(idMotivoVac))
	        	{
	        		motivoVacante=motivoAux.getDescItem();
	        	}
	        }		
	        
            if(vacante.getFechaInicio() != null && !vacante.getFechaInicio().equals(""))
            {
            	vacante.setdFechaInicio(sdf.parse(vacante.getFechaInicio()));
            }
            else
            {
            	vacante.setdFechaInicio(null);
            }
        
            if(vacante.getFechaTermino() != null && !vacante.getFechaTermino().equals(""))
            {
            	vacante.setdFechaTermino(sdf.parse(vacante.getFechaTermino()));
            	if(vacante.getFechaTermino().equals("31/12/2100")) 
            	{
            		vacante.setFechaTermino("DEFINITIVO");
            	} 
            } 
            else 
            {
            	vacante.setdFechaTermino(null);
            }	  	        
			
			if(vacante.getFolio() != null && vacante.getFolio() != "" && !vacante.getFolio().equals("0"))
			{	
				List<CampoRptDTO> lstFiltro = new ArrayList<CampoRptDTO>();					
				CampoRptDTO idFiltro = new CampoRptDTO();
				
				idFiltro.setClave("FOLIO");
				idFiltro.setValor(vacante.getFolio());
				lstFiltro.add(idFiltro);	
				
				String idConvocatoria = vacante.getIdConvocatoria();
				if (idConvocatoria != null && !idConvocatoria.equals("") && !idConvocatoria.equals("0")) 
				{				
					idFiltro = new CampoRptDTO();
					idFiltro.setClave("ID_CONVOCATORIA");
					idFiltro.setValor(vacante.getIdConvocatoria());
					lstFiltro.add(idFiltro);	
				}	
				
				String idFuncion = vacante.getIdFuncion();
				if (idFuncion != null && !idFuncion.equals("") && !idFuncion.equals("0")) 
				{					
					idFiltro = new CampoRptDTO();
					idFiltro.setClave("ID_FUNCION");
					idFiltro.setValor(vacante.getIdFuncion());
					lstFiltro.add(idFiltro);
				}					
				
				AspiranteMSDAO aspiranteMSDAO = new AspiranteMSDaoImpl();
				listaAspirante = aspiranteMSDAO.getAspirantes(lstFiltro);
				
				if(listaAspirante.isEmpty()) 
				{
					FacesMessage msg = new FacesMessage("No se localizó el aspirante.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} 
				else 
				{
					aspirantePlaza = listaAspirante.get(0);
				}				
				
				bndPlazaAsign = true;
				bndPlazaNoAsign = false;
			}
			else
			{
				aspirantePlaza = new AspiranteDTO();
				bndPlazaAsign = false;
				bndPlazaNoAsign = true;	
				cvePresuOriginal = vacante.getCvePresupuestal();
				//System.out.println("Clave presupuestal :"+ vacante.getCvePresupuestal());				
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "consultaAsignacion() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
	}
	
	public void cargarTipoVacante()
	{
		String idMotivoVac = vacante.getMotivoVacante().trim();
		
		//PARA TIPO DE VACANTE TEMPORAL
		if(idMotivoVac.equals("11") || idMotivoVac.equals("12") || idMotivoVac.equals("13") || idMotivoVac.equals("14") ||
		   idMotivoVac.equals("15") || idMotivoVac.equals("16") || idMotivoVac.equals("17") || idMotivoVac.equals("18") || 
		   idMotivoVac.equals("19") || idMotivoVac.equals("26") || idMotivoVac.equals("88")	|| idMotivoVac.equals("91") || 
		   idMotivoVac.equals("92") || idMotivoVac.equals("93") || idMotivoVac.equals("94") || idMotivoVac.equals("FR") ||
		   idMotivoVac.equals("FS"))
		{
			vacante.setTpoVacante("TEMPORAL");
			
		}
		//PARA TIPO DE VACANTE DEFINITIVA
		else if(idMotivoVac.equals("10") || idMotivoVac.equals("27") || idMotivoVac.equals("28") || idMotivoVac.equals("29") ||
				idMotivoVac.equals("30") || idMotivoVac.equals("89") || idMotivoVac.equals("90") || idMotivoVac.equals("FQ") ||
				idMotivoVac.equals("FU") || idMotivoVac.equals("FV") || idMotivoVac.equals("FW") || idMotivoVac.equals("FY") || 
				idMotivoVac.equals("FZ") || idMotivoVac.equals("FT") || idMotivoVac.equals("FK") || idMotivoVac.equals("FJ") || 
				idMotivoVac.equals("FI") )
		{
			vacante.setTpoVacante("DEFINITIVA");
		}
	}
	
	public void cargarAsignaturasMS()
	{
		try
		{
			CatalogoDAO catalogoDAO = new CatalogoDaoImpl();
			CatalogoDTO asignatura = null;
			int idAsignAux=0;
			int idFunAux =  Integer.parseInt(vacante.getIdFuncion().trim());	
			
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
	
	
	public String editaPlaza()
	{
		String outcome = "";
		String resultado = "";
		String msjError = "";
		Validador validador = new Validador();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try 
		{
			if(vacante.getCvePresupuestal() != null && !vacante.getCvePresupuestal().trim().equals(""))
			{				
				if(vacante.getdFechaInicio() != null)
				{
					if(vacante.getdFechaTermino() != null)
					{
						if(validador.isFecha(sdf.format(vacante.getdFechaInicio()), "dd/MM/yyyy"))
						{
							if(validador.isFecha(sdf.format(vacante.getdFechaTermino()), "dd/MM/yyyy"))
							{
								if(vacante.getdFechaInicio().after(vacante.getdFechaTermino()))
								{
									msjError = "La fecha de inicio no puede ser posterior a la fecha de término, favor de verificar.\n";
								}
							}
							else
							{
								msjError = "Fecha de término incorrecta, favor de verificar.\n";
							}
						}
						else
						{
							msjError = "Fecha de inicio incorrecta, favor de verificar.\n";
						}
					}
					else
					{
						msjError = "El campo fecha de térrmino no puede estar vacío, favor de verificar.";
					}
				}
				else
				{
					msjError = "El campo fecha de inicio no puede estar vacío, favor de verificar.";
				}
				
				
				if(msjError.equals(""))
				{
					vacante.setFechaInicio(sdf.format(vacante.getdFechaInicio()));
					vacante.setFechaTermino(sdf.format(vacante.getdFechaTermino()));
					
					String cveUsuario = loginBean.getUsuario().getCveUsuario();
					
					VacanteMSDAO vacanteMSDAO = new VacanteMSDaoImpl();
								
					resultado = vacanteMSDAO.actualizaVacanteNivel(vacante, cvePresuOriginal, cveUsuario);
					
					if(resultado.startsWith("00"))
					{
						outcome = "/sigdip/asignacionms/seguimiento/edicionPlazaNivelMS.xhtml?faces-redirect=true";
						
						FacesMessage message = new FacesMessage(resultado.substring(3));
						message.setSeverity(FacesMessage.SEVERITY_INFO);
						FacesContext.getCurrentInstance().addMessage(null,message);
					}
					else
					{
						FacesMessage message = new FacesMessage(resultado.substring(3));
						message.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null,message);
					}
					
				}
				else
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
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "editaPlaza() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
		
		return outcome;
	}
	
	public String getIdCCT() {
		return idCCT;
	}

	public void setIdCCT(String idCCT) {
		this.idCCT = idCCT;
	}

	public List<VacanteDTO> getListaVacantes() {
		return listaVacantes;
	}

	public void setListaVacantes(List<VacanteDTO> listaVacantes) {
		this.listaVacantes = listaVacantes;
	}
	
	public List<VacanteDTO> getListaVacantesReg() {
		return listaVacantesReg;
	}

	public void setListaVacantesReg(List<VacanteDTO> listaVacantesReg) {
		this.listaVacantesReg = listaVacantesReg;
	}	
	
	public List<AspiranteDTO> getListaAspirante() {
		return listaAspirante;
	}

	public void setListaAspirante(List<AspiranteDTO> listaAspirante) {
		this.listaAspirante = listaAspirante;
	}

	public VacanteDTO getVacante() {
		return vacante;
	}

	public void setVacante(VacanteDTO vacante) {
		this.vacante = vacante;
	}

	public boolean isBndConsulta() {
		return bndConsulta;
	}

	public void setBndConsulta(boolean bndConsulta) {
		this.bndConsulta = bndConsulta;
	}

	public boolean isBndPlazaAsign() {
		return bndPlazaAsign;
	}

	public void setBndPlazaAsign(boolean bndPlazaAsign) {
		this.bndPlazaAsign = bndPlazaAsign;
	}

	public boolean isBndPlazaNoAsign() {
		return bndPlazaNoAsign;
	}

	public void setBndPlazaNoAsign(boolean bndPlazaNoAsign) {
		this.bndPlazaNoAsign = bndPlazaNoAsign;
	}

	public boolean isBndSubsistema() {
		return bndSubsistema;
	}

	public void setBndSubsistema(boolean bndSubsistema) {
		this.bndSubsistema = bndSubsistema;
	}

	public AspiranteDTO getAspirantePlaza() {
		return aspirantePlaza;
	}

	public void setAspirantePlaza(AspiranteDTO aspirantePlaza) {
		this.aspirantePlaza = aspirantePlaza;
	}

	public CctDTO getCct() {
		return cct;
	}

	public void setCct(CctDTO cct) {
		this.cct = cct;
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

	public String getMotivoVacante() {
		return motivoVacante;
	}

	public void setMotivoVacante(String motivoVacante) {
		this.motivoVacante = motivoVacante;
	}

	public String getIdSubsistema() {
		return idSubsistema;
	}

	public void setIdSubsistema(String idSubsistema) {
		this.idSubsistema = idSubsistema;
	}
	
	public String getSubsistema() {
		return subsistema;
	}

	public void setSubsistema(String subsistema) {
		this.subsistema = subsistema;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public boolean isBndPresupuestal() {
		return bndPresupuestal;
	}

	public void setBndPresupuestal(boolean bndPresupuestal) {
		this.bndPresupuestal = bndPresupuestal;
	}

	public String getCvePresupuestal() {
		return cvePresupuestal;
	}

	public void setCvePresupuestal(String cvePresupuestal) {
		this.cvePresupuestal = cvePresupuestal;
	}

	public String getCvePresuOriginal() {
		return cvePresuOriginal;
	}

	public void setCvePresuOriginal(String cvePresuOriginal) {
		this.cvePresuOriginal = cvePresuOriginal;
	}
		
}