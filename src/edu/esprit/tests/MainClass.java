/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import edu.esprit.entities.Comptabilite;
import edu.esprit.entities.Facture;
import edu.esprit.services.ComptabiliteCRUD;
import edu.esprit.services.FactureCRUD;
import edu.esprit.tools.MyConnection;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author louaj
 */
public class MainClass {
    public static void main(String[] args) {
        // MyConnection mc = MyConnection.getInstance();
         //MyConnection mc1 = MyConnection.getInstance();
         //System.out.println(mc.hashCode()+"_____"+mc1.hashCode());

        
            
       Calendar calendar = Calendar.getInstance();
                calendar.set(2020, Calendar.APRIL, 13);
                java.sql.Date date = new java.sql.Date(calendar.getTimeInMillis());
         
          
                 ComptabiliteCRUD cc =new ComptabiliteCRUD(); 
               

       Comptabilite c1 =new Comptabilite( date, 123); 
        cc.ajouterComptabilite(c1);
      //    c1.setValeur(12);
        // cc.supprimer(22);
  //  cc.modifier(c1);
       // System.out.println(cc.afficherComptabilite());
         FactureCRUD fc= new FactureCRUD();
       
          Facture f1 = new Facture(date,46,"vente", new Comptabilite(70));
          fc.ajouterFacture(f1);
          
         System.out.println(fc.afficherFacture ());
    }
 
}
