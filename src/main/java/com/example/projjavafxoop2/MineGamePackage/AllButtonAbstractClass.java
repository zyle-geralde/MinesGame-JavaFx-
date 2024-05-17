package com.example.projjavafxoop2.MineGamePackage;

import com.example.projjavafxoop2.SqlConnect;
import com.example.projjavafxoop2.WalletClass;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class AllButtonAbstractClass {
    Scene scene;

    TextField BombsInput;

    ArrayList<Boolean> isPaneClicked;

    ArrayList<Integer> minesindicator;

    ArrayList<Pane> gridcol;


    public abstract void Clickfunction();


}
