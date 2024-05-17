package com.example.projjavafxoop2.LogSignPackage;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ClickSoundThread implements Runnable{
    @Override
    public void run() {
        String filePathclick = "src/main/resources/com/example/projjavafxoop2/mpfiles/clickmouse.mp3";
        Media clickmouse = new Media(new File(filePathclick).toURI().toString());

        MediaPlayer mediaPlayerclick = new MediaPlayer(clickmouse);

        mediaPlayerclick.play();
    }
}
