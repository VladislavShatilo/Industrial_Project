package com.example.ppfx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloController {

    private static final FileInformationStructure file = new FileInformationStructure();

    @FXML
    private void newArchiveInpWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("archiveInput.fxml"));
        Parent root = loader.load();

        Stage secondStage = new Stage();
        secondStage.setTitle("Choose calculation");

        secondStage.setScene(new Scene(root));

        secondStage.show();

    }

    @FXML
    private void newArchiveOutWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("archiveOutput.fxml"));
        Parent root = loader.load();

        Stage secondStage = new Stage();
        secondStage.setTitle("Choose calculation");

        secondStage.setScene(new Scene(root));

        secondStage.show();

    }


    @FXML
    private void newCalculateWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("calculate.fxml"));
        Parent root = loader.load();

        Stage secondStage = new Stage();
        secondStage.setTitle("Choose calculation");

        secondStage.setScene(new Scene(root));

        secondStage.show();

    }

    @FXML
    private void newProgressWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("progress.fxml"));
        Parent root = loader.load();

        Stage secondStage = new Stage();
        secondStage.setTitle("Loading Screen");


        LoadingController loadingScreenController = loader.getController();


        Scene scene = new Scene(root);
        secondStage.setScene(scene);

        secondStage.show();

    }



    @FXML
    public void zipInp(ActionEvent actionEvent)
    {
        file.typeOfInputArchive = "zip";

            try {
                if(file.isArchiveOutputFile)
                {
                    newArchiveOutWindow();
                }
                else{
                    newCalculateWindow();
                }
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (Exception e) {

                System.out.println("Error new window" + e.toString());
            }

    }

    @FXML
    public void rarInp(ActionEvent actionEvent)
    {
        file.typeOfInputArchive = "rar";
        try {
            if(file.isArchiveOutputFile)
            {
                newArchiveOutWindow();
            }
            else{
                newCalculateWindow();
            }
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        }
        catch (Exception e) {

            System.out.println("Error new window" + e.toString());
        }
    }

    @FXML
    public void zipOut(ActionEvent actionEvent)
    {
        file.typeOfOutputArchive = "zip";
        try {
            newCalculateWindow();
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        }
        catch (Exception e) {

            System.out.println("Error new window" + e.toString());
        }
    }

    @FXML
    public void rarOut(ActionEvent actionEvent)
    {
        file.typeOfOutputArchive = "rar";
        try {
            newCalculateWindow();
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        }
        catch (Exception e) {

            System.out.println("Error new window" + e.toString());
        }

    }

    @FXML
    private ToggleGroup extensionInputToggleGroup;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField nameOutput;
    @FXML
    private ToggleGroup extensionOutputToggleGroup;

    @FXML
    private ToggleGroup typeInputToggleGroup;

    @FXML
    private ToggleGroup typeOutputToggleGroup;
    private void showErrorAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    private void submitForm() {
        if (nameInput.getText().isEmpty() || nameOutput.getText().isEmpty() ||extensionInputToggleGroup.getSelectedToggle() == null
        ||extensionOutputToggleGroup.getSelectedToggle() == null || typeInputToggleGroup.getSelectedToggle() == null
        ||typeOutputToggleGroup.getSelectedToggle() == null )
        {
            showErrorAlert("Error", "Required fields", "Please, fill all required fields");
        }
    }


    @FXML
    public void getData(ActionEvent actionEvent) throws IOException {
        submitForm();

        file.nameInputFile = nameInput.getText();
        file.nameOutputFile = nameOutput.getText();

        RadioButton selectedInputType = (RadioButton) extensionInputToggleGroup.getSelectedToggle();
        file.typeInputFile = selectedInputType.getText();


        selectedInputType = (RadioButton) extensionOutputToggleGroup.getSelectedToggle();
        file.typeOutputFile = selectedInputType.getText();

        selectedInputType = (RadioButton) typeInputToggleGroup.getSelectedToggle();
        switch (selectedInputType.getText()) {
            case "Just file"-> {
                file.isArchiveInputFile = false;
                file.isEncryptInputFile = false;
            }
            case "Encrypt file" -> {

                file.isArchiveInputFile = false;
                file.isEncryptInputFile = true;
            }
            case "Archive file" -> {
                file.isArchiveInputFile = true;
                file.isEncryptInputFile = false;
            }
            case "Encrypt -> Archive file"-> {
                file.isArchiveInputFile = true;
                file.isEncryptInputFile = true;
                file.encryptThanArchiveInput =true;
            }
            case "Archive -> Encrypt file" ->{
                file.isArchiveInputFile = true;
                file.isEncryptInputFile = true;
                file.ArchiveThanEncryptInput =true;
            }
            default -> {
            }
        }

        selectedInputType = (RadioButton) typeOutputToggleGroup.getSelectedToggle();
        switch (selectedInputType.getText()) {
            case "Just file" -> {
                file.isArchiveOutputFile = false;
                file.isEncryptOutputFile = false;

            }
            case "Encrypt file" -> {
                file.isArchiveOutputFile = false;
                file.isEncryptOutputFile = true;
            }
            case "Archive file" -> {
                file.isArchiveOutputFile = true;
                file.isEncryptOutputFile = false;
            }
            case "Encrypt -> Archive file"-> {
                file.isArchiveOutputFile = true;
                file.isEncryptOutputFile= true;
                file.encryptThanArchiveOutput= true;


            }
            case "Archive -> Encrypt file"->{
                file.isArchiveOutputFile = true;
                file.isEncryptOutputFile= true;
                file.ArchiveThanEncryptOutput =true;

            }
            default -> {
            }
        }
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();

        if(file.isArchiveInputFile) {
            newArchiveInpWindow();
        }
        else if(file.isArchiveOutputFile)
        {
            newArchiveOutWindow();
        }
        else{
            newCalculateWindow();
        }




    }



    @FXML
    private ToggleGroup calculateToggleGroup;
    @FXML
    private void openLoadingScreen() {

    }

    @FXML
    public void calculate(ActionEvent actionEvent) throws IOException
    {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        newProgressWindow();



        RadioButton selectedInputType = (RadioButton) calculateToggleGroup.getSelectedToggle();
        switch (selectedInputType.getText()) {
            case "Recursion" -> {
                file.typeOfCalculate = "1";

            }
            case "Regex" -> {
                file.typeOfCalculate = "2";

            }
            case "Library" -> {
                file.typeOfCalculate = "3";

            }
            default -> {

            }
        }

        Manager manage = new Manager(file);
        manage.callFunctionInput();
        manage.callFunctionOutput();



    }

}