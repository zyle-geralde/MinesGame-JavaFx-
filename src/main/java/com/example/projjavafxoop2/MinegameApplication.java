package com.example.projjavafxoop2;

import com.almasb.fxgl.dsl.components.view.TextViewComponent;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Random;

public class MinegameApplication extends Application{

    static double betval = 0;
    static double bombval = 0;

    static int num_of_bombs = 0;

    ArrayList<Pane> gridcol = new ArrayList();
    ArrayList<Integer> minesindicator = new ArrayList<>();

    ArrayList<Boolean> isPaneClicked = new ArrayList();
    Scene initialscene;


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Minegame.fxml"));
        Scene scene = new Scene(root, 1150, 700);
        stage.setTitle("Mine Game");
        stage.setScene(scene);
        stage.show();

        //Designing textfields
        TextField betInput = (TextField) scene.lookup("#BetInp");
        betInput.setStyle("-fx-control-inner-background: rgba(0, 0, 0); -fx-text-fill: white;");

        TextField BombsInput = (TextField) scene.lookup("#BombsInput");
        BombsInput.setStyle("-fx-control-inner-background: rgba(0, 0, 0); -fx-text-fill: white;");

        Button playButton = (Button) scene.lookup("#PlayButton");

        //designing mineboxes
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



        //play button listener
        playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Clicked");
                //Thread for checking textfields
                Label betinv = (Label) scene.lookup("#BetInvalid");
                Label betinv2 = (Label) scene.lookup("#BombsInvalid");
                Label moneylabel = (Label) scene.lookup("#moneylabel");
                Thread thr1 = new Thread(new ValidChecker(betinv,betInput,10,Double.parseDouble(moneylabel.getText().toString())));
                Thread thr2 = new Thread(new ValidChecker(betinv2,BombsInput,1,15));

                try {
                    thr1.start();
                    thr2.start();
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                //check if textfileds have valid inputs
                if(betinv.getOpacity() == 0 && betinv2.getOpacity() == 0){

                    //Showing hidden graphics
                    Rectangle rectMinesLeft = (Rectangle) scene.lookup("#rectMinesLeft");
                    Label LabelMinesleft =(Label) scene.lookup("#LabelMinesleft");
                    Label minesleftnum =(Label) scene.lookup("#minesleftnum");

                    minesleftnum.setOpacity(1);
                    rectMinesLeft.setOpacity(1);
                    LabelMinesleft.setOpacity(1);

                    //calculating mines left

                    int inp = 16 - (int)Math.floor(Double.parseDouble(BombsInput.getText().toString()));
                    minesleftnum.setText(inp+"");

                    //calculating new balance
                    double newmoney = Double.parseDouble(moneylabel.getText().toString())-Double.parseDouble(betInput.getText().toString());
                    moneylabel.setText(String.format("%.1f",newmoney));

                    //disable necessary textfields and button
                    playButton.setDisable(true);
                    betInput.setDisable(true);
                    BombsInput.setDisable(true);


                    //put thread
                    //showing the tiles with animation
                    double indic = 0.5;
                    for(Pane pp : gridcol){

                        Timeline timeline = new Timeline();
                        timeline.getKeyFrames().add(
                                new KeyFrame(Duration.seconds(indic), new KeyValue(pp.opacityProperty(), 1))
                        );
                        timeline.play();
                        if(indic == 1){
                            indic+=0.08;
                        }
                        else{
                            indic+=0.15;
                        }
                    }

                    //creating the array for mines and bombs
                    for(int loop = 0;loop<16; loop++){
                        minesindicator.add(-1);
                    }

                    for(int loop = 1; loop<=(int)Math.floor(Double.parseDouble(BombsInput.getText().toString())); loop++){
                        Random random = new Random();
                        int randomNumber = random.nextInt(15 + 1);
                        while(minesindicator.get(randomNumber) != -1){
                            randomNumber = random.nextInt(15 + 1);
                        }
                        minesindicator.set(randomNumber,1);
                    }
                    for(int loop = 0; loop<16; loop++){
                        if(minesindicator.get(loop) == -1){
                            minesindicator.set(loop,0);
                        }
                    }

                    for(int loop = 0; loop<16; loop++){
                        if(loop == 4|| loop==8 || loop == 12){
                            System.out.println("");
                        }
                        System.out.print(minesindicator.get(loop)+" ");
                    }

                    //set oisClikedPane arraylist to false
                    for(int loop = 0; loop<16; loop++){
                        isPaneClicked.add(false);
                    }


                }
                else{
                    System.out.println("Bad");
                }

            }
        });

        try{
            //adding event listeners for each MineBox
            for(Pane minesPane: gridcol){
                minesPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        try{
                            int indmines = gridcol.indexOf(minesPane);
                            if(isPaneClicked.get(indmines) == true){
                                return;
                            }
                            if(minesindicator.get(indmines) == 0){
                                minesPane.setOpacity(0);
                                Image img = new Image("file:src/main/resources/com/example/projjavafxoop2/diamond.png");
                                ImageView imgview = new ImageView();
                                imgview.setImage(img);
                                imgview.setFitHeight(50);
                                imgview.setFitWidth(50);
                                imgview.setOpacity(0);

                                VBox vbox = new VBox();
                                vbox.getChildren().add(imgview);
                                vbox.setAlignment(Pos.CENTER);
                                vbox.setMinWidth(90);
                                vbox.setMinHeight(90);

                                minesPane.getChildren().add(vbox);
                                minesPane.setStyle("-fx-background-color: rgba(255, 224, 139, 0.5); ");

                                Timeline timeline = new Timeline();
                                timeline.getKeyFrames().add(
                                        new KeyFrame(Duration.seconds(0.2), new KeyValue(minesPane.opacityProperty(), 1))
                                );
                                timeline.play();

                                Timeline timeline2 = new Timeline();
                                timeline2.getKeyFrames().add(
                                        new KeyFrame(Duration.seconds(0.2), new KeyValue(imgview.opacityProperty(), 1))
                                );
                                timeline2.play();

                                Label minesleftnum = (Label) scene.lookup("#minesleftnum");
                                int newnum = Integer.parseInt(minesleftnum.getText().toString()) - 1;
                                minesleftnum.setText(newnum+"");

                                if(minesleftnum.getText().toString().equals("0")){
                                    isPaneClicked.set(indmines,true);

                                    int duration = 1100;
                                    int secme = 1;
                                    for(int inloop = 0; inloop<gridcol.size(); inloop++){
                                        gridcol.get(inloop).setDisable(true);
                                        if(isPaneClicked.get(inloop) == false){
                                            Image imgin;
                                            if(minesindicator.get(inloop) == 0){
                                                imgin = new Image("file:src/main/resources/com/example/projjavafxoop2/diamond.png");
                                            }
                                            else{
                                                imgin = new Image("file:src/main/resources/com/example/projjavafxoop2/bomb.png");
                                            }

                                            Timeline timelinejj = new Timeline();
                                            timelinejj.getKeyFrames().addAll(
                                                    new KeyFrame(Duration.seconds(0.5), new KeyValue(gridcol.get(inloop).opacityProperty(), 0)),
                                                    new KeyFrame(Duration.seconds(0.5), new KeyValue(gridcol.get(inloop).styleProperty(), "-fx-background-color: rgba(50,50,50, 0.3);"))
                                            );
                                            timelinejj.play();


                                            ImageView imgviewin = new ImageView();
                                            imgviewin.setImage(imgin);
                                            imgviewin.setFitHeight(50);
                                            imgviewin.setFitWidth(50);
                                            imgviewin.setOpacity(0);

                                            VBox vboxin = new VBox();
                                            vboxin.getChildren().add(imgviewin);
                                            vboxin.setAlignment(Pos.CENTER);
                                            vboxin.setMinWidth(90);
                                            vboxin.setMinHeight(90);

                                            gridcol.get(inloop).getChildren().add(vboxin);

                                            Timeline timelinein = new Timeline();
                                            timelinein.setDelay(Duration.millis(duration));
                                            timelinein.getKeyFrames().add(
                                                    new KeyFrame(Duration.seconds(1), new KeyValue(gridcol.get(inloop).opacityProperty(), 1))
                                            );
                                            timelinein.play();

                                            Timeline timelinein2 = new Timeline();
                                            timelinein2.setDelay(Duration.millis(duration));
                                            timelinein2.getKeyFrames().add(
                                                    new KeyFrame(Duration.seconds(1), new KeyValue(imgviewin.opacityProperty(), 1))
                                            );
                                            timelinein2.play();
                                            duration+=50;
                                        }
                                    }


                                    Pane Winview = (Pane) scene.lookup("#Winview");
                                    Winview.setOpacity(0);
                                    Winview.setVisible(true);
                                    Timeline timelinejjj = new Timeline();
                                    timelinejjj.setDelay(Duration.millis(2600));
                                    timelinejjj.getKeyFrames().addAll(
                                            new KeyFrame(Duration.seconds(0.7), new KeyValue(Winview.opacityProperty(), 1))
                                    );
                                    timelinejjj.play();

                                    Label totalBalance = (Label) scene.lookup("#totalBalance");
                                    TextField Betinp = (TextField) scene.lookup("#BetInp");
                                    double Betamount = Double.parseDouble(Betinp.getText().toString());
                                    double BombsAmount = Double.parseDouble(BombsInput.getText().toString());


                                    int starter = 9;
                                    totalBalance.setText(String.format("%.2f",Betamount*(BombsAmount+starter)));

                                }
                            }
                            else{
                                minesPane.setOpacity(0);
                                Image img = new Image("file:src/main/resources/com/example/projjavafxoop2/bomb.png");
                                ImageView imgview = new ImageView();
                                imgview.setImage(img);
                                imgview.setFitHeight(50);
                                imgview.setFitWidth(50);


                                VBox vbox = new VBox();
                                vbox.getChildren().add(imgview);
                                vbox.setAlignment(Pos.CENTER);
                                vbox.setMinWidth(90);
                                vbox.setMinHeight(90);
                                imgview.setOpacity(0);

                                minesPane.getChildren().add(vbox);
                                minesPane.setStyle("-fx-background-color: rgba(50,50,50, 0.3);");

                                Timeline timeline = new Timeline();
                                timeline.getKeyFrames().add(
                                        new KeyFrame(Duration.seconds(0.2), new KeyValue(minesPane.opacityProperty(), 1))
                                );
                                timeline.play();

                                Timeline timeline2 = new Timeline();
                                timeline2.getKeyFrames().add(
                                        new KeyFrame(Duration.seconds(0.2), new KeyValue(imgview.opacityProperty(), 1))
                                );
                                timeline2.play();


                                //Lose Pane
                                isPaneClicked.set(indmines,true);
                                int duration = 1100;
                                int secme = 1;
                                for(int inloop = 0; inloop<gridcol.size(); inloop++){
                                    gridcol.get(inloop).setDisable(true);
                                    if(isPaneClicked.get(inloop) == false){
                                        Image imgin;
                                        if(minesindicator.get(inloop) == 0){
                                            imgin = new Image("file:src/main/resources/com/example/projjavafxoop2/diamond.png");
                                            Timeline timelinejj = new Timeline();
                                            timelinejj.getKeyFrames().addAll(
                                                    new KeyFrame(Duration.seconds(0.5), new KeyValue(gridcol.get(inloop).opacityProperty(), 0)),
                                                    new KeyFrame(Duration.seconds(0.5), new KeyValue(gridcol.get(inloop).styleProperty(), "-fx-background-color: rgba(255, 224, 139, 0.5);"))
                                            );
                                            timelinejj.play();
                                        }
                                        else{
                                            imgin = new Image("file:src/main/resources/com/example/projjavafxoop2/bomb.png");
                                            Timeline timelinejj = new Timeline();
                                            timelinejj.getKeyFrames().addAll(
                                                    new KeyFrame(Duration.seconds(0.5), new KeyValue(gridcol.get(inloop).opacityProperty(), 0)),
                                                    new KeyFrame(Duration.seconds(0.5), new KeyValue(gridcol.get(inloop).styleProperty(), "-fx-background-color: rgba(50,50,50, 0.3);"))
                                            );
                                            timelinejj.play();
                                        }



                                        ImageView imgviewin = new ImageView();
                                        imgviewin.setImage(imgin);
                                        imgviewin.setFitHeight(50);
                                        imgviewin.setFitWidth(50);
                                        imgviewin.setOpacity(0);

                                        VBox vboxin = new VBox();
                                        vboxin.getChildren().add(imgviewin);
                                        vboxin.setAlignment(Pos.CENTER);
                                        vboxin.setMinWidth(90);
                                        vboxin.setMinHeight(90);

                                        gridcol.get(inloop).getChildren().add(vboxin);

                                        Timeline timelinein = new Timeline();
                                        timelinein.setDelay(Duration.millis(duration));
                                        timelinein.getKeyFrames().add(
                                                new KeyFrame(Duration.seconds(1), new KeyValue(gridcol.get(inloop).opacityProperty(), 1))
                                        );
                                        timelinein.play();

                                        Timeline timelinein2 = new Timeline();
                                        timelinein2.setDelay(Duration.millis(duration));
                                        timelinein2.getKeyFrames().add(
                                                new KeyFrame(Duration.seconds(1), new KeyValue(imgviewin.opacityProperty(), 1))
                                        );
                                        timelinein2.play();
                                        duration+=50;
                                    }
                                }


                                Pane Winview = (Pane) scene.lookup("#Winview1");
                                Winview.setOpacity(0);
                                Winview.setVisible(true);
                                Timeline timelinejjj = new Timeline();
                                timelinejjj.setDelay(Duration.millis(2900));
                                timelinejjj.getKeyFrames().addAll(
                                        new KeyFrame(Duration.seconds(0.7), new KeyValue(Winview.opacityProperty(), 1))
                                );
                                timelinejjj.play();


                            }
                            isPaneClicked.set(indmines,true);
                        }
                        catch (Exception e){
                            System.out.println("Dont click");
                        }

                    }
                });
            }
        }
        catch (Exception e){
            System.out.println("Dont click");
        }


        Label LabelCheck = (Label) scene.lookup("#LabelCheck");
        LabelCheck.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.close();
                try {
                    new MinegameApplication().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Label LabelCheck1 = (Label) scene.lookup("#LabelCheck1");
        LabelCheck1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.close();
                try {
                    new MinegameApplication().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public static void main(String[] args) {
        launch();
    }
}
