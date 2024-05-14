package com.example.projjavafxoop2;

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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DashBoardSample{

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

    }

}
