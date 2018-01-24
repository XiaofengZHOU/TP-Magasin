package mesCommandes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
class Util {
         
	static public String rechercheCookies(Cookie[] lescookies, String nom)  {
		
		if(nom==null)
		{
			return null;
		}
         String reponse =null;
         Cookie cookie;
         System.out.println("user name is : "+nom );
         for (int i = 0; i < lescookies.length; i++) {
        	 cookie = lescookies[i];
        	 System.out.println(cookie.getName() + " :" +cookie.getValue());
        	 if( nom.equals(cookie.getName()) )
        	 {	
        		 reponse = cookie.getValue();
        		 System.out.println("mdp correct");
        		 System.out.println(cookie.getName() + " :" +cookie.getValue());
        	 }
         }
         
               //  ********************************************************************************************        
               //  recherche si dans le tableau de cookies il en existe un avec le nom donn�.
               //       si oui  la valeur de ce cookie est donn閑 en r閟ultat (mot de passe)
               //  Cette m閠hode sera appel閑 par d抋utres classes aussi elle est � public � 
               //  et � static � pour pouvoir  l抋ppeler directement par la classe  "Util.rechercheCookies(..)"
               //  ********************************************************************************************

         
	        //  ********************************************************************************************
        
        return reponse;
      }


	static boolean identique (String recu, String cookie) {
	    return ( (cookie != null) && (recu.equals(cookie) ));
	
	}
}