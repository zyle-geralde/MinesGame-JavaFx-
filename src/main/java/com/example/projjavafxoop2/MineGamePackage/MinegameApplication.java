package com.example.projjavafxoop2.MineGamePackage;

import com.example.projjavafxoop2.DashBoardPackage.ClickSelectedEffectThread;
import com.example.projjavafxoop2.DashBoardPackage.DashBoardSample;
import com.example.projjavafxoop2.LogSignPackage.ClickSoundThread;
import com.example.projjavafxoop2.SqlConnect;
import com.example.projjavafxoop2.WalletClass;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class MinegameApplication{

    static double betval = 0;
    static double bombval = 0;

    static int num_of_bombs = 0;

    ArrayList<Pane> gridcol = new ArrayList();
    ArrayList<Integer> minesindicator = new ArrayList<>();

    ArrayList<Boolean> isPaneClicked = new ArrayList();
    Scene initialscene;

    WalletClass userwallet;


    public void refresh(Stage stage, WalletClass userwallet) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/projjavafxoop2/Minegame.fxml"));
        Scene scene = new Scene(root, 1150, 700);
        stage.setTitle("Mine Game");
        stage.setScene(scene);
        stage.show();
        this.userwallet = userwallet;

        ImageView backtoDash = (ImageView) scene.lookup("#backtoDash");

        Label moneylabel = (Label) scene.lookup("#moneylabel");
        moneylabel.setText(String.format("%.2f",userwallet.getBalance()));

        //Animating entrance
        Thread thrd = new Thread(new ThreadAnimateEntrance(scene));
        thrd.start();

        //Designing textfields
        Thread designthread = new Thread(new DesignThread(scene));
        designthread.start();

        TextField betInput = (TextField) scene.lookup("#BetInp");
        TextField BombsInput = (TextField) scene.lookup("#BombsInput");

        Button playButton = (Button) scene.lookup("#PlayButton");

        //designing mineboxes
        Thread minesDesignThread = new Thread(new SetMineDesignThread(scene,gridcol));
        minesDesignThread.start();

        minesDesignThread.join();
        Thread.sleep(300);



        //play button listener
        playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                AllButtonAbstractClass clickOn = new PlayButtonClass(scene, betInput,BombsInput, userwallet,playButton, gridcol,minesindicator, isPaneClicked);
                clickOn.Clickfunction();

            }
        });

        playButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread hoversound = new Thread(new ClickSoundThread());
                hoversound.start();
            }
        });
        try{
            //adding event listeners for each MineBox
            for(Pane minesPane: gridcol){

                minesPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        AllButtonAbstractClass minesBUt = new MinesButtonClass(gridcol, minesPane, isPaneClicked,  minesindicator, scene,  BombsInput);
                        minesBUt.Clickfunction();
                    }
                });
            }
        }
        catch (Exception e){
            System.out.println("Dont click");
        }


        AnchorPane PlayAgainclick = (AnchorPane) scene.lookup("#PlayAgainclick");
        PlayAgainclick.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AllButtonAbstractClass playClck = new PlayAgainBut(stage,userwallet);
                playClck.Clickfunction();
            }
        });
        PlayAgainclick.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread hoversound = new Thread(new ClickSoundThread());
                hoversound.start();
            }
        });
        AnchorPane clickCheckOut = (AnchorPane) scene.lookup("#clickCheckOut");
        clickCheckOut.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AllButtonAbstractClass checkOutBut = new CheckOutClass(userwallet,moneylabel, stage,scene);
                checkOutBut.Clickfunction();
            }
        });
        clickCheckOut.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread hoversound = new Thread(new ClickSoundThread());
                hoversound.start();
            }
        });

        backtoDash.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                DashBoardSample dsh = new DashBoardSample(userwallet);
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/projjavafxoop2/DashBoardSample.fxml"));
                    Scene scene = new Scene(root, 1150, 700);
                    stage.setTitle("DashBoard");
                    stage.setScene(scene);
                    stage.show();
                    dsh.refresh(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        backtoDash.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread hoversound = new Thread(new ClickSoundThread());
                hoversound.start();
            }
        });


    }




}
