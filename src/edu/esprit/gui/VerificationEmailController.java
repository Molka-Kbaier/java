/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.service.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class VerificationEmailController implements Initializable {

    @FXML
    private TextField emailv;
    @FXML
    private Button verifebtn;
    
    Random rand = new Random();
    int codeVerification = rand.nextInt((1000) + (9999));
    String codeVerif = String.valueOf(codeVerification);
    @FXML
    private TextField codev;
    @FXML
    private Button validerbtn;
    @FXML
    private Label semail;
    @FXML
    private Label scode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void visibility(boolean phase1, boolean phase2) {
        semail.setVisible(phase1);
        emailv.setVisible(phase1);
        verifebtn.setVisible(phase1); // fin phase 1
        scode.setVisible(phase2);
        codev.setVisible(phase2);
        validerbtn.setVisible(phase2); // fin phase 2
    }

    @FXML
    private void verife(ActionEvent event) throws SQLException, AddressException, MessagingException, IOException{
        
        String mail = emailv.getText();
       ServiceUser su = new ServiceUser();
       
       Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.outlook.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            
            final String username = "agroeasy@outlook.fr";
            final String password = "DevArmyPidev";
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            
      /*      
            String body = " Cher Monsieur ,\n"
                    + "\n"
                    + "We received a request to reset the password for your TBADEL account. To complete the password reset process, please use the following verification code:\n"
                    + "\n"
                    + "Verification code: " + codeVerification + "\n"
                    + "\n"
                    + "Please enter this code on the password reset page to verify your identity and create a new password. Please note that this code is valid for [time period, e.g. 24 hours] only. If you did not request a password reset, please ignore this email and take steps to secure your account.\n"
                    + "\n"
                    + "If you have any questions or concerns, please don't hesitate to contact us at [contact information].\n"
                    + "\n"
                    + "Best regards,  ";
*/
             try {
                    // Création du message
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
                    message.setSubject("Code de vérification AgroEasy");
                    message.setText("Pour finaliser votre inscription à notre compte AgroEasy. \n"
                         +  "\n" 
                         +"Pour terminer le processus d'inscription, veuillez utiliser le code de vérification suivant "
                         +  "\n" 
                         + " Votre code de vérification est : " + codeVerif);

                    // Envoi du message
                    Transport.send(message);
                    System.out.println("message envoyer avec succes");
                    
               /*     try{
                            Stage stage = (Stage) verifebtn.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("Verifier.fxml"));
                            Scene scene = new Scene(root );
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                    }catch(IOException ex){
                            System.out.println(ex.getMessage());
                    }*/
              
            } catch (MessagingException e) {
                    // Gestion des exceptions
                    System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
                    
            }
    }

    @FXML
    private void valider(ActionEvent event) {
        
        String codeV = codev.getText();
        if (!codeV.equals(this.codeVerif)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Code invalide !");
            alert.showAndWait();
        } else {
           try{
                            Stage stage = (Stage) validerbtn.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("login.fxml"));
                            Scene scene = new Scene(root );
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                    }catch(IOException ex){
                            System.out.println(ex.getMessage());
                    }
        }
    }

    
}
