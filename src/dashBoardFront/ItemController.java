package dashBoardFront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Produit;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(p);
    }

    private Produit p;
    private MyListener myListener;

    public void setData(Produit p, MyListener myListener) {
        this.p = p;
        this.myListener = myListener;
        nameLabel.setText(p.getNom());
        priceLable.setText(Main.CURRENCY + p.getPrix());
        Image image = new Image(getClass().getResourceAsStream("/image/"+p.getImage()));
        img.setImage(image);
    }
}
