/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Comptabilite;
import edu.esprit.services.ComptabiliteCRUD;
import edu.esprit.tools.MyConnection;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author louaj
 */
public class MainClass {
    public static void main(String[] args) {
         //MyConnection mc = new MyConnection();
         /*ComptabiliteCRUD c =new ComptabiliteCRUD();
        
         
         
          Calendar calendar = Calendar.getInstance();
          calendar.set(2020, Calendar.MARCH, 20);
         Date date = new Date(calendar.getTimeInMillis());

          Comptabilite c2 = new  Comptabilite (date, 0);
        c.ajouterComptabilite(c2);*/
         ComptabiliteCRUD cc =new ComptabiliteCRUD(); 
          //cc.ajouterComptabilite();
          cc.supprimer(22);
        // cc.modifier(c);
         System.out.println(cc.afficherComptabilite());
         
      
         
       
         
    }
 
}
