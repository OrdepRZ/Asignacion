package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
//import javax.xml.namespace.QName;

/*
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
*/

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.AspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.bean.login.LoginBean;
import mx.gob.edomex.seduca.dgippe.sigdip.util.validador.Validador;
//import mx.gob.edomex.seduca.dgippe.sigdip.ws.cliente.renapo.DatosConsultaCurp;

@ManagedBean
@ViewScoped
public class ValidacionCurpBean implements Serializable
{
	private static final long serialVersionUID = 1L;	
	
	private LoginBean loginBean;
	private AspiranteDTO aspirante;	
	private String curp;	
	private String resultadoCurp;	
	
	public ValidacionCurpBean() 
	{
		curp = "";
		resultadoCurp = "";
		
		try 
		{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			loginBean = (LoginBean) session.getAttribute("loginBean");	
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "ValidacionCurpBean() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public String consultarCurp() 
	{
		String resultado = null;
		
		try
		{
			if(curp != null && !curp.trim().equals("")) 
			{
				Validador IValidador = new Validador();

				if(IValidador.isLetrasNumeros(curp.toUpperCase()) && curp.trim().length() == 18) 
				{
					FacesMessage msg = new FacesMessage("consultarCurp() : El método no está disponible...");
					msg.setSeverity(FacesMessage.SEVERITY_WARN);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}			
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "consultarCurp() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}			
		
		return resultado;
	}
	
	public String consultarCurp2() 
	{
		String resultado = null;
		
		try
		{
			if(curp != null && !curp.trim().equals("")) 
			{
				FacesMessage msg = new FacesMessage("consultarCurp() : El método no está disponible...");
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
			}			
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage((e.getMessage()!=null ? e.getMessage() : "consultarCurp() : Se generó un error interno en el servidor."));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}			
		
		return resultado;
	}	
	
	public void limpiar() 
	{
		curp = null;
		resultadoCurp = null;
	}	
	
	/*
    private static String getSessionID(String xml) 
    {
        String sessionID = "";

        try 
        {
            Document doc = DocumentHelper.parseText(xml);
            Attribute attr = doc.getRootElement().attribute("SessionID");
            sessionID = attr.getText();
        } catch (Exception ignoredException) {
        }

        return sessionID;
    }	*/
	
	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public AspiranteDTO getAspirante() {
		return aspirante;
	}

	public void setAspirante(AspiranteDTO aspirante) {
		this.aspirante = aspirante;
	}
	
	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getResultadoCurp() {
		return resultadoCurp;
	}

	public void setResultadoCurp(String resultadoCurp) {
		this.resultadoCurp = resultadoCurp;
	}	
	
	
}
