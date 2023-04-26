package controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Categorie;
import models.Produit;
import services.ServiceProduit;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Timer;

public class ProduitController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnMod;

    @FXML
    private TableColumn<Produit, String> colCategorie;

    @FXML
    private TableColumn<Produit, String> colDescription;

    @FXML
    private TableColumn<Produit, String> colImage;

    @FXML
    private TableColumn<Produit, String> colNom;

    @FXML
    private TableColumn<Produit, Float> colPrix;

    @FXML
    private TableColumn<Produit, Boolean> colStatut;

    @FXML
    private TableView<Produit> tvProduit;

    @FXML
    private TextField tfSearch;

    @FXML
    private Button btnRetour;



    static ServiceProduit serviceProduit = new ServiceProduit();
    private static ObservableList<Produit> observableListProduits = serviceProduit.getAll() ;
    int index = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
        search_produit();
        observableListProduits = FXCollections.observableArrayList(serviceProduit.getAll());
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            List<Produit> latestCategories = serviceProduit.getAll();

            if (isListContentChanged(latestCategories)) {
                System.out.println("List changed");

                observableListProduits.setAll(latestCategories);
                updateTable();
                search_produit();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private boolean isListContentChanged(List<Produit> latest) {
        if (observableListProduits.size() != latest.size()) {
            return true;
        }

        for (int i = 0; i < observableListProduits.size(); i++) {
            Produit oldCat = observableListProduits.get(i);
            Produit newCat = latest.get(i);

            if (!oldCat.getNom().equals(newCat.getNom()) || oldCat.getPrix()!=newCat.getPrix() || !oldCat.getDescription().equals(newCat.getDescription())|| !oldCat.getCategorie().getLabel().equals(newCat.getCategorie().getLabel()) || oldCat.getStatus()!=newCat.getStatus() || !oldCat.getImage().equals(newCat.getImage())) {
                return true;
            }
        }

        return false;
    }
    public void updateTable() {
        observableListProduits = FXCollections.observableArrayList(serviceProduit.getAll());
        colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        colCategorie.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategorie().getLabel()));
        colPrix.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        colStatut.setCellValueFactory(new PropertyValueFactory<Produit, Boolean>("status"));
        colImage.setCellValueFactory(new PropertyValueFactory<Produit, String>("image"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colImage.setCellFactory(col -> new ImageTableCell());

        tvProduit.setItems(observableListProduits);
    }

    public static class ImageTableCell extends TableCell<Produit, String> {

        private final ImageView imageView = new ImageView();
        private final double imageSize = 50; // taille de l'image

        public ImageTableCell() {
            setAlignment(Pos.CENTER);
            setGraphic(imageView);
        }

        @Override
        protected void updateItem(String imagePath, boolean empty) {
            super.updateItem(imagePath, empty);
            if (empty || imagePath == null) {
                imageView.setImage(null);
                setText(null);
                setGraphic(null);
            } else {
                Path destinationPath = Paths.get("src", "image", imagePath);
                File file = destinationPath.toFile();
                Image image = new Image(file.toURI().toString());
                imageView.setImage(image);
                imageView.setFitWidth(imageSize);
                imageView.setFitHeight(imageSize);
                setGraphic(imageView);
            }
        }
    }
    public void getSelected() {
        index = tvProduit.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        updateTable();
        search_produit();
    }

    @FXML
    void search_produit() {
        colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        colCategorie.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategorie().getLabel()));
        colPrix.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        colStatut.setCellValueFactory(new PropertyValueFactory<Produit, Boolean>("status"));
        colImage.setCellValueFactory(new PropertyValueFactory<Produit, String>("image"));
        ObservableList<Produit> list = serviceProduit.getAll();
        observableListProduits = FXCollections.observableArrayList(serviceProduit.getAll());
        tvProduit.setItems(list);
        FilteredList<Produit> filteredData = new FilteredList<>(list, b->true);
        tfSearch.textProperty().addListener((observable,oldValue,newValue)-> {
            filteredData.setPredicate(produit-> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf((produit.getId())).contains(lowerCaseFilter)){
                    return true;
                }else if (produit.getDescription().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if (produit.getCategorie().getLabel().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if (produit.getNom().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if (String.valueOf(produit.getPrix()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if (produit.getImage().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if (String.valueOf(produit.getStatus()).toLowerCase().contains(lowerCaseFilter)){
                return true;
                }
                else
                    return false ;
            });
        });
        SortedList<Produit> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvProduit.comparatorProperty());
        tvProduit.setItems(sortedData);
    }

    @FXML
    void handleButtonAjout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../templates/produit/formProduit.fxml"));
        AnchorPane formProduit = loader.load();
        Scene formProduitScene = new Scene(formProduit);
        Stage newStage = new Stage();
        newStage.setScene(formProduitScene);
        newStage.show();
    }

    @FXML
    void handleButtonUpdate() throws IOException {
        Produit selectedProduit = tvProduit.getSelectionModel().getSelectedItem();
        if (selectedProduit == null) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un produit à modifier.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../templates/produit/formModif.fxml"));
        AnchorPane formProduit = loader.load();

        FormController controller = loader.getController();
        controller.setSelectedProduit(selectedProduit);

        Scene formScene = new Scene(formProduit);
        Stage newStage = new Stage();
        newStage.setScene(formScene);
        newStage.show();
    }


    @FXML
    public void handleButtonSupprimer() {
        int selectedIndex = tvProduit.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Produit selectedProduit = tvProduit.getItems().get(selectedIndex);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation de suppression");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer le produit \"" + selectedProduit.getNom() + "\" ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                ServiceProduit serviceProduit = new ServiceProduit();
                serviceProduit.supprimer(selectedProduit.getId());
                updateTable();
                search_produit();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucun produit sélectionné");
            alert.setContentText("Veuillez sélectionner un produit dans la table.");
            alert.showAndWait();
        }
    }


    @FXML
    void handleButtonRetour() throws IOException {
        updateTable();
        search_produit();
    }



}
