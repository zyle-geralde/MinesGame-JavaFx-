package com.example.projjavafxoop2.LogSignPackage;

import com.example.projjavafxoop2.SqlConnect;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.sql.*;

public class SignUpVal extends AbstractCalltoActionButton{
    public SignUpVal(TextField unametext, TextField passwordtext, Label ErrorShow) {
        super(unametext, passwordtext, ErrorShow);
    }
    public SignUpVal(ImageView LogSign){
        super(LogSign);
    }

    @Override
    public void validateFields() {
        TextField unametext = this.getUnametext();
        TextField passwordtext = this.getPasswordtext();
        Label ErrorShow = this.getErrorShow();

        Thread clickthread = new Thread(new ClickSoundThread());
        clickthread.start();

        Thread controlthread = new Thread(new ControllActionThread(unametext,passwordtext,ErrorShow));
        controlthread.start();

    }
    public void ChangeSizeEnter(){
        super.ChangeSizeEnter();
    }

    public void ChangeSizeExit(){
        super.ChangeSizeExit();
    }
}
