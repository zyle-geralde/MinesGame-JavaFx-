package com.example.projjavafxoop2.LogSignPackage;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class IntroMedia extends MediaPlayAbstractClass{
    public IntroMedia(MediaView mv, MediaPlayer mp) {
        super(mv, mp);
    }

    @Override
    public void CreateMedia() {
        MediaView mediaView = this.getMv();
        MediaPlayer mediaPlayer = this.getMp();
        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.play();
    }
}
