/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import static edu.esprit.gui.LoginRegister.u;
import edu.esprit.service.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ResetMdpController implements Initializable {

    @FXML
    private Button btnvalid;
    @FXML
    private PasswordField pwdr;
    @FXML
    private PasswordField pwdn;

    
    private String email;
    @FXML
    private PasswordField pwda;
    
    
    ServiceUser su = new ServiceUser();
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void validerm(ActionEvent event) throws SQLException {
        
        if (pwda.getText().equals(u.getPassword()) && pwdn.getText().equals(pwdr.getText())) {
            if (su.resetPassword(pwdn.getText(), u.getEmail())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Mr/Mme" + u.getNom()+ " " +u.getPrenom()+ " " + u.getEmail() + " Votre mot de passe a été bien modifier !", ButtonType.CLOSE);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, " il ya un petit probleme ressayer plus tard !", ButtonType.CLOSE);
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, " l'une des mot de passes ne correspand pas merci de ressayer !", ButtonType.CLOSE);
            alert.show();
    }
        
    }
}
    

