package com.example.casino_finalproject_oop.SlotMachine;


import javafx.scene.image.Image;

public class Symbol implements ISymbol{
    private Image image;
    private int value;
    private String name;

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }


}
