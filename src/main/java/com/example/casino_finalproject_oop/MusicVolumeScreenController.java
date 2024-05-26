package com.example.casino_finalproject_oop;


import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MusicVolumeScreenController {
    @FXML
    private Slider volumeSlider;
    @FXML
    private AnchorPane volumeAnchor;

    private SlotMachineController mainController;
    private double recentVolume;

    @FXML
    private void initialize() {
        volumeSlider.setMax(100);
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            recentVolume = newValue.doubleValue() / 100;
            adjustM(recentVolume); // Adjust the music volume when slider is dragged
        });
        BackgroundImage myBI = new BackgroundImage(
                new Image(getClass().getResource("/images/adjustVolumeBg.png").toExternalForm(),
                        600, 150, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        volumeAnchor.setBackground(new Background(myBI));

    }

    public void setRecentVolume(double volume) {
        recentVolume = volume;
        volumeSlider.setValue(volume * 100);
        adjustM(volume); // Adjust the music volume when recent volume is set
    }

    public double getRecentVolume() {
        return recentVolume;
    }

    private void adjustM(double volume) {
        if (mainController != null) {
            mainController.adjustMusicVolume(volume); // Call adjustMusicVolume in SlotMachineController
        }
    }

}
