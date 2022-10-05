package com.example.f22comp1011s2w2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("create-pizza-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Scorpio's Pizza");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        ArrayList<Topping> toppings = DBUtility.getToppingsFromDB();
        launch();
    }
}