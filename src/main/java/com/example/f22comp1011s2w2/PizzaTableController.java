package com.example.f22comp1011s2w2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.CurrencyStringConverter;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class PizzaTableController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pizzaIdColumn.setCellValueFactory(new PropertyValueFactory<>("pizzaID"));//calls getPizzaID
        crustColumn.setCellValueFactory(new PropertyValueFactory<>("crustStyle"));//calls getCrust()
        doughColumn.setCellValueFactory(new PropertyValueFactory<>("dough"));//calls getPizzaID
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("formattedPrice"));//calls getFormattedPrice
        sauceColumn.setCellValueFactory(new PropertyValueFactory<>("sauce"));//calls getPizzaID
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));//calls getSize()
        toppingsColumn.setCellValueFactory(new PropertyValueFactory<>("toppings"));//calls getPizzaID
        tableView.getItems().addAll(DBUtility.getPizzasFromDB());

        //update the total sales label
        double total =0;
        for (Pizza pizza : tableView.getItems())
        {
            total += pizza.getPrice();
        }
        CurrencyStringConverter csc = new CurrencyStringConverter(Locale.CANADA);
        totalSalesLabel.setText("Total sales: "+csc.toString(total));
    }
}
