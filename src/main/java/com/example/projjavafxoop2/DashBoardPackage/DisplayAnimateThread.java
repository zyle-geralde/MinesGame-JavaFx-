package com.example.projjavafxoop2.DashBoardPackage;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;

public class DisplayAnimateThread implements Runnable {
    private AnchorPane anch;

    private int indic;
    private double delaydur;

    private double transit;

    public DisplayAnimateThread(AnchorPane anch, int indic, double delaydur, double transit) {
        this.anch = anch;
        this.indic = indic;
        this.delaydur = delaydur;
        this.transit = transit;
    }

    @Override
    public void run() {
        System.out.println("Thread");
        Platform.runLater(() -> {
            MainCompoenetTimeline mnc = new MainCompoenetTimeline(delaydur, transit, anch);
            mnc.getAnimate(indic);
        });
    }
}
