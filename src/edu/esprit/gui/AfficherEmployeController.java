/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.twilio.Twilio;
import edu.esprit.entities.Employe;
import static edu.esprit.gui.AfficherEmployeController.ACCOUNT_SID;
import static edu.esprit.gui.AfficherEmployeController.AUTH_TOKEN;
import edu.esprit.services.EmployeCRUD;
import java.io.FileOutputStream;
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
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 * FXML Controller class
 *
 * @author amine
 */
public class AfficherEmployeController implements Initializable {

    @FXML
    private TableView<Employe> table_vieww;
  
    @FXML
    private TableColumn<Employe, String> col_nom;
    @FXML
    private TableColumn<Employe, String> col_prenom;
    @FXML
    private TableColumn<Employe, String> col_cin;
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
      ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Tout", "nom", "prenom", "cin");
    @FXML
    private ComboBox<String> cbtri;
     ObservableList<String> listeTypetri = FXCollections.observableArrayList("Tout",  "nom", "prenom", "cin");
       ObservableList<Employe> reslist = FXCollections.observableArrayList();
    @FXML
    private Button btnAfficher;
    @FXML
    private TableColumn<Employe, String> col_salaire;
    @FXML
    private TableColumn<Employe, String> col_age;
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
    private Button btnExcel;
    @FXML
    private TableColumn<Employe, String> col_tel;
    @FXML
    private Button btnsms;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbRecherche.setItems(listeTypeRecherche);
        cbRecherche.setValue("Tout");
        cbtri.setItems(listeTypetri);
        cbtri.setValue("Tout");
        try {
        listtri();
             try {
                  list();
              } catch (SQLException ex) {
                  Logger.getLogger(AfficherEmployeController.class.getName()).log(Level.SEVERE, null, ex);
               }
        } catch (SQLException ex) {
        Logger.getLogger(AfficherEmployeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


 


    @FXML
    private void list() throws SQLException {
      EmployeCRUD pcd = new EmployeCRUD();
    List<Employe> Employes = pcd.getAll();


    // Récupérer la valeur de recherche et de tri sélectionnée
    String recherche = cbRecherche.getValue();
   

    // Créer un stream à partir de la liste des cultures
    Stream<Employe> stream = Employes.stream();

    // Filtrer les cultures en fonction de la recherche sélectionnée
    if (!recherche.equals("Tout")) {
        stream = stream.filter(c -> {
            switch (recherche) {
                case "nom":
                    return c.getNom().toLowerCase().contains(tfrecherche.getText().toLowerCase());
                       case "prenom":
                    return c.getPrenom().toLowerCase().contains(tfrecherche.getText().toLowerCase());
                       case "cin":
                    return c.getCin().toLowerCase().contains(tfrecherche.getText().toLowerCase());
                    
                    /*case "date_planting":
                    LocalDate date = dtDate_planting.getValue();
                    if (date != null) {
                    Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
                    Date datePlanted = Date.from(instant);
                    return c.getDate_planting().equals(datePlanted);
                    }
                    return false;*/
                    /*case "date_planting":
                    LocalDate date = dtDate_planting.getValue();
                    if (date != null) {
                    try {
                    Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
                    Date datePlanted = Date.from(instant);
                    if (c.getDate_planting() != null) {
                    return c.getDate_planting().compareTo(datePlanted) == 0;
                    } else {
                    throw new NullPointerException("c.getDate_planting() returned null");
                    }
                    } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                    return false;
                    }
                    } else {
                    System.err.println("Error: dtDate_planting is null");
                    return false;
                    }*/
                    /*
                    case "quantite":
                    try {
                    float quantite = Float.parseFloat(tfrecherche.getText());
                    return c.getQuantite() == quantite;
                    } catch (NumberFormatException e) {
                    return false;
                    }*/
                default:
                    return true;
            }
        });
    }

  

    // Convertir le stream en liste et l'afficher dans la table view
    reslist = FXCollections.observableArrayList(stream.collect(Collectors.toList()));
    table_vieww.setItems(reslist);
}


    @FXML
    private void listtri() throws SQLException {
    EmployeCRUD pcd = new EmployeCRUD();
    List<Employe> Employes = pcd.getAll();

     // Récupérer la valeur de recherche et de tri sélectionnée
    String tri = cbtri.getValue();
    // Créer un stream à partir de la liste des cultures
    Stream<Employe> stream = Employes.stream();
    // Trier les cultures en fonction du tri sélectionné
    if (!tri.equals("Tout")) {
        Comparator<Employe> comparator;
        switch (tri) {
            case "nom":
                comparator = Comparator.comparing(c -> c.getNom().toLowerCase());
                break;
                 case "prenom":
                comparator = Comparator.comparing(c -> c.getPrenom().toLowerCase());
                break;
                 case "cin":
                comparator = Comparator.comparing(c -> c.getCin().toLowerCase());
                break;
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
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("AfficherEquipment.fxml"));
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
    private void AddEmploye(ActionEvent event)throws IOException {
         Stage primarystage = null;
        Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("AjouterEmploye.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();}

    @FXML
    private void SupprimerEmploye(ActionEvent event) {
      try {
        // Récupérer la culture à supprimer
          Employe EmployeASupprimer = (Employe) table_vieww.getSelectionModel().getSelectedItem();

        // Vérifier si une culture est bien sélectionnée
        if (EmployeASupprimer == null) {
            showError("Veuillez sélectionner un Employe à supprimer");
            return;
        }

        // Supprimer la culture de la base de données
         EmployeCRUD employeCRUD = new EmployeCRUD();
        employeCRUD.supprimer(EmployeASupprimer);

        // Afficher un message de succès
        showSuccess("L'Employe a été supprimée avec succès");
 JOptionPane.showMessageDialog(null, "Employe supprimeé");
  EmployeData();
        table_vieww.refresh();
      
    } catch (Exception e) {
        showError("Une erreur s'est produite lors de la suppression de l'Employe");
        e.printStackTrace();
    }
    }

    @FXML
    private void ModifierEmploye(ActionEvent event) throws IOException {
      // Récupération de la culture sélectionnée dans le tableau
        Employe EmployeAModifiee = table_vieww.getSelectionModel().getSelectedItem();
 // Vérifier si une culture est bien sélectionnée
        if (EmployeAModifiee == null) {
            showError("Veuillez sélectionner unEmployer");
            return;
        }
        
            
            // Ouverture de la fenêtre de modification
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEmploye.fxml"));
            Parent root = loader.load();
            ModifierEmployeController mcc = loader.getController();

            // Transmission des données de la culture sélectionnée à la fenêtre de modification
            mcc.initData(EmployeAModifiee);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
     
  
            
    }
 private void EmployeData() throws SQLException {  ObservableList<Employe> listEmploye = FXCollections.observableArrayList();
           col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    col_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
    col_salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
    col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
     col_tel.setCellValueFactory(new PropertyValueFactory<>("numtelephone"));
     EmployeCRUD pcd = new EmployeCRUD();
    List<Employe> Employes = pcd.getAll();

    listEmploye.addAll();
        table_vieww.setItems(listEmploye);}
    

    @FXML
    private void AfficherEmploye(ActionEvent event) {
         EmployeCRUD pcd = new EmployeCRUD();
    List<Employe> Employes = pcd.getAll();

    // vider les lignes existantes
    table_vieww.getItems().clear();

    // ajouter les Employes à la TableView
    table_vieww.getItems().addAll(Employes);

    // associer les colonnes avec les attributs de Employe
    
    col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    col_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
    col_salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
    col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
     col_tel.setCellValueFactory(new PropertyValueFactory<>("numtelephone"));
    }
     // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "AC7a16cb903250f33e1a001ae54514951d";
    public static final String AUTH_TOKEN = "4abebe48f8d6b6b4ae1fdef4b7a51440";
   
    @FXML
    public void envoyerSMS() {
    // Connectez-vous au service SMS ici
    
    // Récupérer tous les employés
    EmployeCRUD pcd = new EmployeCRUD();
    List<Employe> employes = pcd.getAll();

    // Boucle à travers chaque employé et envoyez-leur un SMS
    for (Employe employe : employes) {
        String message = "Bonjour " + employe.getNom()+employe.getPrenom() + ",c'est votre employeur. Juste pour vous rappeler que vous avez une réunion importante demain à 10 heures. Merci de confirmer que vous avez bien reçu ce message.\n" +
"\n" +
"Cordialement,";
        int numero = employe.getNumtelephone();

        // Envoyer le SMS à l'employé
        boolean envoiReussi = envoyerSMS(numero, message);

        if (envoiReussi) {
            System.out.println("SMS envoyé à " + employe.getNom());
        } else {
            System.out.println("Échec de l'envoi du SMS à " + employe.getNom());
        }
    }
}
    public boolean envoyerSMS(int numero, String message) {
    // Envoyer le SMS à l'employé
    try {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(new PhoneNumber("+216" + numero), new PhoneNumber("+1 620 658 8364"), message).create();
        return true;
    } catch (Exception ex) {
        System.out.println("Erreur lors de l'envoi du SMS: " + ex.getMessage());
        return false;
    }
}

    /*public boolean envoyerSMS(int numero, String message) {
    // Boucle pour envoyer un SMS à chaque employé
    EmployeCRUD pcd = new EmployeCRUD();
    List<Employe> employes = pcd.getAll();
    
    for (Employe employe : employes) {
    //int numero = employe.getNumtelephone();
    // String message = "Bonjour " + employe.getNom() + ", vous avez un nouveau message.";
    Message.creator(new PhoneNumber(numero), new PhoneNumber("+14155238886"), message).create();
    }
    return true;
    }*/
   


    @FXML
    private void generateExcel(ActionEvent event) {
     // Récupérer la liste des employés
    EmployeCRUD pcd = new EmployeCRUD();
    List<Employe> employes = pcd.getAll();
    
    // Créer un nouveau classeur Excel
    XSSFWorkbook workbook = new XSSFWorkbook();
    
    // Créer une nouvelle feuille
    XSSFSheet sheet = workbook.createSheet("Employes");
    
    // Créer une ligne pour les en-têtes de colonnes
    XSSFRow headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("Nom");
    headerRow.createCell(1).setCellValue("Prénom");
    headerRow.createCell(2).setCellValue("CIN");
    headerRow.createCell(3).setCellValue("Salaire");
    headerRow.createCell(4).setCellValue("Âge");
    headerRow.createCell(5).setCellValue("Numero");

    // Ajouter les données des employés dans le classeur Excel
    for (int i = 0; i < employes.size(); i++) {
        Employe employe = employes.get(i);
        
        XSSFRow row = sheet.createRow(i + 1);
        row.createCell(0).setCellValue(employe.getNom());
        row.createCell(1).setCellValue(employe.getPrenom());
        row.createCell(2).setCellValue(employe.getCin());
        row.createCell(3).setCellValue(employe.getSalaire());
        row.createCell(4).setCellValue(employe.getAge());
        row.createCell(5).setCellValue(employe.getNumtelephone());
    }
    
    // Enregistrer le classeur Excel dans un fichier
    try {
        FileOutputStream outputStream = new FileOutputStream("employes.xlsx");
        workbook.write(outputStream);
        workbook.close();
        System.out.println("****");
        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Excel");
        alert.setHeaderText(null);
        alert.setContentText("Le fichier a été exporté avec succès !");
        alert.showAndWait();
    } catch (IOException e) {
        // Afficher une erreur en cas de problème lors de l'écriture du fichier
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Export Excel");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur est survenue lors de l'export du fichier !");
        alert.showAndWait();
    }
}
}
