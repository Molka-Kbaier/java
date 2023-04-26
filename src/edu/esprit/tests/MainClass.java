package edu.esprit.tests;

import edu.esprit.entities.Comptabilite;
import edu.esprit.entities.Facture;
import edu.esprit.services.ComptabiliteCRUD;
import edu.esprit.services.FactureCRUD;
import edu.esprit.tools.MyConnection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;


public class MainClass {
    public static void main(String[] args) throws ParseException {
        ComptabiliteCRUD comptabiliteCRUD = new ComptabiliteCRUD();
        FactureCRUD factureCRUD = new FactureCRUD();
        Calendar calendar = Calendar.getInstance();

        calendar.set(2020, Calendar.MARCH, 20); // 20 mars 2020
        Date date = new Date(calendar.getTimeInMillis());

        System.out.println(date);
        //ajout        mrigla
//        Comptabilite c = new Comptabilite(date,551.5f);
//        comptabiliteCRUD.ajouterComptabilite(c);
//        //modifier     mrigla
//        Comptabilite comptabilite = new Comptabilite(1,date,12f);
//        comptabiliteCRUD.modifier(comptabilite);
        //supprimer    mrigla
//        comptabiliteCRUD.supprimer(2);
        //getall
//        System.out.println(comptabiliteCRUD.getAll());

        Facture facture = new Facture(date,15,"achat",comptabiliteCRUD.getById(4));
//  ajout facture mrigla
//        factureCRUD.ajouterFacture(facture);

        //modif mrigla
//        facture.setType("Vente");
//        facture.setId(12);

        //suppression mrigla
        factureCRUD.supprimer(12);
        factureCRUD.updateFacture(facture);



        System.out.println(factureCRUD.afficherFacture());

    }

}
