package models;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class BarChartExample extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Create the data model
        Map<String, Integer> data = new HashMap<>();
        data.put("Category A", 10);
        data.put("Category B", 20);
        data.put("Category C", 30);

        // Create the axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Create the chart
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);

        // Populate the chart with data
        ObservableList<XYChart.Series<String, Number>> chartData = FXCollections.observableArrayList();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (String category : data.keySet()) {
            series.getData().add(new XYChart.Data<>(category, data.get(category)));
        }
        chartData.add(series);
        chart.setData(chartData);

        // Customize the chart
        chart.setTitle("Product Categories");
        xAxis.setLabel("Category");
        yAxis.setLabel("Number of Products");

        // Create the scene
        Scene scene = new Scene(chart, 600, 400);

        // Show the stage
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}