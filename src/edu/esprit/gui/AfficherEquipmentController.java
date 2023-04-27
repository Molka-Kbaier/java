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
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AfficherEquipmentController implements Initializable {

   
    private TableColumn<Equipement, Integer> col_id;
    @FXML
    private TableColumn<Equipement, String> col_employe_id;
    @FXML
    private TableColumn<Equipement, String> col_Type;
    @FXML
    private TableColumn<Equipement, String> col_Marque;
    @FXML
    private TableColumn<Equipement, String> col_etat;
    @FXML
    private TableColumn<Equipement, Integer> col_disponnible;
    @FXML
    private TableColumn<Equipement, String> col_matricule;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifier;
   
    @FXML
    private TextField tfrecherche;
    @FXML
    private ComboBox<String> cbRecherche;
    ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Tout", "Type", "Marque", "etat", "matricule");
    @FXML
    private Button btnimpression;
    @FXML
    private ComboBox<String> cbtri;
      ObservableList<String> listeTypetri = FXCollections.observableArrayList("Tout", "Type", "Marque", "etat", "matricule", "employe", "disponnible");
       ObservableList<Equipement> reslist = FXCollections.observableArrayList();
    @FXML
    private Button btnAfficher;
    @FXML
    private TableView<Equipement> table_vieww;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tffirstname;
    @FXML
    private TextField tflastname;
    @FXML
    private TextField tfNbrTel;
    @FXML
    private Button btnstat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        cbRecherche.setItems(listeTypeRecherche);
        cbRecherche.setValue("Tout");
        cbtri.setItems(listeTypetri);
        cbtri.setValue("Tout");
        try {
        listtri();
        list();
        } catch (SQLException ex) {
        Logger.getLogger(AfficherEquipmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void list() {
        EquipementCRUD pcd = new EquipementCRUD();
    List<Equipement> Equipements = pcd.getAll();


    // Récupérer la valeur de recherche et de tri sélectionnée
    String recherche = cbRecherche.getValue();
   

    // Créer un stream à partir de la liste des cultures
    Stream<Equipement> stream = Equipements.stream();

    // Filtrer les cultures en fonction de la recherche sélectionnée
    if (!recherche.equals("Tout")) {
        stream = stream.filter(c -> {
            switch (recherche) {
                case "type":
                    return c.getType().toLowerCase().contains(tfrecherche.getText().toLowerCase());
                       case "marque":
                    return c.getMarque().toLowerCase().contains(tfrecherche.getText().toLowerCase());
                       case "etat":
                    return c.getEtat().toLowerCase().contains(tfrecherche.getText().toLowerCase());
                      case "matricule":
                    return c.getMatricule().toLowerCase().contains(tfrecherche.getText().toLowerCase());
                  
                default:
                    return true;
            }
        });
    }}


    @FXML
    private void Imprimer(ActionEvent event) {
    }

    @FXML
    private void listtri() throws SQLException {
    EquipementCRUD pcd = new EquipementCRUD();
    List<Equipement> Equipements = pcd.getAll();

     // Récupérer la valeur de recherche et de tri sélectionnée
    String tri = cbtri.getValue();
    // Créer un stream à partir de la liste des cultures
    Stream<Equipement> stream = Equipements.stream();
    // Trier les cultures en fonction du tri sélectionné
    if (!tri.equals("Tout")) {
        Comparator<Equipement> comparator;
        switch (tri) {
            case "type":
                comparator = Comparator.comparing(c -> c.getType().toLowerCase());
                break;
                 
                 case "marque":
                comparator = Comparator.comparing(c -> c.getMarque().toLowerCase());
                break;
                 case "etat":
                comparator = Comparator.comparing(c -> c.getEtat().toLowerCase());
                break;
                case "disponnible":
                comparator = Comparator.comparingInt(Equipement::isDisponnible);
                break;
                 case "matricule":
                comparator = Comparator.comparing(c -> c.getMatricule().toLowerCase());
                break;
                 case "employe":
            stream = stream.sorted((t1, t2) -> t1.getEmploye_().getCin().compareTo(t2.getEmploye_().getCin()));
                /* case "date_planting":
                comparator = Comparator.comparing(Culture::getDate_planting);
                break;
                case "quantite":
                comparator = Comparator.comparing(Culture::getQuantite);
                break;*/
            default:
                comparator = null;
                break;
        }

        if (comparator != null) {
            stream = stream.sorted(comparator);
        }
    }

    // Convertir le stream en liste et l'afficher dans la table view
    reslist = FXCollections.observableArrayList(stream.collect(Collectors.toList()));
    table_vieww.setItems(reslist);


    /*//loadDate();
    List arr = cc.trier(cbtri.getValue());
    ObservableList obb = FXCollections.observableArrayList(arr);
    table_vieww.setItems(obb);*/
    }


    @FXML
    private void retour(ActionEvent event) throws IOException {
         Stage primarystage = null;
        Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("AfficherEmploye.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();}
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
    private void AddEquipment(ActionEvent event) throws IOException {
         Stage primarystage = null;
        Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("AjouterEquipment.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();}

    @FXML
    private void SupprimerEquipment(ActionEvent event) { try {
        // Récupérer la culture à supprimer
          Equipement EquipmentASupprimer = table_vieww.getSelectionModel().getSelectedItem();

        // Vérifier si une culture est bien sélectionnée
        if (EquipmentASupprimer == null) {
            showError("Veuillez sélectionner un Equipement à supprimer");
            return;
        }

        // Supprimer la culture de la base de données
          EquipementCRUD  EquipementCRUD = new  EquipementCRUD();
         EquipementCRUD.supprimer(EquipmentASupprimer);

        // Afficher un message de succès
        showSuccess("L'Equipement a été supprimée avec succès");
 JOptionPane.showMessageDialog(null, "Equipement supprimeé");
  EquipmentData();
        table_vieww.refresh();
      
    } catch (Exception e) {
        showError("Une erreur s'est produite lors de la suppression de l'Equipement");
        e.printStackTrace();
    }
    
    }
private void EquipmentData() throws SQLException {  ObservableList<Equipement> listEquipement = FXCollections.observableArrayList();
           // col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    //col_employe_id.setCellValueFactory(new PropertyValueFactory<>("employe_id"));
   col_employe_id.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmploye_().getCin()));

    col_Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
    col_Marque.setCellValueFactory(new PropertyValueFactory<>("Marque"));
    col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    col_disponnible.setCellValueFactory(new PropertyValueFactory<>("disponnible"));
    col_matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));

    EquipementCRUD ec = new EquipementCRUD();
     List<Equipement> Equipements = ec.getAll();
      listEquipement.addAll();
        table_vieww.setItems(listEquipement);
}
    @FXML
    private void ModifierEquipment(ActionEvent event)  throws IOException {
      // Récupération de la culture sélectionnée dans le tableau
        Equipement equipementAModifiee = table_vieww.getSelectionModel().getSelectedItem();
 // Vérifier si une culture est bien sélectionnée
        if (equipementAModifiee == null) {
            showError("Veuillez sélectionner un Equipement à supprimer");
            return;
        }
        
            
            // Ouverture de la fenêtre de modification
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEquipement.fxml"));
            Parent root = loader.load();
            ModifierEquipementController mtc = loader.getController();

            // Transmission des données de la culture sélectionnée à la fenêtre de modification
            mtc.initData(equipementAModifiee);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
     
  
            
    }
    @FXML
    private void AfficherEquipment(ActionEvent event) {
        EquipementCRUD ecd = new EquipementCRUD();
    List<Equipement> Equipements = ecd.getAll();

    // vider les lignes existantes
    table_vieww.getItems().clear();

    // ajouter les Equipments à la TableView
    table_vieww.getItems().addAll(Equipements);

    // associer les colonnes avec les attributs de Employe
   // col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    //col_employe_id.setCellValueFactory(new PropertyValueFactory<>("employe_id"));
   col_employe_id.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmploye_().getCin()));

    col_Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
    col_Marque.setCellValueFactory(new PropertyValueFactory<>("Marque"));
    col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    col_disponnible.setCellValueFactory(new PropertyValueFactory<>("disponnible"));
    col_matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));}

    @FXML
    private void stat(ActionEvent event) throws IOException {
         Stage primarystage = null;
        Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("stat.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();}

}
