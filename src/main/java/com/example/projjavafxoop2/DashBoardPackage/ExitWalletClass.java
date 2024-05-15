package com.example.projjavafxoop2.DashBoardPackage;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ExitWalletClass extends WalletButAbstractClass{


    public ExitWalletClass(ImageView exitWallet, AnchorPane depositBut, AnchorPane withdrawBut, TextField amountField, TextField accountIdField, Label invalidLabel, AnchorPane viewWallet) {
        super(exitWallet, depositBut, withdrawBut, amountField, accountIdField, invalidLabel, viewWallet);
    }

    @Override
    public void onClickfunc() {
        AmountField.setText("");
        AccountIdField.setText("");
        InvalidLabel.setText("");
        ViewWallet.setVisible(false);
        ViewWallet.setOpacity(0);
    }
}
