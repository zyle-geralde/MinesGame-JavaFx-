package com.example.projjavafxoop2;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Time;
import java.util.ArrayList;

public class DashBoardSample{

    ArrayList<Shape> btnsidbar = new ArrayList<>();
    ArrayList<AnchorPane> anchorGameCov = new ArrayList<>();
    ArrayList<ImageView> imgArrow = new ArrayList<>();

    public void refresh(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("DashBoardSample.fxml"));
        Scene scene = new Scene(root, 1150, 700);
        stage.setTitle("DashBoard");
        stage.setScene(scene);
        stage.show();
        //scene.getStylesheets().add(getClass().getResource("bannercss.css").toExternalForm());

        // Create an ImageView
        ImageView imageView = (ImageView) scene.lookup("#Webbanner");
        System.out.println(imageView);
        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(50); // Adjust the arc width as needed for rounded corners
        clip.setArcHeight(50);

        imageView.setClip(clip);


        // Create an ImageView
        ImageView imageView1 = (ImageView) scene.lookup("#game1");
        System.out.println(imageView1);
        Rectangle clip1 = new Rectangle(imageView1.getFitWidth(), imageView1.getFitHeight());
        clip1.setArcWidth(10); // Adjust the arc width as needed for rounded corners
        clip1.setArcHeight(10);

        imageView1.setClip(clip1);


        // Create an ImageView
        ImageView imageView2 = (ImageView) scene.lookup("#game2");
        System.out.println(imageView2);
        Rectangle clip2 = new Rectangle(imageView2.getFitWidth(), imageView2.getFitHeight());
        clip2.setArcWidth(10); // Adjust the arc width as needed for rounded corners
        clip2.setArcHeight(10);

        imageView2.setClip(clip2);


        // Create an ImageView
        ImageView imageView3 = (ImageView) scene.lookup("#game3");
        System.out.println(imageView3);
        Rectangle clip3 = new Rectangle(imageView3.getFitWidth(), imageView3.getFitHeight());
        clip3.setArcWidth(10); // Adjust the arc width as needed for rounded corners
        clip3.setArcHeight(10);

        imageView3.setClip(clip3);


        // Create an ImageView
        ImageView imageView4 = (ImageView) scene.lookup("#game4");
        System.out.println(imageView4);
        Rectangle clip4 = new Rectangle(imageView4.getFitWidth(), imageView4.getFitHeight());
        clip4.setArcWidth(10); // Adjust the arc width as needed for rounded corners
        clip4.setArcHeight(10);

        imageView4.setClip(clip4);


        AnchorPane sideBar = (AnchorPane) scene.lookup("#sideBar");
        Timeline sidebartime= new Timeline();
        sidebartime.setDelay(Duration.seconds(0.2));
        sidebartime.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.3), new KeyValue(sideBar.translateXProperty(),0))
        );
        sidebartime.play();

        AnchorPane moneyBar = (AnchorPane) scene.lookup("#moneyBar");
        Timeline moneyBartime= new Timeline();
        moneyBartime.setDelay(Duration.seconds(0.3));
        moneyBartime.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.3), new KeyValue(moneyBar.translateYProperty(),0))
        );
        moneyBartime.play();

        AnchorPane bannerBar = (AnchorPane) scene.lookup("#bannerBar");
        Timeline bannerBartime= new Timeline();
        bannerBartime.setDelay(Duration.seconds(0.4));
        bannerBartime.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.3), new KeyValue(bannerBar.translateYProperty(),0))
        );
        bannerBartime.play();




        AnchorPane SlotMachBar = (AnchorPane) scene.lookup("#SlotMachBar");

        AnchorPane cardsbar = (AnchorPane) scene.lookup("#cardsbar");

        AnchorPane colorgamebar = (AnchorPane) scene.lookup("#colorgamebar");

        AnchorPane minegamebar = (AnchorPane) scene.lookup("#minegamebar");

        ImageView Point1 = (ImageView) scene.lookup("#Point1");
        ImageView Point2 = (ImageView) scene.lookup("#Point2");
        ImageView Point3 = (ImageView) scene.lookup("#Point3");
        ImageView Point4 = (ImageView) scene.lookup("#Point4");

        imgArrow.add(Point1);
        imgArrow.add(Point2);
        imgArrow.add(Point3);
        imgArrow.add(Point4);


        anchorGameCov.add(SlotMachBar);
        anchorGameCov.add(cardsbar);
        anchorGameCov.add(colorgamebar);
        anchorGameCov.add(minegamebar);

        double durdelay = 0.9;

        for(AnchorPane anch: anchorGameCov){
            Timeline gamebarTime= new Timeline();
            gamebarTime.setDelay(Duration.seconds(durdelay));
            gamebarTime.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(0.1), new KeyValue(anch.translateYProperty(),0))
            );
            gamebarTime.play();
            durdelay+=0.1;

            anch.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int indx = anchorGameCov.indexOf(anch);
                    Timeline gamebarTime= new Timeline();
                    gamebarTime.getKeyFrames().add(
                            new KeyFrame(Duration.seconds(0.1), new KeyValue(imgArrow.get(indx).opacityProperty(),1))
                    );
                    gamebarTime.play();

                }
            });

            anch.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int indx = anchorGameCov.indexOf(anch);
                    Timeline gamebarTime= new Timeline();
                    gamebarTime.getKeyFrames().add(
                            new KeyFrame(Duration.seconds(0.1), new KeyValue(imgArrow.get(indx).opacityProperty(),0))
                    );
                    gamebarTime.play();
                }
            });
        }




        btnsidbar.add((Shape) scene.lookup("#Rect1"));
        btnsidbar.add((Shape) scene.lookup("#Rect2"));
        btnsidbar.add((Shape) scene.lookup("#Rect3"));
        btnsidbar.add((Shape) scene.lookup("#Rect4"));
        btnsidbar.add((Shape) scene.lookup("#Rect5"));

        for(Shape btnsb: btnsidbar){
            btnsb.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Timeline Timebar= new Timeline();
                    Timebar.getKeyFrames().add(
                            new KeyFrame(Duration.seconds(0.1), new KeyValue(btnsb.opacityProperty(),1))
                    );
                    Timebar.play();
                }
            });

            btnsb.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Timeline Timebar= new Timeline();
                    Timebar.getKeyFrames().add(
                            new KeyFrame(Duration.seconds(0.1), new KeyValue(btnsb.opacityProperty(),0))
                    );
                    Timebar.play();
                }
            });
        }
    }

}
