/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Comptabilite;
import edu.esprit.entities.Facture;
import edu.esprit.tools.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author louaj
 */
public class FactureCRUD {
    Connection cnx2;
    public 
        FactureCRUD(){
    cnx2=(Connection) MyConnection.getInstance().getCnx();
            }
        
        public void ajouterFacture(Facture f) {
        try {
            String req = "INSERT INTO `facture` (`comptabilite_id`, `date_facture`, `type`, `montant_totale`) VALUES ('"+f.getComptabilite() +"', '" + f.getDate()+"', '" +f.getType()+"', '"+ f.getMontant_total()+"')";
            Statement st= cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Facture ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
           
    }
          public void supprimer(int id) {
        try {
            String req = "DELETE FROM `facture` WHERE id = " + id;
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("facture supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
        public List<Facture> afficherFacture (){
                       List<Facture> myList=new ArrayList<>();

           try {
               String req4="SElECT * FROM facture";
                Statement st = cnx2.createStatement();
                ResultSet rs = st.executeQuery(req4);
                while(rs.next()){
                    Facture f = new Facture();
                    f.setId(rs.getInt(1)); 
                    int comptabilite_id = rs.getInt("comptabilite_id");
                    f.setDate(rs.getDate("date_facture"));
                    f.setType(rs.getString("type"));
                    f.setMontant_total(rs.getFloat("montant_totale"));
                   
                Comptabilite c = new Comptabilite();
                c.setId(comptabilite_id);
               
                f.setComptabilite(c);
                    
                    myList.add(f);
                   
  
                }
                
                System.out.println("facture affichée !");
           } catch (SQLException ex) {
               System.out.println("Erreur: "+ex.getMessage());
           }
           return myList;
        
        
        
        
        
        
        
        
        
        
        
        }}
