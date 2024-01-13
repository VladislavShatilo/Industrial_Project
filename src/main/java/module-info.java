module com.example.ppfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires exp4j;
    requires com.google.gson;
    requires java.xml;

    opens com.example.ppfx to javafx.fxml;
    exports com.example.ppfx;
}