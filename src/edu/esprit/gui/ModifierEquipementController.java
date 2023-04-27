/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Employe;
import edu.esprit.entities.Equipement;
import edu.esprit.services.EmployeCRUD;
import edu.esprit.services.EquipementCRUD;
//import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ModifierEquipementController implements Initializable {

    @FXML
    private TextField tfType;
    @FXML
    private TextField tfMarque;
    @FXML
    private TextField tfEtat;
    @FXML
    private TextField tfMatricule;
    @FXML
    private CheckBox tfDisponnible1;
    @FXML
    private CheckBox tfDisponnible2;
    @FXML
    private ComboBox<Employe> tfID_emp;
    @FXML
    private Button btnModifier;
    private Equipement EquipmentAModifier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<Employe> Employes = new EmployeCRUD().getAll();
        tfID_emp.setItems(FXCollections.observableArrayList(Employes));
       
    }    
 // Ajouter une variable de classe pour stocker la culture sélectionnée

    public void initData(Equipement equipement) {
        EquipmentAModifier = equipement; // Stocker la culture sélectionnée dans la variable;
      tfID_emp.setValue(equipement.getEmploye_());
        tfEtat.setText(String.valueOf(equipement.getEtat()));
        tfMatricule.setText(String.valueOf(equipement.getMatricule()));
        tfType.setText(equipement.getType());
        tfMarque.setText(equipement.getMarque());
        
    }
  

    @FXML
    private void ModifierEquipment(ActionEvent event) {
         try { 
        Employe employe = tfID_emp.getValue();
        
         if (employe == null) {
            showError("Veuillez selectionner un employé");
            return;
         }
      //  String type = tfType.getText();
       
        String Type= tfType.getText();
        if (Type.isEmpty()) {
            showError("Veuillez saisir un Type");
            return;
       /* } else if (!Type.matches("[a-zA-Z]+")) {
            showError("Le Type doit contenir uniquement des lettres");
            return; */
        } else if (Type.length() < 2 || Type.length() > 20) {
            showError("Le Type doit avoir une longueur comprise entre 2 et 20 caractères");
           // return;
            // JOptionPane.showMessageDialog(null, "Le Type doit avoir une longueur comprise entre 2 et 20 caractères", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
         String Marque= tfMarque.getText();
        if (Marque.isEmpty()) {
            showError("Veuillez saisir un Marque");
         //JOptionPane.showMessageDialog(null, "Veuillez saisir une marque", "Erreur", JOptionPane.ERROR_MESSAGE);
 
            return;
        } /* else if (!Marque.matches("[a-zA-Z]+")) {
            showError("Le Marque doit contenir uniquement des lettres");
            return; 
        } */ 
        else if (Marque.length() < 2 || Marque.length() > 20) {
            showError("Le Marque doit avoir une longueur comprise entre 2 et 20 caractères");
            return;
        }
        
        
        String matriculeStr = tfMatricule.getText();
        if (matriculeStr.isEmpty()) {
            showError("Veuillez saisir une matricule");
            return;
        }
       // float matricule= Float.parseFloat(matriculeStr);
        float matricule;
        try {
            matricule= Float.parseFloat(matriculeStr);
        } catch (NumberFormatException e) {
            showError("La matricule doit être un nombre entier");
            return;
        }
       
        //float matricule = Float.parseFloat(tfMatricule.getText());
         String etat= tfEtat.getText();
        if (etat.isEmpty()) {
            showError("Veuillez saisir  l'etat de l'equipment");
            return;
        } else if (!etat.matches("[a-zA-Z]+")) {
            showError("L'etat doit contenir uniquement des lettres");
            return;
        } else if (etat.length() < 2 || etat.length() > 20) {
            showError("Le Type doit avoir une longueur comprise entre 2 et 20 caractères");
            return;
        }
       //  String matriculeStr = tfMatricule.getText();
        if (matriculeStr.isEmpty()) {
            showError("Veuillez saisir le numero de  matricule");
            return;
         } else if (matriculeStr.length() < 8 || matriculeStr.length() > 8) {
            showError("la matricule doit avoir une longueur de 8 caractères");
            return;
        }
        Boolean disponnible;
if (!tfDisponnible1.isSelected() && !tfDisponnible2.isSelected()) {
    showError("Veuillez sélectionner si l'équipement est disponible ou non");
    return;
} else if (tfDisponnible1.isSelected() && tfDisponnible2.isSelected()) {
    showError("Veuillez sélectionner un seul état de disponibilité pour l'équipement");
    return;
} else {
    disponnible = tfDisponnible1.isSelected();
}
  // Mettre à jour l'equipment dans la base de données
        EquipementCRUD ecd = new EquipementCRUD();
      //   Equipement equipementToUpdate = (Equipement) table_view.getSelectionModel().getSelectedItem();
             /*  if (equipementToUpdate == null) {
        showError("Veuillez sélectionner un equipment à modifier");
        return;
        }*/
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherTerrain.fxml"));
Parent root = loader.load();
AfficherEquipmentController afficherEquipmentController = loader.getController();
       EquipementCRUD ec = new EquipementCRUD();
 
            EquipmentAModifier.setEmploye(employe);
            EquipmentAModifier.setType(Type);
            EquipmentAModifier.setMarque(Marque);
            EquipmentAModifier.setDisponnible(disponnible);
            EquipmentAModifier.setEtat(etat);
        EquipmentAModifier.setMatricule(matriculeStr);
            ec.modifier2(EquipmentAModifier);
         
  
        
        // Afficher un message de succès
        showSuccess("L'equipment est modifiée avec succès");
        
        // Vider les champs de texte 
        tfID_emp.setValue(null);
        tfType.setText("");
        tfMarque.setText("");
        tfDisponnible1.setText("");
        tfDisponnible2.setText("");
        tfEtat.setText("");
        tfMatricule.setText("");
        
    Stage stage = (Stage) btnModifier.getScene().getWindow();
    stage.close();
     } catch (Exception e) {
        showError("Une erreur s'est produite lors de la modification de la liste equipement");
        e.printStackTrace();
    }
    }
    

    @FXML
    private void btnretour(ActionEvent event) throws IOException {
        Stage primaryStage = null;
        Stage SecondWindow = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("AfficherEquipment.fxml"));;
        Scene rec2 = new Scene(root);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }
     private void showError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

private void showSuccess(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
} 
}
