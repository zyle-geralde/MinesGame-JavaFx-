package com.example.projjavafxoop2.LogSignPackage;

import com.example.projjavafxoop2.DashBoardPackage.DashBoardSample;
import com.example.projjavafxoop2.SqlConnect;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogInVal extends AbstractCalltoActionButton{
    public LogInVal(TextField unametext, TextField passwordtext, Label ErrorShow, MediaPlayer mediaPlayer2, Stage stage) {
        super(unametext, passwordtext, ErrorShow,mediaPlayer2,stage);
    }
    public LogInVal(ImageView LogSign){
        super(LogSign);
    }

    @Override
    public void validateFields() {
        TextField unametext = this.getUnametext();
        TextField passwordtext = this.getPasswordtext();
        Label ErrorShow = this.getErrorShow();
        MediaPlayer mediaPlayer2 = this.getMediaPlayer2();
        Stage stage = this.getStage();

        Thread clickthread = new Thread(new ClickSoundThread());
        clickthread.start();

        Thread controlthread = new Thread(new ControllActionThread(unametext,passwordtext,ErrorShow,mediaPlayer2,stage));
        controlthread.start();

    }
    public void ChangeSizeEnter(){
        super.ChangeSizeEnter();
    }

    public void ChangeSizeExit(){
        super.ChangeSizeExit();
    }
}
