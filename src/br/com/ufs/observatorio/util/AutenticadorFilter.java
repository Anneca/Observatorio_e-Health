package br.com.ufs.observatorio.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ufs.observatorio.model.Usuario;

/**
 * Servlet Filter implementation class AutenticadorFilter
 */
@WebFilter("/AdminLTE-2.3.6/*")
public class AutenticadorFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AutenticadorFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = (HttpSession)req.getSession();
		
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		
		if (usuario==null) {
			resp.sendRedirect(req.getContextPath() + "//AdminLTE-2.3.6/Login.xhtml");
		} else {
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
