package com.example.f22comp1011s2w2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class CreatePizzaController implements Initializable {

    @FXML
    private VBox meatsVBox;

    @FXML
    private VBox veggisVBox;

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

    /**
     * The initialize method is loaded when the scene is loaded.  It is used
     * to configure any of the objects prior to using them.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pizzaSizeComboBox.getItems().addAll(Pizza.validSizes());

        //get a list of the meat toppings and add them to the MeatsVBox as a
        //CheckBox
        TreeSet<String> meats = Pizza.getMeatToppingOptions();
        for (String meat : meats)
        {
            meatsVBox.getChildren().add(new CheckBox(meat));
        }
    }

    @FXML
    private void createPizza()
    {
        ArrayList<String> toppings = new ArrayList<>();

        //loop over the veggies and add any selected items
        //to the toppings ArrayList
        for (Node node : veggisVBox.getChildren())
        {
            //this will cast the generic Node object to be a CheckBox
            CheckBox checkBox = (CheckBox) node;
            if (checkBox.isSelected())
                toppings.add(checkBox.getText());
        }

        for (Node node : meatsVBox.getChildren())
        {
            //this will cast the generic Node object to be a CheckBox
            CheckBox checkBox = (CheckBox) node;
            if (checkBox.isSelected())
                toppings.add(checkBox.getText());
        }
        System.out.println(toppings);
        Pizza newPizza = new Pizza("small",toppings,"regular","regular","tomato", false,199.99);
        costPizzaLabel.setText(newPizza.toString());
    }
}
