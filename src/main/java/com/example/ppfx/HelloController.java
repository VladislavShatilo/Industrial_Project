package com.example.ppfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private void newCalculateWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("calculate.fxml"));
        Parent root = loader.load();

        Stage secondStage = new Stage();
       // secondStage.setTitle("Второе окно");

        secondStage.setScene(new Scene(root));

        secondStage.show();

    }

    @FXML
    private ToggleGroup extensionInputToggleGroup;

    @FXML
    private ToggleGroup extensionOutputToggleGroup;

    @FXML
    private ToggleGroup typeInputToggleGroup;

    @FXML
    private ToggleGroup typeOutputToggleGroup;

    @FXML
    public void getData(ActionEvent actionEvent) {
        RadioButton selectedInputType = (RadioButton) extensionInputToggleGroup.getSelectedToggle();
        System.out.println(selectedInputType.getText());

        selectedInputType = (RadioButton) extensionOutputToggleGroup.getSelectedToggle();
        System.out.println(selectedInputType.getText());

        selectedInputType = (RadioButton) typeInputToggleGroup.getSelectedToggle();
        System.out.println(selectedInputType.getText());

        selectedInputType = (RadioButton) typeOutputToggleGroup.getSelectedToggle();
        System.out.println(selectedInputType.getText());
        try {
            newCalculateWindow();
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        }
        catch (Exception e) {

            System.out.println("Error new window" + e.toString());
        }
    }
}