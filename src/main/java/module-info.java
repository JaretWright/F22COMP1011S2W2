module com.example.f22comp1011s2w2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.f22comp1011s2w2 to javafx.fxml;
    exports com.example.f22comp1011s2w2;
}