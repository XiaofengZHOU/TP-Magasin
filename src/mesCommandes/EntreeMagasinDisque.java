package mesCommandes;
import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//@WebServlet("/servlet/entreclient")
public class EntreeMagasinDisque extends HttpServlet {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	   {
			String queryString = request.getQueryString();
			System.out.println("entreeMagasinDisque: "+queryString);	  
			String nomRecu = queryString.split("=")[1];
			Stock stock = new Stock();
			
			HttpSession session = request.getSession(true);
			session.setAttribute("nomClient", nomRecu);
			session.setAttribute("stockCourant", stock);
			
			//  ********************************************************************************************        
			//  Cr閑z deux variables de session : � nom � qui a pour valeur le nom de l抲tilisateur  
			//  et � LeStock �  qui a pour valeur une instance de la classe Stock, 
			//  et appeler la servlet  AfficherLesDisques.java
			//  ********************************************************************************************
			response.sendRedirect("../servlet/afficheDisque");

	     
	     
	     
	     
  //  ********************************************************************************************
		}
   
	public void doPost(HttpServletRequest request,  HttpServletResponse response) throws IOException, ServletException
	      { 
	         doGet(request, response);
	      }
}
