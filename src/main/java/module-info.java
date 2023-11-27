module com.example.rupizza {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.example.rupizza to javafx.fxml;
    exports com.example.rupizza;
}