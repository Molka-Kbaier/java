/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;
import com.mysql.jdbc.Connection;
import edu.esprit.tools.MyConnection;
import edu.esprit.entities.Comptabilite;
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
public class ComptabiliteCRUD {
    Connection cnx2;
    public ComptabiliteCRUD(){
    cnx2=(Connection) MyConnection.getInstance().getCnx();
            }

      


    public void ajouterComptabilite(Comptabilite c){
        try {
            
             String requete = "INSERT INTO `comptabilite` (`date_comptabilite`,`valeur`) VALUES ('" + c.getDate_comptabilite()+"', '" + c.getValeur()+"')";
            Statement st= cnx2.createStatement();
            st.executeUpdate(requete);
//            System.out.println("Comptabilite ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   /* public void ajouterComptabilite2(Comptabilite c){
         try { String requete2="INSERT INTO comptabilite (date_comptabilite ,valeur)"
                    + "VALUES (?,?)";
           PreparedStatement pst = cnx.prepareStatement(requete2);

            
        pst.setDate(1, new java.sql.Date(c.getDate_comptabilite().getTime()));
            pst.setFloat(2, c.getValeur());
            pst.executeUpdate();
            System.out.println("comptabilite2 est ajoutée!!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }*/
    
     public void supprimer(int id) {
        try {
            String req = "DELETE FROM `comptabilite` WHERE id = " + id;
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("comptabilite supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public void modifier(Comptabilite c) {
        try {
            String req = "UPDATE `comptabilite` SET `date_comptabilite` = '" + c.getDate_comptabilite()+ "',  `valeur` = '" + c.getValeur()+ "' WHERE `comptabilite`.`id` = " + c.getId();
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("comptabilite modifiée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public List<Comptabilite> afficherComptabilite (){
                       List<Comptabilite> myList=new ArrayList<>();

           try {
               String req3="SElECT * FROM comptabilite";
                Statement st = cnx2.createStatement();
                ResultSet rs = st.executeQuery(req3);
                while(rs.next()){
                    Comptabilite c = new Comptabilite();
                    c.setId(rs.getInt(1));
                    c.setDate_comptabilite(rs.getDate("date_comptabilite"));
                    c.setValeur(rs.getFloat("valeur"));
                    
                    myList.add(c);
                }
                
                
           } catch (SQLException ex) {
               System.out.println(ex.getMessage());
           }
           return myList;
    }  
    
  
    
    
}
