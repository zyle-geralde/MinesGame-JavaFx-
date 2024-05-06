package com.example.projjavafxoop2;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidChecker implements Runnable {
    Label invalid;
    TextField field;

    double min;
    double max;
    public ValidChecker(Label invalid, TextField field,double min,double max){
        this.invalid = invalid;
        this.field = field;
        this.min = min;
        this.max = max;
    }
    @Override
    public void run() {
        try {
            Double.parseDouble(field.getText().toString());

            if(Double.parseDouble(field.getText().toString()) < min || Double.parseDouble(field.getText().toString()) > max){
                throw new Exception("Not in range");
            }

            invalid.setOpacity(0);

        } catch (Exception e) {
            invalid.setOpacity(1);
        }
    }
}
