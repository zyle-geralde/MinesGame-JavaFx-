package com.example.casino_finalproject_oop.SlotMachine;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Reel {
    private ArrayList<Symbol> symbol = new ArrayList<Symbol>();

    public ArrayList<Symbol> getSymbol() {
        return symbol;
    }

    public Reel()
    {
        try {
            addSymbol("BELL","/images/bell.png");
            addSymbol("CHERRY", "/images/cherry.png");
            addSymbol("LEMON",  "/images/lemon.png");
            addSymbol("PLUM",  "/images/plum.png");
            addSymbol("REDSEVEN",  "/images/redseven.png");
            addSymbol("WATERMELON",  "/images/watermelon.png");

            addSymbol("MONKEY",  "/images/monkey.png");
            addSymbol("BANANA",  "/images/banana.png");
            addSymbol("BIRD",  "/images/bird.png");

            Collections.shuffle(symbol);
        } catch (Exception e) {
            System.out.println("Error in image");
        }

    }
    private void addSymbol(String name,String imagePath) {
        Symbol symbol = new Symbol();
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        symbol.setImage(image);
        symbol.setName(name);
        this.symbol.add(symbol);
    }
    //When the spin method is invoked it will return the whole instance of a symbol ArrayList
    public ArrayList<Symbol> spin() {
        return symbol;
    }
}
