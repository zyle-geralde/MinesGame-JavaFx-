package com.example.projjavafxoop2.LogSignPackage;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public abstract class MediaPlayAbstractClass{
    private MediaView mv;
    private MediaPlayer mp;
    public MediaPlayAbstractClass(MediaView mv, MediaPlayer mp){
        this.mv = mv;
        this.mp = mp;
    }

    public MediaView getMv() {
        return mv;
    }


    public MediaPlayer getMp() {
        return mp;
    }


    public abstract void CreateMedia();
}
