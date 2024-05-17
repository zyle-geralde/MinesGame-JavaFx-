package com.example.projjavafxoop2.LogSignPackage;

import com.example.projjavafxoop2.DashBoardPackage.DashBoardSample;
import com.example.projjavafxoop2.SqlConnect;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class LogInSignUpApplication extends Application {

    public MediaPlayer mediaPlayer2 = null;

    public static Stage AppInstance;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/projjavafxoop2/LogInSignUpPage.fxml"));
        Scene scene = new Scene(root, 1150, 700);
        stage.setTitle("Casino Game");

        TextField unametext = (TextField) scene.lookup("#unametext");
        PasswordField passwordtext = (PasswordField) scene.lookup("#passwordtext");
        Label ErrorShow = (Label) scene.lookup("#ErrorShow");

        MediaView mediaView = (MediaView) scene.lookup("#IntroPlay");

        String mediaSource = getClass().getResource("/com/example/projjavafxoop2/mpfiles/IntroSec.mp4").toString();

        Media media = new Media(mediaSource);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaPlayAbstractClass introvid = new IntroMedia(mediaView,mediaPlayer);
        introvid.CreateMedia();

        MediaView BackView = (MediaView) scene.lookup("#BackCoverVid");
        BackView.setVisible(false);

        mediaPlayer.setOnEndOfMedia(() -> {
            Label skipvid = (Label) scene.lookup("#skipvid");
            skipvid.setOpacity(0);
            mediaView.setOpacity(0);
            mediaView.setVisible(false);

            if (mediaPlayer2 == null) {
                String mediaSourceview = getClass().getResource("/com/example/projjavafxoop2/mpfiles/BackCover.mp4").toString();
                Media mediaView2 = new Media(mediaSourceview);
                mediaPlayer2 = new MediaPlayer(mediaView2);

                MediaPlayAbstractClass backvid = new BackMedia(BackView,mediaPlayer2);
                backvid.CreateMedia();
            } else {
                mediaPlayer2.play();
            }

        });

        ImageView LogInBut = (ImageView) scene.lookup("#LogInBut");
        ImageView SignUpBut = (ImageView) scene.lookup("#SignUpBut");
        AbstractCalltoActionButton LogChangeSizes = new LogInVal(LogInBut);
        AbstractCalltoActionButton SignChangeSizes = new SignUpVal(SignUpBut);


        Label skipvid = (Label) scene.lookup("#skipvid");
        skipvid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                skipvid.setOpacity(0);
                mediaPlayer.stop(); // Stop the first video
                mediaView.setOpacity(0);
                mediaView.setVisible(false);
                String mediaSourceview = getClass().getResource("/com/example/projjavafxoop2/mpfiles/BackCover.mp4").toString();
                Media mediaView2 = new Media(mediaSourceview);
                mediaPlayer2 = new MediaPlayer(mediaView2);

                MediaPlayAbstractClass backvid = new BackMedia(BackView,mediaPlayer2);
                backvid.CreateMedia();
            }
        });
        LogInBut.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                LogChangeSizes.ChangeSizeEnter();
            }
        });

        LogInBut.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                LogChangeSizes.ChangeSizeExit();
            }
        });

        LogInBut.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AbstractCalltoActionButton LogInfunc = new LogInVal(unametext,passwordtext,ErrorShow,mediaPlayer2,stage);
                LogInfunc.validateFields();
            }
        });


        SignUpBut.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SignChangeSizes.ChangeSizeEnter();
            }
        });

        SignUpBut.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SignChangeSizes.ChangeSizeExit();
            }
        });

        SignUpBut.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AbstractCalltoActionButton SignUpfunc = new SignUpVal(unametext,passwordtext,ErrorShow);
                SignUpfunc.validateFields();
            }
        });

        stage.setScene(scene);
        stage.show();
        AppInstance = stage;


    }
    public static void main(String[] args) {

        launch(args);
    }

}
