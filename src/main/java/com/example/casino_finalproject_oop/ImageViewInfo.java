package com.example.casino_finalproject_oop;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewInfo {
    private ImageView imageView;
    private String filename;

    public ImageViewInfo(ImageView imageView, String filename) {
        this.imageView = imageView;
        this.filename = filename;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public String getFilename() {
        return filename;
    }

    public void setImage(Image image, String filename) {
        this.imageView = new ImageView(image);
        this.filename = filename;
    }
}