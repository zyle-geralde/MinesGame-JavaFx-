package com.example.casino_finalproject_oop;

import com.example.casino_finalproject_oop.SlotMachine.*;
import com.example.projjavafxoop2.DashBoardPackage.ClickSelectedEffectThread;
import com.example.projjavafxoop2.DashBoardPackage.DashBoardSample;
import com.example.projjavafxoop2.WalletClass;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.casino_finalproject_oop.SlotMachine.AlertOperations.*;
import static com.example.casino_finalproject_oop.SlotMachine.MoneyOperations.checkCashtoCoins;
import static com.example.casino_finalproject_oop.SlotMachine.MoneyOperations.checkCoinstoCash;

public class SlotMachineController{
    @FXML
    private AnchorPane myContainer;
    public final AtomicBoolean shutdownCalled = new AtomicBoolean(false);
    public final AtomicInteger usageCounter = new AtomicInteger(0);
    private MediaPlayer mediaPlayer;
    @FXML
    public Label lbl_cashAmount;
    @FXML
    public Label lbl_coinsAmount;
    @FXML
    public Label lbl_betAmount;
    @FXML
    public Label lbl_playername;
    @FXML
    public Button btn_betPlus;
    @FXML
    public Button btn_betMinus;
    @FXML
    public Button btn_betMax;
    @FXML
    public Button btn_music;

    @FXML
    public Button btn_spin;
    @FXML
    public Button moneyIcon;
    @FXML
    public Button coinsIcon;
    @FXML
    public Button btn_qMark;
    @FXML
    public ImageView reel1;
    @FXML
    public ImageView reel2;
    @FXML
    public ImageView reel3;
    @FXML
    public ImageView img_spinIcon;
    @FXML
    public ImageView img_betMaxIcon;
    @FXML
    public ImageView img_addIcon;
    @FXML
    public ImageView img_subIcon;
    @FXML
    public ImageView img_qMarkIcon;
    @FXML
    public ImageView img_musicIcon;
    @FXML
    public ImageView img_coinsIcon;
    @FXML
    public ImageView img_moneyIcon;
    @FXML
    public HBox slotMachine;
    private int index;

    public String currSymbolNameReel1, currSymbolNameReel2, currSymbolNameReel3;
    private ThreadsHandler reelThread1, reelThread2, reelThread3;

    private Reel[] reel = new Reel[3];

    //Returns the appropriate reel according to its No, and spins it
    public ArrayList<Symbol> getReel(int reelNo){
        return reel[reelNo].spin();
    }
    //a reel array which will hold 3 reels
    private ArrayList<ArrayList<Symbol>> reelImages;
    public MoneyOperations moneyOp;
    public static MediaPlayer backgroundMusicPlayer;

    public static WalletClass userwallet = HelloApplication.wallet;


    @FXML
    public void initialize(){
        moneyOp = MoneyOperations.getInstance();
        moneyOp.setSlotMachineController(this);

        lbl_cashAmount.setText((int)Math.floor(userwallet.getBalance())+"");
        lbl_coinsAmount.setText("0");
        //initializeIntroVideos();
        BackgroundImage myBI = new BackgroundImage(
                new Image(getClass().getResource("/images/slotmachine_bg.png").toExternalForm(),
                        1200, 700, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        myContainer.setBackground(new Background(myBI));

        moneyOp.setCashAmount(Integer.parseInt(lbl_cashAmount.getText()));
        moneyOp.setCoinsAmount(Integer.parseInt(lbl_coinsAmount.getText()));
        moneyOp.setBetAmount(Integer.parseInt(lbl_betAmount.getText()));


        //Initialize reels
        for(int x = 0 ; x < 3 ; x++) {
            reel[x] = new Reel();
        }
        //adding the 3 reels array (which each contains 7 symbols) in our reelImages array
        reelImages = new ArrayList<ArrayList<Symbol>>();
        for (int x = 0; x < 3; x++) {
            reelImages.add(getReel(x));
        }
        //set the random preview of images everytime we open the game
        //random images in each reel handled inside the Reel.java using Collections.shuffle
        reel1.setImage(reelImages.get(0).get(index).getImage());
        reel2.setImage(reelImages.get(1).get(index).getImage());
        reel3.setImage(reelImages.get(2).get(index).getImage());

        spinBtnVisibility();
        String backgroundMusicPath = "src/main/resources/audio/slotmachineBGmusic.wav";
        backgroundMusicPlayer = createMediaPlayer(backgroundMusicPath);
        adjustMusicVolume(0.8);
        backgroundMusicPlayer.play();
    }

    /*private void initializeIntroVideos(){
        Media media1 = new Media(getClass().getResource("/videos/intro_vid.mp4").toString());
        MediaPlayer mediaPlayer1;
        MediaPlayer mediaPlayer2;
        MediaView mediaView1;
        MediaView mediaView2;
        mediaPlayer1 = new MediaPlayer(media1);
        mediaView1 = new MediaView(mediaPlayer1);

        Media media2 = new Media(getClass().getResource("/videos/intro_start.mp4").toString());
        mediaPlayer2 = new MediaPlayer(media2);
        mediaView2 = new MediaView(mediaPlayer2);

        myContainer.getChildren().add(mediaView1);
        mediaView1.setFitWidth(1200);
        mediaView1.setFitHeight(700);

        mediaPlayer1.setOnReady(() -> {
            mediaPlayer1.play();
        });

        mediaPlayer1.setOnEndOfMedia(() -> {
            myContainer.getChildren().remove(mediaView1);
            myContainer.getChildren().add(mediaView2);
            mediaView2.setFitWidth(1200);
            mediaView2.setFitHeight(700);
            mediaPlayer2.play();
        });
        mediaView2.setOnMouseClicked(event -> {
            addMusic("src/main/resources/audio/ConvertClick.wav");
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.2), mediaView2);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(e -> {
                myContainer.getChildren().remove(mediaView2); // Remove the second video
            });
            fadeOut.play();
            adjustMusicVolume(0.4);
        });
    }*/


    //Buttons functionality (Coins and Bet only)

    private void openMoneyConversionStage(String imageUrl, String coinsOrCash) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("moneyConversion-view.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setScene(new Scene(fxmlLoader.load(), 350, 500));
            stage.setTitle("Money Conversion");
            stage.initModality(Modality.APPLICATION_MODAL); // block interactions with other windows
            // Set up scale transition
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.25), stage.getScene().getRoot());
            scaleTransition.setFromX(0.7);
            scaleTransition.setFromY(0.7);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);

            // Set up fade-in transition
            stage.getScene().getRoot().setOpacity(0.9); // Start with opacity 0
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.25), stage.getScene().getRoot());
            fadeTransition.setToValue(1.0);

            // Sequentially play scale and fade transitions
            scaleTransition.setOnFinished(event -> fadeTransition.play());
            scaleTransition.play();


            stage.show();

            BackgroundImage myBI = new BackgroundImage(
                    new Image(getClass().getResource(imageUrl).toExternalForm(),
                            350, 500, false, true),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            BackgroundModel.getInstance().setBackgroundImage(myBI);

            if(coinsOrCash.equals("coins")){
                MoneyController.coinsOrCashPage = "coins";
            }else{
                MoneyController.coinsOrCashPage = "cash";
            }

        } catch (Exception e) {
            System.out.println("Can't load new window");
        }
    }
    @FXML
    public void clickAnimate(ImageView img_icon) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), img_icon);
        scaleTransition.setToX(0.9);
        scaleTransition.setToY(0.9);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);
        scaleTransition.play();
    }
    @FXML
    public void addMusic(String fileMusic) {
        String audioFile = fileMusic;
        Media sound = new Media(new File(audioFile).toURI().toString());

        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        });
    }

    @FXML
    public void coinsIconBtnPressed(){
        addMusic("src/main/resources/audio/clickMouse.mp3");
        clickAnimate(img_coinsIcon);
        openMoneyConversionStage("/images/coinsIconPage.png", "coins");
    }
    @FXML
    public void moneyIconBtnPressed(){
        addMusic("src/main/resources/audio/clickMouse.mp3");
        clickAnimate(img_moneyIcon);
        openMoneyConversionStage("/images/cashIconPage.png", "cash");
    }

    @FXML
    public void qMarkBtnPressed(){
        addMusic("src/main/resources/audio/clickMouse.mp3");
        clickAnimate(img_qMarkIcon);
        showPayoutTable();
    }
    @FXML
    public void musicBtnPressed(){
        addMusic("src/main/resources/audio/clickMouse.mp3");
        clickAnimate(img_musicIcon);
        showMusicVolumeScreen();

    }
    private void showMusicVolumeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MusicVolumeScreen.fxml"));
            AnchorPane root = loader.load();

            MusicVolumeScreenController controller = loader.getController();
            controller.setRecentVolume(backgroundMusicPlayer.getVolume()); // Set recent volume

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Music Volume");
            stage.setOnHidden(event -> {
                double recentVolume = controller.getRecentVolume();
                adjustMusicVolume(recentVolume); // Apply recent volume
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private MediaPlayer createMediaPlayer(String audioFilePath) {
        try {
            Media backgroundMusic = new Media(new File(audioFilePath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(backgroundMusic);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop indefinitely
            return mediaPlayer;
        } catch (Exception e) {
            System.err.println("Error loading background music: " + e.getMessage());
            return null;
        }
    }

    public void adjustMusicVolume(double volume) {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setVolume(volume);
        }
    }

    @FXML
    public void betPlusBtnPressed(){
        clickAnimate(img_addIcon);
        if(moneyOp.getCoinsAmount()>0){
            addMusic("src/main/resources/audio/betCoins.wav");
            moneyOp.setBetAmount(moneyOp.getBetAmount()+1);
            moneyOp.setCoinsAmount(moneyOp.getCoinsAmount()-1);
            lbl_betAmount.setText(String.valueOf(moneyOp.getBetAmount()));
            lbl_coinsAmount.setText(String.valueOf(moneyOp.getCoinsAmount()));
            spinBtnVisibility();
        }else{
            addMusic("src/main/resources/audio/showError.wav");
            showErrorAlert("BET INCREMENT ERROR", "Increment Operation Failed", "You have no coins left to increase the bet.");
        }
    }
    @FXML
    public void betMinusBtnPressed(){
        clickAnimate(img_subIcon);
        if(moneyOp.getBetAmount()>0){
            addMusic("src/main/resources/audio/betCoins.wav");
            moneyOp.setBetAmount(moneyOp.getBetAmount()-1);
            moneyOp.setCoinsAmount(moneyOp.getCoinsAmount()+1);
            lbl_betAmount.setText(String.valueOf(moneyOp.getBetAmount()));
            lbl_coinsAmount.setText(String.valueOf(moneyOp.getCoinsAmount()));
            spinBtnVisibility();
        }else{
            addMusic("src/main/resources/audio/showError.wav");
            showErrorAlert("BET DECREMENT ERROR", "Decrement Operation Failed", "The bet amount must be at least 1 coin.");
        }
    }
    @FXML
    public void betMaxBtnPressed(){
        clickAnimate(img_betMaxIcon);
        if(moneyOp.getCoinsAmount()>=100){
            addMusic("src/main/resources/audio/betCoins.wav");
            moneyOp.setBetAmount(moneyOp.getBetAmount()+100);
            moneyOp.setCoinsAmount(moneyOp.getCoinsAmount()-100);
            lbl_betAmount.setText(String.valueOf(moneyOp.getBetAmount()));
            lbl_coinsAmount.setText(String.valueOf(moneyOp.getCoinsAmount()));
            spinBtnVisibility();
        }else{
            addMusic("src/main/resources/audio/showError.wav");
            showErrorAlert("BET MAX ERROR", "Cannot do BetMax Operation", "Not enough Coins");
        }
    }

    /*public void resetBtnPressed(){
        moneyOp.setCoinsAmount(moneyOp.getCoinsAmount()+ moneyOp.getBetAmount());
        moneyOp.setBetAmount(0);
        lbl_coinsAmount.setText(String.valueOf(moneyOp.getCoinsAmount()));
        lbl_betAmount.setText(String.valueOf(moneyOp.getBetAmount()));
        spinBtnVisibility();
    }*/

    public void spinBtnVisibility(){
        if(moneyOp.getBetAmount() == 0){
            btn_spin.setDisable(true);
           // btn_stop.setDisable(true);
        } else {
            btn_spin.setDisable(false);
        }
    }
    @FXML
    public void spinBtnPressed(){
        if(lbl_betAmount.getText().toString().equals("0")){
            return;
        }
        if(!btn_spin.isDisabled()) {
            addMusic("src/main/resources/audio/slotReels.wav");
            clickAnimate(img_spinIcon);
            turnOffOtherButtonVisibility();
            btn_spin.setDisable(true); //!important
            startReelThreads();
            System.out.println("spinBtn is pressed");
        }else{
            addMusic("src/main/resources/audio/showError.wav");
        }

    }

    public void stopBtnPressed() {
        turnOnOtherButtonVisibility();
        stopReelThreads();
        calculateResult();
        spinBtnVisibility(); //if we have still betAmount, spinBtn is visible
        usageCounter.incrementAndGet();
        System.out.println(usageCounter);
    }
    private void startReelThreads(){
        reelThread1 = new ThreadsHandler(this, getReel(0), 0 );
        reelThread2 = new ThreadsHandler(this, getReel(1), 1 );
        reelThread3 = new ThreadsHandler(this, getReel(2), 2);

        // Start the threads
        reelThread1.start();
        reelThread2.start();
        reelThread3.start();
    }
    private void stopReelThreads(){
        // Stop existing threads if they are running
       if (reelThread1 != null && reelThread1.isAlive()) {
            reelThread1.stopThread();
        }
        if (reelThread2 != null && reelThread2.isAlive()) {
            reelThread2.stopThread();
        }
        if (reelThread3 != null && reelThread3.isAlive()) {
            reelThread3.stopThread();
        }
    }

    public void turnOffOtherButtonVisibility(){
        btn_betPlus.setDisable(true);
        btn_betMinus.setDisable(true);
        btn_betMax.setDisable(true);
       // btn_reset.setDisable(true);
    }
    public void turnOnOtherButtonVisibility(){
        btn_betPlus.setDisable(false);
        btn_betMinus.setDisable(false);
        btn_betMax.setDisable(false);
       // btn_reset.setDisable(false);
    }
    public void calculateResult() {
        if (currSymbolNameReel1.equals(currSymbolNameReel2) && currSymbolNameReel2.equals(currSymbolNameReel3) && currSymbolNameReel1.equals(currSymbolNameReel3)) {
            int latestCoinAmount = moneyOp.checkSymbolsAmount(currSymbolNameReel1);
            lbl_coinsAmount.setText(String.valueOf(latestCoinAmount));
            lbl_betAmount.setText(String.valueOf(moneyOp.getBetAmount()));
        } else if ((currSymbolNameReel1.equals(currSymbolNameReel2)) || (currSymbolNameReel2.equals(currSymbolNameReel3)) || (currSymbolNameReel1.equals(currSymbolNameReel3))) {
            Timeline pauseTimeline = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
                addMusic("src/main/resources/audio/freeSpin.wav");
                showSuccessAlert("FREE SPIN AWARDED", "Congratulations!", "You've earned a free spin!");
            }));
            pauseTimeline.play();
        } else {
            Timeline pauseTimeline = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
                addMusic("src/main/resources/audio/betLose.wav");
                showErrorAlert("BET LOST", "Better Luck Next Time", "Place a new bet and try again!");
                moneyOp.setBetAmount(0);
                lbl_betAmount.setText(String.valueOf(moneyOp.getBetAmount()));
            }));
            pauseTimeline.play();
        }
        System.out.println("Symbol 1: "+currSymbolNameReel1);
        System.out.println("Symbol 2: "+currSymbolNameReel2);
        System.out.println("Symbol 3: "+currSymbolNameReel3);
    }
    public void showUpdatedAmountAfterMoneyConvert(int cash, int coins){
        lbl_cashAmount.setText(String.valueOf(cash));
        lbl_coinsAmount.setText(String.valueOf(coins));
    }

}


