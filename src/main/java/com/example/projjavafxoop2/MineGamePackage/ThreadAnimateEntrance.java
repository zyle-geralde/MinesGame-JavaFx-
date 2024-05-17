package com.example.projjavafxoop2.MineGamePackage;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ThreadAnimateEntrance implements Runnable{
    Scene scene;
    public ThreadAnimateEntrance(Scene scene){
        this.scene = scene;
    }
    @Override
    public void run() {
        AnchorPane MinesLogo =(AnchorPane) scene.lookup("#MinesLogo");
        Timeline MinesLogotime= new Timeline();
        MinesLogotime.setDelay(Duration.seconds(0.2));
        MinesLogotime.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.2), new KeyValue(MinesLogo.opacityProperty(),1))
        );
        MinesLogotime.play();


        AnchorPane MoneyTans =(AnchorPane) scene.lookup("#MoneyTans");
        Timeline MoneyTanstime= new Timeline();
        MoneyTanstime.setDelay(Duration.seconds(0.3));
        MoneyTanstime.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.2), new KeyValue(MoneyTans.opacityProperty(),1))
        );
        MoneyTanstime.play();

        ImageView backtoDash =(ImageView) scene.lookup("#backtoDash");
        Timeline backtoDashtime= new Timeline();
        backtoDashtime.setDelay(Duration.seconds(0.4));
        backtoDashtime.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.2), new KeyValue(backtoDash.opacityProperty(),1))
        );
        backtoDashtime.play();

        AnchorPane BoardTrans =(AnchorPane) scene.lookup("#BoardTrans");
        Timeline BoardTranstime= new Timeline();
        BoardTranstime.setDelay(Duration.seconds(0.5));
        BoardTranstime.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.3), new KeyValue(BoardTrans.opacityProperty(),1))
        );
        BoardTranstime.play();


    }
}
