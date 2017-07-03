package mx.gob.edomex.seduca.dgippe.sigdip.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.edomex.seduca.dgippe.sigdip.bean.login.LoginBean;

public class FiltroAcceso implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		LoginBean loginBean = (LoginBean)((HttpServletRequest)request).getSession().getAttribute("loginBean");
		//AccesoBean loginBean = (AccesoBean)((HttpServletRequest)request).getSession().getAttribute("accesoBean");
		
		// Para el primer request no hay un loginBean en la sesion por lo que el usuario debe loggearse.
		// Para cualquier otro request, existe un loginBean pero debemos revisar si el usuario esta loggeado exitosamente.
		if (loginBean == null || !loginBean.isLoggedIn()) {
		    String contextPath = req.getContextPath();
		    res.sendRedirect(contextPath + "/acceso.xhtml");
		} else {			
		    res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		    res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		    res.setDateHeader("Expires", 0); // Proxies.
		}
		 
		filterChain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
