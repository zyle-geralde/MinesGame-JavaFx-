package com.example.casino_finalproject_oop.SlotMachine;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.BackgroundImage;

public class BackgroundModel {
    private static BackgroundModel instance;

    private ObjectProperty<BackgroundImage> backgroundImage = new SimpleObjectProperty<>();

    public static BackgroundModel getInstance() {
        if (instance == null) {
            instance = new BackgroundModel();
        }
        return instance;
    }

    public ObjectProperty<BackgroundImage> backgroundImageProperty() {
        return backgroundImage;
    }

    public BackgroundImage getBackgroundImage() {
        return backgroundImage.get();
    }

    public void setBackgroundImage(BackgroundImage image) {
        backgroundImage.set(image);
    }
}
