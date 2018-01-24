package mesCommandes;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;

public class Depot {
	public static TreeMap<String, ArrayList<String> > lesCaddy = new TreeMap<String, ArrayList<String> >();
    //String 

	
	static public  void afficherContenuCaddy ( ArrayList<String> caddy, PrintWriter out, String repertoire)
	{  Disque leDisque;
	   Iterator <String> iter = caddy.iterator();
	   out.println("<table border=1>");
		while (iter.hasNext()){
			  leDisque = Stock.trouveDisque((String)iter.next());
			  out.println( "<tr> <td>" + leDisque.getTitre() + "  ,  " + leDisque.getNom() + " ,  "  + leDisque.getPrix() + " Euros  ,  Année :" + leDisque.getAnnee()  +"</td>");
	          out.println("<td> <IMG SRC= '" + repertoire + "/images/" + leDisque.getImage() +"'  BORDER=0> </A><br> </td> "); 
	 }
		out.println("</tr> </table>");
	}


	static public  void afficherDisquesEnVente ( Stock leStock,  PrintWriter out, String repertoire)
	{
		Disque leDisque;
	    Iterator <String>  iter = leStock.getListeDisques().iterator();
	    out.println("<table border=1>");
		while (iter.hasNext())
		{
			leDisque = Stock.trouveDisque((String)iter.next());
			out.println( "<tr> <td>" + leDisque.getTitre() + "  ,  " + leDisque.getNom() + " ,  "  + leDisque.getPrix() + " Euros  ,  Année :" + leDisque.getAnnee()  +"</td>");
	        out.println("<td> <IMG SRC= '" + repertoire + "/images/" + leDisque.getImage() +"'  BORDER=0> </A><br> </td> "); 
	        out.println("<td><A HREF='commandeClient?code=" + leDisque.getReference()+ "&ordre=ajouter'>"); 
	        out.println("<IMG SRC='" + repertoire + "/images/panier.gif\' BORDER=0></A><br> </td> </tr>");
		}
		out.println("</tr> </table>");
	}


	static public  void afficherDisquesDansBase ( ResultSet  rs, PrintWriter out, String repertoire)
	{  Disque leDisque;

	    try {
		  out.println("<table border=1>");
	      while (rs.next()) {          
	         //out.println( rs.getString("article") + "<br>" );  
	         
	  		  leDisque = Stock.trouveDisque(rs.getString("nomarticle"));   
			  out.println( "<tr> <td>" + leDisque.getTitre() + "  ,  " + leDisque.getNom() + " ,  "  + leDisque.getPrix() + " Euros  ,  Année :" + leDisque.getAnnee()  +"</td>");
	          out.println("<td> <IMG SRC= '" + repertoire + "/images/" + leDisque.getImage() +"'  BORDER=0> </A><br> </td> "); 
	      } 
		  out.println("</tr></table>");
	     }         
	   catch (Exception E) {   
			   out.println("erreur base");         
	           System.out.println(" - probeme ajoute " + E.getClass().getName() );
	           E.printStackTrace();
	     }	
   }
}
