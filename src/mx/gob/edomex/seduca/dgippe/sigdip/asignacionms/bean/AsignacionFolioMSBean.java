package mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

//Deprecated
//import com.mx.gob.ServiciosSello.SelloDigital;
/*
import mx.gob.edomex.seduca.dgippe.sigdip.ws.cliente.sello4.ArchivoFirmado;
import mx.gob.edomex.seduca.dgippe.sigdip.ws.cliente.sello4.GeneraSelloDigitalImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.ws.cliente.sello4.SelloDigital;
*/

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.FirmanteDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.ArchivoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.AspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.FirmanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.InformacionCctDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.VacanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl.FirmanteDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.dao.AspiranteMSDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.dao.VacanteMSDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.impl.AspiranteMSDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.impl.VacanteMSDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.bean.login.LoginBean;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dao.CatalogoDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dto.CatalogoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.impl.CatalogoDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.db.Conexion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.validador.Validador;

@ManagedBean
@ViewScoped
public class AsignacionFolioMSBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//private String idConvocatoria;
	private String folio;
	//private List<CatalogoDTO> lstConvocatoria;
	private List<AspiranteDTO> lstAspirante;
	private LoginBean loginBean;
	
	private String idSubsistema;
	private String idMunicipio;
	private String cct;
	private List<CatalogoDTO> lstMunicipio;
	private List<CatalogoDTO> lstSubsistema;
	private List<VacanteDTO> lstVacante;
	private VacanteDTO vacanteSeleccionada;
	private AspiranteDTO aspirante;
	private InformacionCctDTO informacionCct;
	
	private AspiranteDTO aspiranteFormato;
	private String formato;
	
	private boolean bndConsulta;
	private boolean bndAsignacion;
	private boolean bndRenuncia;
	private boolean bndNombramientos;
	
	private String idMotivoRechazo;
	private String observacionRechazo;
	
	private List<CatalogoDTO> lstMotivoRechazo;
	
	private String centerMap;
	private MapModel simpleModel;
	
	private Marker marker;
	
	public AsignacionFolioMSBean() {
		mostrarConsulta();
		//lstConvocatoria = new ArrayList<CatalogoDTO>();
		informacionCct = new InformacionCctDTO();
		
		try {
			/*
			//Cargar lista de Convocatorias.
			CatalogoDAO catalogoDAO = new CatalogoDaoImpl();
			lstConvocatoria = catalogoDAO.getCatalogoItems("ASGCONVOC");
			*/

			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			loginBean = (LoginBean) session.getAttribute("loginBean");	
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "ConsultaAtpBean() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void limpiar() {
		//idConvocatoria = "0";
		folio = null;
		lstAspirante = new ArrayList<AspiranteDTO>();
	}

	public void limpiarPlazas() {
		String idSubsistemaUsu = loginBean.getUsuario().getIdSubsistema();
		if (idSubsistemaUsu != null && !idSubsistemaUsu.trim().equals("") && !idSubsistemaUsu.trim().equals("0")) {
			idSubsistema = idSubsistemaUsu;
		} else {
			idSubsistema = aspirante.getSubsistema();
		}
		idMunicipio = "0";
		cct = "";
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
	
	public String inicializarAsignacion() {
		String resultado = null;
		
		lstVacante = new ArrayList<VacanteDTO>();
		
		try {
			if (aspirante != null) {
				//aspirante = lstAspirante.get(0); //Recupera aspirante
				
				if (aspirante.getBndAsignar() != null && aspirante.getBndAsignar().equals("1")) {
					//Cargar lista de Municipios.
					CatalogoDAO catalogoDAO = new CatalogoDaoImpl();
					lstSubsistema = catalogoDAO.getCatalogoItems("ASGMSSUBSIS");
					lstMunicipio = catalogoDAO.getCatalogoItems("ASGMPO");

					//Restringir la búsqueda del subsistema.
					if (aspirante.getSubsistema() != null && !aspirante.getSubsistema().trim().equals("") && !aspirante.getSubsistema().trim().equals("0")) {
						idSubsistema = aspirante.getSubsistema();
					} else if (loginBean.getUsuario().getIdSubsistema() != null && !loginBean.getUsuario().getIdSubsistema().trim().equals("") && !loginBean.getUsuario().getIdSubsistema().trim().equals("0")) {
						idSubsistema = loginBean.getUsuario().getIdSubsistema();
					}
					
					mostrarAsignacion();
				} else {
					FacesMessage msg = new FacesMessage("El aspirante " + aspirante.getNombre() + " con folio " + aspirante.getFolioFederal() + " no puede ser asignado, consulte restricciones.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
				
			} else {
				FacesMessage msg = new FacesMessage("inicializarAsignacion() : Ocurrió un error al recuperar la información del aspirante.");
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
				
				if (aspirante.getBndAsignar() != null && aspirante.getBndAsignar().equals("1")) {
					if (aspirante.getCveAsignatura() != null && !aspirante.getCveAsignatura().trim().equals("")) {
						if ( (idMunicipio != null && !idMunicipio.trim().equals("") && !idMunicipio.trim().equals("0")) 
								|| (idSubsistema != null && !idSubsistema.trim().equals("") && !idSubsistema.trim().equals("0"))
								|| (cct != null && !cct.trim().equals("")) ) {
							List<CampoRptDTO> lstFiltro = new ArrayList<CampoRptDTO>();
							CampoRptDTO idFiltro = new CampoRptDTO();
							
							idFiltro.setClave("CVE_ASIGNATURA");
							idFiltro.setValor(aspirante.getCveAsignatura());
							lstFiltro.add(idFiltro);

							idFiltro = new CampoRptDTO();
							idFiltro.setClave("ID_FUNCION");
							idFiltro.setValor(aspirante.getIdFuncion());
							lstFiltro.add(idFiltro);					

							String idSubsistemaUsu = loginBean.getUsuario().getIdSubsistema();
							if (idSubsistemaUsu != null && !idSubsistemaUsu.trim().equals("") && !idSubsistemaUsu.trim().equals("0")) {
								idFiltro = new CampoRptDTO();
								idFiltro.setClave("SUBSISTEMA");
								idFiltro.setValor(idSubsistemaUsu);
								lstFiltro.add(idFiltro);								
							} else if (idSubsistema != null && !idSubsistema.trim().equals("") && !idSubsistema.trim().equals("0")) {
								idFiltro = new CampoRptDTO();
								idFiltro.setClave("SUBSISTEMA");
								idFiltro.setValor(idSubsistema);
								lstFiltro.add(idFiltro);								
							}

							if (idMunicipio != null && !idMunicipio.trim().equals("") && !idMunicipio.trim().equals("0")) {
								idFiltro = new CampoRptDTO();
								idFiltro.setClave("CVE_MUNICIPIO");
								idFiltro.setValor(idMunicipio);
								lstFiltro.add(idFiltro);								
							}

							if (cct != null && !cct.trim().equals("")) {
								idFiltro = new CampoRptDTO();
								idFiltro.setClave("CCT");
								idFiltro.setValor(cct);
								lstFiltro.add(idFiltro);
							}
							
							idFiltro = new CampoRptDTO();
							idFiltro.setClave("DISPONIBLE");
							idFiltro.setValor("0");
							lstFiltro.add(idFiltro);
							
							VacanteMSDAO vacanteDAO = new VacanteMSDaoImpl();
							lstVacante = vacanteDAO.getVacantes(lstFiltro);
							
							if (lstVacante.isEmpty()) {
								FacesMessage msg = new FacesMessage("No se localizarón vacantes disponibles para la asignatura y municipios seleccionados.");
								msg.setSeverity(FacesMessage.SEVERITY_ERROR);
								FacesContext.getCurrentInstance().addMessage(null, msg);
								
							}
						} else {
							FacesMessage msg = new FacesMessage("Por favor, selecciona al menos un filtro.");
							msg.setSeverity(FacesMessage.SEVERITY_ERROR);
							FacesContext.getCurrentInstance().addMessage(null, msg);
						}
						
					} else {
						FacesMessage msg = new FacesMessage("consultarPlazas() : Ocurrió un error al recuperar la clave de asignatura del aspirante.");
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null, msg);										
					}
				} else {
					FacesMessage msg = new FacesMessage("El aspirante " + aspirante.getNombre() + " con folio " + aspirante.getFolioFederal() + " ya no puede ser asignado, consulte restricciones." );
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
		vacanteSeleccionada = vacante;
	}
	
	public String asignarPlaza() {
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
					String respuesta = aspiranteDAO.asignarVacante(folio, idConvocatoria, idFuncion, idVacante, idSubsistema, cveUsuario);
					
					aspiranteFormato = null;
					if (respuesta.startsWith("00")) {				
						this.folio = aspirante.getFolioFederal();
						consultarFolio();
						
						//Emitir mensaje de asignación.
						respuesta = respuesta.substring(3); //Eliminar "00|"
						FacesMessage msg = new FacesMessage(respuesta);
						msg.setSeverity(FacesMessage.SEVERITY_INFO);
						FacesContext.getCurrentInstance().addMessage(null, msg);

						//Generar nombramiento.
						/*
						aspiranteFormato = aspirante;
						formato = "OTRO";
						generarFormato();
						*/
						
						mostrarConsulta();
					} else {
						FacesMessage msg = new FacesMessage(respuesta);
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

	public void prepararGenNombramiento(VacanteDTO vacante) {
		vacanteSeleccionada = vacante;
		formato = "OTRO";
	}
	
	public String generarNombramiento() {
		String resultado = null;
		
		//Se realiza está asignación porque debe ser el actual aspirante.
		aspiranteFormato = aspirante;
		
		try {
			if(vacanteSeleccionada != null  
					&& vacanteSeleccionada.getFolio() != null && !vacanteSeleccionada.getFolio().trim().equals("") && !vacanteSeleccionada.getFolio().trim().equals("0")
					&& vacanteSeleccionada.getIdConvocatoria() != null && !vacanteSeleccionada.getIdConvocatoria().trim().equals("") 
					&& vacanteSeleccionada.getIdSubsistema() != null && !vacanteSeleccionada.getIdSubsistema().trim().equals("")
							&& vacanteSeleccionada.getMotivoVacante() != null && !vacanteSeleccionada.getMotivoVacante().trim().equals("") && !vacanteSeleccionada.getMotivoVacante().trim().equals("0")) {

					String folioFederal = vacanteSeleccionada.getFolio();
					String idConvocatoria = vacanteSeleccionada.getIdConvocatoria();
					String idFuncion = vacanteSeleccionada.getIdFuncion();
					String idSubsistema = vacanteSeleccionada.getIdSubsistema();
					String idVacante = vacanteSeleccionada.getCve();
					//String motivoVacante = vacanteSeleccionada.getMotivoVacante();
					
					boolean existeNombramiento = false;
					ArchivoDTO archivoNombramiento = null;
															
					String fileName = folioFederal + "-" + idVacante + ".pdf";
					
					String rutaRecursos = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/");
					
					String nombreJASPER = null;
					Map<String, Object> params = new HashMap<String, Object>();
					

					//Nombramiento.
					AspiranteMSDAO aspiranteDAO = new AspiranteMSDaoImpl();
					archivoNombramiento = aspiranteDAO.getNombramiento(folioFederal, idConvocatoria, idFuncion, idVacante);
					if (archivoNombramiento != null 
							&& archivoNombramiento.getFolioFeder() != null && archivoNombramiento.getFolioFeder().equals(folioFederal)
							&& archivoNombramiento.getIdConvocatoria() != null && archivoNombramiento.getIdConvocatoria().equals(idConvocatoria)
							&& archivoNombramiento.getIdVacante() != null && archivoNombramiento.getIdVacante().equals(idVacante)) {
						existeNombramiento = true;
					}
					
					if (!existeNombramiento) {
						fileName = "NOMBRAMIENTO_" + fileName;
						nombreJASPER = "/jasper/nombramientoms.jasper";

						/*
						if (motivoVacante == null || motivoVacante.equals("") || motivoVacante.equals("0")) {
							FacesMessage msg = new FacesMessage("La vacante seleccionada no tiene asociado un motivo de nombramiento.");
							msg.setSeverity(FacesMessage.SEVERITY_ERROR);
							FacesContext.getCurrentInstance().addMessage(null, msg);
							return null;								
						}
						*/
						
						params.put("P_FOLIOFEDER", folioFederal);
						params.put("P_ID_CONVOCATORIA", idConvocatoria);
						params.put("P_ID_VACANTE", idVacante);
						params.put("P_RUTA_RECURSOS", rutaRecursos);
					}
						
					
					//Se genera el formato, siempre que existeNombramiento es falso.
					byte[] bytes = null;
					if (!existeNombramiento) {
						Conexion cnn = new Conexion();
						cnn.conectar();
						
						String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(nombreJASPER);
						JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, params, cnn.getConexion());
						
						bytes = JasperExportManager.exportReportToPdf(jasperPrint);
						
						cnn.desconectar();
					}

					//Implementación de sello digital
					if (!existeNombramiento) {
						//Sellar el nombramiento generado.
						List<CampoRptDTO> lstFiltroFirmante = new ArrayList<CampoRptDTO>();
						CampoRptDTO idFiltroFirmante = new CampoRptDTO();						
						idFiltroFirmante.setClave("ID_SUBSISTEMA");
						idFiltroFirmante.setValor(idSubsistema);
						lstFiltroFirmante.add(idFiltroFirmante);

						FirmanteDAO firmanteDAO = new FirmanteDaoImpl();
						FirmanteDTO firmanteDTO = firmanteDAO.getFirmante(lstFiltroFirmante);

						if(firmanteDTO != null){
							
							/*
							 * WS Sello digital 
							ArchivoFirmado archivoFirmado = null;
							
							if (loginBean.getUsuario().getLstPermiso().contains("funAsignaSelloDigitalMS")) {
								//Deprecated
								//bytes = SelloDigital.aplicarSelloDigital(firmanteDTO.getUsuario(), firmanteDTO.getPassword(), firmanteDTO.getKeyId(), bytes, fileName);
													
								SelloDigital servicioSello = new SelloDigital();
								GeneraSelloDigitalImpl generaSello = servicioSello.getServicioSello();				
								archivoFirmado = generaSello.aplicarSelloDigitalBytes(firmanteDTO.getUsuario(), firmanteDTO.getPassword(), firmanteDTO.getKeyId(), bytes, fileName);
							}
							
							
							//if (bytes == null) { //Deprecated
							if (archivoFirmado == null) {
								FacesMessage msg = new FacesMessage("Ocurrió un error al recuperar archivo firmado digitalmente.");
								msg.setSeverity(FacesMessage.SEVERITY_ERROR);
								FacesContext.getCurrentInstance().addMessage(null, msg);
								return null;							
							} else {
								//Almacenar archivo en la BD.
								ArchivoDTO archivoDTO = new ArchivoDTO();
								archivoDTO.setFolioFeder(folioFederal);
								archivoDTO.setIdConvocatoria(idConvocatoria);
								archivoDTO.setIdFuncion(idFuncion);
								archivoDTO.setIdVacante(idVacante);
								archivoDTO.setNombreArchivo(fileName);
								//archivoDTO.setArhivo(bytes); //Deprecated
								archivoDTO.setArhivo(archivoFirmado.getDocumento());
								archivoDTO.setSecuencia(archivoFirmado.getSecuencia());
								archivoDTO.setUsuAlta(loginBean.getUsuario().getCveUsuario());
								
								bytes = archivoFirmado.getDocumento();
								
								String respuesta = aspiranteDAO.almacenarNombramiento(archivoDTO);
								
								if (respuesta.equals("00")) {
									//Recuperar datos del objeto almacenado.
									archivoNombramiento = aspiranteDAO.getNombramiento(folioFederal, idConvocatoria, idFuncion, idVacante);
								} else {
									FacesMessage msg = new FacesMessage("Ocurrió un error al almacenar el nombramiento firmado. \n" + respuesta);
									msg.setSeverity(FacesMessage.SEVERITY_ERROR);
									FacesContext.getCurrentInstance().addMessage(null, msg);
								}
							}
							
							*/
						} else {
							FacesMessage msg = new FacesMessage("Ocurrió un error al recuperar la información del firmante.");
							msg.setSeverity(FacesMessage.SEVERITY_ERROR);
							FacesContext.getCurrentInstance().addMessage(null, msg);
							return null;
						}
					} else {
						//Envíar archivo recuperado
						fileName = archivoNombramiento.getNombreArchivo();
						bytes = archivoNombramiento.getArhivo();
					}

					if (archivoNombramiento != null && archivoNombramiento.getUrl() != null && !archivoNombramiento.getUrl().equals("")) {
						RequestContext.getCurrentInstance().execute("window.open(\"" + archivoNombramiento.getUrl() + "\",\""+fileName+"\",\"toolbar=yes,scrollbars=yes,resizable=yes\");");
					} else {
						FacesMessage msg = new FacesMessage("Ocurrió un error al recuperar la URL del documento.");
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return null;						
					}
					
					/*
					FacesContext context = FacesContext.getCurrentInstance();
					HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
					
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
					response.getOutputStream().write(bytes);
					response.setStatus(HttpServletResponse.SC_OK);
					response.getOutputStream().close();					
					
					context.responseComplete();
					*/					
				
			} else {
				FacesMessage msg = new FacesMessage("No se pudo recuperar la información de la asignación o es insuficiente");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);				
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "generarNombramiento() - Se generó un error interno en el servidor.") );
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return resultado;
	}

	public String generarChecklist() {
		String resultado = null;
		
		//Se realiza está asignación porque debe ser el actual aspirante.
		aspiranteFormato = aspirante;
		
		try {
			if(vacanteSeleccionada != null  
					&& vacanteSeleccionada.getFolio() != null && !vacanteSeleccionada.getFolio().trim().equals("") && !vacanteSeleccionada.getFolio().trim().equals("0")
					&& vacanteSeleccionada.getIdConvocatoria() != null && !vacanteSeleccionada.getIdConvocatoria().trim().equals("") 
					&& vacanteSeleccionada.getIdSubsistema() != null && !vacanteSeleccionada.getIdSubsistema().trim().equals("")
					&& vacanteSeleccionada.getMotivoVacante() != null && !vacanteSeleccionada.getMotivoVacante().trim().equals("") && !vacanteSeleccionada.getMotivoVacante().trim().equals("0")) {

					String folioFederal = vacanteSeleccionada.getFolio();
					String idConvocatoria = vacanteSeleccionada.getIdConvocatoria();
					String idFuncion = vacanteSeleccionada.getIdFuncion();
					//String idSubsistema = vacanteSeleccionada.getIdSubsistema();
					String idVacante = vacanteSeleccionada.getCve();				
															
					String fileName = folioFederal + "-" + idVacante + ".pdf";
					String imgEdomex = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/img/gemcenter.jpg");
					String imgGrande = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/img/engrande.jpg");
					
					String nombreJASPER = null;
					Map<String, Object> params = new HashMap<String, Object>();
										
					//CHECK LIST DOCUMENTACIÓN
					fileName = "RECEPCION_DOC_" + fileName;
					nombreJASPER = "/jasper/checklist_doc_ms.jasper";

					params.put("P_FOLIOFEDER", folioFederal);
					params.put("P_ID_CONVOCATORIA", idConvocatoria);
					params.put("P_ID_FUNCION", idFuncion);
					params.put("P_ID_VACANTE", idVacante);
					params.put("P_IMG_GEM", imgEdomex);
					params.put("P_IMG_EG", imgGrande);

					
					//Se genera el formato, siempre que existeNombramiento es falso.
					byte[] bytes = null;
					

					Conexion cnn = new Conexion();
					cnn.conectar();
					
					String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(nombreJASPER);
					JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, params, cnn.getConexion());
					
					bytes = JasperExportManager.exportReportToPdf(jasperPrint);
					
					cnn.desconectar();
					
					FacesContext context = FacesContext.getCurrentInstance();
					HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
					
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
					response.getOutputStream().write(bytes);
					response.setStatus(HttpServletResponse.SC_OK);
					response.getOutputStream().close();					
					
					context.responseComplete();					
				
			} else {
				FacesMessage msg = new FacesMessage("No se pudo recuperar la información de la asignación o es insuficiente");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);				
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "generarNombramiento() - Se generó un error interno en el servidor.") );
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return resultado;
	}
	
	public String generarFormato() {
		String resultado = null;
		
		try {
			if(aspiranteFormato != null  && aspiranteFormato.getFolioFederal() != null && !aspiranteFormato.getFolioFederal().trim().equals("")
					&& aspiranteFormato.getIdConvocatoria() != null && !aspirante.getIdConvocatoria().trim().equals("") 
					&& aspiranteFormato.getCveAsignatura() != null && !aspirante.getCveAsignatura().trim().equals("") && !aspirante.getCveAsignatura().trim().equals("0")
					&& aspiranteFormato.getStatus() != null && !aspiranteFormato.getStatus().trim().equals("") && !aspiranteFormato.getStatus().trim().equals("0")) {
				
				String cveStatus = aspiranteFormato.getStatus().trim();
				
				if (cveStatus.equals("2") || cveStatus.equals("3")) {
					String folioFederal = aspiranteFormato.getFolioFederal();
					String idConvocatoria = aspiranteFormato.getIdConvocatoria();
					String idFuncion = aspiranteFormato.getIdFuncion();
					String idSubsistema = aspiranteFormato.getSubsistema();
					
					//Para generación de Check list.
					if (formato != null && formato.equals("CHECK")) {
						formato = "CHECK";
					} else {
						formato = "OTRO";
					}
					
					String fileName = folioFederal + ".pdf";
					
					String imgEdomex = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/img/gemcenter.jpg");
					String imgGrande = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/img/engrande.jpg");
					String imgCECYTEM = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/img/cecytem.jpg");

					String nombreJASPER = null;
					String idReporte = null;
					Map<String, Object> params = new HashMap<String, Object>();
					
					if(cveStatus.equals("2") && formato.equals("CHECK")) {
						//CHECK LIST DOCUMENTACIÓN
						fileName = "RECEPCION_DOC_" + fileName;
						nombreJASPER = "/jasper/check_list_doc.jasper";

						params.put("P_FOLIOFEDER", folioFederal);
						params.put("P_ID_CONVOCATORIA", idConvocatoria);
						params.put("P_ID_FUNCION", idFuncion);
						params.put("P_IMG_GEM", imgEdomex);
						params.put("P_IMG_EG", imgGrande);

					} else if(cveStatus.equals("3")) {
						String idMotivoRechazo = "0";
						AspiranteMSDAO aspiranteDAO = new AspiranteMSDaoImpl();
						idMotivoRechazo = aspiranteDAO.getIdMotivoRechazoAspirante(folioFederal, idConvocatoria, idFuncion); 

						List<String> lstCNA = Arrays.asList("2", "3", "5");
						List<String> lstACNP = Arrays.asList("4", "6", "10");
						List<String> lstORAFR = Arrays.asList("9", "1", "7", "8", "11", "12");
						List<String> lstACNFNAN = Arrays.asList("14");
						List<String> lstORP = Arrays.asList("21");
						
						if (idMotivoRechazo != null && !idMotivoRechazo.trim().equals("0") && !idMotivoRechazo.trim().equals("")) {
							if (lstCNA.contains(idMotivoRechazo)) {
								//CARTA DE NO ACEPTACION.
								fileName = "NO_ACEPTACION_" + fileName;

								if (idSubsistema.equals("1013")) {
									nombreJASPER = "/jasper/carta_no_acept_CECYTEM.jasper";
									idReporte = idFuncion.equals("1") ? "CNA_CECYTEM_ING" : "CNA_CECYTEM_PRO";
									params.put("P_IMG_CECYTEM", imgCECYTEM);
								} else {
									nombreJASPER = "/jasper/carta_no_acept_ms.jasper";
									idReporte = idFuncion.equals("1") ? "CNA_MS_ING" : "CNA_MS_PRO";
								}

								params.put("P_FOLIOFEDER", folioFederal);
								params.put("P_ID_CONVOCATORIA", idConvocatoria);
								params.put("P_ID_FUNCION", idFuncion);
								params.put("P_ID_RPT", idReporte);
								params.put("P_IMG_GEM", imgEdomex);
								params.put("P_IMG_EG", imgGrande);						

							} else if (lstACNP.contains(idMotivoRechazo)) {
								//ACTA NO PRESENTACION
								fileName = "NO_PRESENTACION_" + fileName;
																
								if (idSubsistema.equals("1013")) {
									nombreJASPER = "/jasper/acta_no_presenta_CECYTEM.jasper";
									idReporte = idFuncion.equals("1") ? "ANP_CECYTEM_ING" : "ANP_CECYTEM_PRO";
									params.put("P_IMG_CECYTEM", imgCECYTEM);
								} else {
									nombreJASPER = "/jasper/acta_no_presenta_ms.jasper";
									idReporte = idFuncion.equals("1") ? "ANP_MS_ING" : "ANP_MS_PRO";
								}

								params.put("P_FOLIOFEDER", folioFederal);
								params.put("P_ID_CONVOCATORIA", idConvocatoria);
								params.put("P_ID_FUNCION", idFuncion);
								params.put("P_ID_RPT", idReporte);
								params.put("P_IMG_GEM", imgEdomex);
								params.put("P_IMG_EG", imgGrande);
								
							} else if (lstORAFR.contains(idMotivoRechazo)) {
								//OFICIO DE RECHAZO ASPIRANTE
								fileName = "OFICIO_RECHAZO_" + fileName;
								if (idSubsistema.equals("1013")) {
									nombreJASPER = "/jasper/oficio_rechazo_CECYTEM.jasper";
									idReporte = idFuncion.equals("1") ? "OR_CECYTEM_ING" : "OR_CECYTEM_PRO";
									params.put("P_IMG_CECYTEM", imgCECYTEM);
								} else {
									nombreJASPER = "/jasper/oficio_rechazo_ms.jasper";
									idReporte = idFuncion.equals("1") ? "OR_MS_ING" : "OR_MS_PRO";
								}
								
								params.put("P_FOLIOFEDER", folioFederal);
								params.put("P_ID_CONVOCATORIA", idConvocatoria);
								params.put("P_ID_FUNCION", idFuncion);
								params.put("P_ID_RPT", idReporte);
								params.put("P_IMG_GEM", imgEdomex);
								params.put("P_IMG_EG", imgGrande);
								
							} else if (lstACNFNAN.contains(idMotivoRechazo)) {
								//NEGATIVA FIRMA
								fileName = "NEGATIVA_FIRMA_" + fileName;
								if (idSubsistema.equals("1013")) {
									nombreJASPER = "/jasper/negativa_acept_nombr_CECYTEM.jasper";
									idReporte = idFuncion.equals("1") ? "NF_CECYTEM_ING" : "NF_CECYTEM_PRO";
									params.put("P_IMG_CECYTEM", imgCECYTEM);
								} else {
									nombreJASPER = "/jasper/negativa_acept_nombr_ms.jasper";
									idReporte = idFuncion.equals("1") ? "NF_MS_ING" : "NF_MS_PRO";
								}
								
								params.put("P_FOLIOFEDER", folioFederal);
								params.put("P_ID_CONVOCATORIA", idConvocatoria);
								params.put("P_ID_FUNCION", idFuncion);
								params.put("P_ID_RPT", idReporte);
								params.put("P_IMG_GEM", imgEdomex);
								params.put("P_IMG_EG", imgGrande);
								
							} else if (lstORP.contains(idMotivoRechazo)) { //Agregado por ATD
								//OFICIO DE RENUNCIA A LA PRELACIÓN
								fileName = "OFICIO_RENUNCIA_" + fileName;
								//nombreJASPER = "/jasper/negativa_acept_nombr_ba.jasper";
								nombreJASPER = idFuncion.equals("1") ? "/jasper/oficio_renuncia_ms_ing.jasper":"/jasper/oficio_renuncia_ms_pro.jasper";
								idReporte = idFuncion.equals("1") ? "ORP_MS_ING" : "ORP_MS_PRO";

								params.put("P_FOLIOFEDER", folioFederal);
								params.put("P_ID_CONVOCATORIA", idConvocatoria);
								params.put("P_ID_FUNCION", idFuncion);
								params.put("P_ID_RPT", idReporte);
								params.put("P_IMG_GEM", imgEdomex);
								params.put("P_IMG_EG", imgGrande);	
								params.put("P_NOM_CAT", "ASGMSFUNASP");
								params.put("P_NOM_CAT_SUB", "ASGMSSUBSIS");

							} else {
								FacesMessage msg = new FacesMessage("No existe un formato para el motivo de rechazo " +idMotivoRechazo + ".");
								msg.setSeverity(FacesMessage.SEVERITY_ERROR);
								FacesContext.getCurrentInstance().addMessage(null, msg);
								return null;															
							}
						} else {
							FacesMessage msg = new FacesMessage("No se localizó el motivo de rechazo o el tipo de convocatoria.");
							msg.setSeverity(FacesMessage.SEVERITY_ERROR);
							FacesContext.getCurrentInstance().addMessage(null, msg);
							return null;							
						}
					} 
					
					byte[] bytes = null;

					Conexion cnn = new Conexion();
					cnn.conectar();
					
					String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(nombreJASPER);
					JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, params, cnn.getConexion());
					
					bytes = JasperExportManager.exportReportToPdf(jasperPrint);
					
					cnn.desconectar();
					
					FacesContext context = FacesContext.getCurrentInstance();
					HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
					
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
					response.getOutputStream().write(bytes);
					response.setStatus(HttpServletResponse.SC_OK);
					response.getOutputStream().close();					
					
					context.responseComplete();					
				} else {
					FacesMessage msg = new FacesMessage("Para generar un formato, el aspirante debe tener estatus ASIGNADO o RECHAZADO.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
				
			} else {
				FacesMessage msg = new FacesMessage("No se pudo recuperar la información del aspirante o el tipo formato requerido, inténta nuevamente.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);				
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "generarFormato() - Se generó un error interno en el servidor.") );
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return resultado;
	}

	public String inicializarRenuncia() {
		String resultado = null;
		idMotivoRechazo = "0";
		observacionRechazo = null;
		
		try {
			CatalogoDAO catalogoDAO = new CatalogoDaoImpl();
			lstMotivoRechazo = catalogoDAO.getCatalogoItems("ASGMTVRECH");
			
			mostrarRenuncia();
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "inicializarRenuncia() - Se generó un error interno en el servidor.") );
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return resultado;
	}
	
	public String registrarRenuncia() {
		String resultado = null;
		
		try {
			if (aspirante != null && aspirante.getFolioFederal() != null && !aspirante.getFolioFederal().trim().equals("")) {
				if (idMotivoRechazo != null && !idMotivoRechazo.trim().equals("") && !idMotivoRechazo.trim().equals("0")) {
					String folioFederal = aspirante.getFolioFederal();
					String idConvocatoria = aspirante.getIdConvocatoria();
					String idFuncion = aspirante.getIdFuncion();
					String usuario = loginBean.getUsuario().getCveUsuario();
					
					AspiranteMSDAO aspiranteDAO = new AspiranteMSDaoImpl();
					String respuesta = aspiranteDAO.registrarRenuncia(folioFederal, idConvocatoria, idFuncion, idMotivoRechazo, observacionRechazo, usuario);
					
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
					FacesMessage msg = new FacesMessage("Motivo de rechazo es requerido, por favor selecciona uno.");
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

	public void cancelarAsignacion() {
		aspirante = null;
		mostrarConsulta();
	}

	public void regresarAsignacion() {
		idMotivoRechazo = "0";
		mostrarAsignacion();
	}

	public void cancelarNombramiento() {
		aspirante = null;
		mostrarConsulta();
	}
	
	private void mostrarConsulta() {
		bndConsulta = true;
		bndAsignacion = false;
		bndRenuncia = false;
		bndNombramientos = false;
	}

	private void mostrarAsignacion() {
		bndConsulta = false;
		bndAsignacion = true;
		bndRenuncia = false;
		bndNombramientos = false;
	}

	private void mostrarRenuncia() {
		bndConsulta = false;
		bndAsignacion = false;
		bndRenuncia = true;
		bndNombramientos = false;
	}

	private void mostrarNombramientos() {
		bndConsulta = false;
		bndAsignacion = false;
		bndRenuncia = false;
		bndNombramientos = true;
	}

	public void onMarkerSelect(OverlaySelectEvent event) {  
        marker = (Marker) event.getOverlay();  
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

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public List<CatalogoDTO> getLstMunicipio() {
		return lstMunicipio;
	}

	public void setLstMunicipio(List<CatalogoDTO> lstMunicipio) {
		this.lstMunicipio = lstMunicipio;
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

	public boolean isBndRenuncia() {
		return bndRenuncia;
	}

	public void setBndRenuncia(boolean bndRenuncia) {
		this.bndRenuncia = bndRenuncia;
	}

	public List<CatalogoDTO> getLstMotivoRechazo() {
		return lstMotivoRechazo;
	}

	public void setLstMotivoRechazo(List<CatalogoDTO> lstMotivoRechazo) {
		this.lstMotivoRechazo = lstMotivoRechazo;
	}

	public String getIdMotivoRechazo() {
		return idMotivoRechazo;
	}

	public void setIdMotivoRechazo(String idMotivoRechazo) {
		this.idMotivoRechazo = idMotivoRechazo;
	}

	public String getObservacionRechazo() {
		return observacionRechazo;
	}

	public void setObservacionRechazo(String observacionRechazo) {
		this.observacionRechazo = observacionRechazo;
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}

	public String getCenterMap() {
		return centerMap;
	}

	public void setCenterMap(String centerMap) {
		this.centerMap = centerMap;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public InformacionCctDTO getInformacionCct() {
		return informacionCct;
	}

	public void setInformacionCct(InformacionCctDTO informacionCct) {
		this.informacionCct = informacionCct;
	}

	public String getIdSubsistema() {
		return idSubsistema;
	}

	public void setIdSubsistema(String idSubsistema) {
		this.idSubsistema = idSubsistema;
	}

	public String getCct() {
		return cct;
	}

	public void setCct(String cct) {
		this.cct = cct;
	}

	public List<CatalogoDTO> getLstSubsistema() {
		return lstSubsistema;
	}

	public void setLstSubsistema(List<CatalogoDTO> lstSubsistema) {
		this.lstSubsistema = lstSubsistema;
	}

	public boolean isBndNombramientos() {
		return bndNombramientos;
	}

	public void setBndNombramientos(boolean bndNombramientos) {
		this.bndNombramientos = bndNombramientos;
	}

	/*
	public String getIdConvocatoria() {
		return idConvocatoria;
	}

	public void setIdConvocatoria(String idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}

	public List<CatalogoDTO> getLstConvocatoria() {
		return lstConvocatoria;
	}

	public void setLstConvocatoria(List<CatalogoDTO> lstConvocatoria) {
		this.lstConvocatoria = lstConvocatoria;
	}
	*/
	
}
