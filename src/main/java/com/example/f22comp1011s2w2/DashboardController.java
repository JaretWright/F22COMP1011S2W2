package com.example.f22comp1011s2w2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.io.IOException;
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
        //Add data to our Pie chart
        pieChart.getData().addAll(DBUtility.getCategorySummary());
        barChart.getData().addAll(DBUtility.getToppingsSummary());
        barChart.setLegendVisible(false);
    }

    @FXML
    private void viewTable(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event,"pizza-table-view.fxml");
    }

}
