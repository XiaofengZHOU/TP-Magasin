package mesCommandes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

public class Facturation extends HttpServlet{

	private static final long serialVersionUID = 1L;
	Connection connexion=null;
    Statement stmt=null;
    PreparedStatement pstmt=null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		String nom = null;
		Stock stockDisponible = null;
		
		HttpSession session = request.getSession(true);
		nom = (String) session.getAttribute("nomClient");
		stockDisponible = (Stock) session.getAttribute("stockCourant");
				
		OuvreBase();
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>  votre facture </title>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' >");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        out.println("<a HREF=afficheDisque> Vous pouvez commandez un autre disque </a><br> ");
		int sum = MontreCommandeBase(nom, out, request.getContextPath());
		out.println("<h3>"  + nom.toUpperCase()  + "  Voici  la fucture de votre commande: " + Integer.toString(sum) + "Euros </h3>");
		out.println("</body>");
        out.println("</html>");

	} 

    protected void OuvreBase() 
    {
        try 
        {
           Class.forName("org.gjt.mm.mysql.Driver").newInstance(); 
           connexion = DriverManager.getConnection(  "jdbc:mysql://localhost/magasin","root","");
           connexion.setAutoCommit(true);
           stmt = connexion.createStatement();
        }
        catch (Exception E)
        {         
              log(" -------- problème  " + E.getClass().getName() );
              E.printStackTrace();
        }	
    }
  
    protected void fermeBase() 
    {
        try {
           stmt.close();        
           connexion.close();         
        }
            catch (Exception E) {         
              log(" -------- problème  " + E.getClass().getName() );
              E.printStackTrace();
           }	
    }

    protected int MontreCommandeBase(String nom, PrintWriter out, String repertoire) 
    {
	      ResultSet rset = null;
	      ResultSet rs = null;
	      Disque leDisque=null;
	      int cle =0;
	      int sum=0;
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
			  		//Depot.afficherDisquesDansBase(rs, out, repertoire);
			  		while (rs.next()) {
			  			leDisque = Stock.trouveDisque(rs.getString("nomarticle"));
			  			int price = leDisque.getPrix();
			  			sum = sum+price;
			  		}
			   }
	
	      //  ********************************************************* 
	      }           
	      catch (Exception E) 
	      {   
	          out.println("erreur base");         
	          log(" - probeme ajoute " + E.getClass().getName() );
	          E.printStackTrace();
	 
	      }
	      return sum;
    }
    
    
	public void doPost(HttpServletRequest request,  HttpServletResponse response) throws IOException, ServletException
	{
	   doGet(request, response);
	}
}
