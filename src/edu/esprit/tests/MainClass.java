
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Culture;
import edu.esprit.entities.Terrain;
import edu.esprit.services.CultureCRUD;
import edu.esprit.services.TerrainCRUD;
import edu.esprit.utils.MyConnection;
import java.util.Calendar;
//import java.util.Date;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class MainClass {
    public static void main(String[] args) {
       
       // MyConnection mc = new MyConnection();
       CultureCRUD cc= new CultureCRUD();
        // Créer un objet Calendar pour la date "20/03/2020"
        Calendar calendar = Calendar.getInstance();
       
  calendar.set(2020, Calendar.MARCH, 20); // 20 mars 2020
        // Convertir la date en millisecondes
       
        Date date = new Date(calendar.getTimeInMillis());

        // Ajouter une nouvelle culture en utilisant la méthode "ajouter"
         Culture c1 = new Culture("tomate", date, 25);
        cc.ajouter(c1);
        /* Culture c1 = new Culture(36, "tomate", date, 25);
        cc.ajouter(c1);*/

        // Ajouter une nouvelle culture en utilisant la méthode "ajouter2"
         Culture c2 = new Culture("fraise", date, 20);
       // Culture c2 = new Culture(37, "fraise", date, 20);
        cc.ajouter2(c2);
        c2.setQuantite(50);
        cc.modifier(c2);
        // Modifier la quantité de la culture avec l'id 2
          Culture c3 = new Culture("pomme", date, 30);
        //Culture c3 = new Culture(38, "pomme", date, 30);
        cc.ajouter2(c3);
        c3.setType("kiwi");
       // cc.modifier2(c3);
Culture c = new Culture();
c.setType("maïs");
System.out.println(c.getType());

        // Supprimer la culture avec l'id 1
        Culture c4 = new Culture("pomme", date, 30);
        cc.ajouter2(c4);

        cc.supprimer(c4);

        // Récupérer la liste de toutes les cultures
       // cc.afficher();
       ////ArrayList<Culture> cultures = new ArrayList<>();
// Ajouter des cultures à la liste
///int nombreCultures = cultures.size();
        System.out.println(cc.afficher());
        TerrainCRUD tc= new TerrainCRUD();
     // Terrain t1 = new Terrain(1,c1,2,50,"tunis");
        Terrain t1 = new Terrain(new Culture(130),2,50,"tunis");
        tc.ajouter(t1);
         //Terrain t2 = new Terrain(9,c3,10,60,"hammamet");
       Terrain t2 = new Terrain(new Culture(251),10,60,"hammamet");
        tc.ajouter2(t2);
        //Terrain t3 = new Terrain(9,c3,10,70,"hammamet");
       // Créez un nouveau terrain avec les informations souhaitées
Terrain t3 = new Terrain(new Culture(250),10,70,"hammamet");

// Ajoutez le terrain à la base de données pour obtenir un nouvel ID

tc.ajouter(t3);

// Modifiez le terrain en utilisant le nouvel ID
t3.setLieu("sousse");
tc.modifier(t3);
        // Supprimer le terrain avec l'id 1
        tc.supprimer(t2);
         // Supprimer le terrain avec l'id 1
        tc.supprimer3(295);
        
       
        System.out.println(tc.afficher());
       
}

    }

