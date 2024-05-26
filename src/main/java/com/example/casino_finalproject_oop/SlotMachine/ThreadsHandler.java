package com.example.casino_finalproject_oop.SlotMachine;

import com.example.casino_finalproject_oop.SlotMachineController;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadsHandler extends Thread {
    private SlotMachineController slotMachine;
    private ArrayList<Symbol> reel;

    private final int reelIndex;
    private int index = 0;
    private long startTime;
    private volatile boolean running = true; // Flag to control thread execution


    public void stopThread() {
        running = false; // Set the flag to stop the thread
    }

    public ThreadsHandler(SlotMachineController slotMachine, ArrayList<Symbol> reel, int reelIndex) {
        this.slotMachine = slotMachine;
        this.reel = reel;
        this.reelIndex = reelIndex;
    }


    //Animating the spin process using Threads
    @Override
    public void run() {
        System.out.println("Thread " + reelIndex + " started.");
        ImageView reelImageView;
        if (reelIndex == 0) {
            reelImageView = slotMachine.reel1;
        } else if (reelIndex == 1) {
            reelImageView = slotMachine.reel2;
        } else {
            reelImageView = slotMachine.reel3;
        }
        Random random = new Random();
        startTime = System.currentTimeMillis();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        if (slotMachine.shutdownCalled.compareAndSet(false, true)) {
            scheduler.schedule(this::stopAllThreads, 6, TimeUnit.SECONDS);
        }
        while (running) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            CountDownLatch latch = new CountDownLatch(1);
            Platform.runLater(() -> {
                int index;
                //CHEAT CODE: if usageCounter==2(REDSEVEN); 3(BIGWIN); 5("MEGAWIN"); 7("SUPERWIN")
                int cheatA = -1;
                //Cheat Code: Check if it's the third usage and set index to REDSEVEN
                if (slotMachine.usageCounter.get() == 1 && elapsedTime >= 5800) {
                    for (int i = 0; i < reel.size(); i++) {
                        if (reel.get(i).getName().equals("MONKEY")) {
                            cheatA = i;
                            break;
                        }
                    }
                    // If REDSEVEN not found, default to random index
                    index = (cheatA == -1) ? random.nextInt(reel.size()) : cheatA;
                } else if (slotMachine.usageCounter.get() == 2 && elapsedTime >= 5800) {
                    for (int i = 0; i < reel.size(); i++) {
                        if (reel.get(i).getName().equals("BANANA")) {
                            cheatA = i;
                            break;
                        }
                    }
                    // If REDSEVEN not found, default to random index
                    index = (cheatA == -1) ? random.nextInt(reel.size()) : cheatA;
                } else if (slotMachine.usageCounter.get() == 3 && elapsedTime >= 5800) {
                    for (int i = 0; i < reel.size(); i++) {
                        if (reel.get(i).getName().equals("BIRD")) {
                            cheatA = i;
                            break;
                        }
                    }
                    // If REDSEVEN not found, default to random index
                    index = (cheatA == -1) ? random.nextInt(reel.size()) : cheatA;
                }else {
                    index = random.nextInt(reel.size());
                }

                // Update the image
                reelImageView.setImage(reel.get(index).getImage());
                // Set current symbol name based on reel index
                if (reelIndex == 0) {
                    slotMachine.currSymbolNameReel1 = reel.get(index).getName();
                } else if (reelIndex == 1) {
                    slotMachine.currSymbolNameReel2 = reel.get(index).getName();
                } else {
                    slotMachine.currSymbolNameReel3 = reel.get(index).getName();
                }

                // Add translation animation for moving upwards
                TranslateTransition translateUp = new TranslateTransition(Duration.seconds(0.1), reelImageView);
                translateUp.setByY(-30); // Move upwards

                // Add color adjustment effect for darkening while moving upwards
                ColorAdjust colorAdjustUp = new ColorAdjust();
                colorAdjustUp.setBrightness(-0.25); // Adjust brightness to make it slightly darker
                reelImageView.setEffect(colorAdjustUp);

                translateUp.setOnFinished(event -> {
                    reelImageView.setTranslateY(0);
                    reelImageView.setEffect(null);

                    // Add translation animation for moving downwards
                    TranslateTransition translateDown = new TranslateTransition(Duration.seconds(0.009), reelImageView);
                    translateDown.setByY(180); // Move downwards

                    ColorAdjust colorAdjustDown = new ColorAdjust();
                    colorAdjustDown.setBrightness(-0.25);
                    reelImageView.setEffect(colorAdjustDown);

                    translateDown.setOnFinished(event1 -> {
                        reelImageView.setTranslateY(0);
                        reelImageView.setEffect(null);
                        latch.countDown(); // Release the latch when animation is done
                    });

                    translateDown.play(); // Start the downward transition
                });

                translateUp.play(); // Start the upward transition
            });

            try {
                latch.await();
                long timeRemaining = 6000 - elapsedTime;
                if (timeRemaining <= 2000) { // Last 2 seconds
                    Thread.sleep(50);
                } else if (timeRemaining <= 4000) { // Last 4 seconds
                    Thread.sleep(30);
                } else {
                    Thread.sleep(10); // Default delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Thread " + reelIndex + " stopped.");
        //scheduler.shutdown();
        if (slotMachine.shutdownCalled.compareAndSet(true, false)) {
            scheduler.shutdown();
        }
    }

    private void stopAllThreads() {
       Platform.runLater(() -> {
           try {
               slotMachine.stopBtnPressed();
           } catch (Exception e) {
               e.printStackTrace();
           }
       });
    }

}
