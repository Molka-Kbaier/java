/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Employe;
import edu.esprit.services.EmployeCRUD;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ModifierEmployeController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfCin;
    @FXML
    private Button btnModifier;
    private TableView<Employe> table_view;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfSalaire;
    @FXML
    private TextField tfNumtelephone;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* TableColumn<Employe, String> nomCol = new TableColumn<>("Nom");
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        TableColumn<Employe, String> prenomCol = new TableColumn<>("Prenom");
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        
        TableColumn<Employe, String> cinCol = new TableColumn<>("Cin");
        cinCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
        TableColumn<Employe, String> ageCol = new TableColumn<>("age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        
        TableColumn<Employe, String> salaireCol = new TableColumn<>("salaire");
        salaireCol.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        
        table_view.getColumns().addAll(nomCol, prenomCol, cinCol);*/
    }    
private Employe employeAModifier; // Ajouter une variable de classe pour stocker la culture sélectionnée

    public void initData(Employe employe) {
        employeAModifier = employe; // Stocker la culture sélectionnée dans la variable
        tfNom.setText(employe.getNom());
          tfPrenom.setText(employe.getPrenom());
            tfCin.setText(employe.getCin());
             tfAge.setText(Integer.toString(employe.getAge()));
              tfSalaire.setText(Float.toString(employe.getSalaire()));
              tfNumtelephone.setText(Integer.toString(employe.getNumtelephone()));
           //
          
    }
  
    @FXML
    private void ModiferEmploye(ActionEvent event) {
         try {
        String nom = tfNom.getText();
        if (nom.isEmpty()) {
            showError("Veuillez saisir un nom");
            return;
        } else if (!nom.matches("[a-zA-Z]+")) {
            showError("Le nom doit contenir uniquement des lettres");
            return;
        } else if (nom.length() < 2 || nom.length() > 40) {
            showError("Le nom doit avoir une longueur comprise entre 2 et 40 caractères");
            return;
        }
      
        String prenom = tfPrenom.getText();  
        if (prenom.isEmpty()) {
            showError("Veuillez saisir un prénom");
            return;
        } else if (!prenom.matches("[a-zA-Z]+")) {
            showError("Le prénom doit contenir uniquement des lettres");
            return;
        } else if (prenom.length() < 2 || prenom.length() > 40) {
            showError("Le prénom doit avoir une longueur comprise entre 2 et 40 caractères");
            return;
        }
        String CinStr = tfCin.getText();
        //String cin = tfCin.getText();
        // Créer un objet Culture avec les valeurs entrées
        if (CinStr.isEmpty()) {
            showError("Veuillez saisir une cin");
            return;
        } else if (CinStr.length() != 8) {
            showError("Le cin doit etre composé de 8 chiffres");
            return;
        }
       // float Cin= Float.parseFloat(CinStr);
        float cin;
        try {
            cin= Float.parseFloat(CinStr);
        } catch (NumberFormatException e) {
            showError("Le cin doit être un nombre entier");
            return;
        }
          String AgeStr = tfAge.getText();
        //String cin = tfCin.getText();
        // Créer un objet Culture avec les valeurs entrées
        if (AgeStr.isEmpty()) {
            showError("Veuillez saisir un age");
            return;
        }else if (AgeStr.length() != 2) {
            showError("L'âge doit être composé de 2 chiffres");
            return;
        }
       // float Cin= Float.parseFloat(CinStr);
        int age;
        try {
            age = Integer.parseInt(AgeStr);
        } catch (NumberFormatException e) {
            showError("L'age doit être un nombre entier");
            return;
        }
        if (age < 18) {
            showError("L'âge doit être supérieur ou égal à 18 ans");
            return;
        }
        String SalaireStr = tfSalaire .getText();
        //String cin = tfCin.getText();
        // Créer un objet Culture avec les valeurs entrées
        if (SalaireStr.isEmpty()) {
            showError("Veuillez saisir une salaire");
            return;
            /*} else if (SalaireStr.length() < 3 || CinStr.length() > 4) {
            showError("Le cin doit etre composé de 3 ou 4  chiffres");
            return;*/
            }
       // float Cin= Float.parseFloat(CinStr);
         float salaire = Float.parseFloat(SalaireStr);
        try {
            cin= Integer.parseInt(CinStr);
        } catch (NumberFormatException e) {
            showError("La salaire  doit être un nombre entier");
            return;
        }
         String NumtelephoneStr = tfNumtelephone.getText();
        //String cin = tfCin.getText();
        // Créer un objet Culture avec les valeurs entrées
        if (NumtelephoneStr.isEmpty()) {
            showError("Veuillez saisir un numero de telephone");
            return;
       } else if (NumtelephoneStr.length() != 8) {
            showError("Le numero de telephone doit être composé de 8 chiffres");
            return;
        }
         int numtelephone;
        try {
            numtelephone= Integer.parseInt(NumtelephoneStr);
        } catch (NumberFormatException e) {
            showError("Le numero de telephone doit être un nombre entier");
            return;
        }
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEmploye.fxml"));
Parent root = loader.load();
AfficherEmployeController afficherEmployeController = loader.getController();
        // Mettre à jour la culture dans la base de données
        //récupérer les valeurs saisies par l'utilisateur vérifie que tous les champs ont bien été rempli
        EmployeCRUD pcd = new EmployeCRUD();
        //récupèrer la culture à modifier à partir de la ligne sélectionnée dans la TableView
        /* Culture cultureToUpdate = table_view.getSelectionModel().getSelectedItem();
        if (cultureToUpdate == null) {
        showError("Veuillez sélectionner une culture à modifier");
        return;
        }*/
        //met à jour les attributs de la culture avec les nouvelles valeurs saisies
        /*Culture cultureToUpdate = new Culture();
        cultureToUpdate.setType(type);
        cultureToUpdate.setDate_planting(date_planting);
        cultureToUpdate.setQuantite(quantite);
        cc.modifier(cultureToUpdate);*/
// Transfert des données de la culture modifiée au contrôleur AfficherCultureController
//afficherCultureController.modifierCulture(cultureToUpdate);

 
            employeAModifier.setNom(nom); // Mettre à jour la culture sélectionnée
            employeAModifier.setPrenom(prenom);
            employeAModifier.setCin(CinStr);
             employeAModifier.setAge((int) age);
              employeAModifier.setSalaire(salaire);
               employeAModifier.setNumtelephone(numtelephone);
            pcd.modifier(employeAModifier);
        // Afficher un message de succès
        showSuccess("L'employé a été modifiée avec succès");

        // Vider les champs de texte et de date de sélection
        tfNom.setText("");
       tfPrenom.setText("");
        tfCin.setText("");
         tfAge.setText("");
         tfSalaire.setText("");
          tfNumtelephone.setText("");
         //
Stage stage = (Stage) btnModifier.getScene().getWindow();
    stage.close();

    } catch (Exception e) {
        showError("Une erreur s'est produite lors de la modification de l'employé");
        e.printStackTrace();
    }

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

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage primaryStage = null;
        Stage SecondWindow = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("AfficherEmploye.fxml"));;
        Scene rec2 = new Scene(root);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }
}
