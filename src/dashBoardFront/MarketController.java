package dashBoardFront;

import com.google.zxing.WriterException;
import controllers.QrCodeGng;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import models.Produit;
import services.ServiceProduit;


import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MarketController implements Initializable {
    @FXML
    private Button btnSearch;

    @FXML
    private VBox chosenProduitCard;

    @FXML
    private GridPane grid;

    @FXML
    private ImageView imageViewProduit;

    @FXML
    private ImageView imageViewQR;

    @FXML
    private Label lblCategorie;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblNom;

    @FXML
    private Label lblPrix;

    @FXML
    private Label lblStatus;

    @FXML
    private ScrollPane scroll;

    @FXML
    private TextField tfSearch;

    Random random = new Random();

    private List<Produit> produits = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    ServiceProduit serviceProduit = new ServiceProduit();
    private ObservableList<Produit> observableListProduits = serviceProduit.getAll() ;

    @FXML
    public void handleSearch() {
        String input = tfSearch.getText().toLowerCase();

        List<Produit> results = produits.stream()
                .filter(produit -> produit.getNom().toLowerCase().contains(input))
                .collect(Collectors.toList());

        grid.getChildren().clear();

        int column = 0;
        int row = 1;
        try {
            for (Produit produit : results) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/dashBoardFront/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(produit, myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setChosenProduitCard(Produit produit) {
        lblNom.setText(produit.getNom());
        lblPrix.setText(produit.getPrix() + Main.CURRENCY );
        lblCategorie.setText(produit.getCategorie().getLabel());
        lblDescription.setText(produit.getDescription());
        lblStatus.setText(produit.getStatus() ? "disponible" : "pas disponible");

        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/"+produit.getImage())));
        imageViewProduit.setImage(image);

        Color randomColor = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));

        chosenProduitCard.setBackground(new Background(new BackgroundFill(randomColor, new CornerRadii(30), null)));
        try {
            Image qrCodeImage = QrCodeGng.generateQRCodeImage(produit.toString(), 200, 200);
            imageViewQR.setImage(qrCodeImage);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        produits.addAll(observableListProduits);
        if (produits.size() > 0) {
            setChosenProduitCard(produits.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit produit) {
                    setChosenProduitCard(produit);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/dashBoardFront/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(produits.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
