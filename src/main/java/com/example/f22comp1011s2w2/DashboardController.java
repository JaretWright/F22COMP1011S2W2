package com.example.f22comp1011s2w2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.zip.InflaterInputStream;

public class DashboardController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private PieChart pieChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        pieChartData.add(new PieChart.Data("dairy",6));
        pieChartData.add(new PieChart.Data("veggie",70));
        pieChartData.add(new PieChart.Data("meat",57));
        pieChartData.add(new PieChart.Data("Jerry's Fav",100));
        pieChart.getData().addAll(pieChartData);
    }
}
