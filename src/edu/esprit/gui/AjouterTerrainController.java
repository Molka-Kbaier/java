package edu.esprit.gui;

import edu.esprit.entities.Culture;
import edu.esprit.entities.Terrain;
import edu.esprit.services.CultureCRUD;

import edu.esprit.services.TerrainCRUD;
//import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;



public class AjouterTerrainController implements Initializable {
 
    @FXML
    private TextField tfNumero;
    @FXML
    private TextField tfSurface;
    @FXML
    private TextField tfLieu;
    @FXML
    private ComboBox<Culture> cbCulture;
    @FXML
    private Button btnAjouter;
  
    /*private TableColumn<Terrain,Culture > Col_culture_id;
    private TableColumn<Terrain, String> Col_numero;
    private TableColumn<Terrain, String> Col_surface;
    private TableColumn<Terrain, String> Col_lieu;
    private TableView<Terrain> table_viewt;*/
     int index=-1;
    @FXML
    private ImageView Im_agro;
    @FXML
    private Button btnimage;
    @FXML
    private ImageView ivImage;
     private Image image;
      private FileChooser fileChooser;
 private File imageFile;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Culture> cultures = new CultureCRUD().afficher();
        cbCulture.setItems(FXCollections.observableArrayList(cultures));
       // btnimage.setOnAction(this::selectImage);

     // Ajouter un listener à la table-view pour détecter lorsqu'un terrain est sélectionné
     /*table_viewt.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
     if (newSelection != null && newSelection.getImage() != null) {
     // Mettre à jour les champs correspondants avec les informations du terrain sélectionné
     tfNumero.setText(String.valueOf(newSelection.getNumero()));
     tfSurface.setText(String.valueOf(newSelection.getSurface()));
     tfLieu.setText(newSelection.getLieu());
     cbCulture.setValue(newSelection.getCulture());
     // ivImage.setImage(new Image(newSelection.getImage()));
     // Afficher l'image correspondante
     //String imagePath = newSelection.getImage();
     // String imagePath = newSelection.getImage().substring(7);
     String imagePath = newSelection.getImage().replaceAll("^file:/+", "");
     
     
     
     
     } else {
     // Si aucun terrain n'est sélectionné, vider les champs
     tfNumero.setText("");
     tfSurface.setText("");
     tfLieu.setText("");
     cbCulture.getSelectionModel().clearSelection();
     ivImage.setImage(null);
     }
     });
     // Créer un file chooser pour les images
     fileChooser = new FileChooser();
     fileChooser.setTitle("Choisir une image");
     fileChooser.getExtensionFilters().addAll(
     new ExtensionFilter("Fichiers d'image", "*.png", "*.jpg")
     );*/
    fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers d'image", "*.png", "*.jpg")
        );
    }

    @FXML
    private void AddTerrain(ActionEvent event) {
          try {
        String numero = tfNumero.getText();
        
        if (numero.isEmpty()) {
            showError("Veuillez saisir un numero");
            return;
        }
       // float quantite = Float.parseFloat(quantiteStr);
        float numerof;
        try {
            numerof = Float.parseFloat(numero);
        } catch (NumberFormatException e) {
            showError("Le numero doit être un nombre décimal");
            return;
        }
       // int surface = Integer.parseInt( tfSurface.getText());
        String surface = tfSurface.getText();
        if (surface.isEmpty()) {
            showError("Veuillez saisir une surface");
            return; 
        } else {
            try {
                int surfaceInt = Integer.parseInt(surface);
                if (surfaceInt <= 0) {
                    showError("La surface doit être un entier positif.");
                    return;
                }
            } catch (NumberFormatException e) {
                showError("La surface doit être un entier.");
                return;
            }
        }
        String lieu = tfLieu.getText();
        
        if (lieu.isEmpty()) {
            showError("Veuillez saisir un lieu");
            return;
        } else if (lieu.length() <= 2 || lieu.length() >= 20) {
            showError("Le lieu du terrain doit contenir entre 2 et 20 caractères.");
            return;
        }
        
        Culture culture = cbCulture.getValue();
        if (culture == null) {
            showError("Veuillez sélectionner une culture.");
            return;}
        /*   Image image = ivImage.getImage();
        if (image == null) {
        showError("Veuillez sélectionner une image.");
        return;}
        String filename = image.toString();
        
        
        if (!(filename.endsWith(".png") || filename.endsWith(".jpg"))) {
        showError("Veuillez sélectionner un fichier image PNG ou JPG.");
        return;
        }*/
        
        Image image = ivImage.getImage();
        if (image == null) {
            showError("Veuillez sélectionner une image.");
            return;
        }

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile == null) {
            showError("Veuillez sélectionner un fichier image.");
            return;
        }

        String filename = selectedFile.toURI().toString();
        String extension = filename.substring(filename.lastIndexOf(".") + 1);

        if (!(extension.equals("png") || extension.equals("jpg"))) {
            showError("Veuillez sélectionner un fichier image PNG ou JPG.");
            return;
        }

        /*  BufferedImage bufferedImage = ImageIO.read(selectedFile);
        if (bufferedImage == null) {
        showError("Le fichier sélectionné n'est pas une image valide.");
        return;
        }*/

         
          // Vérifier que toutes les valeurs ont été saisies
        if (numero.isEmpty() || surface.isEmpty() || lieu.isEmpty() || culture == null || image == null) {
            showError("Veuillez remplir tous les champs");
            return;
        }
         
        // Culture culture = cbCulture.getValue();
        // Vérifier si une image a été sélectionnée
    
    

    // Vérifier si l'image est un fichier PNG ou JPG
    /*  FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg");
    fileChooser.getExtensionFilters().add(extFilter);
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile == null) {
    showError("Veuillez sélectionner un fichier image PNG ou JPG.");
    return;
    }*/
    // Vérifier si l'image est un fichier PNG ou JPG
  
    // Récupérer le chemin d'accès absolu de l'image sélectionnée
   // String imagePath = null;
    // Récupérer le chemin d'accès absolu de l'image sélectionnée
    //String imagePath = filename.substring(6);
    /*try {
    File imageFile = fileChooser.showOpenDialog(null);
    if (imageFile != null) {
    BufferedImage bufferedImage = ImageIO.read(imageFile);
    Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
    imagePath = imageFile.getAbsolutePath();
    }
    } catch (IOException ex) {
    Logger.getLogger(AjouterTerrainController.class.getName()).log(Level.SEVERE, null, ex);
    showError("Erreur lors de la sélection de l'image.");
    return;
    }*/
        // récupèrer les valeurs saisies par l'utilisateur à partir des champs de saisie
 Terrain t= new Terrain(culture, Integer.parseInt(numero), Integer.parseInt(surface), lieu,filename);
  
 /*String uri = "file://" + t.getImage();
 
 image = new Image(uri,165,118,false,true);
 ivImage.setImage(image);
 t.setImage(uri);*/
 //t.setImage(imagePath);

 //crée un nouvel objet TerrainCRUD qui permet d'effectuer l'ajout a la bd
        TerrainCRUD tc = new TerrainCRUD();
       tc.ajouter2(t);
        showSuccess("Le terrain a été ajouté avec succès.");
        tfNumero.clear();
        tfSurface.clear();
        tfLieu.clear();
        cbCulture.getSelectionModel().clearSelection();
        ivImage.setImage(null);
     } catch (Exception ex) {
        Logger.getLogger(AjouterTerrainController.class.getName()).log(Level.SEVERE, null, ex);
        showError("Une erreur est survenue lors du chargement de l'image.");
    }
}
    
        /* if ( numero.isEmpty() || surface.isEmpty() || lieu.isEmpty() || culture == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs manquants");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs et sélectionner une culture.");
        alert.showAndWait();
        } else {
        Terrain terrain = new Terrain(culture,Integer.parseInt(numero), Integer.parseInt(surface), lieu);
        TerrainCRUD terrainCRUD = new TerrainCRUD();
        terrainCRUD.ajouter(terrain);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Terrain ajouté");
        alert.setHeaderText(null);
        alert.setContentText("Le terrain a été ajouté avec succès.");
        alert.showAndWait();
        tfNumero.clear();
        tfSurface.clear();
        tfLieu.clear();
        cbCulture.getSelectionModel().clearSelection();
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
    alert.setTitle("Le terrain a été ajouté avec succès.");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

  
     @FXML
    private void selectImage(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    fileChooser.getExtensionFilters().addAll(
    new ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
    new ExtensionFilter("Tous les fichiers", "*.*")
    );
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
    try {
    BufferedImage image = ImageIO.read(selectedFile);
    ivImage.setImage(SwingFXUtils.toFXImage(image, null));
    
    } catch (IOException ex) {
    showError("Erreur lors du chargement de l'image.");
    Logger.getLogger(AjouterTerrainController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    }
    /*@FXML
    private void selectImage(ActionEvent event) {
    File file = fileChooser.showOpenDialog(btnimage.getScene().getWindow());
    if (file != null) {
    try {
    BufferedImage bufferedImage = ImageIO.read(file);
    image = SwingFXUtils.toFXImage(bufferedImage, null);
    ivImage.setImage(image);
    imagePath = "src/main/resources/images/" + file.getName();
    } catch (IOException ex) {
    Logger.getLogger(AjouterTerrainController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    }
    */

    @FXML
    private void Retour(ActionEvent event)throws IOException {
        Stage primaryStage = null;
        Stage SecondWindow = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("AfficherTerrain.fxml"));;
        Scene rec2 = new Scene(root);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }
}
