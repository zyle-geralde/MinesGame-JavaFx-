package com.example.projjavafxoop2.DashBoardPackage;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ClickSelectedEffectThread implements Runnable{
    @Override
    public void run() {
        String filePathclick = "src/main/resources/com/example/projjavafxoop2/ClickEffect.mp3";
        Media clickmouse = new Media(new File(filePathclick).toURI().toString());

        MediaPlayer mediaPlayerclick = new MediaPlayer(clickmouse);

        mediaPlayerclick.play();
    }
}
