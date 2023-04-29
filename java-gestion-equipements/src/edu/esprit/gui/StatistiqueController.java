/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Equipement;
import edu.esprit.services.EquipementCRUD;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StatistiqueController extends JFrame {
    
    private static final long serialVersionUID = 1L;

    @FXML
    private Label label;
    @FXML
    private PieChart PieChart;

    /**
     * Initializes the controller class.
     */
    public StatistiqueController(String title) {
        super(title);

        // Create dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        EquipementCRUD ec = new EquipementCRUD();
        List<Equipement> equipements = ec.getAll();
        Map<String, Integer> counts = new HashMap<>();
        for (Equipement e : equipements) {
            String etat = e.getEtat().toString();
            counts.put(etat, counts.getOrDefault(etat, 0) + 1);
        }
        int totalCount=equipements.size();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            String etat = entry.getKey();
            int count = entry.getValue();
            double percentage = count * 100.0 / equipements.size();
            dataset.setValue(etat + " (" + String.format("%.2f", percentage) + "%)", count);
        }

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
            "Répartition des équipements par état", // Chart title
            dataset, // Dataset
            true, // Include legend
            true, // Tooltips
            false // URLs
        );

        // Set chart properties
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));
        plot.setLabelFont(plot.getLabelFont().deriveFont(18f));
        plot.setNoDataMessage("Aucune donnée disponible");
        plot.setNoDataMessageFont(plot.getLabelFont().deriveFont(24f));
        plot.setSectionPaint("Disponible", new Color(102, 204, 0));
        plot.setSectionPaint("En panne", new Color(204, 0, 0));
        plot.setSectionPaint("En maintenance", new Color(255, 204, 51));

        // Create panel
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(500, 500));
        setContentPane(panel);
    }

    /*   public static void main(String[] args) {
    PieChartExample example = new PieChartExample("JFreeChart Pie Chart Example");
    example.pack();
    example.setLocationRelativeTo(null);
    example.setVisible(true);
    }*/
}