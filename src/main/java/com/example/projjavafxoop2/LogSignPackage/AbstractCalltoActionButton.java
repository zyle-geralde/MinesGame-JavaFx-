package com.example.projjavafxoop2.LogSignPackage;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public abstract class AbstractCalltoActionButton {
    private TextField unametext;
    private TextField passwordtext;
    private Label ErrorShow;
    private MediaPlayer mediaPlayer2;
    private Stage stage;

    private ImageView LogSign;

    public AbstractCalltoActionButton(TextField unametext, TextField passwordtext, Label ErrorShow, MediaPlayer mediaPlayer2, Stage stage){
        this.unametext = unametext;
        this.passwordtext = passwordtext;
        this.ErrorShow = ErrorShow;
        this.mediaPlayer2 = mediaPlayer2;
        this.stage = stage;
    }
    public AbstractCalltoActionButton(TextField unametext, TextField passwordtext, Label ErrorShow){
        this.unametext = unametext;
        this.passwordtext = passwordtext;
        this.ErrorShow = ErrorShow;
        this.mediaPlayer2 = null;
        this.stage = null;
    }

    public AbstractCalltoActionButton(ImageView LogSign){
        this.LogSign = LogSign;
    }


    public void ChangeSizeEnter(){
        LogSign.setFitHeight(88);
        LogSign.setFitWidth(160);
    }

    public void ChangeSizeExit(){
        LogSign.setFitHeight(78);
        LogSign.setFitWidth(147);
    }

    public abstract void validateFields();


    public TextField getUnametext() {
        return unametext;
    }


    public TextField getPasswordtext() {
        return passwordtext;
    }


    public Label getErrorShow() {
        return ErrorShow;
    }


    public MediaPlayer getMediaPlayer2() {
        return mediaPlayer2;
    }


    public Stage getStage() {
        return stage;
    }


}
