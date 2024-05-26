package com.example.casino_finalproject_oop;


import com.example.projjavafxoop2.DashBoardPackage.ClickSelectedEffectThread;
import com.example.projjavafxoop2.DashBoardPackage.DashBoardSample;
import com.example.projjavafxoop2.SqlConnect;
import com.example.projjavafxoop2.WalletClass;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelloApplication{

    private MediaPlayer mediaPlayer;

    public static WalletClass wallet;

    public void refresh(Stage stage, WalletClass wallet) throws IOException {
        try{
            this.wallet = wallet;
           FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("slotmachine-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
            stage.setTitle("Slot Machine!");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

//            String audioFilePath = "src/main/resources/audio/slotmachineBGmusic.wav";
//            Media media = new Media(new File(audioFilePath).toURI().toString());
//            mediaPlayer = new MediaPlayer(media);
//            mediaPlayer.play();
//            // Set the MediaPlayer to loop
//            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(mediaPlayer.getStartTime()));

            ImageView homebutdash = (ImageView) scene.lookup("#homebutdash");

            homebutdash.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    SlotMachineController.backgroundMusicPlayer.stop();
                    UpdatedbWallet(scene,wallet);
                    Thread clickefect = new Thread(new ClickSelectedEffectThread());
                    clickefect.start();
                    DashBoardSample dsh = new DashBoardSample(wallet);
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
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public void UpdatedbWallet(Scene scene,WalletClass ww){
        Label amount = (Label) scene.lookup("#lbl_cashAmount");
        double newbalance = Double.parseDouble(amount.getText().toString());
        String newstring = String.format("%.2f",newbalance);
        double origbal = Double.parseDouble(newstring);

        ww.setBalance(origbal);

        try (Connection connection = SqlConnect.getConnection()) {
            PreparedStatement pstatement = connection.prepareStatement("UPDATE wallet SET balance = ? WHERE walletid = ?");

            int wid = ww.getWalletid();
            double bb = ww.getBalance();
            pstatement.setDouble(1,bb);
            pstatement.setInt(2,wid);


            int resultSet = pstatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}