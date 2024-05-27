package com.example.casino_finalproject_oop;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RollingBlock3D extends Application {

    @Override
    public void start(Stage primaryStage) {
        Box box = new Box(100, 100, 100);

        // Create materials for each face
        PhongMaterial[] materials = {
                new PhongMaterial(Color.RED),
                new PhongMaterial(Color.GREEN),
                new PhongMaterial(Color.BLUE),
                new PhongMaterial(Color.YELLOW),
                new PhongMaterial(Color.ORANGE),
                new PhongMaterial(Color.PURPLE)
        };

        // Set a different material for each face of the box
        for (int i = 0; i < 6; i++) {
            box.setMaterial(materials[i]);
        }

        // Create a rotation animation
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), box);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE);

        // Create a button to trigger the animation
        Button button = new Button("Roll");
        button.setOnAction(event -> {
            if (!rotateTransition.getStatus().equals(RotateTransition.Status.RUNNING)) {
                rotateTransition.play();
            }
        });

        // Place the button and the 3D block side by side
        HBox root = new HBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(box, button);

        // Set up the scene
        Scene scene = new Scene(root, 600, 400, true);
        scene.setFill(Color.LIGHTGRAY);
        scene.setCamera(new PerspectiveCamera());

        // Set up the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("3D Block Roller");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
