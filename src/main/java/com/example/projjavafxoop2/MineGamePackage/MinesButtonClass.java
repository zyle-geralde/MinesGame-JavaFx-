package com.example.projjavafxoop2.MineGamePackage;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class MinesButtonClass extends AllButtonAbstractClass{

    Pane minesPane;




    public MinesButtonClass(ArrayList<Pane> gridcol, Pane minesPane, ArrayList<Boolean> isPaneClicked, ArrayList<Integer> minesindicator, Scene scene, TextField bombsInput) {
        this.gridcol = gridcol;
        this.minesPane = minesPane;
        this.isPaneClicked = isPaneClicked;
        this.minesindicator = minesindicator;
        this.scene = scene;
        BombsInput = bombsInput;
    }

    @Override
    public void Clickfunction() {
        try{
            int indmines = gridcol.indexOf(minesPane);
            if(isPaneClicked.get(indmines) == true){
                return;
            }
            if(minesindicator.get(indmines) == 0){
                String filePathclick2 = "src/main/resources/com/example/projjavafxoop2/mpfiles/Correctchoice.mp3";
                Media clickmouse2 = new Media(new File(filePathclick2).toURI().toString());

                MediaPlayer mediaPlayerclick2 = new MediaPlayer(clickmouse2);

                mediaPlayerclick2.play();
                minesPane.setOpacity(0);
                Image img = new Image("file:src/main/resources/com/example/projjavafxoop2/images/diamond.png");
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
                                imgin = new Image("file:src/main/resources/com/example/projjavafxoop2/images/diamond.png");
                            }
                            else{
                                imgin = new Image("file:src/main/resources/com/example/projjavafxoop2/images/bomb.png");
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
                String filePathclick2 = "src/main/resources/com/example/projjavafxoop2/mpfiles/Minesgamefailed.mp3";
                Media clickmouse2 = new Media(new File(filePathclick2).toURI().toString());

                MediaPlayer mediaPlayerclick2 = new MediaPlayer(clickmouse2);

                mediaPlayerclick2.play();
                minesPane.setOpacity(0);
                Image img = new Image("file:src/main/resources/com/example/projjavafxoop2/images/bomb.png");
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
                            imgin = new Image("file:src/main/resources/com/example/projjavafxoop2/images/diamond.png");
                            Timeline timelinejj = new Timeline();
                            timelinejj.getKeyFrames().addAll(
                                    new KeyFrame(Duration.seconds(0.5), new KeyValue(gridcol.get(inloop).opacityProperty(), 0)),
                                    new KeyFrame(Duration.seconds(0.5), new KeyValue(gridcol.get(inloop).styleProperty(), "-fx-background-color: rgba(255, 224, 139, 0.5);"))
                            );
                            timelinejj.play();
                        }
                        else{
                            imgin = new Image("file:src/main/resources/com/example/projjavafxoop2/images/bomb.png");
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
}
