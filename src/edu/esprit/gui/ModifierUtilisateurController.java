/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entity.Utilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierUtilisateurController implements Initializable {

    @FXML
    private TextField nomTextField;
    @FXML
    private TextField prenomTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField MotDePassePasswordField;
    
    private Utilisateur utilisateur;
    @FXML
    private TextField rolestext;
   
    @FXML
    private TextField telFieldText;
    @FXML
    private TextField cinFieldText1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 

    }    
    
    
     public void initData(Utilisateur utilisateur) {
         this.utilisateur = utilisateur;
    nomTextField.setText(utilisateur.getNom());
    prenomTextField.setText(utilisateur.getPrenom());
    emailTextField.setText(utilisateur.getEmail());
    MotDePassePasswordField.setText(utilisateur.getPassword());
    rolestext.setText(utilisateur.getRoles());
    telFieldText.setText(utilisateur.getTelephone());
//    cinFieldText1.setText(Integer.toString(utilisateur.getCin()));
        
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    @FXML
    private void enregistrer(ActionEvent event) {
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String email = emailTextField.getText();
        String motDePasse = MotDePassePasswordField.getText();
        String tel = telFieldText.getText();
        String roles = rolestext.getText();
     //int cin = Integer.parseInt(cinFieldText1.getText()); // Ajouter le champ 'cin'


        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || motDePasse.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs doivent être remplis");
            alert.showAndWait();
            return;
        }

        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        utilisateur.setPassword(motDePasse);
        utilisateur.setRoles(roles);
        utilisateur.setTelephone(tel);
 //       utilisateur.setCin(cin); // Mettre à jour le champ 'cin'
 

        ((Stage) nomTextField.getScene().getWindow()).close();
    }
@FXML
    private void annuler(ActionEvent event) {
        utilisateur = null;
        ((Stage) nomTextField.getScene().getWindow()).close();
    }

}

