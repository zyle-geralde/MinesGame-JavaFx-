package com.example.casino_finalproject_oop.SlotMachine;

import com.example.casino_finalproject_oop.SlotMachineController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

import static com.example.casino_finalproject_oop.SlotMachine.AlertOperations.*;

public class MoneyOperations {

    private int cashAmount, coinsAmount, betAmount;
    public static MediaPlayer audioPlayer;

    // Static instance
    private static MoneyOperations instance;

    // Private constructor to prevent instantiation
    private MoneyOperations() {}

    // Public method to provide access to the instance
    public static MoneyOperations getInstance() {
        if (instance == null) {
            instance = new MoneyOperations();
        }
        return instance;
    }

    public static SlotMachineController smc;
    public void setSlotMachineController(SlotMachineController controller) {
        smc = controller;
    }

    public int getCashAmount() {
       return cashAmount;
   }

    public void setCashAmount(int cashAmount) {
        this.cashAmount = cashAmount;
    }

    public int getCoinsAmount() {
        return coinsAmount;
    }

    public void setCoinsAmount(int coinsAmount) {
        this.coinsAmount = coinsAmount;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public static void addMusic(String filePath) {
        Media sound = new Media(new File(filePath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public static void checkCoinstoCash(int cashTemp, int coinsTemp){
       MoneyOperations mo = MoneyOperations.getInstance();
       if(coinsTemp == 0 && cashTemp == 0){
           addMusic("src/main/resources/audio/showError.wav");
           showErrorAlert("Conversion Failed", "Transaction Unsuccessful",
                   "No coins to be converted.");
       }else if(coinsTemp>mo.getCoinsAmount()){
           addMusic("src/main/resources/audio/showError.wav");
           showErrorAlert("Conversion Failed", "Transaction Unsuccessful",
                   "The amount of coins exceeds your current balance.");
       }else{
           addMusic("src/main/resources/audio/ConvertClick.wav");
           mo.setCoinsAmount(mo.getCoinsAmount()-coinsTemp);
           mo.setCashAmount(mo.getCashAmount()+cashTemp);
          showSuccessAlert("Conversion Successful", "Transaction Completed",
                   "$" + cashTemp + " cash was added to your wallet.");
       }
        smc.showUpdatedAmountAfterMoneyConvert(mo.getCashAmount(), mo.getCoinsAmount());
        System.out.println("GetCashAmount: "+mo.getCashAmount()+" GetCoinsAmount: "+mo.getCoinsAmount());
        System.out.println("Curr Cash: "+cashTemp+" Curr Coins: "+coinsTemp);
    }
    public static void checkCashtoCoins(int cashTemp, int coinsTemp){
        MoneyOperations mo = MoneyOperations.getInstance();
        if(coinsTemp == 0 && cashTemp == 0){
            addMusic("src/main/resources/audio/showError.wav");
            showErrorAlert("Conversion Failed", "Transaction Unsuccessful",
                    "No cash to be converted.");
        }else if(cashTemp>mo.getCashAmount()){
            addMusic("src/main/resources/audio/showError.wav");
            showErrorAlert("Conversion Failed", "Transaction Unsuccessful",
                    "The amount of cash exceeds your current balance.");
        }else{
            addMusic("src/main/resources/audio/ConvertClick.wav");
            mo.setCoinsAmount(mo.getCoinsAmount()+coinsTemp);
            mo.setCashAmount(mo.getCashAmount()-cashTemp);
            showSuccessAlert("Conversion Successful", "Transaction Completed",
                    coinsTemp + " coins was added to your wallet.");
        }
        smc.showUpdatedAmountAfterMoneyConvert(mo.getCashAmount(), mo.getCoinsAmount());
        System.out.println("GetCashAmount: "+mo.getCashAmount()+" GetCoinsAmount: "+mo.getCoinsAmount());
        System.out.println("Curr Cash: "+cashTemp+" Curr Coins: "+coinsTemp);
    }



    public int checkSymbolsAmount(String currSymbolName) {
       int symbolsAmount = 0;
        int finalSymbolsAmount;
       switch(currSymbolName){
           case "REDSEVEN":
               symbolsAmount = getBetAmount()*2;
               finalSymbolsAmount = symbolsAmount;
               Timeline pauseTimeline11 = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
                   addMusic("src/main/resources/audio/ConvertClick.wav");
                   showSuccessAlert("YOU WON", "RED SEVEN JACKPOT!","You've earned: " + finalSymbolsAmount+" Credits");
               }));
               pauseTimeline11.play();
//               addMusic("src/main/resources/audio/ConvertClick.wav");
//               showSuccessAlert("YOU WON", "RED SEVEN JACKPOT!","You've earned: " + symbolsAmount+" Credits");
               break;
           case "MONKEY":
               symbolsAmount = getBetAmount()*3;
               finalSymbolsAmount = symbolsAmount;
               Timeline pauseTimeline = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
                   addMusic("src/main/resources/audio/ConvertClick.wav");
                   showSuccessAlert("Congratulations!", "BIG WIN", "You've earned BET*3 = "+ finalSymbolsAmount +" COINS!");
               }));
               pauseTimeline.play();
               /*addMusic("src/main/resources/audio/ConvertClick.wav");
               showSuccessAlert("Congratulations!", "BIG WIN", "You've earned BET*3 = "+symbolsAmount+" COINS!");*/
               /*showWinningVideo("BIG WIN!",  "src/main/resources/videos/bigwin.mp4", () -> {
                   addMusic("src/main/resources/audio/ConvertClick.wav");
                   showSuccessAlert("Congratulations!", "BIG WIN", "You've earned BET*3 = "+symbolsAmount+" COINS!");
               });*/
               break;
           case "BANANA":
               symbolsAmount = getBetAmount()*5;
               finalSymbolsAmount = symbolsAmount;
               Timeline pauseTimeline2 = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
                   addMusic("src/main/resources/audio/ConvertClick.wav");
                   showSuccessAlert("Congratulations!", "MEGA WIN", "You've earned BET*5 = "+finalSymbolsAmount+" COINS!");
               }));
               pauseTimeline2.play();
//               addMusic("src/main/resources/audio/ConvertClick.wav");
//               showSuccessAlert("Congratulations!", "MEGA WIN", "You've earned BET*5 = "+symbolsAmount+" COINS!");
               /*showWinningVideo("MEGA WIN!",  "src/main/resources/videos/megawin.mp4", () -> {
                   addMusic("src/main/resources/audio/ConvertClick.wav");
                   showSuccessAlert("Congratulations!", "MEGA WIN", "You've earned BET*5 = "+symbolsAmount+" COINS!");
               });*/
               break;
           case "BIRD":
               symbolsAmount = getBetAmount()*10;
               finalSymbolsAmount = symbolsAmount;
               Timeline pauseTimeline3 = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
                   addMusic("src/main/resources/audio/ConvertClick.wav");
                   showSuccessAlert("Congratulations!", "SUPER WIN", "You've earned BET*10 = "+finalSymbolsAmount+" COINS!");
               }));
               pauseTimeline3.play();
//               addMusic("src/main/resources/audio/ConvertClick.wav");
//               showSuccessAlert("Congratulations!", "SUPER WIN", "You've earned BET*10 = "+symbolsAmount+" COINS!");
               /*showWinningVideo("SUPER WIN!",  "src/main/resources/videos/superwin.mp4", () -> {
                   addMusic("src/main/resources/audio/ConvertClick.wav");
                   showSuccessAlert("Congratulations!", "SUPER WIN", "You've earned BET*10 = "+symbolsAmount+" COINS!");
               });*/
               break;
           default:
               addMusic("src/main/resources/audio/showSuccess.mp3");
               symbolsAmount = getBetAmount();
               break;
       }

      // showSuccessAlert("YOU WON", header,"You've earned: " + symbolsAmount+" Credits");
       setBetAmount(0);
       setCoinsAmount(getCoinsAmount()+symbolsAmount);
       return (getCoinsAmount());
    }
}
