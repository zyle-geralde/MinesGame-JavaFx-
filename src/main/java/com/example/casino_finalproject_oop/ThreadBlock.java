package com.example.casino_finalproject_oop;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

import static com.example.casino_finalproject_oop.HelloApplicationV2.finishedAnimations;
import static com.example.casino_finalproject_oop.HelloApplicationV2.rolledFaces;

public class ThreadBlock implements Runnable{
    public static final int NUM_BLOCK = 3;
    ArrayList<ImageView> blockList = new ArrayList<>();
    Random random = new Random();
    int index;

    public ThreadBlock(ArrayList<ImageView> blockList, int index) {
        this.blockList = blockList;
        this.index = index;
    }

    @Override
    public void run() {
            int time = random.nextInt(2500, 4000);
            RotateTransition rt = new RotateTransition();
            rt.setByAngle(1800);
            rt.setNode(blockList.get(index));
            rt.setDuration(Duration.millis(time));
            rt.setInterpolator(Interpolator.EASE_OUT);
            rt.setAutoReverse(false);
            rt.play();
            rt.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Image img = new Image(String.format("file:src/main/resources/com/example/casino_finalproject_oop/%s.png", HelloApplicationV2.rollBlock()));
                    blockList.get(index).setImage(img);
                    String imageUrl = img.getUrl();
                    int numberRep = Character.getNumericValue(imageUrl.charAt(imageUrl.length() - 5));
                    rolledFaces[index] = numberRep - 1;
                    System.out.println("Rolled face " + (index + 1) + ": " + (numberRep - 1));

                    synchronized (finishedAnimations) { // Synchronize access to the shared counter
                        finishedAnimations[0]++; // Increment the count of finished animations
                        if (finishedAnimations[0] == NUM_BLOCK) {
                            try {
                                HelloApplicationV2.calculateWin();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            });
    }
}
