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
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AjouterEquipmentController implements Initializable {

    /**
     * Initializes the controller class.
     */
        // Declare the text fields defined in the FXML file using @FXML annotation
   
   
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfMarque;
    
    @FXML
    private TextField tfEtat;
    @FXML
    private TextField tfMatricule;
    @FXML
    private ComboBox<Employe> tfID_emp;
    @FXML
    private Button btnAjouter;
    @FXML
    private CheckBox tfDisponnible1;
    @FXML
    private CheckBox tfDisponnible2;
    private TableView<Equipement> table_view;
    private TableColumn<Equipement, Integer> col_id;
    private TableColumn<Equipement, Employe> col_employe_id;
    private TableColumn<Equipement, String> col_Type;
    private TableColumn<Equipement, String> col_Marque;
    private TableColumn<Equipement, String> col_etat;
    private TableColumn<Equipement, Integer> col_disponnible;
    private TableColumn<Equipement, String> col_matricule;

    /*/**
    * Initializes the controller class.
    * @param url
    * @param rb
    *///
    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Employe> employes = new EmployeCRUD().getAll();
        tfID_emp.setItems(FXCollections.observableArrayList(employes));
   // Ajouter un listener à la table-view pour détecter lorsqu'un terrain est sélectionné
   /*table_view.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
   if (newSelection != null) {
   // Mettre à jour les champs correspondants avec les informations du terrain sélectionné
   tfType.setText(String.valueOf(newSelection.getType()));
   tfMatricule.setText(String.valueOf(newSelection.getMatricule()));
   tfEtat.setText(newSelection.getEtat());
   tfMarque.setText(newSelection.getMarque());
   tfID_emp.setValue(newSelection.getEmploye_());
   } else {
   // Si aucun terrain n'est sélectionné, vider les champs
   tfID_emp.setValue(null);
   tfType.setText("");
   tfMarque.setText("");
   tfDisponnible1.setText("");
   tfDisponnible2.setText("");
   tfEtat.setText("");
   tfMatricule.setText("");
   
   }
   });*/

    }     
          // Configurer les colonnes de TableView
    /*TableColumn<Employe, String> idCol = new TableColumn<>("id");
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

     TableColumn<Employe, String> employe_idCol = new TableColumn<>("employe_id");
    employe_idCol.setCellValueFactory(new PropertyValueFactory<>("employe_id"));

     TableColumn<Employe, String> TypeCol = new TableColumn<>("Type");
    TypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
   
    TableColumn<Employe, String> MarqueCol = new TableColumn<>("Marque");
    MarqueCol.setCellValueFactory(new PropertyValueFactory<>("Marque"));
    
     TableColumn<Employe, String> disponnibleCol = new TableColumn<>("disponnible ");
    disponnibleCol.setCellValueFactory(new PropertyValueFactory<>("disponnible "));
    
     TableColumn<Employe, String> etatCol = new TableColumn<>("etat ");
    etatCol.setCellValueFactory(new PropertyValueFactory<>("etat "));
    
     TableColumn<Employe, String> matriculeCol = new TableColumn<>("matricule ");
    matriculeCol.setCellValueFactory(new PropertyValueFactory<>("matricule "));
    
    table_view.getColumns().addAll(idCol, employe_idCol, TypeCol, MarqueCol, disponnibleCol, etatCol, matriculeCol);*/
    
     @FXML
     private void AddEquipment(ActionEvent event) {
  
        // try {
             
         
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
        int matricule;
        try {
            matricule= Integer.parseInt(matriculeStr);
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
        /*Boolean disponnible;
        // Vérifier que l'utilisateur a coché soit "oui" soit "non"
        if (!tfDisponnible1.isSelected() && !tfDisponnible2.isSelected()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le champ 'disponible' est obligatoire.");
        alert.showAndWait();
        return;
        }
        // Vérifier que l'utilisateur n'a coché qu'un seul choix
        if (tfDisponnible1.isSelected() && tfDisponnible2.isSelected()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Choix non valide. Veuillez cocher un seul choix.");
        alert.showAndWait();
        return;
        }*/
    // Le formulaire est valide, continuer avec la logique d'ajout
    // ...

        // Créer un objet Culture avec les valeurs entrées
          Equipement e = new Equipement( employe, Type, Marque, disponnible, etat,String.valueOf(matricule) );

        // Insérer l'objet Culture dans la base de données
         EquipementCRUD ecd = new  EquipementCRUD();
        ecd.ajouterEquipement3(e); 
        showSuccess("L'Equipement a été ajoutée avec succès");
    
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
        Equipement equipementToUpdate = (Equipement) table_view.getSelectionModel().getSelectedItem();
        /*if (equipementToUpdate == null) {
        showError("Veuillez sélectionner un equipment à modifier");
        return;
        }*/
         tfID_emp.setValue(equipementToUpdate.getEmploye_());
        tfEtat.setText(String.valueOf(equipementToUpdate.getEtat()));
        tfMatricule.setText(String.valueOf(equipementToUpdate.getMatricule()));
        tfType.setText(equipementToUpdate.getType());
        tfMarque.setText(equipementToUpdate.getMarque());
        
        equipementToUpdate.setEmploye(employe);
        equipementToUpdate.setType(Type);
        equipementToUpdate.setMarque(Marque);
        equipementToUpdate.setDisponnible(disponnible);
        equipementToUpdate.setEtat(etat);
        equipementToUpdate.setMatricule(matriculeStr);
       
        ecd.modifier(equipementToUpdate);
        
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
        
    
     } catch (Exception e) {
        showError("Une erreur s'est produite lors de la modification de la liste equipement");
        e.printStackTrace();
    }
    }

    private void SupprimerEquipment(ActionEvent event) {
        
         try {
        // Récupérer la culture à supprimer
        Equipement equipementASupprimer = table_view.getSelectionModel().getSelectedItem();

        // Vérifier si une culture est bien sélectionnée
        if (equipementASupprimer == null) {
            showError("Veuillez sélectionner un equipement à supprimer");
            return;
        }

        // Supprimer la culture de la base de données
        EquipementCRUD equipementCRUD = new EquipementCRUD();
        equipementCRUD.supprimer(equipementASupprimer);

        // Afficher un message de succès
        showSuccess("L'equipement a été supprimée avec succès");

        // Vider les champs de texte et de date de sélection
      // Vider les champs de texte et de date de sélection
        tfID_emp.setValue(null);
        tfType.setText("");
        tfMarque.setText("");
        tfDisponnible1.setText("");
        tfDisponnible2.setText("");
        tfEtat.setText("");
        tfMatricule.setText("");
        
       
    } catch (Exception e) {
        showError("Une erreur s'est produite lors de la suppression d'equipement");
        e.printStackTrace();
    }
    }

    

    private void ShowEquipment(ActionEvent event) {

     EquipementCRUD ecd = new EquipementCRUD();
    List<Equipement> Equipements = ecd.getAll();

    // vider les lignes existantes
    table_view.getItems().clear();

    // ajouter les Equipments à la TableView
    table_view.getItems().addAll(Equipements);

    // associer les colonnes avec les attributs de Employe
    col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    //col_employe_id.setCellValueFactory(new PropertyValueFactory<>("employe_id"));
   col_employe_id.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getEmploye_()));

    col_Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
    col_Marque.setCellValueFactory(new PropertyValueFactory<>("Marque"));
    col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    col_disponnible.setCellValueFactory(new PropertyValueFactory<>("disponnible"));
    col_matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
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
    
}

          