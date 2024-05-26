package com.example.casino_finalproject_oop;

import com.example.casino_finalproject_oop.SlotMachine.BackgroundModel;
import com.example.casino_finalproject_oop.SlotMachine.MoneyOperations;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;

import static com.example.casino_finalproject_oop.SlotMachine.MoneyOperations.checkCashtoCoins;
import static com.example.casino_finalproject_oop.SlotMachine.MoneyOperations.checkCoinstoCash;

public class MoneyController {
    @FXML
    private Label TempHolderOne;
    @FXML
    private Label TempHolderTwo;
    public int cashTempVal, coinsTempVal;
    @FXML
    private ImageView img_convertIcon;

    @FXML
    private AnchorPane moneyAnchorPane;
    @FXML
    private Button btn_convert;
    public static String coinsOrCashPage;
    @FXML
    Button fivePlusButton;
    @FXML
    Button fiveMinusButton;
    @FXML
    Button fifteenPlusButton;
    @FXML
    Button fifteenMinusButton;
    @FXML
    Button thirtyPlusButton;
    @FXML
    Button thirtyMinusButton;
    @FXML
    Button sixtyPlusButton;
    @FXML
    Button sixtyMinusButton;
    @FXML
    Button twoHCPlusButton;
    @FXML
    Button twoHCMinusButton;
    @FXML
    Button fiveHCPlusButton;
    @FXML
    Button fiveHCMinusButton;


    public void initialize() {
        BackgroundModel.getInstance().backgroundImageProperty().addListener((observable, oldValue, newValue) -> {
            moneyAnchorPane.setBackground(new Background(newValue));
        });
        setBackgroundToButtons();

    }
    private void setBackgroundToButtons() {
        fivePlusButton.setStyle("-fx-background-color: linear-gradient(to bottom, gold, white);");
        fiveMinusButton.setStyle("-fx-background-color: linear-gradient(to bottom, gold, white);");
        fifteenPlusButton.setStyle("-fx-background-color: linear-gradient(to bottom, gold, white);");
        fifteenMinusButton.setStyle("-fx-background-color: linear-gradient(to bottom, gold, white);");
        thirtyPlusButton.setStyle("-fx-background-color: linear-gradient(to bottom, gold, white);");
        thirtyMinusButton.setStyle("-fx-background-color: linear-gradient(to bottom, gold, white);");
        sixtyPlusButton.setStyle("-fx-background-color: linear-gradient(to bottom, gold, white);");
        sixtyMinusButton.setStyle("-fx-background-color: linear-gradient(to bottom, gold, white);");
        twoHCPlusButton.setStyle("-fx-background-color: linear-gradient(to bottom, gold, white);");
        twoHCMinusButton.setStyle("-fx-background-color: linear-gradient(to bottom, gold, white);");
        fiveHCPlusButton.setStyle("-fx-background-color: linear-gradient(to bottom, gold, white);");
        fiveHCMinusButton.setStyle("-fx-background-color: linear-gradient(to bottom, gold, white);");
    }

   @FXML
   public void clickAnimate(Button button) {
       String audioFile = "src/main/resources/audio/moneyConvertClick.wav";
       Media sound = new Media(new File(audioFile).toURI().toString());

       MediaPlayer mediaPlayer = new MediaPlayer(sound);
       mediaPlayer.play();
       mediaPlayer.setOnEndOfMedia(() -> {
           mediaPlayer.stop();
           mediaPlayer.dispose();
       });
       ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), button);
       scaleTransition.setToX(0.9);
       scaleTransition.setToY(0.9);
       scaleTransition.setAutoReverse(true);
       scaleTransition.setCycleCount(2);
       scaleTransition.play();
   }
    @FXML
    public void convertBtnPressed(){
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), img_convertIcon);
        scaleTransition.setToX(0.9);
        scaleTransition.setToY(0.9);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);
        scaleTransition.play();
        //converting coins to cash
        if(coinsOrCashPage.equals("coins")){
            checkCashtoCoins(cashTempVal, coinsTempVal);
        }else{  //converting cash to coins
            checkCoinstoCash(cashTempVal, coinsTempVal);
        }
        TempHolderOne.setText("0");
        TempHolderTwo.setText("0");
        cashTempVal = 0;
        coinsTempVal = 0;

    }
    public void updateTempHolders(){
        if(coinsOrCashPage.equals("coins")){
            TempHolderOne.setText(String.valueOf(cashTempVal));
            TempHolderTwo.setText(String.valueOf(coinsTempVal));
        }else{
            TempHolderTwo.setText(String.valueOf(cashTempVal));
            TempHolderOne.setText(String.valueOf(coinsTempVal));
        }
    }
    public void errorColorTempHolders(){
        TempHolderOne.setText("0");
        TempHolderTwo.setText("0");
        cashTempVal = 0;
        coinsTempVal = 0;
      /*  TempHolderOne.setTextFill(Color.web("#FF00FF"));
        TempHolderTwo.setTextFill(Color.web("#FF00FF"));*/
    }
    @FXML
    public void fivePlusBtnPressed(){
        clickAnimate(fivePlusButton);
        cashTempVal+=20;coinsTempVal+=5;
        updateTempHolders();
    }
    @FXML
    public void fiveMinusBtnPressed(){
        clickAnimate(fiveMinusButton);
        if(cashTempVal>=20 && coinsTempVal>=5){
            cashTempVal-=20;coinsTempVal-=5;
            updateTempHolders();
        }else{
            errorColorTempHolders();
        }

    }
    @FXML
    public void fifteenPlusBtnPressed(){
        clickAnimate(fifteenPlusButton);
        cashTempVal+=50;coinsTempVal+=15;
        TempHolderOne.setText(String.valueOf(cashTempVal));TempHolderTwo.setText(String.valueOf(coinsTempVal));}
    @FXML
    public void fifteenMinusBtnPressed(){
        clickAnimate(fifteenMinusButton);
        if(cashTempVal>=50 && coinsTempVal>=15){
            cashTempVal-=50;coinsTempVal-=15;
            updateTempHolders();
        }else{
            errorColorTempHolders();
        }
    }
    @FXML
    public void thirtyPlusBtnPressed(){
        clickAnimate(thirtyPlusButton);
        cashTempVal+=100;coinsTempVal+=30;
        updateTempHolders();}
    @FXML
    public void thirtyMinusBtnPressed(){
        clickAnimate(thirtyMinusButton);
        if(cashTempVal>=100 && coinsTempVal>=30){
            cashTempVal-=100;coinsTempVal-=30;
            updateTempHolders();
        }else{
            errorColorTempHolders();
        }
    }
    @FXML
    public void sixtyPlusBtnPressed(){
        clickAnimate(sixtyPlusButton);
        cashTempVal+=200;coinsTempVal+=60; updateTempHolders();}
    @FXML
    public void sixtyMinusBtnPressed(){
        clickAnimate(sixtyMinusButton);
        if(cashTempVal>=200 && coinsTempVal>=60){
            cashTempVal-=200;coinsTempVal-=60;
            updateTempHolders();
        }else{
            errorColorTempHolders();
        }
    }
    @FXML
    public void twoHCPlusBtnPressed(){
        clickAnimate(twoHCPlusButton);
        cashTempVal+=500;coinsTempVal+=200;
        updateTempHolders();}
    @FXML
    public void twoHCMinusBtnPressed(){
        clickAnimate(twoHCMinusButton);
        if(cashTempVal>=500 && coinsTempVal>=200){
            cashTempVal-=500;coinsTempVal-=200;
            updateTempHolders();
        }else{
            errorColorTempHolders();
        }
    }
    @FXML
    public void fiveHCPlusBtnPressed(){
        clickAnimate(fiveHCPlusButton);
        cashTempVal+=1000;coinsTempVal+=500;
        updateTempHolders();}
    @FXML
    public void fiveHCMinusBtnPressed(){
        clickAnimate(fiveHCMinusButton);
        if(cashTempVal>=1000 && coinsTempVal>=500){
            cashTempVal-=1000;coinsTempVal-=500;
            updateTempHolders();
        }else{
            errorColorTempHolders();
        }
    }

}