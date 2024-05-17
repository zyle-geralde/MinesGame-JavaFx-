package com.example.projjavafxoop2.MineGamePackage;

import com.example.projjavafxoop2.SqlConnect;
import com.example.projjavafxoop2.WalletClass;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class PlayButtonClass extends AllButtonAbstractClass{
    TextField betInput;
    WalletClass userwallet;

    Button playButton;




    public PlayButtonClass(Scene scene, TextField betInput, TextField bombsInput, WalletClass userwallet, Button playButton, ArrayList<Pane> gridcol, ArrayList<Integer> minesindicator, ArrayList<Boolean> isPaneClicked) {
        this.scene = scene;
        this.betInput = betInput;
        BombsInput = bombsInput;
        this.userwallet = userwallet;
        this.playButton = playButton;
        this.gridcol = gridcol;
        this.minesindicator = minesindicator;
        this.isPaneClicked = isPaneClicked;
    }

    @Override
    public void Clickfunction() {
        //Thread for checking textfields
        Label betinv = (Label) scene.lookup("#BetInvalid");
        Label betinv2 = (Label) scene.lookup("#BombsInvalid");
        Label moneylabel = (Label) scene.lookup("#moneylabel");
        Thread thr1 = new Thread(new ValidChecker(betinv,betInput,10,Double.parseDouble(moneylabel.getText().toString())));
        Thread thr2 = new Thread(new ValidChecker(betinv2,BombsInput,1,15));

        try {
            thr1.start();
            thr2.start();
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //check if textfileds have valid inputs
        if(betinv.getOpacity() == 0 && betinv2.getOpacity() == 0){

            String filePathclick2 = "src/main/resources/com/example/projjavafxoop2/mpfiles/letsgosound.mp3";
            Media clickmouse2 = new Media(new File(filePathclick2).toURI().toString());

            MediaPlayer mediaPlayerclick2 = new MediaPlayer(clickmouse2);

            mediaPlayerclick2.play();

            //Showing hidden graphics
            Rectangle rectMinesLeft = (Rectangle) scene.lookup("#rectMinesLeft");
            Label LabelMinesleft =(Label) scene.lookup("#LabelMinesleft");
            Label minesleftnum =(Label) scene.lookup("#minesleftnum");

            minesleftnum.setOpacity(1);
            rectMinesLeft.setOpacity(1);
            LabelMinesleft.setOpacity(1);

            //calculating mines left

            int inp = 16 - (int)Math.floor(Double.parseDouble(BombsInput.getText().toString()));
            minesleftnum.setText(inp+"");

            //calculating new balance
            double newmoney = Double.parseDouble(betInput.getText().toString());
            userwallet.withdrawAmount(newmoney);
            moneylabel.setText(String.format("%.2f",userwallet.getBalance()));
            updateWalletDb();

            //disable necessary textfields and button
            playButton.setDisable(true);
            betInput.setDisable(true);
            BombsInput.setDisable(true);


            //put thread
            //showing the tiles with animation
            double indic = 0.5;
            for(Pane pp : gridcol){

                Timeline timeline = new Timeline();
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(indic), new KeyValue(pp.opacityProperty(), 1))
                );
                timeline.play();
                if(indic == 1){
                    indic+=0.08;
                }
                else{
                    indic+=0.15;
                }
            }

            //creating the array for mines and bombs
            for(int loop = 0;loop<16; loop++){
                minesindicator.add(-1);
            }

            for(int loop = 1; loop<=(int)Math.floor(Double.parseDouble(BombsInput.getText().toString())); loop++){
                Random random = new Random();
                int randomNumber = random.nextInt(15 + 1);
                while(minesindicator.get(randomNumber) != -1){
                    randomNumber = random.nextInt(15 + 1);
                }
                minesindicator.set(randomNumber,1);
            }
            for(int loop = 0; loop<16; loop++){
                if(minesindicator.get(loop) == -1){
                    minesindicator.set(loop,0);
                }
            }

            for(int loop = 0; loop<16; loop++){
                if(loop == 4|| loop==8 || loop == 12){
                    System.out.println("");
                }
                System.out.print(minesindicator.get(loop)+" ");
            }


            for(int loop = 0; loop<16; loop++){
                isPaneClicked.add(false);
            }


        }
        else{
            System.out.println("Bad");
        }

    }
    public void updateWalletDb(){
        try (Connection connection = SqlConnect.getConnection()) {
            PreparedStatement pstatement = connection.prepareStatement("UPDATE wallet SET balance = ? WHERE walletid = ?");

            int wid = userwallet.getWalletid();
            double bb = userwallet.getBalance();
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

