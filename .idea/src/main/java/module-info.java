module com.example.ppv {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.xml;
    requires exp4j;
    requires com.google.gson;


    opens com.example.ppv2 to javafx.fxml;
    exports com.example.ppv2;
}