package mx.gob.edomex.seduca.dgippe.sigdip.util.listener;

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.FactoryFinder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.edomex.seduca.dgippe.sigdip.bean.login.LoginBean;

import org.primefaces.context.RequestContext;

public class TimeoutPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		FacesContext fc = FacesContext.getCurrentInstance();
		RequestContext rc = RequestContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) ec.getResponse();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		
		LoginBean loginBean = (LoginBean) ((HttpServletRequest)request).getSession().getAttribute("loginBean");
		
		System.out.println("Entró a TimeoutPhaseListener.");
		
		if (loginBean == null) {
			//Credenciales inválidas, considerar sesión expirada.
			
			if(ec.isResponseCommitted()) {
				//No es posible el redireccionamiento.
				return;
			}
			
			try {
				System.out.println("TimeoutPhaseListener: Tratará de redireccionar por expiración de sesión.");
				if (( (rc!=null &&  RequestContext.getCurrentInstance().isAjaxRequest()) || (fc!=null && fc.getPartialViewContext().isPartialRequest())) 
						&& fc.getResponseWriter() == null && fc.getRenderKit() == null) {
					
			        response.setCharacterEncoding(request.getCharacterEncoding());
			        RenderKitFactory factory = (RenderKitFactory) FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
			        RenderKit renderKit = factory.getRenderKit(fc,fc.getApplication().getViewHandler().calculateRenderKitId(fc));
			        ResponseWriter responseWriter = renderKit.createResponseWriter(response.getWriter(), null, request.getCharacterEncoding());
			        fc.setResponseWriter(responseWriter);
			        ec.redirect(ec.getRequestContextPath() + "/acceso.xhtml");
			    }
			} catch (IOException ioe) {
				System.out.println("Falló el redireccionamiento la página especificada: /acceso.xhtml");
				throw new FacesException(ioe);
			}
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
	