package com.example.projjavafxoop2.DashBoardPackage;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class GameButtonClass extends MainCompoenetTimeline{
    public GameButtonClass(double dalay_duration, double transit_duration, AnchorPane anchor) {
        super(dalay_duration, transit_duration, anchor);
    }
    public GameButtonClass(double transit_duration) {
        super(0, transit_duration, null);
    }


    public void actionEntered(ImageView img){
        Timeline gamebarTime= new Timeline();
        gamebarTime.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.1), new KeyValue(img.opacityProperty(),1))
        );
        gamebarTime.play();
    }
    public void actionExit(ImageView img){
        Timeline gamebarTime= new Timeline();
        gamebarTime.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.1), new KeyValue(img.opacityProperty(),0))
        );
        gamebarTime.play();
    }

    public void actionEntered(Shape shp){
        Timeline Timebar= new Timeline();
        Timebar.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.1), new KeyValue(shp.opacityProperty(),1))
        );
        Timebar.play();
    }
    public void actionExit(Shape shp){
        Timeline Timebar= new Timeline();
        Timebar.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.1), new KeyValue(shp.opacityProperty(),0))
        );
        Timebar.play();
    }
    public void onClickfunc(){

    }
}
