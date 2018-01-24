package mesCommandes;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


//@WebServlet("/servlet/commandeClient")
public class CommanderUnDisque extends HttpServlet {

    public void doGet(HttpServletRequest request,  HttpServletResponse response) 
        throws IOException, ServletException
	{  
		String nom = null;
		HttpSession session = request.getSession(); 
		nom = (String)session.getAttribute("nomClient");
	
	
		//  *********************************************************        
		//   Si la personne dont le nom est dans la session, ne poss鑔e pas de caddie , 
		//                       son caddie est cr殚 dans l抏nsemble des caddies, "Depot.lesCaddy"
		//   C抏st une nouvelle ArrayList qui est rajout閑 dans la TreeMap "lesCaddy"  de la classe � Depot �, 
		//         avec   comme cl� le nom.
		// 
		//  **********************************************************        
		if(Depot.lesCaddy.get(nom)==null)
		{
			Depot.lesCaddy.put(nom, new ArrayList<String>());
		}
		
	  
	  
	  
		//  ***********************************************************  
		ArrayList<String> leCaddie = Depot.lesCaddy.get(nom);
		
		//  **********************************************************        
		//   Si le param鑤re � ordre � est pr閟ent est a comme valeur � ajouter �,
		//  la r閒閞ence du disque pass閑 en param鑤re est rajout閑 dans le panier (ArrayList<String>).
		// 
		//  ***********************************************************
		String queryString = request.getQueryString();
		if(queryString.contains("ajouter"))
		{
			String[] queryString_list = queryString.split("&");
			String reference = queryString_list[0].split("=")[1];
			leCaddie.add(reference);
	         
		}
	
	  
		//**************************************************************** 
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 out.println("<html>");
		 out.println("<head>");
		 out.println("<title>  votre commande </title>");
		 out.println("<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' >");
		 out.println("</head>");
		 out.println("<body bgcolor=\"white\">");
		 out.println("<h3>" + "Bonjour  "+ nom + "  voici  votre commande" + "</h3>");
		 
		//  ************************************************************     
		//   affichage du contenu du caddie par la m閠hode afficherContenuCaddy de � Depot � avec trois param鑤res : 
		//    - le caddie 
		//    - le � PrintWriter � pour pouvoir rajouter ces disques dans la r閜onse HTML,
		//    - le r閜ertoire courant de votre application  "request.getContextPath()"
		//  *************************************************************       
		
		Depot.afficherContenuCaddy(leCaddie, out, request.getContextPath());
		 
		//  *************************************************************
		 out.println(" </table>");
		 out.println("<A HREF=afficheDisque> Vous pouvez commandez un autre disque </A><br> ");
		 out.println("<A HREF=enregistreCommande> Vous pouvez enregistrer votre commande </A><br> ");
		
		 out.println("</body>");
		 out.println("</html>");
	}	



public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
{
     doGet(request, response);
}

}
