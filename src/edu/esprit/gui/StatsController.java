/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Equipement;
import edu.esprit.services.EquipementCRUD;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StatsController implements Initializable {

    @FXML
    private PieChart PieChart;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         EquipementCRUD ec = new EquipementCRUD();
        List<Equipement> equipements = ec.getAll();

        Map<String, Long> counts = equipements.stream()
                .collect(Collectors.groupingBy(e -> e.getEtat().toString(), Collectors.counting()));

        int totalCount = equipements.size();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Long> entry : counts.entrySet()) {
            String etat = entry.getKey();
            long count = entry.getValue();
            double percentage = count * 100.0 / equipements.size();
            pieChartData.add(new PieChart.Data(etat, percentage));
        }

        PieChart.setAnimated(true);
       PieChart.setData(pieChartData);

        // afficher le nombre total d'équipements
        label.setText("Nombre total d'équipements : " + totalCount);
    }


    @FXML
    private void retour(ActionEvent event) {
    }
    
}
