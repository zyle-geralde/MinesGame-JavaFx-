package com.example.projjavafxoop2.MineGamePackage;

import com.example.projjavafxoop2.DashBoardPackage.ClickSelectedEffectThread;
import com.example.projjavafxoop2.WalletClass;
import javafx.stage.Stage;

public class PlayAgainBut extends AllButtonAbstractClass{
    Stage stage;
    WalletClass userwallet;

    public PlayAgainBut(Stage stage, WalletClass userwallet) {
        this.stage = stage;
        this.userwallet = userwallet;
    }

    @Override
    public void Clickfunction() {
        Thread clickefect = new Thread(new ClickSelectedEffectThread());
        clickefect.start();
        try {
            new MinegameApplication().refresh(stage,userwallet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
