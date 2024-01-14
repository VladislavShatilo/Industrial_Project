package com.example.ppfx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class LoadingController {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label statusLabel;

    public void initialize() {

        simulateLoading();
    }

    private void simulateLoading() {
        double durationInSeconds = 2.0;

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(durationInSeconds), new KeyValue(progressBar.progressProperty(), 1))
        );


        timeline.setOnFinished(event -> {
            statusLabel.setText("Loading complete");
            closeLoadingScreen();
        });


        timeline.play();
    }

    private void closeLoadingScreen() {

        Stage stage = (Stage) progressBar.getScene().getWindow();
        stage.close();
    }
}