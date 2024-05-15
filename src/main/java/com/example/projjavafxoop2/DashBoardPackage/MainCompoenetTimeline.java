package com.example.projjavafxoop2.DashBoardPackage;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MainCompoenetTimeline {
    private double dalay_duration;
    private double transit_duration;

    private AnchorPane anchor;

    public MainCompoenetTimeline(double dalay_duration, double transit_duration, AnchorPane anchor) {
        this.dalay_duration = dalay_duration;
        this.transit_duration = transit_duration;
        this.anchor = anchor;
    }

    public double getDalay_duration() {
        return dalay_duration;
    }

    public void setDalay_duration(int dalay_duration) {
        this.dalay_duration = dalay_duration;
    }

    public double getTransit_duration() {
        return transit_duration;
    }

    public void setTransit_duration(int transit_duration) {
        this.transit_duration = transit_duration;
    }

    public void getAnimate(int indic){
        Timeline sidebartime= new Timeline();
        sidebartime.setDelay(Duration.seconds(this.dalay_duration));
        if(indic== 0){
            sidebartime.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(this.transit_duration), new KeyValue(anchor.translateXProperty(),0))
            );
        }
        else{
            sidebartime.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(this.transit_duration), new KeyValue(anchor.translateYProperty(),0))
            );
        }

        sidebartime.play();
    }

}
