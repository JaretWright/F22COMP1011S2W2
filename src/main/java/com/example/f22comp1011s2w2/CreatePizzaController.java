package com.example.f22comp1011s2w2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreatePizzaController implements Initializable {

    @FXML
    private CheckBox baconCheckBox;

    @FXML
    private CheckBox cornCheckBox;

    @FXML
    private Label costPizzaLabel;

    @FXML
    private RadioButton deepDishRadioButton;

    @FXML
    private CheckBox deliveryCheckBox;

    @FXML
    private CheckBox greenPepperCheckBox;

    @FXML
    private CheckBox jalapenoCheckBox;

    @FXML
    private CheckBox mushroomsCheckBox;

    @FXML
    private CheckBox olivesCheckBox;

    @FXML
    private CheckBox onionCheckBox;

    @FXML
    private CheckBox pepperoniCheckBox;

    @FXML
    private CheckBox pineappleCheckBox;

    @FXML
    private ComboBox<String> pizzaSizeComboBox;

    @FXML
    private CheckBox redPepperCheckBox;

    @FXML
    private RadioButton regDoughRadioButton;

    @FXML
    private RadioButton regularCrustRadioButton;

    @FXML
    private ComboBox<?> sauceComboBox;

    @FXML
    private CheckBox sausageCheckBox;

    @FXML
    private Label taxLabel;

    @FXML
    private RadioButton thinCrustRadioButton;

    @FXML
    private Label totalLabel;

    @FXML
    private RadioButton wholeWheatRadioButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pizzaSizeComboBox.getItems().addAll(Pizza.validSizes());
    }

    @FXML
    private void createPizza()
    {
        ArrayList<String> toppings = new ArrayList<>();
        toppings.add("pepperoni");
        toppings.add("pineapple");
        Pizza newPizza = new Pizza("small",toppings,"regular","regular","tomato", false,199.99);
        costPizzaLabel.setText(newPizza.toString());
    }
}
