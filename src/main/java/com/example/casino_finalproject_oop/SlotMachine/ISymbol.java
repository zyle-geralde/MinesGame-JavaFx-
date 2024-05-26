package com.example.casino_finalproject_oop.SlotMachine;


import javafx.scene.image.Image;

public interface ISymbol {
    public void setImage(Image image);

    public Image getImage();

    public void setName(String symbolName);
    public String getName();
}
