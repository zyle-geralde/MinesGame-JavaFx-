package com.example.projjavafxoop2.DashBoardPackage;

import com.example.projjavafxoop2.WalletClass;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class WalletButAbstractClass {

    public ImageView ExitWallet;
    public AnchorPane DepositBut;
    public AnchorPane WithdrawBut;
    public TextField AmountField;
    public TextField AccountIdField;
    public Label InvalidLabel;

    public AnchorPane ViewWallet;

    public WalletClass userwallet;

    public Label amountLabel;

    public WalletButAbstractClass(ImageView exitWallet, AnchorPane depositBut, AnchorPane withdrawBut, TextField amountField, TextField accountIdField, Label invalidLabel, AnchorPane viewWallet, WalletClass userwallet, Label amountLabel) {
        ExitWallet = exitWallet;
        DepositBut = depositBut;
        WithdrawBut = withdrawBut;
        AmountField = amountField;
        AccountIdField = accountIdField;
        InvalidLabel = invalidLabel;
        ViewWallet = viewWallet;
        this.userwallet = userwallet;
        this.amountLabel = amountLabel;
    }

    public WalletButAbstractClass(ImageView exitWallet, AnchorPane depositBut, AnchorPane withdrawBut, TextField amountField, TextField accountIdField, Label invalidLabel, AnchorPane viewWallet) {
        ExitWallet = exitWallet;
        DepositBut = depositBut;
        WithdrawBut = withdrawBut;
        AmountField = amountField;
        AccountIdField = accountIdField;
        InvalidLabel = invalidLabel;
        ViewWallet = viewWallet;
    }

    public WalletButAbstractClass(AnchorPane ViewWallet){
        this.ViewWallet = ViewWallet;
    }

    public abstract void onClickfunc();
}
