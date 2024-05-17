package com.example.projjavafxoop2.MineGamePackage;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DesignThread implements Runnable{
    Scene scene;

    public DesignThread(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void run() {
        //Designing textfields
        TextField betInput = (TextField) scene.lookup("#BetInp");
        betInput.setStyle("-fx-control-inner-background: rgba(0, 0, 0); -fx-text-fill: white;");

        TextField BombsInput = (TextField) scene.lookup("#BombsInput");
        BombsInput.setStyle("-fx-control-inner-background: rgba(0, 0, 0); -fx-text-fill: white;");

    }
}
