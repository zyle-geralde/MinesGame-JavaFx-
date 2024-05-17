package com.example.projjavafxoop2.MineGamePackage;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class SetMineDesignThread implements Runnable{

    Scene scene;
    ArrayList<Pane>gridcol;

    public SetMineDesignThread(Scene scene, ArrayList<Pane> gridcol) {
        this.scene = scene;
        this.gridcol = gridcol;
    }

    @Override
    public void run() {
        Pane MineBox1 = (Pane) scene.lookup("#MineBox1");
        MineBox1.setStyle("-fx-background-color: rgba(255,255,255,0.2);");
        Pane MineBox2 = (Pane) scene.lookup("#MineBox2");
        MineBox2.setStyle("-fx-background-color: rgba(255,255,255,0.2);");
        Pane MineBox3 = (Pane) scene.lookup("#MineBox3");
        MineBox3.setStyle("-fx-background-color: rgba(255,255,255,0.2);");
        Pane MineBox4 = (Pane) scene.lookup("#MineBox4");
        MineBox4.setStyle("-fx-background-color: rgba(255,255,255,0.2);");

        Pane MineBox5 = (Pane) scene.lookup("#MineBox5");
        MineBox5.setStyle("-fx-background-color: rgba(255,255,255,0.2);");
        Pane MineBox6 = (Pane) scene.lookup("#MineBox6");
        MineBox6.setStyle("-fx-background-color: rgba(255,255,255,0.2);");
        Pane MineBox7 = (Pane) scene.lookup("#MineBox7");
        MineBox7.setStyle("-fx-background-color: rgba(255,255,255,0.2);");
        Pane MineBox8 = (Pane) scene.lookup("#MineBox8");
        MineBox8.setStyle("-fx-background-color: rgba(255,255,255,0.2);");

        Pane MineBox9 = (Pane) scene.lookup("#MineBox9");
        MineBox9.setStyle("-fx-background-color: rgba(255,255,255,0.2);");
        Pane MineBox10 = (Pane) scene.lookup("#MineBox10");
        MineBox10.setStyle("-fx-background-color: rgba(255,255,255,0.2);");
        Pane MineBox11 = (Pane) scene.lookup("#MineBox11");
        MineBox11.setStyle("-fx-background-color: rgba(255,255,255,0.2);");
        Pane MineBox12 = (Pane) scene.lookup("#MineBox12");
        MineBox12.setStyle("-fx-background-color: rgba(255,255,255,0.2);");

        Pane MineBox13 = (Pane) scene.lookup("#MineBox13");
        MineBox13.setStyle("-fx-background-color: rgba(255,255,255,0.2);");
        Pane MineBox14 = (Pane) scene.lookup("#MineBox14");
        MineBox14.setStyle("-fx-background-color: rgba(255,255,255,0.2);");
        Pane MineBox15 = (Pane) scene.lookup("#MineBox15");
        MineBox15.setStyle("-fx-background-color: rgba(255,255,255,0.2);");
        Pane MineBox16 = (Pane) scene.lookup("#MineBox16");
        MineBox16.setStyle("-fx-background-color: rgba(255,255,255,0.2);");


        //adding all mineboxes to an arraylist and setting their opacity to 0
        gridcol.add(MineBox1);
        gridcol.add(MineBox2);
        gridcol.add(MineBox3);
        gridcol.add(MineBox4);
        gridcol.add(MineBox5);
        gridcol.add(MineBox6);
        gridcol.add(MineBox7);
        gridcol.add(MineBox8);
        gridcol.add(MineBox9);
        gridcol.add(MineBox10);
        gridcol.add(MineBox11);
        gridcol.add(MineBox12);
        gridcol.add(MineBox13);
        gridcol.add(MineBox14);
        gridcol.add(MineBox15);
        gridcol.add(MineBox16);

        for(Pane pp : gridcol){
            pp.setOpacity(0);
        }
    }
}
