package com.example.projjavafxoop2.DashBoardPackage;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class OpenWallet extends WalletButAbstractClass{
    public OpenWallet(AnchorPane ViewWallet) {
        super(ViewWallet);
    }

    @Override
    public void onClickfunc() {
        ViewWallet.setVisible(true);
        Timeline TimeWallet= new Timeline();
        TimeWallet.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.2), new KeyValue(ViewWallet.opacityProperty(),1))
        );
        TimeWallet.play();
    }
}
