package mesCommandes;
import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.sql.*;

//@WebServlet("/servlet/enregistreCommande")
public class EnregistrerCommande extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
	
	Connection connexion=null;
    Statement stmt=null;
    PreparedStatement pstmt=null;
 
     public void doGet(HttpServletRequest request,  HttpServletResponse response)
                        throws IOException, ServletException
    {  
    	 String nom = null;
    	 ArrayList<String>  leCaddy = null;
       
	     //  ******************************************************       
	     //   initialisez  nom et  le caddie du client : variable � nom � et  lesdisques �
	     //      
	     //  ****************************************************** 
    	 HttpSession session = request.getSession(); 
 		 nom = (String)session.getAttribute("nomClient");
 		 leCaddy = Depot.lesCaddy.get(nom);
       
       
       
    	 //  ******************************************************
          OuvreBase();
          AjouteNomBase(nom);
          response.setContentType("text/html");
          PrintWriter out = response.getWriter();
          out.println("<html>");
          out.println("<head>");
          out.println("<title>  votre commande </title>");
          out.println("<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' >");
          out.println("</head>");
       
          out.println("<body bgcolor=\"white\">");
          out.println("<a HREF=afficheDisque> Vous pouvez commandez un autre disque </a><br> ");
		  out.println("<A HREF=facturer> Fin de la commande et demande de la facture  de   " + nom.toUpperCase()+" </A><br> ");
          out.println("<h3>" + " Disques rajouté la commande de  " + nom.toUpperCase()  + "</h3>");

	      //  ******************************************************       
	      //   afficher le contenu du caddie
	      //  ******************************************************   
          Depot.afficherContenuCaddy(leCaddy, out, request.getContextPath());
          
          //  **************************************************
          AjouteCaddyBase(nom,leCaddy);
          out.println("<h3>" + "et voici " + nom.toUpperCase()  + "  Voici  l'ensemble de tes commandes  enregistrés " + "</h3>");
          MontreCommandeBase(nom, out, request.getContextPath());
          out.println("</body>");
          out.println("</html>");
       }	
    
     protected void OuvreBase() {
       try {
          Class.forName("org.gjt.mm.mysql.Driver").newInstance(); 
          connexion = DriverManager.getConnection(  "jdbc:mysql://localhost/magasin","root","");
          connexion.setAutoCommit(true);
          stmt = connexion.createStatement();
       }
           catch (Exception E) {         
             log(" -------- problème  " + E.getClass().getName() );
             E.printStackTrace();
          }	
     }
 
     protected void fermeBase() {
       try {
          stmt.close();        
          connexion.close();         
       }
           catch (Exception E) {         
             log(" -------- problème  " + E.getClass().getName() );
             E.printStackTrace();
          }	
     }
     
     protected void AjouteNomBase(String nom) 
     {
	   try 
	   {
	      ResultSet rset = null;
	      pstmt= connexion.prepareStatement("select id from client where nom=?");
		  pstmt.setString(1,nom);
		  rset=pstmt.executeQuery();
		  if (!rset.next())   
	      stmt.executeUpdate("INSERT INTO client (nom)VALUES  ('" +  nom + "' )" );
	   }
	   
	   catch (Exception E) 
	   {
		   log(" - probeme  " + E.getClass().getName() );
		   E.printStackTrace();
	   }	
     }

     protected void AjouteCaddyBase(String nom, ArrayList<String> lesdisques ) {
       ResultSet rset = null;
       int cle =0;
       try 
       {
    	   
	        //  ********************************************************       
	        //   ajoutez le contenu du caddie dans la  base de donn閑s. � table commande � 
	    	//   utilisez une PreparedStatement JDBC de pr閒閞ence
	        //  ********************************************************  
    	    pstmt= connexion.prepareStatement("select id from client where nom=?");
    	    pstmt.setString(1,nom);
  		  	rset=pstmt.executeQuery();
  		  	if (rset.next())
  		  	{
  		  		cle = rset.getInt(1);
  		  		for (String disque:lesdisques)
  		  		{
  		  			System.out.println(disque);
  		  			stmt.executeUpdate("INSERT INTO commande (nomarticle,client) VALUES  ('" +  disque + "'," + cle + ")" );
  		  			
  		  		}
  		  	}
  		  	
	
	 
	    	   
	          //  ******************************************************
       }
         
       catch (Exception E) 
       {        
             log(" - problème  ajoute " + E.getClass().getName() );
             E.printStackTrace();
       }	
    }
 
     protected void MontreCommandeBase(String nom, PrintWriter out, String repertoire) 
     {
       ResultSet rset = null;
       ResultSet rs = null;
       Disque leDisque=null;
       int cle =0;
       try 
       {
    	   
	       //  *********************************************************      
	       //   affichez les disques que client a command�  dans la  base de donn閑s. � table commande � 
	       // vous pouvez utiliser "afficherDisquesDansBase" avec une instance de "Resulset" de la table commande
	       //  **********************************************************  

    	   pstmt= connexion.prepareStatement("select id from client where nom=?");
   	       pstmt.setString(1,nom);
 		   rset=pstmt.executeQuery();
 		   if (rset.next())
 		   {
 		  		cle = rset.getInt(1);
 		  		pstmt= connexion.prepareStatement("select nomarticle from commande where client=?");
 	   	        pstmt.setInt(1, cle);
 	 		    rs=pstmt.executeQuery();
 		  		Depot.afficherDisquesDansBase(rs, out, repertoire);
 		   }
 
       //  ********************************************************* 
       }           
           catch (Exception E) {   
	           out.println("erreur base");         
               log(" - probeme ajoute " + E.getClass().getName() );
               E.printStackTrace();
  
           }	  
     }

 
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
       doGet(request, response);
    }
 

}
