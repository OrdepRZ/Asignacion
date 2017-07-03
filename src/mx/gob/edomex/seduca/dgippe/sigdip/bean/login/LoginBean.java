package mx.gob.edomex.seduca.dgippe.sigdip.bean.login;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dto.AvisoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dto.UsuarioDTO;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private UsuarioDTO usuario;
	private List<AvisoDTO> lstAvisos;
	private boolean loggedIn;
	
	public LoginBean () {
		usuario = new UsuarioDTO();
	}
	
	public String cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		loggedIn = false;
		
		return "/acceso.xhtml?faces-redirect=true";
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public List<AvisoDTO> getLstAvisos() {
		return lstAvisos;
	}

	public void setLstAvisos(List<AvisoDTO> lstAvisos) {
		this.lstAvisos = lstAvisos;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
}
