/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.zxing.BarcodeFormat;
import entité.Evenement;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.EvenementService;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddEventController implements Initializable {

    @FXML
    private AnchorPane modpan;
    @FXML
    private ImageView im1;
    @FXML
    private ImageView im2;
    @FXML
    private ImageView imv;
    @FXML
    private Label tx1;
    @FXML
    private TextField titre;
    @FXML
    private TextField lieu;
    @FXML
    private TextField image;
    @FXML
    private Label tx2;
    @FXML
    private Label tx3;
    @FXML
    private Label tx4;
    @FXML
    private Button btn1;
    @FXML
    private Label tx5;
    @FXML
    private Button btn55;
    @FXML
    private Button bbn;
    @FXML
    private Button bbnn;
    @FXML
    private TableView<Evenement> tab;
    @FXML
    private TableColumn<Evenement, String> tr1;
    @FXML
    private TableColumn<Evenement, String> tr2;
    @FXML
    private TableColumn<Evenement, String> tr3;
    @FXML
    private TableColumn<Evenement, String> tr4;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML


     EvenementService es = new EvenementService();
    private List<Evenement> L;
    private TemplateEngine templateEngine;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       L=es.getAll();
        
        ObservableList <Evenement> list = FXCollections.observableArrayList();
      for (Evenement evenement: L){
          list.add(evenement);
      }
        tab.setItems(list);
        tr4.setCellValueFactory(new PropertyValueFactory<>("id"));
        tr1.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tr2.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        tr3.setCellValueFactory(new PropertyValueFactory<>("image"));
           
    }    

      @FXML
    private void ajouter(ActionEvent event) {
       String Titre = titre.getText();
        String Lieu = lieu.getText();
        String Image = image.getText();
        String titreRegex = "^[a-zA-Z]+$";

        if (Titre.isEmpty() || Lieu.isEmpty() || Image.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs doivent être remplis");
            alert.showAndWait();
            return;
        }
        else{
         if (!Titre.matches(titreRegex)) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Erreur");
             alert.setHeaderText("le titre de votre évènement est invalide!");
             alert.showAndWait();
        }else if (!(Lieu.length()>5)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Le lieu doit être d'au moins 6 caractères!");
            alert.showAndWait();
        }
        else{
            Evenement evenement = new Evenement();
        evenement.setTitre(Titre);
        evenement.setLieu(Lieu);
        evenement.setImage(Image);
         Image image110= new Image(new File(evenement.getImage()).toURI().toString());
             try {
                 FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *.jpeg, *.gif)", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
            FileWriter writer = new FileWriter("evenements.txt", true);
            writer.write(Stream.of(evenement.getId(), evenement.getTitre(), evenement.getLieu(), new File(evenement.getImage()).toURI().toString())
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ")) + System.lineSeparator());
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        
        }
            es.ajouter(evenement);
        }

    }}
    
    
    

     @FXML
    private void modifier(ActionEvent event) {
        try {
            Evenement selectedEvent = tab.getSelectionModel().getSelectedItem();
            // Vérifier si l'événement a été trouvé, afficher un message d'erreur si ce n'est pas le cas
            if (selectedEvent == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Aucun événement trouvé pour le modifier.");
                alert.showAndWait();
                return;
            }
            
            
            // Si l'événement a été trouvé, afficher les données de cet événement dans les champs de texte de la page modif.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modif.fxml"));
            Parent root = loader.load();
            ModifController modifController = loader.getController();
            modifController.initializeData(selectedEvent);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Evenement ev = modifController.getEvenement();
              } catch (IOException ex) {     
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
       

}

     @FXML
    private void supprimer(ActionEvent event) {
    Evenement selectedEvent1 = tab.getSelectionModel().getSelectedItem();
    int a = selectedEvent1.getId();
    System.out.println(a);
    if (selectedEvent1 == null) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aucune sélection");
        alert.setHeaderText("Sélectionnez un événement à supprimer");
        alert.showAndWait();
        return;
    }

    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet événement ?");
    alert.setContentText("L'evenement de titre: " + selectedEvent1.getTitre());

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
        es.supprimer(a);
        // Remove the deleted event from the event list view
        L = es.getAll();
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        for (Evenement evenement : L) {
            list.add(evenement);
        }
        tab.setItems(list);
    }

}
    

     @FXML
    public void handleRefresh(ActionEvent event) {
    // Get the current stage
    Stage stage = (Stage) btn4.getScene().getWindow();
    
    // Reload the page
    stage.getScene().setRoot(loadFXML("AddEvent.fxml"));
}
    public static Parent loadFXML(String fxmlFilePath) {
    FXMLLoader loader = new FXMLLoader(AddEventController.class.getResource("AddEvent.fxml"));
    try {
        return loader.load();
    } catch (IOException e) {
        // Handle the exception
        e.printStackTrace();
        return null;
    }
}

     @FXML
    private void chooseImage() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *.jpeg, *.gif)", "*.png", "*.jpg", "*.jpeg", "*.gif");
    fileChooser.getExtensionFilters().add(extFilter);
    File file = fileChooser.showOpenDialog(null);
    if (file != null) {

        String imagePath = file.getAbsolutePath();
         image.setText(imagePath);
        Evenement evenement = new Evenement();
        evenement.setImage(imagePath);
        evenement.setImageFile(es.loadImage(imagePath));
        imv.setImage(evenement.getImageFile());
    }
}
      @FXML
     private void genererPDF(ActionEvent event) {
     Evenement selectedEvent = tab.getSelectionModel().getSelectedItem();

    if (selectedEvent == null) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aucun évènement sélectionné");
        alert.setHeaderText("Sélectionnez un événement à générer");
        alert.showAndWait();
        return;
    }

    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText("Êtes-vous sûr de vouloir générer cet événement de titre: " + selectedEvent.getTitre() + " sous forme Pdf ?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
        EvenementService service = new EvenementService();
        service.genererPDF(selectedEvent);
    }
}
 @FXML
private void handleGenerateQRCode(ActionEvent event) throws com.google.zxing.WriterException {
    Evenement evenement = tab.getSelectionModel().getSelectedItem();
    if (evenement != null) {
        try {

          

            // Encode the HTML content as a QR Code
            String qrCodeData = evenement.getTitre() + "," + evenement.getLieu();
            String filePath = "C:/Users/ASUS/Desktop/pdf_uploading/QRCode.png";
            String fileType = "png";
            File qrFile = new File(filePath);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToFile(bitMatrix, fileType, qrFile);
            System.out.println("QR Code generated at : " + qrFile.getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
}