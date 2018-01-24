package mesCommandes;
import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//@WebServlet("/servlet/afficheDisque")
public class AfficherLesDisques extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		String nom = null;
		Stock stockDisponible = null;
		
		HttpSession session = request.getSession(true);
		nom = (String) session.getAttribute("nomClient");
		stockDisponible = (Stock) session.getAttribute("stockCourant");
				
		//  ********************************************************************************************        
		//   initialisez  nom et  stockDisponible  � partir des variables de session.
		//      pour l抜nstant il n抏st pas fait de test pour savoir si ces variables existent, par la suite
		//      ce test sera fait par un filtre.
		//  ********************************************************************************************                             	
		
		
		//  ********************************************************************************************  
	   response.setContentType("text/html");
	   PrintWriter out = response.getWriter();
	   out.println("<html>");
	   out.println("<head>");
	   out.println("<title> Commande de disques </title>");
	   out.println("<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' >");
	   out.println("</head>");
	   out.println("<body bgcolor=\"white\">");
	   out.println("<h1> Super Marché du disque </h1>");
	   out.println("<h3>" + "Bonjour " + nom.toUpperCase() + " vous pouvez commander un disque" + "</h3>");

		//  ********************************************************************************************        
		// Appel de la m閠hode. afficherDisquesEnVente de Depot, avec trois param鑤res : 
		//    - le stockDisponible, la variable que vous venez d'initialiser
		//    - le � PrintWriter � pour pouvoir rajouter ces disques dans la page de la r閜onse HTML,
		//    - et le repertoire courant de votre application  "request.getContextPath()"
		// 
		//  ********************************************************************************************                   
		 
	   Depot depot = new Depot();
	   Depot.afficherDisquesEnVente(stockDisponible, out, request.getContextPath());


	   
	   out.println("<h1> Super Marché du disque </h1>");                  
	   out.println("</body>");
	   out.println("</html>");
	} 


	public void doPost(HttpServletRequest request,  HttpServletResponse response) throws IOException, ServletException
	{
	   doGet(request, response);
	}

}
