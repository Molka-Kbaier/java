/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public class NewWindow extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       // Créez une nouvelle scène pour le premier bouton
        Parent root1 = FXMLLoader.load(getClass().getResource("AfficherCulturee.fxml"));
       
       Scene scene1 = new Scene(root1);
       // Créez une nouvelle fenêtre pour la première scène
       
       Stage stage1 = new Stage();
       stage1.setTitle("Gestion Culture !");
       stage1.setScene(scene1);
       
        // Créez une nouvelle scène pour le deuxième bouton
        Parent root2 = FXMLLoader.load(getClass().getResource("AjouterTerrain.fxml"));
        Scene scene2 = new Scene(root2);
        
        // Créez une nouvelle fenêtre pour la deuxième scène
        Stage stage2 = new Stage();
        stage2.setTitle("Gestion Terrain !");
        stage2.setScene(scene2);
        // Créez les boutons pour chaque fenêtre
        Button btn1 = new Button();
        btn1.setText("Gestion Culture");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            
              @Override
            public void handle(ActionEvent event) {
            stage1.show();
            }
        });
        
        Button btn2 = new Button();
        btn2.setText("Gestion Terrain");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                stage2.show();
            }
        });
        
        // Créez un conteneur VBox pour les boutons
        VBox buttonContainer = new VBox();
        buttonContainer.getChildren().addAll(btn1, btn2);
        
        // Affichez les boutons dans la scène principale
        Scene mainScene = new Scene(buttonContainer, 300, 250);
        primaryStage.setTitle("Nouvelle fenêtre");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}