package com.example.projjavafxoop2.LogSignPackage;

import com.example.projjavafxoop2.DashBoardPackage.DashBoardSample;
import com.example.projjavafxoop2.SqlConnect;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ControllActionThread implements Runnable{
    private TextField unametext;
    private TextField passwordtext;
    private Label ErrorShow;
    private MediaPlayer mediaPlayer2;
    private Stage stage;


    public ControllActionThread(TextField unametext, TextField passwordtext, Label ErrorShow, MediaPlayer mediaPlayer2, Stage stage){
        this.unametext = unametext;
        this.passwordtext = passwordtext;
        this.ErrorShow = ErrorShow;
        this.mediaPlayer2 = mediaPlayer2;
        this.stage = stage;
    }
    public ControllActionThread(TextField unametext, TextField passwordtext, Label ErrorShow){
        this.unametext = unametext;
        this.passwordtext = passwordtext;
        this.ErrorShow = ErrorShow;
        this.mediaPlayer2 = null;
        this.stage = null;
    }
    @Override
    public void run() {
        if(stage == null){
            signfunc();
        }
        else{
            logfunc();
        }

    }

    public void logfunc(){
        if(unametext.getText().toString().trim().equals("") || passwordtext.getText().toString().equals("")){
            Platform.runLater(() -> {
                ErrorShow.setOpacity(1);
                ErrorShow.setText("Invalid Input");
            });
        }
        else{
            try (Connection connection = SqlConnect.getConnection();
                 Statement statement = connection.createStatement()) {

                String selectQuery = "SELECT * FROM user";
                ResultSet resultSet = statement.executeQuery(selectQuery);

                while (resultSet.next()) {
                    String usernameSet = resultSet.getString("Username");
                    String passwordSet = resultSet.getString("Password");
                    int userid = resultSet.getInt("userId");

                    if(unametext.getText().toString().equals(usernameSet) && passwordtext.getText().toString().equals(passwordSet)){
                        mediaPlayer2.stop();

                        PreparedStatement walletquery = connection.prepareStatement("SELECT * FROM wallet WHERE userid = ?");
                        walletquery.setInt(1,userid);
                        int walletid = -1;
                        double balance = 0;

                        ResultSet resset = walletquery.executeQuery();
                        while (resset.next()){
                            int idd = resset.getInt("walletid");
                            balance = resset.getDouble("balance");
                            walletid = idd;
                        }


                        int finalWalletid = walletid;
                        double finalBalance = balance;
                        Platform.runLater(() -> {
                            DashBoardSample dsh = new DashBoardSample(userid,usernameSet, finalWalletid, finalBalance);
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
                        });

                        Platform.runLater(() -> {
                            unametext.setText("");
                            passwordtext.setText("");

                            ErrorShow.setOpacity(0);
                        });

                        LocalDateTime currentDateTime = LocalDateTime.now();

                        // Define the format
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

                        // Format the current date and time
                        String formattedDateTime = currentDateTime.format(formatter);
                        appendTransaction("User Logged In "+formattedDateTime,userid);

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void signfunc(){
        if(unametext.getText().toString().trim().equals("") || passwordtext.getText().toString().trim().equals("")){
            Platform.runLater(() -> {
                ErrorShow.setOpacity(1);
                ErrorShow.setText("Invalid Input");
            });
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
                        Platform.runLater(() -> {
                            ErrorShow.setOpacity(1);
                            ErrorShow.setText("Username already exists");
                        });
                        indic = 1;
                    }
                }

                if(indic == 0){
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (Username, Password) VALUES (?, ?)",PreparedStatement.RETURN_GENERATED_KEYS);

                    String name = unametext.getText().trim();
                    String password = passwordtext.getText().trim();

                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, password);

                    int rowsInserted = preparedStatement.executeUpdate();

                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO wallet (userid) VALUES (?)");
                        preparedStatement2.setInt(1,generatedId);
                        int rowme = preparedStatement2.executeUpdate();

                    }

                    Platform.runLater(() -> {
                        unametext.setText("");
                        passwordtext.setText("");

                        ErrorShow.setOpacity(0);
                    });

                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void appendTransaction(String trans,int uid){
        try (Connection connection = SqlConnect.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO transaction (userid, transString) VALUES (?, ?)");
            preparedStatement.setInt(1, uid);
            preparedStatement.setString(2, trans);

            int rr = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
