package com.example.ppfx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;


public class LoadingController {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label statusLabel;
    @FXML
    private Button okButton;
    @FXML
    public void okExit(){
       System.exit(0);
    }

    public void initialize() {
        okButton.setVisible(false);

        simulateLoading();
    }

    private void simulateLoading() {
        double durationInSeconds = 0.7;

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(durationInSeconds), new KeyValue(progressBar.progressProperty(), 1))
        );


        timeline.setOnFinished(event -> {
            statusLabel.setText("FILE IS DONE");
            progressBar.setVisible(false);
            okButton.setVisible(true);


        });

        timeline.play();
    }


}