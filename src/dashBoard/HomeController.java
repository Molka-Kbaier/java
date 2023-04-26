package dashBoard;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class HomeController implements Initializable {
    @FXML
    private Button btnCategorie;

    @FXML
    private Button btnProduit;

    @FXML
    private Button btnAddCategorie;

    @FXML
    private Button btnAddProduit;

    @FXML
    private AnchorPane holderPane;

    AnchorPane categories, produits,welcome,aadProduits,addCategorie;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            categories = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../templates/categorie/consulterCategorie.fxml")));
            produits = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../templates/produit/consulterProduit.fxml")));
            aadProduits = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../templates/produit/formProduit.fxml")));
            addCategorie = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../templates/categorie/formCategorie.fxml")));
            welcome = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../templates/produit/consulterProduit.fxml")));

            setNode(categories);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void setNode(Node node) {
        if (node != null) {
            holderPane.getChildren().clear();
            holderPane.getChildren().add(node);

            FadeTransition ft = new FadeTransition(Duration.millis(1500));
            ft.setNode(node);
            ft.setFromValue(0.1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();
        }
    }


    @FXML
    private void switchAddCategories() {
        setNode(addCategorie);
    }
    @FXML
    private void switchAddProduits() {
        setNode(aadProduits);
    }
    @FXML
    private void switchCategories() {
        setNode(categories);
    }

    @FXML
    private void switchProduits() {
        setNode(produits);
    }

}
