/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.main;

import edu.esprit.entity.Utilisateur;
import edu.esprit.service.ServiceUser;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class GestionUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Utilisateur u1 = new Utilisateur();
        Utilisateur u2 = new Utilisateur(1, "Aloui", "Ayoub", 12345678, "51935869", "ayouba.loui@gmail.com", "11402820","ROLES_USER",(byte)0);
       Utilisateur u5 = new Utilisateur("Ayoub","Aloui", 11223344, "51935824", "ayoub.aloui@gmail.com", "11402820","USER3" );
        ServiceUser ps = new ServiceUser();
        
    
     // ps.ajouter2(u3);
      
    // ps.ajouter(u2);
      // ps.supprimer(10);
      //ps.modifier(u2);
      //ps.getAll();
      ps.getOneById(12);
      
    }
    
}
