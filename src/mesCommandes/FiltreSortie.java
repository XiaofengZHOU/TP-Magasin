package mesCommandes;
import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

public class FiltreSortie implements Filter {
	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		  String nom = null;
		  HttpServletRequest hrequest = (HttpServletRequest) request;
		
		  
		  //************************************************************
		  // ce filtre doit s'executer après la servlet 
		  // il vide le caddie du client en cours
		  //************************************************************	
		  
		  

		  chain.doFilter(request, response); 
		  HttpSession session = ((HttpServletRequest) request).getSession(true);
		  nom = (String)session.getAttribute("nomClient");
		  Depot.lesCaddy.put(nom, new ArrayList<String>());
		  System.out.println("finishi efface caddy");
	
	}

	public void destroy() {
		this.filterConfig = null;
	}

}
