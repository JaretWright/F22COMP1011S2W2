package com.example.f22comp1011s2w2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PizzaTableController {

    @FXML
    private TableColumn<Pizza, String> crustColumn;

    @FXML
    private TableColumn<Pizza, String> doughColumn;

    @FXML
    private TableColumn<Pizza, Integer> pizzaIdColumn;

    @FXML
    private TableColumn<Pizza, Double> priceColumn;

    @FXML
    private TableColumn<Pizza, String> sauceColumn;

    @FXML
    private TableColumn<Pizza, String> sizeColumn;

    @FXML
    private TableView<Pizza> tableView;

    @FXML
    private TableColumn<Pizza, String> toppingsColumn;

    @FXML
    private Label totalSalesLabel;

}
