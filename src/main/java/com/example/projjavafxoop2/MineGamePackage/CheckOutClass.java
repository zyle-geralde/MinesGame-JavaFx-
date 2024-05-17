package com.example.projjavafxoop2.MineGamePackage;

import com.example.projjavafxoop2.DashBoardPackage.ClickSelectedEffectThread;
import com.example.projjavafxoop2.SqlConnect;
import com.example.projjavafxoop2.WalletClass;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CheckOutClass extends AllButtonAbstractClass{
    WalletClass userwallet;
    Label moneylabel;

    Stage stage;



    public CheckOutClass(WalletClass userwallet, Label moneylabel, Stage stage, Scene scene) {
        this.userwallet = userwallet;
        this.moneylabel = moneylabel;
        this.stage = stage;
        this.scene = scene;
    }

    @Override
    public void Clickfunction() {
        Thread clickefect = new Thread(new ClickSelectedEffectThread());
        clickefect.start();
        try {
            Label totalBalance = (Label) scene.lookup("#totalBalance");
            userwallet.depositAmount(Double.parseDouble(totalBalance.getText().toString()));
            moneylabel.setText(String.format("%.2f",userwallet.getBalance()));
            updateWalletDb();
            new MinegameApplication().refresh(stage,userwallet);
        } catch (Exception e) {
            e.printStackTrace();
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
