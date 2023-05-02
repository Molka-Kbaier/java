/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Culture;
import edu.esprit.services.CultureCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterCultureController implements Initializable {

    private TextField tfID;
    @FXML
    private TextField tfType;
    @FXML
    private DatePicker dtDate_planting;
    @FXML
    private TextField tfQuantite;
    private TableColumn<Culture, String> Col_type;
    private TableColumn<Culture, Date> Col_date_planting;
    private TableColumn<Culture, Float> Col_quantite;
    private TableView<Culture> table_view;
     private Stage primarystage;
    @FXML
    private ImageView Im_agro;
    @FXML
    private Button btnAjouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    // Configurer les colonnes de TableView
    /* TableColumn<Culture, String> typeCol = new TableColumn<>("Type");
    typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    
    TableColumn<Culture, Date> dateCol = new TableColumn<>("Date de plantation");
    dateCol.setCellValueFactory(new PropertyValueFactory<>("date_planting"));
    
    TableColumn<Culture, Float> quantiteCol = new TableColumn<>("Quantité");
    quantiteCol.setCellValueFactory(new PropertyValueFactory<>("quantite"));

    table_view.getColumns().addAll(typeCol, dateCol, quantiteCol);*/
    // Ajouter un listener à la table-view pour détecter lorsqu'une culture est sélectionné
    /*table_view.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
    if (newSelection != null) {
    // Mettre à jour les champs correspondants avec les informations du culture sélectionné
    tfQuantite.setText(String.valueOf(newSelection.getQuantite()));
    tfType.setText(newSelection.getType());
    //convertir une java.util.Date en une LocalDate afin de pouvoir afficher la date dans un DatePicker.
    Date date = newSelection.getDate_planting();
    Instant instant = Instant.ofEpochMilli(date.getTime());
    ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
    LocalDate localDate = zdt.toLocalDate();
    dtDate_planting.setValue(localDate);
    
    
    } else {
    // Si aucune culutre n'est sélectionné, vider les champs
    tfQuantite.setText("");
    dtDate_planting.setValue(null);
    
    tfType.setText("");
    
    }
    });*/
    }    

    @FXML
    private void AddCulture(ActionEvent event) {
  
          try {
              /*if (tfID.getText().isEmpty() || tfType.getText().isEmpty() || dtDate_planting.getValue() == null || tfQuantite.getText().isEmpty()) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Champs vides");
              alert.setHeaderText(null);
              alert.setContentText("Tous les champs doivent être remplis");
              alert.showAndWait();
              return;}*/
        
        //int id = Integer.parseInt(tfID.getText());
        String type = tfType.getText();
        if (type.isEmpty()) {
            showError("Veuillez saisir un type");
            return;
        } else if (!type.matches("[a-zA-Z]+")) {
            showError("Le type doit contenir uniquement des lettres");
            return;
        } else if (type.length() < 2 || type.length() > 20) {
            showError("Le type doit avoir une longueur comprise entre 2 et 20 caractères");
            return;
        }
         LocalDate localDate = dtDate_planting.getValue();
         
         if (localDate == null) {
            showError("Veuillez saisir une date");
            return;
        } else if (localDate.isAfter(LocalDate.now())) {
            showError("La date doit être inférieure ou égale à la date d'aujourd'hui");
            return;
        }
        Date date_planting = java.sql.Date.valueOf(localDate);
       // Date date_planting = java.sql.Date.valueOf(dtDate_planting.getValue());
        String quantiteStr = tfQuantite.getText();
        if (quantiteStr.isEmpty()) {
            showError("Veuillez saisir une quantité");
            return;
        }
       // float quantite = Float.parseFloat(quantiteStr);
        float quantite;
        try {
            quantite = Float.parseFloat(quantiteStr);
        } catch (NumberFormatException e) {
            showError("La quantité doit être un nombre décimal");
            return;
        }
       
        //float quantite = Float.parseFloat(tfQuantite.getText());
 
        // Créer un objet Culture avec les valeurs entrées
        Culture c = new Culture( type, date_planting, quantite);

        // Insérer l'objet Culture dans la base de données
        CultureCRUD cc = new CultureCRUD();
        cc.ajouter(c);
        showSuccess("La culture a été ajoutée avec succès");
        } catch (Exception e) {
        showError("Une erreur s'est produite lors de l'ajout de la culture");
        e.printStackTrace();
    }
    
}
    
    /*private void ModifierCulture(ActionEvent event) {
    
    try {
    String type = tfType.getText();
    if (type.isEmpty()) {
    showError("Veuillez saisir un type");
    return;
    } else if (!type.matches("[a-zA-Z]+")) {
    showError("Le type doit contenir uniquement des lettres");
    return;
    } else if (type.length() < 2 || type.length() > 20) {
    showError("Le type doit avoir une longueur comprise entre 2 et 20 caractères");
    return;
    }
    
    LocalDate localDate = dtDate_planting.getValue();
    if (localDate == null) {
    showError("Veuillez saisir une date");
    return;
    } else if (localDate.isAfter(LocalDate.now())) {
    showError("La date doit être inférieure ou égale à la date d'aujourd'hui");
    return;
    }
    Date date_planting = java.sql.Date.valueOf(localDate);
    
    String quantiteStr = tfQuantite.getText();
    if (quantiteStr.isEmpty()) {
    showError("Veuillez saisir une quantité");
    return;
    }
    
    float quantite;
    try {
    quantite = Float.parseFloat(quantiteStr);
    } catch (NumberFormatException e) {
    showError("La quantité doit être un nombre décimal");
    return;
    }
    
    // Mettre à jour la culture dans la base de données
    //récupérer les valeurs saisies par l'utilisateur vérifie que tous les champs ont bien été rempli
    CultureCRUD cc = new CultureCRUD();
    //récupèrer la culture à modifier à partir de la ligne sélectionnée dans la TableView
    Culture cultureToUpdate = table_view.getSelectionModel().getSelectedItem();
    if (cultureToUpdate == null) {
    showError("Veuillez sélectionner une culture à modifier");
    return;
    }
    //met à jour les attributs de la culture avec les nouvelles valeurs saisies
    cultureToUpdate.setType(type);
    cultureToUpdate.setDate_planting(date_planting);
    cultureToUpdate.setQuantite(quantite);
    cc.modifier(cultureToUpdate);
    
    // Afficher un message de succès
    showSuccess("La culture a été modifiée avec succès");
    
    // Vider les champs de texte et de date de sélection
    tfType.setText("");
    dtDate_planting.setValue(null);
    tfQuantite.setText("");
    
    
    } catch (Exception e) {
    showError("Une erreur s'est produite lors de la modification de la culture");
    e.printStackTrace();
    }
    }*/
    /* private void AfficherCulture(ActionEvent event) {
    // utilise une instance de la classe CultureCRUD pour récupérer une liste de tous les cultures à afficher à partir de la base de données.
    //et stockée dans  cultures
    CultureCRUD cc = new CultureCRUD();
    List<Culture> cultures = cc.afficher();
    
    // vider les lignes existantes
    table_view.getItems().clear();
    
    // ajouter les cultures à la TableView
    table_view.getItems().addAll(cultures);
    
    // associer les colonnes avec les attributs de Culture
    //  Col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    Col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
    
    Col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
    Col_date_planting.setCellValueFactory(new PropertyValueFactory<>("date_planting"));
    }
    
    private void SupprimerCulture(ActionEvent event) {
    try {
    // Récupérer la culture à supprimer
    Culture cultureASupprimer = table_view.getSelectionModel().getSelectedItem();
    
    // Vérifier si une culture est bien sélectionnée
    if (cultureASupprimer == null) {
    showError("Veuillez sélectionner une culture à supprimer");
    return;
    }
    
    // Supprimer la culture de la base de données
    CultureCRUD cultureCRUD = new CultureCRUD();
    cultureCRUD.supprimer(cultureASupprimer);
    
    // Afficher un message de succès
    showSuccess("La culture a été supprimée avec succès");
    
    // Vider les champs de texte et de date de sélection
    tfType.setText("");
    dtDate_planting.setValue(null);
    tfQuantite.setText("");
    
    } catch (Exception e) {
    showError("Une erreur s'est produite lors de la suppression de la culture");
    e.printStackTrace();
    }
    }*/

    /*  @FXML
    private void SupprimerCulture(ActionEvent event) {
    try {
    String type = tfType.getText();
    float quantite;
    String quantiteStr = tfQuantite.getText();
    quantite = Float.parseFloat(quantiteStr);
    LocalDate localDate = dtDate_planting.getValue();
    Date date_planting = java.sql.Date.valueOf(localDate);
    // Récupérer l'identifiant de la culture à supprimer
    int id = Integer.parseInt(tfID.getText());
    
    CultureCRUD cc = new CultureCRUD();
    Culture cultureTodelete = table_view.getSelectionModel().getSelectedItem();
    if (cultureTodelete == null) {
    showError("Veuillez sélectionner une culture à modifier");
    return;
    }
    cultureTodelete.setType(type);
    cultureTodelete.setDate_planting(date_planting);
    cultureTodelete.setQuantite(quantite);
    cc.supprimer(cultureTodelete);
    
    // Afficher un message de succès
    showSuccess("La culture a été supprimée avec succès");
    
    // Vider les champs de texte et de date de sélection
    tfType.setText("");
    dtDate_planting.setValue(null);
    tfQuantite.setText("");
    
    } catch (NumberFormatException e) {
    showError("Le format de la quantité est incorrect");
    } catch (Exception e) {
    showError("Une erreur s'est produite lors de la modification de la culture");
    e.printStackTrace();
    }
    }*/

  
   

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
    private void Retour(ActionEvent event) throws IOException {
        Stage primaryStage = null;
        Stage SecondWindow = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("AfficherCulturee.fxml"));;
        Scene rec2 = new Scene(root);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }








    
}





