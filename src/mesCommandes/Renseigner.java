package mesCommandes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


//@WebServlet("/servlet/formulaire")
public class Renseigner extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String demande, nomRecu, erreur, inscriptionFaite;


     //  ********************************************************************************************        
     //  initialisation des différents paramètres possibles
     //  ********************************************************************************************    
         String queryString = request.getQueryString();
         System.out.println("connect: "+queryString);
         
         if(request.getQueryString().equals("connect"))
         {
        	 response.setContentType("text/html");
             PrintWriter out = response.getWriter();
             out.println("vous êtes dans la servlet Renseignement, modifiez la"
             		+ "<form action=\"/TP-Magasin/servlet1/voirCookies\" >"
             		+ "Identifiant: <input type=\"text\" name=\"id\"><br>"
             		+ "Mot de passe: <input type=\"password\" name=\"mdp\"><br>"
             		+ "<input type=\"submit\" name='connect' value=\"Connect\">"
             		+ "</form>");
         }
         if(request.getQueryString().equals("inscrire"))
         {
        	 response.setContentType("text/html");
	         PrintWriter out = response.getWriter();
	         out.println("vous êtes dans la servlet inscrire, modifiez la"
	          		+ "<form action=\"/TP-Magasin/servlet1/voirCookies\">"
	          		+ "identifiant: <input type=\"text\" name=\"id\"><br>"
	          		+ "Mot de passe: <input type=\"password\" name=\"mdp\"><br>"
	          		+ "Email: <input type=\"text\" name=\"Email\"><br>"
	         		+ "Telephone: <input type=\"text\" name=\"telephone\"><br>"
	          		+ "<input type=\"submit\" name='inscrire' value='Inscrire'>"
	          		+ "</form>"); 
         }
         if(request.getQueryString().contains("erreur"))
         {
        	 if(request.getQueryString().contains("erreur=inscription"))
        	 {
	        	 response.setContentType("text/html");
		         PrintWriter out = response.getWriter();
		         out.println("les informations sont incorrect. Id et mdp doivent etre plus long que 4");
	        	 out.println( "<form action=\"/TP-Magasin/servlet1/voirCookies\">"
 	          				+ "identifiant: <input type=\"text\" name=\"id\"><br>"
 	          				+ "Mot de passe: <input type=\"password\" name=\"mdp\"><br>"
 	          				+ "Email: <input type=\"text\" name=\"Email\"><br>"
 	          				+ "Telephone: <input type=\"text\" name=\"telephone\"><br>"
 	          				+ "<input type=\"submit\" name='inscrire' value='Inscrire'>"
 	          				+ "</form>");
        	 }
        	 
        	 if(request.getQueryString().contains("erreur=connect"))
        	 {
        		 String userName = "";
            	 response.setContentType("text/html");
    	         PrintWriter out = response.getWriter();
        		 Cookie[] cookies;
    	         Cookie cookie;
    	         cookies = request.getCookies();
    	         cookie = cookies[cookies.length-1];
    	         userName = cookie.getName();         
    	         out.println("id ou mdp sont incorrect. Veillez reessayer"
    	             		+ "<form action=\"/TP-Magasin/servlet1/voirCookies\" >"
    	             		+ "Identifiant: <input type=\"text\" name=\"id\" value=" + userName + "><br>"
    	             		+ "Mot de passe: <input type=\"password\" name=\"mdp\"><br>"
    	             		+ "<input type=\"submit\" name='connect' value=\"Connect\">"
    	             		+ "</form>");
        	 }
         }
         if(request.getQueryString().equals("inscriptionFaite"))
         {
        	 String userName = "";
        	 response.setContentType("text/html");
	         PrintWriter out = response.getWriter();
	         out.println("votre informations sont bien enregistre");
	         Cookie[] cookies;
	         Cookie cookie;
	         cookies = request.getCookies();
	         cookie = cookies[cookies.length-1];
	         userName = cookie.getName();         
	         out.println("vous êtes dans la servlet Renseignement, modifiez la"
	             		+ "<form action=\"/TP-Magasin/servlet1/voirCookies\" >"
	             		+ "Identifiant: <input type=\"text\" name=\"id\" value=" + userName + "><br>"
	             		+ "Mot de passe: <input type=\"password\" name=\"mdp\"><br>"
	             		+ "<input type=\"submit\" name='connect' value=\"Connect\">"
	             		+ "</form>");
	         
         }

     //  ********************************************************************************************           
         
         
         
         
        //  ********************************************************************************************            		 
       	//   cas 1   inscription       paramètre demande = "inscription"        
        //       Si le paramètre "erreur" est présent, informez que les informations transmises ne sont pas acceptables.
     	//   Demandez des informations (nom, mot de passe, email, téléphone) par un formulaire
     	//   et rappel de la  servlet   "GererComptes" avec ces informations en paramètre pour enregistrer ces valeurs
        //   le paramètre inscrire est envoyé avec comme valeur inscrire (bouton submit)
        //  ********************************************************************************************       
              
              
          //  ********************************************************************************************            		 
          //   cas 2   connection     paramètre demande = "connexion"        
          //       Si le paramètre "erreur" est présent, informez que les informations transmises ne sont pas acceptables,
          //     et sortir la valeur de erreur
          //       Si le paramètre inscriptionFaite est présent, il vient de s'inscrire, on rajoute un message comme quoi 
          //    l'inscription  s'est bien réalisée et dans le formulaire on initialise le nom avec le nom 
          //       reçu en paramètre.
          //   Demandez des informations (nom, mot de passe) par un formulaire
          //   et rappel de la  servlet   "GererComptes" avec ces informations en paramètres pour vérifier ces valeurs
          //   le paramètre connecter est envoyé avec comme valeur connecter (bouton submit)
          //  ********************************************************************************************         
    
   }  // doGet(HttpServletRequest
         
public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
      { 
         doGet(request, response);
      }   

}
