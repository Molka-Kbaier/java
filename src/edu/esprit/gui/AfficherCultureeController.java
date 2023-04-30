/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.entities.Culture;
import edu.esprit.services.CultureCRUD;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Date;
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
import javafx.print.PrinterJob;
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
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import javax.swing.text.Document;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import java.awt.print.Printable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import org.apache.pdfbox.pdfparser.PDFParser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficherCultureeController implements Initializable {

    @FXML
    private TableColumn<Culture, String> Col_type;
    @FXML
    private TableColumn<Culture, Date> Col_date_planting;
    @FXML
    private TableColumn<Culture, Float> Col_quantite;
    @FXML
     TableView<Culture> table_vieww;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifier;
     @FXML
    private TextField tfType;
    @FXML
    private DatePicker dtDate_planting;
    @FXML
    private TextField tfQuantite;
    @FXML
    private Button updateBtn;
    @FXML
    private TextField tfrecherche;
    @FXML
    private ComboBox<String> cbRecherche;
        ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Tout", "type", "quantite");

    @FXML
    private Button btnimpression;
    @FXML
    private ComboBox<String> cbtri;
      ObservableList<String> listeTypetri = FXCollections.observableArrayList("Tout", "type", "date_planting", "quantite");
       ObservableList<Culture> reslist = FXCollections.observableArrayList();
    @FXML
    private Button btnAfficher;
   
 private Culture cultureAModifiee;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*    try {
        listtri();
        } catch (SQLException ex) {
        Logger.getLogger(AfficherCultureeController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        cbRecherche.setItems(listeTypeRecherche);
        cbRecherche.setValue("Tout");
        cbtri.setItems(listeTypetri);
        cbtri.setValue("Tout");
        try {
        listtri();
             try {
                  list();
              } catch (SQLException ex) {
                  Logger.getLogger(AfficherCultureeController.class.getName()).log(Level.SEVERE, null, ex);
               }
        } catch (SQLException ex) {
        Logger.getLogger(AfficherCultureeController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    // Ajouter un listener à la table-view pour détecter lorsqu'une culture est sélectionné
table_vieww.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
    if (newSelection != null) {
        /*// Mettre à jour les champs correspondants avec les informations du culture sélectionné
        tfQuantite.setText(String.valueOf(newSelection.getQuantite()));
        tfType.setText(newSelection.getType());
        //convertir une java.util.Date en une LocalDate afin de pouvoir afficher la date dans un DatePicker.
        Date date = newSelection.getDate_planting();
        Instant instant = Instant.ofEpochMilli(date.getTime());
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        dtDate_planting.setValue(localDate);*/


        /*} else {
        // Si aucune culutre n'est sélectionné, vider les champs
        tfQuantite.setText("");
        dtDate_planting.setValue(null);
        
        tfType.setText("");*/
        
    }
});

    }    
    
    

    @FXML
    private void AddCulture(ActionEvent event) throws IOException {
         Stage primarystage = null;
        Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("AjouterCulture.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();}

    @FXML
    private void SupprimerCulture(ActionEvent event) {
          try {
        // Récupérer la culture à supprimer
        Culture cultureASupprimer = table_vieww.getSelectionModel().getSelectedItem();

        // Vérifier si une culture est bien sélectionnée
        if (cultureASupprimer == null) {
            showError("Veuillez sélectionner une culture à supprimer");
            return;
        }

        // Supprimer la culture de la base de données
        CultureCRUD cc = new CultureCRUD();
        cc.supprimer(cultureASupprimer);

        // Afficher un message de succès
        showSuccess("La culture a été supprimée avec succès");
 JOptionPane.showMessageDialog(null, "culture supprimeé");
  CultureData();
        table_vieww.refresh();
      
    } catch (Exception e) {
        showError("Une erreur s'est produite lors de la suppression de la culture");
        e.printStackTrace();
    }
    }
   



   @FXML
    private void ModifierCulture(ActionEvent event) throws IOException {
      // Récupération de la culture sélectionnée dans le tableau
        Culture cultureAModifiee = table_vieww.getSelectionModel().getSelectedItem();
 // Vérifier si une culture est bien sélectionnée
        if (cultureAModifiee == null) {
            showError("Veuillez sélectionner une culture à Modifier");
            return;
        }
        
            
            // Ouverture de la fenêtre de modification
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCulture.fxml"));
            Parent root = loader.load();
            ModifierCultureController mcc = loader.getController();

            // Transmission des données de la culture sélectionnée à la fenêtre de modification
            mcc.initData(cultureAModifiee);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
     
  
            
    }

    @FXML
    private void ButtonAction(ActionEvent event) {
    }

    /* @FXML
    private void list(KeyEvent event) {
    }*/

    @FXML
    private void list()throws SQLException {
    CultureCRUD cultureCRUD = new CultureCRUD();// crée une instance de CultureCRUD,
    List<Culture> cultures = cultureCRUD.afficher();// récupèrer toutes les cultures de bd

    // Récupérer la valeur de recherche sélectionnée (ComboBox) 
    String recherche = cbRecherche.getValue();
   

    // Créer un stream à partir de la liste des cultures
    Stream<Culture> stream = cultures.stream();

    // Filtrer les cultures en fonction de la recherche sélectionnée
    if (!recherche.equals("Tout")) {
        stream = stream.filter(c -> {
            switch (recherche) {
                case "type":
                    return c.getType().toLowerCase().contains(tfrecherche.getText().toLowerCase());
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

                case "quantite":
                    try {
                        float quantite = Float.parseFloat(tfrecherche.getText());
                        return c.getQuantite() == quantite;
                    } catch (NumberFormatException e) {
                        return false;
                    }
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
private void Imprimer1(ActionEvent event) throws FileNotFoundException, DocumentException {
    System.out.println("To Printer!");
    
    // Création du document PDF
    com.itextpdf.text.Document document = new com.itextpdf.text.Document();
    PdfWriter.getInstance(document, new FileOutputStream("liste_cultures.pdf"));
    document.open();
    document.addTitle("Liste des cultures");
    // Création de la table à partir des données de la TableView
    /* TableView<Culture> table = this.table_vieww;
    ObservableList<TableColumn<Culture, ?>> columns = table.getColumns();
    int columnCount = columns.size();
    PdfPTable pdfTable = new PdfPTable(columnCount);
    
    // Ajout des en-têtes de colonnes
    for (TableColumn<Culture, ?> column : columns) {
    pdfTable.addCell(column.getText());
    }
    
    // Ajout des données de la TableView
    for (Culture culture : table.getItems()) {
    for (int i = 0; i < columnCount; i++) {
    TableColumn<Culture, ?> column = columns.get(i);
    Object cellValue = column.getCellData(culture);
    pdfTable.addCell(cellValue == null ? "" : cellValue.toString());
    }
    }*/
    // Ajouter une table au document
    PdfPTable table = new PdfPTable(3); // 3 colonnes
    table.addCell("Type");
    table.addCell("Date de plantation");
    table.addCell("Quantité");

    // Remplir la table avec les données de la table-view
    for (Culture culture : table_vieww.getItems()) {
        table.addCell(culture.getType());
        table.addCell(culture.getDate_planting().toString());
        table.addCell(String.valueOf(culture.getQuantite()));
    }
    // Ajout de la table au document PDF
    document.add(table);
    
    // Fermeture du document PDF
    document.close();
    
    // Impression du document PDF
     PrinterJob job = PrinterJob.createPrinterJob();
    if (job != null) {
        Window primaryStage = null;
        job.showPrintDialog(primaryStage);
        Node root = this.table_vieww;
        job.printPage(root);
        job.endJob();
      
        /* File file = new File("liste_cultures.pdf");
        PDDocument pdDocument;
        try {
        pdDocument = PDDocument.load(file);
        job.printPage(pdDocument.getPage(0));
        pdDocument.close();
        job.endJob();
        } catch (IOException e) {
        e.printStackTrace();
        }*/
        /* PDDocument pdDocument = PDDocument;
        job.printPage(pdDocument.getPage(0));
        pdDocument.close();*/
        
    }
}

@FXML
private void Imprimer(ActionEvent event) throws FileNotFoundException, DocumentException {
    System.out.println("To Printer!");

    // Crée un nouveau document PDF en utilisant la bibliothèque iText.
    com.itextpdf.text.Document document = new com.itextpdf.text.Document();
    //Initialiser l'objet PdfWriter pour écrire le contenu du document PDF dans un fichier appelé "liste_cultures.pdf".
    PdfWriter.getInstance(document, new FileOutputStream("liste_cultures.pdf"));
     //Ouvrir le document PDF pour y écrire du contenu.
    document.open();
    document.addTitle("Liste des cultures");

    // Créer une nouvelle table PDF à 3 colonnes.
    PdfPTable table = new PdfPTable(3); // 3 colonnes

    // Ajouter les en-têtes de colonnes personnalisées
    Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);//Crée une police de caractères pour les en-têtes de colonnes 
    PdfPCell cell = new PdfPCell(new Phrase("Type", titleFont));
    cell.setBackgroundColor(BaseColor.YELLOW);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Date de plantation", titleFont));
    cell.setBackgroundColor(BaseColor.YELLOW);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Quantité", titleFont));
    cell.setBackgroundColor(BaseColor.YELLOW);
    table.addCell(cell);

    // Remplir la table avec les données de la table-view
    for (Culture culture : table_vieww.getItems()) {//Boucle à travers les éléments de la table-view qui contient les données de la liste de cultures.


        table.addCell(culture.getType());//Ajouter le type de culture dans la colonne "Type" de la table PDF.
        table.addCell(culture.getDate_planting().toString());
        table.addCell(String.valueOf(culture.getQuantite()));
    }

    // Ajout de la table au document PDF
    document.add(table);

    // Fermeture du document PDF
    document.close();

    // Impression du document PDF
    PrinterJob job = PrinterJob.createPrinterJob();
    if (job != null) {
        Window primaryStage = null;
        job.showPrintDialog(primaryStage);
        Node root = this.table_vieww;
        job.printPage(root);
        job.endJob();
    }
}



    @FXML
    private void listtri()throws SQLException {
CultureCRUD cc = new CultureCRUD();
    List<Culture> cultures = cc.afficher();
     // Récupérer la valeur de recherche et de tri sélectionnée
    String tri = cbtri.getValue();
    // Créer un stream à partir de la liste des cultures
    Stream<Culture> stream = cultures.stream();
    // Trier les cultures en fonction du tri sélectionné
    if (!tri.equals("Tout")) {
        Comparator<Culture> comparator;
        switch (tri) {
            case "type":
                comparator = Comparator.comparing(c -> c.getType().toLowerCase());
                break;
            case "date_planting":
                comparator = Comparator.comparing(Culture::getDate_planting);
                break;
            case "quantite":
                comparator = Comparator.comparing(Culture::getQuantite);
                break;
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
    private void AfficherCulture(ActionEvent event) throws SQLException {
          CultureCRUD cc = new CultureCRUD();
    List<Culture> cultures = cc.afficher();

    // vider les lignes existantes
    table_vieww.getItems().clear();

    // ajouter les cultures à la TableView
    table_vieww.getItems().addAll(cultures);

    // associer les colonnes avec les attributs de Culture
  //  Col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    Col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
   
    Col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
     Col_date_planting.setCellValueFactory(new PropertyValueFactory<>("date_planting"));
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Stage primarystage = null;
        Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("AfficherTerrain.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();}
     private void CultureData() throws SQLException {  ObservableList<Culture> listCulture = FXCollections.observableArrayList();
    
     Col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
    Col_date_planting.setCellValueFactory(new PropertyValueFactory<>("date_planting"));
    Col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
     CultureCRUD cc = new CultureCRUD();
     List<Culture> cultures = cc.afficher();
    listCulture.addAll();
        table_vieww.setItems(listCulture);}
    


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
