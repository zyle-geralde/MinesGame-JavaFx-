package com.example.casino_finalproject_oop;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class RollBlocks extends Application {
    public static final int NUM_BLOCK = 3;
    ArrayList<ImageView> blockList = new ArrayList<>();
    Random random = new Random();

    public int rollBlock() {
        return random.nextInt(6, 12)+1;
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(10, 10, 10, 10));
//        grid.setVgap(30);
//        grid.setHgap(20);
        grid.setGridLinesVisible(true);

        Text sceneTitle = new Text("");
        sceneTitle.setTextAlignment(TextAlignment.CENTER);
        sceneTitle.setStrokeWidth(20);
        sceneTitle.setFill(Paint.valueOf("#325622"));
        sceneTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.setHalignment(sceneTitle, HPos.CENTER);
        grid.setColumnSpan(sceneTitle, 2);
        grid.setRowSpan(sceneTitle, 1);
        grid.add(sceneTitle, 1, 1);

        //set the initial faces of the blocks
        for(int i = 0; i < NUM_BLOCK; i++) {
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(rollBlock() + ".png"), 100, 0, true, true));
            blockList.add(i, imageView);
        }

        Button roll = new Button("Roll Blocks");
        roll.setMinSize(15, 25);
        roll.setMinWidth(100);
        roll.setMinHeight(50);
        roll.fontProperty().setValue(Font.font(18));

        roll.setOnAction(e -> {
            for (int i = 0; i < NUM_BLOCK; i++) {
                RotateTransition rt = new RotateTransition();
                rt.setByAngle(360);
                rt.setNode(blockList.get(i));
                rt.setDuration(Duration.millis(600));
                rt.play();
                int x = i;
                rt.setOnFinished(j -> blockList.get(x).setImage(new Image(getClass().getResourceAsStream(rollBlock() + ".png"), 100, 0, true, true)));
            }
        });

        //add each block node to grid
        for(int i = 0; i < NUM_BLOCK; i++) {
            grid.add(blockList.get(i), i, 0);
        }

//        grid.add(blockList.get(0), 1, 0);
//        grid.add(blockList.get(1), 0, 1);
//        grid.add(blockList.get(2), 2, 1);

        grid.add(roll, 0, 1);
        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid, 200+110*NUM_BLOCK, 400);
        stage.setTitle("Roll Blocks");
        stage.setScene(scene);
        stage.show();
    }
}
