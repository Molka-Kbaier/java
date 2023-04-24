/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.mysql.jdbc.Connection;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import edu.esprit.entities.Comptabilite;
import edu.esprit.services.ComptabiliteCRUD;
import edu.esprit.tools.MyConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author louaj
 */
public class AddCompController implements Initializable {

    @FXML
    private TextField tfValeur;
    @FXML
    private DatePicker tfDate;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Comptabilite> tvList;
    @FXML
    private TableColumn<Comptabilite, Date> colDate;
    @FXML
    private TableColumn<Comptabilite, Float> colValeur;
    @FXML
    private TableColumn<Comptabilite, Integer> colId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showList();
    }    

    @FXML
    private void AjouterC(ActionEvent event) {
        if(event.getSource()==btnAjouter){
            insertRecord();}
        
        
      String valeur = tfValeur.getText();
      if (valeur.isEmpty()) {
            showError("Veuillez saisir une valeur");
            return;
        }
       float valeurf;
        try {
            valeurf = Float.parseFloat(valeur);
        } catch (NumberFormatException e) {
            showError("La valeur doit être un nombre ");
            return;
        }
      LocalDate localDate = tfDate.getValue();
      if (localDate == null) {
            showError("Veuillez saisir une date");
           return; 
        } 
    else if (localDate.isAfter(LocalDate.now())) {
            showError("La date doit être inférieure ou égale à la date d'aujourd'hui");
        return ;
            
        }
        Date date_comptabilite = java.sql.Date.valueOf(localDate);
      Comptabilite c = new Comptabilite (date_comptabilite, valeurf);

        // Insérer l'objet Culture dans la base de données
        ComptabiliteCRUD cc = new ComptabiliteCRUD();
        cc.ajouterComptabilite(c);
        showSuccess("La COMPTABILITE a été ajoutée avec succès");
   
      
    }
      
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

    @FXML
    private void ModifierC(ActionEvent event) {
        if(event.getSource()==btnUpdate){
            insertRecord();}
        
    }

    @FXML
    private void SupprimerC(ActionEvent event) {
        
    if(event.getSource()==btnDelete){
        deleteRecord();
       }}
    
   public ObservableList<Comptabilite> getList(){
       Connection cnx2;
         cnx2=(Connection) MyConnection.getInstance().getCnx();
           
      ObservableList<Comptabilite> list =FXCollections.observableArrayList();
      try {
               String req3="SElECT * FROM comptabilite";
                Statement st = cnx2.createStatement();
                ResultSet rs = st.executeQuery(req3);
                while(rs.next()){
                    Comptabilite c = new Comptabilite();
                    c.setId(rs.getInt(1));
                    c.setDate_comptabilite(rs.getDate("date_comptabilite"));
                    c.setValeur(rs.getFloat("valeur"));
                    
                    list.add(c);
                }
                
                
           } catch (SQLException ex) {
               System.out.println(ex.getMessage());
           }
           return list;
     } 
     public void showList(){
     ObservableList<Comptabilite>list=getList ();
     colDate.setCellValueFactory(new PropertyValueFactory<Comptabilite,Date>("date_comptabilite"));
     colValeur.setCellValueFactory(new PropertyValueFactory<Comptabilite,Float>("valeur"));
      colId.setCellValueFactory(new PropertyValueFactory<Comptabilite,Integer>("id"));
     
     tvList.setItems(list);
     
     
     }
     
     
     private void insertRecord(){
     String query ="INSERT INTO comptabilite VALUES("+tfDate.getValue()+",'"+tfValeur.getText()+")";
     executeQuery(query);
     showList();
     }
      private void updateRecord(){
          String query = "UPDATE `comptabilite` SET `date_comptabilite` = '" + tfDate.getValue()+ "' WHERE `valeur` = " + tfValeur.getText();
        executeQuery(query);
        showList();
      }
private void deleteRecord(){
      String query = "DELETE FROM `comptabilite` WHERE valeur = " +tfValeur.getText() ;
        executeQuery(query);
        showList();
      }
    private void executeQuery(String query) {
        Connection cnx;
         cnx=(Connection) MyConnection.getInstance().getCnx();
         Statement st;
         try {
         st=cnx.createStatement();
         st.executeUpdate(query);
         }
         catch(Exception e){
             e.printStackTrace();
             
         
         }
    }
}
