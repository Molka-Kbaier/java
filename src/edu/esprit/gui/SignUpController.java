/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entity.User;
import edu.esprit.entity.Utilisateur;
import edu.esprit.service.ServiceUser;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField noms;
    @FXML
    private TextField prenoms;
    @FXML
    private TextField cins;
    @FXML
    private TextField tels;
  
    @FXML
    private PasswordField pwds;
    @FXML
    private Button signupbtn;

    
    ServiceUser us = new ServiceUser();
    @FXML
    private TextField emails;
    @FXML
    private ComboBox<String> roless;
    
    
     private Connection cnx;
    private PreparedStatement prepare;
    private ResultSet result;
ServiceUser su = new ServiceUser();
    
    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        // Ajout des options possibles pour le choix de rôle
        
         roless.getItems().addAll("USER_ROLES", "ADMIN_ROLES");
    }

    @FXML
    private void signup(ActionEvent event) throws SQLException {
        
        Utilisateur u = new Utilisateur();
        
    
   String nom = noms.getText();
    String prenom = prenoms.getText();
    String email = emails.getText();
    String cin = cins.getText();
    String tel = tels.getText();
    String password = pwds.getText();
    String roles = roless.getValue();

    
     String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
         String nameRegex = "^[a-zA-Z]+$";
         String phoneRegex = "^(2|5|9)[0-9]{7}$";
         
           if (!email.matches(emailRegex)){
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Email invalide !");
            alert.showAndWait();
         }else  if ((password.length()<8)){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Mot de passe  doit etre supérieure à 8 caractère  !");
            alert.showAndWait();
         }else if(!nom.matches(nameRegex)){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("nom invalide !");
            alert.showAndWait();
         }else if(!prenom.matches(nameRegex)){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("prenom invalide !");
            alert.showAndWait();
         }
         else if(!tel.matches(phoneRegex)){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("numéro invalide !");
            alert.showAndWait();
         }else if (cin.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("adresse invalide !");
            alert.showAndWait();
    }else{
              u.setEmail(email);
         u.setPassword(password);
         u.setNom(nom);
         u.setPrenom(prenom);
         u.setRoles(roles);
         u.setCin(Integer.parseInt(cin));
         u.setTelephone(tel);
         
         us.ajouter(u);
     try{
                            Stage stage = (Stage) signupbtn.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("VerificationEmail.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
         }
}
}
