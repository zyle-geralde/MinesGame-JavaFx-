package com.example.projjavafxoop2.DashBoardPackage;

import com.example.projjavafxoop2.SqlConnect;
import com.example.projjavafxoop2.WalletClass;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DepostWithdrawClass extends WalletButAbstractClass{
    int userid;
    public DepostWithdrawClass(ImageView exitWallet, AnchorPane depositBut, AnchorPane withdrawBut, TextField amountField, TextField accountIdField, Label invalidLabel, AnchorPane viewWallet, WalletClass userwallet, Label amountLabel) {
        super(exitWallet, depositBut, withdrawBut, amountField, accountIdField, invalidLabel, viewWallet, userwallet, amountLabel);
    }

    @Override
    public void onClickfunc(){

    }
    public void onClickfunc(int n,int uid) {
        userid = uid;
        if(n == 0){
            depositClick();
        }
        else{
            withdrawClick();
        }
    }

    public void depositClick(){
        if(AmountField.getText().toString().trim().equals("") || AccountIdField.getText().toString().trim().equals("")){
            InvalidLabel.setOpacity(1);
            InvalidLabel.setText("Invalid Input");
        }
        else{
            try{
                double deposithold = Double.parseDouble(AmountField.getText().toString());
                userwallet.depositAmount(deposithold);
                amountLabel.setText(String.format("%.2f",userwallet.getBalance()));
                updateWalletDb();

                LocalDateTime currentDateTime = LocalDateTime.now();

                // Define the format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

                // Format the current date and time
                String formattedDateTime = currentDateTime.format(formatter);
                appendTransaction("User Deposited +"+AmountField.getText()+" from AccountId:"+AccountIdField.getText()+"---"+formattedDateTime,userid);

                AmountField.setText("");
                AccountIdField.setText("");
                InvalidLabel.setText("");

                InvalidLabel.setOpacity(0);
                ViewWallet.setVisible(false);
                ViewWallet.setOpacity(0);
            }catch (Exception e){
                InvalidLabel.setOpacity(1);
                InvalidLabel.setText("Balance must be a number");
            }
        }
    }
    public void withdrawClick(){
        if(AmountField.getText().toString().trim().equals("") || AccountIdField.getText().toString().trim().equals("")){
            InvalidLabel.setOpacity(1);
            InvalidLabel.setText("Invalid Input");
        }
        else{
            try{
                double withdrawhold = Double.parseDouble(AmountField.getText().toString());
                if(withdrawhold <= userwallet.getBalance()){
                    userwallet.withdrawAmount(withdrawhold);
                    amountLabel.setText(String.format("%.2f",userwallet.getBalance()));
                    updateWalletDb();

                    LocalDateTime currentDateTime = LocalDateTime.now();

                    // Define the format
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

                    // Format the current date and time
                    String formattedDateTime = currentDateTime.format(formatter);
                    appendTransaction("User Withdrawed -"+AmountField.getText()+" to Account Id:"+AccountIdField.getText()+"---"+formattedDateTime,userid);

                    AmountField.setText("");
                    AccountIdField.setText("");
                    InvalidLabel.setText("");

                    InvalidLabel.setOpacity(0);
                    ViewWallet.setVisible(false);
                    ViewWallet.setOpacity(0);
                }
                else{
                    InvalidLabel.setOpacity(1);
                    InvalidLabel.setText("Insufficient Balance");
                }
            }catch (Exception e){
                InvalidLabel.setOpacity(1);
                InvalidLabel.setText("Balance must be a number");
            }

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
