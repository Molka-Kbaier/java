/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.service;

import edu.esprit.connection.MyConnection;
import edu.esprit.entity.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author user
 */
public class ServiceUser implements IService<Utilisateur>{

    
     Connection cnx = MyConnection.getInstance().getCnx();

    public Utilisateur Signin (String email , String password){
       try{
           String req5 = "SELECT * FROM utilisateur WHERE email = ?";
          PreparedStatement pr5 = cnx.prepareStatement(req5);
          pr5.setString(1, email);
          ResultSet rs = pr5.executeQuery();
          if (rs.next()){
              String storedPassword = rs.getString("password");
              String mot = storedPassword.replaceFirst("^\\$2y\\$", "\\$2a\\$");
              
              if (BCrypt.checkpw(password, mot)){
                  Utilisateur u = new Utilisateur();
                  u.setId(rs.getInt("id"));
                  
                  u.setEmail(rs.getString("email"));
                  System.out.println(u);
                  try{
                      FileWriter writer = new FileWriter("session.txt",false);
                      
                      writer.write(String.valueOf(u.getId()));
                      writer.flush();
                      writer.close();
                      return u;
                  }catch(IOException ex ){
                     
                  }
              }else{
                 Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur login");
            alert.setHeaderText("Mot de passe incorrecte");
            alert.showAndWait();
              }
          }else{
               Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur login");
            alert.setHeaderText("Utilisateur existe pas !");
            alert.showAndWait();
          }
       }catch(SQLException ex){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur login");
            alert.setHeaderText(ex.getMessage());
            alert.showAndWait();
       }
        
        return null;
    }

     
    @Override
    public void ajouter(Utilisateur u) throws SQLException {
        
        String password = u.getPassword();
            String hashedPassword =BCrypt.hashpw(password, BCrypt.gensalt(12));        
         try {
            String req = "INSERT INTO `utilisateur`(`email`, `roles`, `password`, `nom`, `prenom`, `telephone`, `cin`, `is_verified`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getRoles());
            ps.setString(3, hashedPassword);
            ps.setString(4, u.getNom());
            ps.setString(5, u.getPrenom());
            ps.setString(6, u.getTelephone());
             ps.setInt(7, u.getCin());
             ps.setByte(8, u.getIsVerified());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void supprimer(Utilisateur user) throws SQLException {
      try {
            String req = "DELETE FROM `utilisateur` WHERE id = ? " ;
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, user.getId());
            st.executeUpdate();
            System.out.println("Utilisateur supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public void supprimerUtilisateur(Utilisateur user) {
        try {
            String requete="delete from utilisateur where nom=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,user.getNom());
            pst.executeUpdate();
           
            System.out.println("Utlisateur est supprimée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
    

    @Override
    public void modifier(Utilisateur u) throws SQLException {
 try{
        String updatereq="UPDATE `utilisateur` SET `email`=?, `roles`=?, `nom`=?, `prenom`=?, `telephone`=?, `cin`=? WHERE `nom`=? AMD `prenom`=?";
         PreparedStatement pr2 = cnx.prepareStatement(updatereq);
         pr2.setString(1, u.getEmail());
           pr2.setString(2, u.getNom());
            pr2.setString(3, u.getPrenom());
             pr2.setString(4, u.getTelephone());
              pr2.setInt(5, u.getCin());
          
              pr2.setInt(6, u.getId());
                  pr2.setString(7, u.getRoles());
             pr2.executeUpdate();
             System.out.println("Utilisateur modifier avec succé");
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
       }
    }

    @Override
    public List<Utilisateur> getAll() throws SQLException {
List<Utilisateur> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM utilisateur ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
               Utilisateur u = new Utilisateur();
               u.setEmail(rs.getString("email"));
               u.setRoles(rs.getString("roles"));
               u.setNom(rs.getString("nom"));
               u.setPrenom(rs.getString("prenom"));
               u.setTelephone(rs.getString("telephone"));
                u.setCin(rs.getInt("cin"));
               
               list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Utilisateur getOneById(int id) throws SQLException {
Utilisateur u = new Utilisateur();
        
        try{
             String req4 = "SELECT * FROM `utilisateur` WHERE id = ? ";
             PreparedStatement pr4 = cnx.prepareStatement(req4);
             pr4.setInt(1, id);
             ResultSet rs = pr4.executeQuery();
             if (rs.next()){
                      u.setEmail(rs.getString("email"));
               u.setRoles(rs.getString("roles"));
               u.setNom(rs.getString("nom"));
               u.setPrenom(rs.getString("prenom"));
               u.setTelephone(rs.getString("telephone"));
                u.setCin(rs.getInt("cin"));
             }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
       return u;
    }
    
   
     
    
}
