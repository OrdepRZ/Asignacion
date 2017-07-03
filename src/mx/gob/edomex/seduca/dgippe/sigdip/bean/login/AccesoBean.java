package mx.gob.edomex.seduca.dgippe.sigdip.bean.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dao.UsuarioDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.impl.UsuarioDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dto.AvisoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dto.UsuarioDTO;

@ManagedBean
@RequestScoped
public class AccesoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String usuario;
	private String password;
	private UsuarioDTO usuarioDTO;
	private boolean loggedIn;
	
	public AccesoBean() {
		usuarioDTO = new UsuarioDTO();
	}

	public String validarAcceso() {
		String resultado = null;
		
		try {
			if(usuario != null && !usuario.trim().equals("") && password != null && !password.trim().equals("")) {
				UsuarioDAO usuarioDAO = new UsuarioDaoImpl();
				usuarioDTO = usuarioDAO.getUsuario(usuario);
				
				if (usuarioDTO != null) {
					if (usuarioDAO.compararPassword(password, usuarioDTO.getPwdUsuario())) {

						//Obtener avisos.
						List<CampoRptDTO> lstFiltro = new ArrayList<CampoRptDTO>();
						
						//AvisoDAO avisoDAO = new AvisoDaoImpl();
						List<AvisoDTO> lstAvisos = new ArrayList<AvisoDTO>(); //avisoDAO.getAvisos(lstFiltro);
						
						//loggedIn = true;
						FacesContext facesContext = FacesContext.getCurrentInstance();		
						HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
						LoginBean loginBean = new LoginBean();
						loginBean.setUsuario(usuarioDTO);
						loginBean.setLoggedIn(true);
						loginBean.setLstAvisos(lstAvisos);
						session.setAttribute("loginBean", loginBean);
						
						resultado = "/sigdip/inicio.xhtml?faces-redirect=true";
					} else {
						FacesMessage msg = new FacesMessage("La contraseña es incorrecta, por favor, verífica.");
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null, msg);															
					}
				} else {
					FacesMessage msg = new FacesMessage("El usuario no existe en la BD, por favor, verífica.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);									
				}
				
			} else {
				FacesMessage msg = new FacesMessage("Usuario y Contraseña requeridos.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);				
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "Error en el inicio de sesión, inténta nuevamente."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return resultado;
	}
		
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
	
}
