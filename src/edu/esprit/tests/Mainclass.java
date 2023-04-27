/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Employe;
import edu.esprit.entities.Equipement;
import edu.esprit.services.EmployeCRUD;
import edu.esprit.services.EquipementCRUD;
import edu.esprit.utils.Myconnection;

/**
 *
 * @author amine
 */
public class Mainclass {
    public static void main(String[] args) {
        Myconnection mc = new Myconnection();
    
     EmployeCRUD pcd = new EmployeCRUD();
     Employe p1 = new Employe("kbair","molka","123475856",20,1200);
      pcd.ajouter2(p1);
// Modifier la quantité des equipements avec l'id 2
        Employe p2 = new Employe("kbaier","fathi","123475856",40,1200);
       
       pcd.ajouter2(p2);
       
       Employe p3 = new Employe("kebaier","fathi","123475856",50,1200);
       pcd.ajouter2(p3);
       
       p3.setNom("kbaier");
      p3.setCin("012345678");
      p3.setPrenom("molka");
       pcd.modifier(p3);
       pcd.supprimer(p1);

        // Récupérer la liste de toutes les equipements
      System.out.println( pcd.getAll());
       
      
        EquipementCRUD ecd = new EquipementCRUD();
    //  Equipement e2 = new Equipement(p2,"voiture","isuZu", true,"bon","1274782555");
    Equipement e2 = new Equipement(new Employe(222),"voiture","isuZu", true,"bon","1274782555");
    ecd.ajouterEquipement3(e2);
     Equipement e1 = new Equipement(new Employe(66),"voiture","isuZu",true,"bon","1274782555");
     //ecd.ajouterEquipement2(e1);
         Equipement e3 = new Equipement(new Employe(122),"voiture","isuZu",false,"bon","1274782555");  
        ecd.ajouterEquipement3(e3);
           Equipement e4 = new Equipement(new Employe(122),"camion","toyota",false,"mauvais","1274782555");  
         e3.setMarque("toyota");
        ecd.modifier(e4);
        // Supprimer l'Employe avec l'id 1
        ecd.supprimer(e2);
        System.out.println(ecd.getAll());
        
    }
   
}
