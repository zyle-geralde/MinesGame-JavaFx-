package com.example.projjavafxoop2.LogSignPackage;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class BackMedia extends MediaPlayAbstractClass{
    public BackMedia(MediaView mv, MediaPlayer mp) {
        super(mv, mp);
    }

    @Override
    public void CreateMedia() {
        MediaView BackView = this.getMv();
        MediaPlayer mediaPlayer2 = this.getMp();

        BackView.setMediaPlayer(mediaPlayer2);

        mediaPlayer2.setOnReady(() -> {
            BackView.setVisible(true);
            mediaPlayer2.play();
        });

        mediaPlayer2.setOnEndOfMedia(() -> {
            mediaPlayer2.seek(Duration.ZERO);
            mediaPlayer2.play();
        });
    }
}
