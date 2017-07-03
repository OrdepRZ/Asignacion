package mx.gob.edomex.seduca.dgippe.sigdip.bean.usuario;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.gob.edomex.seduca.dgippe.sigdip.bean.login.LoginBean;
import mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dao.UsuarioDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.impl.UsuarioDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.util.validador.Validador;

@ManagedBean
@ViewScoped
public class CambioPwdBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String passwordActual;
	private String passwordNuevo;

	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
		
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
	public String cambiarPassword() {
		String outcome = null;
		
		try {
			Validador validador = new Validador();
			
			String cveUsuario = loginBean.getUsuario().getCveUsuario();
			String pwdUsuario = loginBean.getUsuario().getPwdUsuario();
			UsuarioDAO usuarioDAO = new UsuarioDaoImpl();
			
			//
			boolean bCompararActual = usuarioDAO.compararPassword(passwordActual, pwdUsuario);
			//boolean bCompararNuevo = usuarioDAO.compararPassword(passwordNuevo, pwdUsuario);
			boolean isPassword = validador.isPassword(passwordNuevo);
			
			outcome = "/sigdip/usuario/cambioPwd.xhtml?faces-redirect=true";
			
			if (bCompararActual) {
				if(!cveUsuario.equals(passwordNuevo)) {
					if (!passwordActual.equals(passwordNuevo)) {
						if (isPassword) {
							String resultado = usuarioDAO.cambiarPassword(cveUsuario, passwordNuevo);
							if(resultado.equals("exito")) {
								FacesMessage msg = new FacesMessage("Tu password ha sido actualizado y tendrá efecto la próxima vez que inicies sesión, asegúrate de recordarlo.");
								msg.setSeverity(FacesMessage.SEVERITY_INFO);
								FacesContext.getCurrentInstance().addMessage(null, msg);
								
								outcome = "/sigdip/inicio.xhtml?faces-redirect=true";
								
							} else {
								FacesMessage msg = new FacesMessage("Falló la actualización, inténta nuevamente.");
								msg.setSeverity(FacesMessage.SEVERITY_ERROR);
								FacesContext.getCurrentInstance().addMessage(null, msg);
							}						
						} else {
							FacesMessage msg = new FacesMessage("El password nuevo no cumple los requerimientos de seguridad.");
							msg.setSeverity(FacesMessage.SEVERITY_ERROR);
							FacesContext.getCurrentInstance().addMessage(null, msg);						
						}
					} else {
						FacesMessage msg = new FacesMessage("El password nuevo no debe ser igual al actual.");
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
				} else {
					FacesMessage msg = new FacesMessage("El password no debe ser igual a la clave de usuario.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);					
				}
			} else {
				FacesMessage msg = new FacesMessage("Tu password actual es incorrecto.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);				
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "Se generó un error interno en el servidor.") );
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return outcome;
	}
	
	public void limpiarCaptura() {
		passwordActual = "";
		passwordNuevo = "";
	}
	
	public String getPasswordActual() {
		return passwordActual;
	}
	public void setPasswordActual(String passwordActual) {
		this.passwordActual = passwordActual;
	}
	public String getPasswordNuevo() {
		return passwordNuevo;
	}
	public void setPasswordNuevo(String passwordNuevo) {
		this.passwordNuevo = passwordNuevo;
	}

}
