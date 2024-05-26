package com.example.casino_finalproject_oop.SlotMachine;

import com.example.casino_finalproject_oop.HelloApplication;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.util.Optional;

public class AlertOperations {
    private static MediaPlayer audioPlayer;
    public static void addMusic(String fileMusic) {
        String audioFile = fileMusic;
        Media sound = new Media(new File(audioFile).toURI().toString());
        audioPlayer = new MediaPlayer(sound);
        audioPlayer.setVolume(0);
        audioPlayer.setOnReady(() -> {
            Timeline fadeIn = new Timeline(
                    new KeyFrame(Duration.seconds(0), event -> audioPlayer.setVolume(0)),
                    new KeyFrame(Duration.seconds(5), event -> audioPlayer.setVolume(1))
            );
            fadeIn.setCycleCount(1);
            fadeIn.play();
        });

        audioPlayer.setOnError(() -> {
            System.err.println("Error occurred while playing media: " + audioPlayer.getError());
            audioPlayer.stop();
            audioPlayer.dispose();
        });

        audioPlayer.setOnEndOfMedia(() -> {
            audioPlayer.stop();
            audioPlayer.dispose();
        });
    }
        public static void showErrorAlert(String title, String header,String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);  // Set the title, but it won't be visible
            alert.setHeaderText(header);
            alert.setContentText(message);
            alert.setGraphic(null);

            // Get the DialogPane and apply the stylesheet
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    HelloApplication.class.getResource("alertError_styles.css").toExternalForm());
            dialogPane.getStyleClass().add("alert");


            ButtonType closeButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(closeButton);

            // Get the Stage and remove the title bar
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.UNDECORATED); // Remove the title bar
            stage.centerOnScreen();
            stage.setOnShown(event -> {
                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
                stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
            });

            // Show the alert and handle the close button action
            alert.show();
            alert.resultProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue == closeButton) {
                    stage.close(); // Close the dialog when OK is pressed
                }
            });
        }
        public static void showSuccessAlert(String title, String header,String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);  // Set the title, but it won't be visible
            alert.setHeaderText(header);
            alert.setContentText(message);
            alert.setGraphic(null);

            // Get the DialogPane and apply the stylesheet
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    HelloApplication.class.getResource("alertSuccess_styles.css").toExternalForm());
            dialogPane.getStyleClass().add("alert");


            ButtonType closeButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(closeButton);

            // Get the Stage and remove the title bar
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.UNDECORATED); // Remove the title bar
            stage.centerOnScreen();
            stage.setOnShown(event -> {
                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
                stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
            });

            // Show the alert and handle the close button action
            alert.show();
            alert.resultProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue == closeButton) {
                    stage.close(); // Close the dialog when OK is pressed
                }
            });
        }
    public static void showPayoutTable() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PAYOUT TABLE");
        alert.setGraphic(null);
        alert.setHeaderText(null);
        Image backgroundImage = new Image(AlertOperations.class.getResource("/images/payTablePage.png").toString(), 800, 600, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        alert.getDialogPane().setBackground(new Background(background));
        alert.getDialogPane().setPrefSize(800, 600);

        alert.initStyle(StageStyle.UTILITY);
        alert.initModality(Modality.APPLICATION_MODAL);
        Optional<ButtonType> okButtonType = alert.getDialogPane().getButtonTypes().stream()
                .filter(buttonType -> buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE)
                .findFirst();

        okButtonType.ifPresent(buttonType -> {
            Button okButton = (Button) alert.getDialogPane().lookupButton(buttonType);
            okButton.setStyle("-fx-opacity: 0;");
        });
        alert.showAndWait();
    }

    public static void showWinningVideo(String title, String videoPath, Runnable callback) {
        Timeline pauseTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            // Load the video file
            Media media = new Media(new File(videoPath).toURI().toString());
            MediaPlayer videoPlayer = new MediaPlayer(media);
            videoPlayer.setAutoPlay(true);
            MediaView mediaView = new MediaView(videoPlayer);
            mediaView.setFitWidth(800);
            mediaView.setFitHeight(600);
            StackPane root = new StackPane();
            root.getChildren().add(mediaView);
            Scene scene = new Scene(root, 800, 600);

            // Create a new Stage (window)
            Stage videoStage = new Stage();
            videoStage.initStyle(StageStyle.UNDECORATED);
            videoStage.setScene(scene);

            // Dispose the video player and close the window when the video ends
            videoPlayer.setOnEndOfMedia(() -> {
                videoPlayer.stop();
                videoPlayer.dispose();
                videoStage.close();
                callback.run(); // Execute the callback after disposing the video player
            });

            // Show the window
            videoStage.show();
        }));
        pauseTimeline.play();
    }
}
