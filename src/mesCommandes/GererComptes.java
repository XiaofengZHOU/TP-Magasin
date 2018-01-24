package mesCommandes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


//@WebServlet("/servlet/voirCookies")
public class GererComptes extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String nomRecu, motPasseRecu,email,telephone;
         String inscription, connexion;
         String nomCookie, motPasseCookie;
         Cookie[] cookies ;
         
         String queryString = request.getQueryString();
         System.out.println("GererCompte "+queryString);
         
         if(queryString.contains("inscrire"))
         {
	         String[] queryString_list = queryString.split("&");
	         nomRecu = queryString_list[0].split("=")[1];
	         motPasseRecu = queryString_list[1].split("=")[1];
	         email = queryString_list[2].split("=")[1];
	         telephone = queryString_list[3].split("=")[1];
	         
	         if(nomRecu.length()>4 && motPasseRecu.length()>4 )
	         {
	        	 System.out.println("name mdp are ok ");
	        	 Cookie c = new Cookie(nomRecu, motPasseRecu);
	        	 c.setMaxAge(60000); 
	        	 response.addCookie(c);
	        	 response.sendRedirect("formulaire?inscriptionFaite");       	 
	         }
	         else
	         {
	        	 System.out.println("name mdp are not ok ");
	        	 response.sendRedirect("formulaire?erreur=inscription");
	         }
         }
         
         if(queryString.contains("connect"))
         {
        	 
        	 String[] queryString_list = queryString.split("&");
        	 nomRecu = queryString_list[0].split("=")[1];
	         motPasseRecu = queryString_list[1].split("=")[1];
	         System.out.println(nomRecu+motPasseRecu);
	         cookies = request.getCookies();
	         String reponse = Util.rechercheCookies(cookies, nomRecu);
	         System.out.println(reponse);
	         if(reponse.equals(motPasseRecu))
	         {
	        	 System.out.println("log in success ");
	        	 response.sendRedirect("entreclient?nomClient="+nomRecu);
	         }
	         else
	         {
	        	 response.sendRedirect("formulaire?erreur=connect");
	         }
	         
         }
         


     //  ********************************************************************************************        
     //  initialisation des différents paramètres possibles
     //  ********************************************************************************************    
              
     //   cas 1    paramètre inscrire  présent
//         Si les informations passées sont acceptables (nom et mot de passe > 4 caractères)
//           un cookie est créé avec comme nom, le nom passé et comme valeur, le mot de passe passé
//           puis appel à la servlet "Renseigner" pour la connexion avec les paramètres inscriptionFaite à true et le nom passé
//         sinon appel à la servlet "Renseigner" pour l'inscription avec le paramètre erreur
     //  ********************************************************************************************            
            
     //   cas 2    paramètre connecter  présent
//          si le parametre nom est absent appel à la servlet Renseigner avec demande = connexion
//          autrement, on vérifie que le nom et le mot de passe passés se trouvent dans un cookie
//          
//           si oui  appel à la servlet "EntreeMagasinDisque" avec comme paramètre  le nom passé
//           sinon appel à la servlet "Renseigner" pour la connexion avec le paramètre erreur
     //  ********************************************************************************************          
     
        }       
  
     // doGet(HttpServletRequest
         
public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
      { 
         doGet(request, response);
      }   

}
