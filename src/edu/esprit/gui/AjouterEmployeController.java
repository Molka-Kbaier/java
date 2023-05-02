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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AjouterEmployeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfCin;
    @FXML
    private Button btnAjouter;
    private TableColumn<Employe, Integer> col_id;
    private TableColumn<Employe, String> col_nom;
    private TableColumn<Employe, String> col_prenom;
    private TableColumn<Employe, String> col_cin;
    private TableColumn<Employe, String> col_numtelephone;
    private TableView<Employe> table_view;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfSalaire;
    @FXML
    private TextField tfNumtelephone;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Configurer les colonnes de TableView
         /* TableColumn<Employe, String> nomCol = new TableColumn<>("Nom");
         nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
         
         TableColumn<Employe, String> prenomCol = new TableColumn<>("Prenom");
         prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         
         TableColumn<Employe, String> cinCol = new TableColumn<>("Cin");
         cinCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
         
         TableColumn<Employe, String> ageCol = new TableColumn<>("age");
         ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
         
         TableColumn<Employe, String> salaireCol = new TableColumn<>("salaire");
         salaireCol.setCellValueFactory(new PropertyValueFactory<>("salaire"));*/
   // table_view.getColumns().addAll(nomCol, prenomCol, cinCol, ageCol, salaireCol);
    }    
    @FXML
     private void AddEmploye(ActionEvent event) {
          try {
              /*if (tfNom.getText().isEmpty() || tfPrenom.getText().isEmpty() || tfCin.getText().isEmpty()) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Champs vides");
              alert.setHeaderText(null);
              alert.setContentText("Tous les champs doivent être remplis");
              alert.showAndWait();
              return;}*/
       // int id = Integer.parseInt(tfID.getText());
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
            showError("Le cin doit être composé de 8 chiffres");
            return;
        }
       // float Cin= Float.parseFloat(CinStr);
        int cin;
        try {
            cin= Integer.parseInt(CinStr);
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
        } else if (AgeStr.length() != 2) {
            showError("L'âge doit être composé de 2 chiffres");
            return;
        }
       // float Cin= Float.parseFloat(CinStr);
        int age;
        try {
            age= Integer.parseInt(AgeStr);
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
            /* } else if (SalaireStr.length() < 4 || CinStr.length() > 4) {
            showError("Le salaire doit etre composé de  4  chiffres");
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
       // float Numtelephone= Float.parseFloat(CinStr);
        int numtelephone;
        try {
            numtelephone= Integer.parseInt(NumtelephoneStr);
        } catch (NumberFormatException e) {
            showError("Le numero de telephone doit être un nombre entier");
            return;
        }
       
    
        
      //  Employe p = new Employe( nom, prenom, cin);
        Employe p = new Employe(nom, prenom, String.valueOf(cin), (int) age, salaire ,numtelephone );

        // Insérer l'objet Culture dans la base de données
        EmployeCRUD pcd = new EmployeCRUD();
        // Vérifier si l'employé existe déjà
        Employe existant = pcd.rechercherParCin(String.valueOf(cin));
        if (existant != null) {
            showError("Cet employé existe déjà");
            return;
        }
        pcd.ajouter2(p); 
           showSuccess("L'employe a été ajoutée avec succès");
    }catch (NumberFormatException e) {
        // afficher un message d'erreur si les champs ne sont pas valides
        /*Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.showAndWait();*/
        showError("Le format du nom ou de  CIN est incorrect");
       // return;
}}

   
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

/* private void ModiferEmploye(ActionEvent event) {
try {
String nom = tfNom.getText();
if (nom.isEmpty()) {
showError("Veuillez saisir un nom");
return;
} else if (!nom.matches("[a-zA-Z]+")) {
showError("Le nom doit contenir uniquement des lettres");
return;
} else if (nom.length() < 2 || nom.length() > 20) {
showError("Le nom doit avoir une longueur comprise entre 2 et 20 caractères");
return;
}

String prenom = tfPrenom.getText();
if (prenom.isEmpty()) {
showError("Veuillez saisir un prénom");
return;
} else if (!prenom.matches("[a-zA-Z]+")) {
showError("Le prénom doit contenir uniquement des lettres");
return;
} else if (prenom.length() < 2 || prenom.length() > 20) {
showError("Le prénom doit avoir une longueur comprise entre 2 et 20 caractères");
return;
}
String CinStr = tfCin.getText();
//String cin = tfCin.getText();
// Créer un objet Culture avec les valeurs entrées
if (CinStr.isEmpty()) {
showError("Veuillez saisir une cin");
return;
} else if (CinStr.length() < 8 || CinStr.length() > 8) {
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
// Mettre à jour l'employe dans la base de données
EmployeCRUD pcd = new EmployeCRUD();
Employe EmployeToUpdate = (Employe) table_view.getSelectionModel().getSelectedItem();
if (EmployeToUpdate == null) {
showError("Veuillez sélectionner un Employe à modifier");
return;
}
EmployeToUpdate.setNom(nom);
EmployeToUpdate.setPrenom(prenom);
EmployeToUpdate.setCin(CinStr);
pcd.modifier(EmployeToUpdate);

// Afficher un message de succès
showSuccess("L'employe a été modifiée avec succès");

// Vider les champs de texte et de date de sélection
tfNom.setText("");
tfPrenom.setText("");
tfCin.setText("");
} catch (Exception e) {
showError("Une erreur s'est produite lors de la modification de la liste employe");
e.printStackTrace();
}
}*/
/*   private void SupprimerEmploye(ActionEvent event) {
try {
// Récupérer la culture à supprimer
Employe EmployeASupprimer = (Employe) table_view.getSelectionModel().getSelectedItem();

// Vérifier si une culture est bien sélectionnée
if (EmployeASupprimer == null) {
showError("Veuillez sélectionner une culture à supprimer");
return;
}

// Supprimer la culture de la base de données
EmployeCRUD employeCRUD = new EmployeCRUD();
employeCRUD.supprimer(EmployeASupprimer);

// Afficher un message de succès
showSuccess("La culture a été supprimée avec succès");

// Vider les champs de texte et de date de sélection
tfNom.setText("");
tfPrenom.setText("");
tfCin.setText("");

} catch (Exception e) {
showError("Une erreur s'est produite lors de la suppression de la employe");
e.printStackTrace();
}
}*/

    private void ShowEmploye(ActionEvent event) {
       
    EmployeCRUD pcd = new EmployeCRUD();
    List<Employe> Employes = pcd.getAll();

    // vider les lignes existantes
    table_view.getItems().clear();

    // ajouter les Employes à la TableView
    table_view.getItems().addAll(Employes);

    // associer les colonnes avec les attributs de Employe
    col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    col_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
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

