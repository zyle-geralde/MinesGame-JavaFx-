package com.example.projjavafxoop2;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LogInSignUpPage.fxml"));
        Scene scene = new Scene(root, 1150, 700);
        stage.setTitle("Casino Game");

        TextField unametext = (TextField) scene.lookup("#unametext");
        PasswordField passwordtext = (PasswordField) scene.lookup("#passwordtext");
        Label ErrorShow = (Label) scene.lookup("#ErrorShow");

        MediaView mediaView = (MediaView) scene.lookup("#IntroPlay");

        String mediaSource = getClass().getResource("/com/example/projjavafxoop2/IntroVidNew.mp4").toString();

        Media media = new Media(mediaSource);
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.play();

        MediaView BackView = (MediaView) scene.lookup("#BackCoverVid");
        BackView.setVisible(false);

        mediaPlayer.setOnEndOfMedia(() -> {
            Label skipvid = (Label) scene.lookup("#skipvid");
            skipvid.setOpacity(0);
            //mediaPlayer.stop(); // Stop the first video
            mediaView.setOpacity(0);
            mediaView.setVisible(false);

            if (mediaPlayer2 == null) {
                String mediaSourceview = getClass().getResource("/com/example/projjavafxoop2/BackCover.mp4").toString();
                Media mediaView2 = new Media(mediaSourceview);
                mediaPlayer2 = new MediaPlayer(mediaView2);

                // Link MediaPlayer to MediaView
                BackView.setMediaPlayer(mediaPlayer2);

                // Set up an event handler to make BackView visible after the second video starts playing
                mediaPlayer2.setOnReady(() -> {
                    BackView.setVisible(true);
                    mediaPlayer2.play();
                });

                mediaPlayer2.setOnEndOfMedia(() -> {
                    mediaPlayer2.seek(Duration.ZERO); // Restart video from the beginning
                    mediaPlayer2.play();
                });
            } else {
                // If mediaPlayer2 is already initialized, just play it
                mediaPlayer2.play();
            }

        });


        Label skipvid = (Label) scene.lookup("#skipvid");
        skipvid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                skipvid.setOpacity(0);
                mediaPlayer.stop(); // Stop the first video
                mediaView.setOpacity(0);
                mediaView.setVisible(false);
                String mediaSourceview = getClass().getResource("/com/example/projjavafxoop2/BackCover.mp4").toString();
                Media mediaView2 = new Media(mediaSourceview);
                mediaPlayer2 = new MediaPlayer(mediaView2);

                // Link MediaPlayer to MediaView
                BackView.setMediaPlayer(mediaPlayer2);

                // Set up an event handler to make BackView visible after the second video starts playing
                mediaPlayer2.setOnReady(() -> {
                    BackView.setVisible(true);
                    mediaPlayer2.play();
                });
                mediaPlayer2.setOnEndOfMedia(() -> {
                    mediaPlayer2.seek(Duration.ZERO); // Restart video from the beginning
                    mediaPlayer2.play();
                });
            }
        });

        ImageView LogInBut = (ImageView) scene.lookup("#LogInBut");
        LogInBut.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                LogInBut.setFitHeight(88);
                LogInBut.setFitWidth(160);
            }
        });

        LogInBut.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                LogInBut.setFitHeight(78);
                LogInBut.setFitWidth(147);
            }
        });

        LogInBut.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String filePathclick = "src/main/resources/com/example/projjavafxoop2/clickmouse.mp3";
                Media clickmouse = new Media(new File(filePathclick).toURI().toString());

                MediaPlayer mediaPlayerclick = new MediaPlayer(clickmouse);

                mediaPlayerclick.play();

                if(unametext.getText().toString().trim().equals("") || passwordtext.getText().toString().equals("")){
                    ErrorShow.setOpacity(1);
                    ErrorShow.setText("Invalid Input");
                }
                else{
                    try (Connection connection = SqlConnect.getConnection();
                         Statement statement = connection.createStatement()) {

                        String selectQuery = "SELECT * FROM user";
                        ResultSet resultSet = statement.executeQuery(selectQuery);

                        while (resultSet.next()) {
                            String usernameSet = resultSet.getString("Username");
                            String passwordSet = resultSet.getString("Password");

                            if(unametext.getText().toString().equals(usernameSet) && passwordtext.getText().toString().equals(passwordSet)){
                                mediaPlayer2.stop();
                                Parent root = FXMLLoader.load(getClass().getResource("DashBoardSample.fxml"));
                                Scene scene = new Scene(root, 1150, 700);
                                stage.setTitle("DashBoard");
                                stage.setScene(scene);
                                DashBoardSample dsh = new DashBoardSample();
                                dsh.refresh(stage);


                                unametext.setText("");
                                passwordtext.setText("");

                                ErrorShow.setOpacity(0);


                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }


            }
        });

        ImageView SignUpBut = (ImageView) scene.lookup("#SignUpBut");
        SignUpBut.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                SignUpBut.setFitHeight(88);
                SignUpBut.setFitWidth(160);
            }
        });

        SignUpBut.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                SignUpBut.setFitHeight(78);
                SignUpBut.setFitWidth(147);
            }
        });

        SignUpBut.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String filePathclick = "src/main/resources/com/example/projjavafxoop2/clickmouse.mp3";
                Media clickmouse = new Media(new File(filePathclick).toURI().toString());

                MediaPlayer mediaPlayerclick = new MediaPlayer(clickmouse);

                mediaPlayerclick.play();
                if(unametext.getText().toString().trim().equals("") || passwordtext.getText().toString().trim().equals("")){
                    ErrorShow.setOpacity(1);
                    ErrorShow.setText("Invalid Input");
                }
                else{
                    try (Connection connection = SqlConnect.getConnection();
                         Statement statement = connection.createStatement()) {

                        String selectQuery = "SELECT * FROM user";
                        ResultSet resultSet = statement.executeQuery(selectQuery);

                        int indic = 0;
                        while(resultSet.next()){
                            String username = resultSet.getString("Username");

                            if(unametext.getText().toString().equals(username)){
                                ErrorShow.setOpacity(1);
                                ErrorShow.setText("Username already exists");
                                indic = 1;
                            }
                        }

                        if(indic == 0){
                            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (Username, Password) VALUES (?, ?)");

                            String name = unametext.getText().trim();
                            String password = passwordtext.getText().trim();

                            preparedStatement.setString(1, name);
                            preparedStatement.setString(2, password);

                            int rowsInserted = preparedStatement.executeUpdate();

                            unametext.setText("");
                            passwordtext.setText("");

                            ErrorShow.setOpacity(0);

                            System.out.println("Clicked");


                        }


                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
