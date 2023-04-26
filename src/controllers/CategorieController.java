package controllers;

import com.mysql.jdbc.PreparedStatement;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.SneakyThrows;
import models.Categorie;
import services.ServiceCategorie;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CategorieController implements Initializable {

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnMod;

    @FXML
    private Button btnDel;

    @FXML
    private TableColumn<Categorie, String> colLabel;

    @FXML
    private TableColumn<Categorie, String> colIcon;

    @FXML
    private TableColumn<Categorie, String> colIconPath;


    @FXML
    private TextField tfSearch;

    @FXML
    private TableView<Categorie> tvCategorie;

    static ServiceCategorie serviceCategorie = new ServiceCategorie();
    private static ObservableList<Categorie> observableListCategorie = serviceCategorie.getAll();
    int index = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
        search_categorie();
        observableListCategorie = FXCollections.observableArrayList(serviceCategorie.getAll());
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            List<Categorie> latestCategories = serviceCategorie.getAll();

            if (isListContentChanged(latestCategories)) {
                System.out.println("List changed");

                observableListCategorie.setAll(latestCategories);
                updateTable();
                search_categorie();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private boolean isListContentChanged(List<Categorie> latestCategories) {
        if (observableListCategorie.size() != latestCategories.size()) {
            return true;
        }

        for (int i = 0; i < observableListCategorie.size(); i++) {
            Categorie oldCat = observableListCategorie.get(i);
            Categorie newCat = latestCategories.get(i);

            if (!oldCat.getLabel().equals(newCat.getLabel())
                    || !oldCat.getIcon().equals(newCat.getIcon())) {
                return true;
            }
        }

        return false;
    }

    public void updateTable() {
        colLabel.setCellValueFactory(new PropertyValueFactory<Categorie, String>("label"));
        colIcon.setCellValueFactory(new PropertyValueFactory<Categorie, String>("icon"));
        colIconPath.setCellValueFactory(new PropertyValueFactory<>("icon"));
        colIcon.setCellFactory(col -> new ImageTableCell());
        tvCategorie.setItems(observableListCategorie);
    }

    @FXML
    void search_categorie() {
        colIconPath.setCellValueFactory(new PropertyValueFactory<Categorie, String>("icon"));
        colLabel.setCellValueFactory(new PropertyValueFactory<Categorie, String>("label"));
        List<Categorie> categorieList = serviceCategorie.getAll();
        ObservableList<Categorie> observableListCategorie = FXCollections.observableArrayList(categorieList);
        tvCategorie.setItems(observableListCategorie);

        FilteredList<Categorie> filteredData = new FilteredList<>(observableListCategorie, b -> true);
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(categorie -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return String.valueOf(categorie.getId()).contains(lowerCaseFilter) ||
                        categorie.getLabel().toLowerCase().contains(lowerCaseFilter) ||
                        categorie.getIcon().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Categorie> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvCategorie.comparatorProperty());
        tvCategorie.setItems(sortedData);
    }



    public static class ImageTableCell extends TableCell<Categorie, String> {

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

    @FXML
    public void handleButtonAjout(MouseEvent event) throws IOException {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../templates/categorie/formCategorie.fxml"));
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.show();
    }

    @SneakyThrows
    public void handleButtonSupp(MouseEvent event) throws IOException {
        if (event.getSource() == btnDel) {
            getSelected();
            if (index <= -1) {
                JOptionPane.showMessageDialog(null, "Sélectionnez une catégorie à supprimer.");
                return;
            }

            String categoryName = observableListCategorie.get(index).getLabel();
            int confirm = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer la catégorie \"" + categoryName + "\" ?");
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            try {
                serviceCategorie.supprimer(observableListCategorie.get(index).getId());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Impossible de supprimer la catégorie sélectionnée car elle est utilisée par des produits.");
                return;
            }

            updateTable();
            search_categorie();
        }
    }

    public void getSelected() {
        index = tvCategorie.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        updateTable();
        search_categorie();
    }

    @FXML
    void handleButtonUpdate() throws IOException {
        Categorie selectedCategorie = tvCategorie.getSelectionModel().getSelectedItem();
        if (selectedCategorie == null) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un categorie à modifier.");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../templates/categorie/formModif.fxml"));
        AnchorPane formCategorie = loader.load();

        CategorieFormController controller = loader.getController();
        controller.setSelectedCtegorie(selectedCategorie);

        Stage newStage = new Stage();
        newStage.setScene(new Scene(formCategorie));
        newStage.show();
    }
}

