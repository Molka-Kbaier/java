/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entity.Utilisateur;
import edu.esprit.service.ServiceUser;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController implements Initializable {

    @FXML
    private BorderPane loginform;
    @FXML
    private TextField emailc;
    @FXML
    private TextField pwdc;
    @FXML
    private Button logincbtn;
    
     ServiceUser us = new ServiceUser();
    @FXML
    private Button signupbtn;
    @FXML
    private Label reset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {
        String email = emailc.getText();
        String password = pwdc.getText();
        Utilisateur loggedInUser = authenticate (email,password);
        if(loggedInUser != null){
                        try{
                             Utilisateur u = new Utilisateur();
                  String a = u.getRoles();
                  if(a== "ADMIN_ROLES"){
                            Stage stage = (Stage) logincbtn.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("GestionUtilisateur.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();}
                  else{
                        Stage stage = (Stage) logincbtn.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("Front.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
                  }
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
                        
        }
    }
    
     public Utilisateur authenticate (String email,String password){
        Utilisateur u = us.Signin(email, password);
        return u;
    }

     
    private void goToInscription(MouseEvent event) {
          try{
                            Stage stage = (Stage) logincbtn.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("GestionUtilisateur.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void inscription(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader ();//creation de FXMLLoader 
                            loader.setLocation(getClass().getResource("SignUp.fxml")); //emplacement du fichier fxml 
                            try {
                                loader.load();
                            } catch (Exception ex) {
                               System.err.println(ex.getMessage());
                            }
                          
                            Parent parent = loader.getRoot(); 
                            Stage stage = new Stage(); //affichage de la fenetre 
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
    }

    @FXML
    private void resetpwd(javafx.scene.input.MouseEvent event) {
    }
    
}
