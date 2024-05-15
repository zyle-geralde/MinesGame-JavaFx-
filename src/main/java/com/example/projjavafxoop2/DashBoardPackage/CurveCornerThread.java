package com.example.projjavafxoop2.DashBoardPackage;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class CurveCornerThread implements Runnable{
    public Scene scene;

    public CurveCornerThread(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void run() {
        ImageView imageView = (ImageView) scene.lookup("#Webbanner");
        System.out.println(imageView);
        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(50);
        clip.setArcHeight(50);

        imageView.setClip(clip);


        ImageView imageView1 = (ImageView) scene.lookup("#game1");
        System.out.println(imageView1);
        Rectangle clip1 = new Rectangle(imageView1.getFitWidth(), imageView1.getFitHeight());
        clip1.setArcWidth(10);
        clip1.setArcHeight(10);

        imageView1.setClip(clip1);


        ImageView imageView2 = (ImageView) scene.lookup("#game2");
        System.out.println(imageView2);
        Rectangle clip2 = new Rectangle(imageView2.getFitWidth(), imageView2.getFitHeight());
        clip2.setArcWidth(10);
        clip2.setArcHeight(10);

        imageView2.setClip(clip2);



        ImageView imageView3 = (ImageView) scene.lookup("#game3");
        System.out.println(imageView3);
        Rectangle clip3 = new Rectangle(imageView3.getFitWidth(), imageView3.getFitHeight());
        clip3.setArcWidth(10);
        clip3.setArcHeight(10);

        imageView3.setClip(clip3);


        ImageView imageView4 = (ImageView) scene.lookup("#game4");
        System.out.println(imageView4);
        Rectangle clip4 = new Rectangle(imageView4.getFitWidth(), imageView4.getFitHeight());
        clip4.setArcWidth(10);
        clip4.setArcHeight(10);

        imageView4.setClip(clip4);

    }
}
