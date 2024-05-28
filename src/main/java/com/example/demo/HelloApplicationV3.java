package com.example.demo;

import com.example.projjavafxoop2.DashBoardPackage.ClickSelectedEffectThread;
import com.example.projjavafxoop2.DashBoardPackage.DashBoardSample;
import com.example.projjavafxoop2.SqlConnect;
import com.example.projjavafxoop2.WalletClass;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class HelloApplicationV3 {

    private ArrayList<ImageView> cardList = new ArrayList<>();
    private ArrayList<ImageView> coinList = new ArrayList<>();
    private ArrayList<Button> betButtons = new ArrayList<>();
    private ArrayList<Rectangle> glowCard = new ArrayList<>();
    private TextField[] betFields = new TextField[5]; // Array to store bet fields
    private final int[] userInputBets = new int[5]; // Array to store user bets

    private ArrayList<String> betAmm = new ArrayList<>();
    private ArrayList<Integer> betIndx = new ArrayList<>();

    private ArrayList<ImageView> diamondAlasbut = new ArrayList<>();
    private ArrayList<ImageView> eightclubbut = new ArrayList<>();
    private ArrayList<ImageView> Alasclubbut = new ArrayList<>();
    private ArrayList<ImageView> AlasSpeedbut = new ArrayList<>();
    private ArrayList<ImageView> eightspeedbut = new ArrayList<>();

    Random random = new Random();
    int [] lastIndex = new int[1];

    int userCurrentPoints = 1000;
    Label pointsLabel; // Label to display current points
    AnchorPane pnMain;



    public void refresh(Stage stage, WalletClass wallet) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        userCurrentPoints = (int)Math.floor(wallet.getBalance());
        pnMain = new AnchorPane();
        GridPane grid = new GridPane();

        pnMain.getChildren().add(grid);
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(false); //SET IT TO FALSE IF MANA OVERALL PROJECT!!!!


        grid.getColumnConstraints().add(new ColumnConstraints(38));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(60));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(80));
        grid.getColumnConstraints().add(new ColumnConstraints(60));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(80));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(65));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(37));

        //QUESTION: Ngano di mu show up if ColumnConstraints ra naa pero wala pa RowConstraints?
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(60));

        BackgroundImage myBI = new BackgroundImage(
                new Image(getClass().getResource("finalbg.png").toExternalForm(),
                        1200, 700, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pnMain.setBackground(new Background(myBI));

        StackPane pointsBoardsp = new StackPane();

        Image pointsB = new Image(Objects.requireNonNull(getClass().getResourceAsStream("pointsbg.png")));
        ImageView imagepointsB = new ImageView(pointsB);
        imagepointsB.setFitWidth(350);
        imagepointsB.setFitHeight(120);
        pointsBoardsp.getChildren().add(imagepointsB);


        pointsLabel = new Label("$" + userCurrentPoints);
        pointsLabel.getStyleClass().add("pointsLabel");
        pointsBoardsp.getChildren().add(pointsLabel);

        AnchorPane.setTopAnchor(pointsBoardsp, 10.0);
        AnchorPane.setRightAnchor(pointsBoardsp, 10.0);
        grid.add(pointsBoardsp, 12, 8,6,1);

        ImageView homeme = new ImageView();
        homeme.setImage(new Image("file:src/main/resources/com/example/demo/school.png"));
        homeme.setFitHeight(50);
        homeme.setFitWidth(50);

        homeme.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                UpdatedbWallet(wallet);
                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                DashBoardSample dsh = new DashBoardSample(wallet);
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/projjavafxoop2/DashBoardSample.fxml"));
                    Scene scene = new Scene(root, 1150, 700);
                    stage.setTitle("DashBoard");
                    stage.setScene(scene);
                    stage.show();
                    dsh.refresh(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        grid.add(homeme,1,0);



        //black rectangle background
        Rectangle rectangleBack = new Rectangle();
        rectangleBack.setHeight(590);
        rectangleBack.setWidth(730);
        rectangleBack.setFill(Color.BLACK); // or Color.GRAY for a gray fill
        rectangleBack.setArcWidth(50); // Set arc width for rounded corners
        rectangleBack.setArcHeight(50);
        rectangleBack.setOpacity(0); // Set opacity to 50%
        grid.add(rectangleBack, 4, 4,1 ,2);




        Rectangle rectangle1 = new Rectangle();
        rectangle1.setHeight(210);
        rectangle1.setWidth(150);
        rectangle1.setFill(Color.BLACK);
        rectangle1.getStyleClass().add("rectangle_glow");
        rectangle1.setArcWidth(20); // Set arc width for rounded corners
        rectangle1.setArcHeight(20);
        rectangle1.setOpacity(0);
        grid.add(rectangle1,2,3);
        glowCard.add(rectangle1);

        //Image image = new Image(new FileInputStream("src/main/java/com/example/demo/1.png"));
        Image diamondAce = new Image(Objects.requireNonNull(getClass().getResourceAsStream("1.png")));
        ImageView imageDiaAce = new ImageView(diamondAce);
        imageDiaAce.setFitWidth(150);
        imageDiaAce.setFitHeight(210);
        Rectangle clip = new Rectangle(150, 210);
        clip.setArcWidth(20); // Set arc width for rounded corners
        clip.setArcHeight(20); // Set arc height for rounded corners
        imageDiaAce.setClip(clip);
        //x    //y
        grid.add(imageDiaAce,  2, 3);
        cardList.add(imageDiaAce);

        HBox holdButton1 = new HBox();
        ImageView b1 = new ImageView();
        b1.setImage(new Image("file:src/main/resources/com/example/demo/rec.png"));
        holdButton1.getChildren().add(b1);
        b1.setFitHeight(40);
        b1.setFitWidth(40);
        grid.add(holdButton1,2, 1);

        ImageView b2 = new ImageView();
        b2.setImage(new Image("file:src/main/resources/com/example/demo/rec.png"));
        holdButton1.getChildren().add(b2);
        b2.setFitHeight(40);
        b2.setFitWidth(40);

        ImageView b3 = new ImageView();
        b3.setImage(new Image("file:src/main/resources/com/example/demo/rec.png"));
        holdButton1.getChildren().add(b3);
        b3.setFitHeight(40);
        b3.setFitWidth(40);

        eightclubbut.add(b1);
        eightclubbut.add(b2);
        eightclubbut.add(b3);

        Image coin = new Image(Objects.requireNonNull(getClass().getResourceAsStream("coinCard.png")));
        ImageView imageCoin = new ImageView(coin);
        imageCoin.setFitWidth(100);
        imageCoin.setFitHeight(100);
        grid.add(imageCoin, 2,3,2,1);
        GridPane.setHalignment(imageCoin, HPos.CENTER);
        imageCoin.setOpacity(0);
        coinList.add(imageCoin);

        StackPane betsp1 = new StackPane();

        Image betImg1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("BET.png")));
        ImageView imageBet1 = new ImageView(betImg1);
        imageBet1.setFitWidth(100);
        imageBet1.setFitHeight(47);
        betsp1.getChildren().add(imageBet1);

        Button bet_diamondAce = new Button();
        bet_diamondAce.setText("Bet");
        bet_diamondAce.setOnAction(event -> openBetStage(0));
        bet_diamondAce.setPrefSize(100,44);
        bet_diamondAce.setOpacity(0);
        betsp1.getChildren().add(bet_diamondAce);

        grid.add(betsp1, 2, 5, 2, 1);
        GridPane.setHalignment(betsp1, HPos.CENTER);

        TextField diamondAce_betInput = new TextField();
        diamondAce_betInput.setEditable(false);
        diamondAce_betInput.setAlignment(Pos.CENTER);
        diamondAce_betInput.getStyleClass().add("bet-input");
        grid.add(diamondAce_betInput, 2, 3,2,1);
        GridPane.setHalignment(diamondAce_betInput, HPos.CENTER);
        betFields[0] = diamondAce_betInput;

        Rectangle rectangle2 = new Rectangle();
        rectangle2.setHeight(210);
        rectangle2.setWidth(150);
        rectangle2.setFill(Color.BLACK);
        rectangle2.getStyleClass().add("rectangle_glow");
        rectangle2.setOpacity(0);
        rectangle2.setArcWidth(20); // Set arc width for rounded corners
        rectangle2.setArcHeight(20);
        grid.add(rectangle2,8,3,5,1);
        glowCard.add(rectangle2);


        Image diamondQueen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("2.png")));
        ImageView imageDiaQueen = new ImageView(diamondQueen);
        imageDiaQueen.setFitWidth(150);
        imageDiaQueen.setFitHeight(210);
        Rectangle clip2 = new Rectangle(150, 210);
        clip2.setArcWidth(20); // Set arc width for rounded corners
        clip2.setArcHeight(20); // Set arc height for rounded corners
        imageDiaQueen.setClip(clip2);
        grid.add(imageDiaQueen,  8, 4);
        cardList.add(imageDiaQueen);

        holdButton1 = new HBox();
        b1 = new ImageView();
        b1.setImage(new Image("file:src/main/resources/com/example/demo/rec.png"));
        holdButton1.getChildren().add(b1);
        b1.setFitHeight(40);
        b1.setFitWidth(40);
        grid.add(holdButton1,8, 2);

        b2 = new ImageView();
        b2.setImage(new Image("file:src/main/resources/com/example/demo/rec.png"));
        holdButton1.getChildren().add(b2);
        b2.setFitHeight(40);
        b2.setFitWidth(40);

        b3 = new ImageView();
        b3.setImage(new Image("file:src/main/resources/com/example/demo/rec.png"));
        holdButton1.getChildren().add(b3);
        b3.setFitHeight(40);
        b3.setFitWidth(40);

        diamondAlasbut.add(b1);
        diamondAlasbut.add(b2);
        diamondAlasbut.add(b3);


        StackPane betsp2 = new StackPane();

        Image betImg2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("BET.png")));
        ImageView imageBet2 = new ImageView(betImg2);
        imageBet2.setFitWidth(100);
        imageBet2.setFitHeight(47);
        betsp2.getChildren().add(imageBet2);

        Button bet_diamondQueen = new Button();
        bet_diamondQueen.setText("Bet");
        bet_diamondQueen.setOnAction(event -> openBetStage(1));
        bet_diamondQueen.setPrefSize(100,42);
        bet_diamondQueen.setOpacity(0);
        betsp2.getChildren().add(bet_diamondQueen);

        grid.add(betsp2, 8, 6, 2, 1);
        GridPane.setHalignment(betsp2, HPos.CENTER);

        ImageView imageCoin2 = new ImageView(coin);
        imageCoin2.setFitWidth(100);
        imageCoin2.setFitHeight(100);
        imageCoin2.setOpacity(0);
        grid.add(imageCoin2, 8,3,2,1);
        GridPane.setHalignment(imageCoin2, HPos.CENTER);
        coinList.add(imageCoin2);

        TextField diamondQueen_betInput = new TextField();
        diamondQueen_betInput.setEditable(false);
        diamondQueen_betInput.setAlignment(Pos.CENTER);
        diamondQueen_betInput.getStyleClass().add("bet-input");
        grid.add(diamondQueen_betInput, 8, 3,2,1);
        GridPane.setHalignment(diamondQueen_betInput, HPos.CENTER);
        betFields[1] = diamondQueen_betInput;


        Rectangle rectangle3 = new Rectangle();
        rectangle3.setHeight(210);
        rectangle3.setWidth(150);
        rectangle3.setFill(Color.BLACK);
        rectangle3.getStyleClass().add("rectangle_glow");
        rectangle3.setOpacity(0);
        rectangle3.setArcWidth(20); // Set arc width for rounded corners
        rectangle3.setArcHeight(20);
        grid.add(rectangle3,11,5,5,1);
        glowCard.add(rectangle3);

        Image diamondKing = new Image(Objects.requireNonNull(getClass().getResourceAsStream("3.png")));
        ImageView imageDiaKing = new ImageView(diamondKing);
        imageDiaKing.setFitWidth(150);
        imageDiaKing.setFitHeight(210);
        Rectangle clip3 = new Rectangle(150, 210);
        clip3.setArcWidth(20); // Set arc width for rounded corners
        clip3.setArcHeight(20); // Set arc height for rounded corners
        imageDiaKing.setClip(clip3);
        grid.add(imageDiaKing,  11, 5);
        cardList.add(imageDiaKing);

        holdButton1 = new HBox();
        b1 = new ImageView();
        b1.setImage(new Image("file:src/main/resources/com/example/demo/rec.png"));
        holdButton1.getChildren().add(b1);
        b1.setFitHeight(40);
        b1.setFitWidth(40);
        grid.add(holdButton1,11, 3);

        b2 = new ImageView();
        b2.setImage(new Image("file:src/main/resources/com/example/demo/rec.png"));
        holdButton1.getChildren().add(b2);
        b2.setFitHeight(40);
        b2.setFitWidth(40);

        b3 = new ImageView();
        b3.setImage(new Image("file:src/main/resources/com/example/demo/rec.png"));
        holdButton1.getChildren().add(b3);
        b3.setFitHeight(40);
        b3.setFitWidth(40);

        AlasSpeedbut.add(b1);
        AlasSpeedbut.add(b2);
        AlasSpeedbut.add(b3);

        StackPane betsp3 = new StackPane();

        Image betImg3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("BET.png")));
        ImageView imageBet3 = new ImageView(betImg3);
        imageBet3.setFitWidth(100);
        imageBet3.setFitHeight(47);
        betsp3.getChildren().add(imageBet3);

        Button bet_diamondKing = new Button();
        bet_diamondKing.setText("Bet");
        bet_diamondKing.setOnAction(event -> openBetStage(2));
        bet_diamondKing.setPrefSize(100,45);
        bet_diamondKing.setOpacity(0);
        betsp3.getChildren().add(bet_diamondKing);

        grid.add(betsp3, 11, 7, 2, 1);
        GridPane.setHalignment(betsp3, HPos.CENTER);

        ImageView imageCoin3 = new ImageView(coin);
        imageCoin3.setFitWidth(100);
        imageCoin3.setFitHeight(100);
        imageCoin3.setOpacity(0);
        grid.add(imageCoin3, 11,5,2,1);
        GridPane.setHalignment(imageCoin3, HPos.CENTER);
        coinList.add(imageCoin3);


        TextField diamondKing_betInput = new TextField();
        diamondKing_betInput.setEditable(false);
        diamondKing_betInput.setAlignment(Pos.CENTER);
        diamondKing_betInput.getStyleClass().add("bet-input");
        grid.add(diamondKing_betInput, 11, 5,2,1);
        GridPane.setHalignment(diamondKing_betInput, HPos.CENTER);
        betFields[2] = diamondKing_betInput;

        Rectangle rectangle4 = new Rectangle();
        rectangle4.setHeight(210);
        rectangle4.setWidth(150);
        rectangle4.setFill(Color.BLACK);
        rectangle4.getStyleClass().add("rectangle_glow");
        rectangle4.setOpacity(0);
        rectangle4.setArcWidth(20); // Set arc width for rounded corners
        rectangle4.setArcHeight(20);
        grid.add(rectangle4,5,5,5,1);
        glowCard.add(rectangle4);

        Image club8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("4.png")));
        ImageView imageClub8 = new ImageView(club8);
        imageClub8.setFitWidth(150);
        imageClub8.setFitHeight(210);
        Rectangle clip4 = new Rectangle(150, 210);
        clip4.setArcWidth(20); // Set arc width for rounded corners
        clip4.setArcHeight(20); // Set arc height for rounded corners
        imageClub8.setClip(clip4);
        grid.add(imageClub8,  5, 5);
        cardList.add(imageClub8);

        holdButton1 = new HBox();
        b1 = new ImageView();
        b1.setImage(new Image("file:src/main/resources/com/example/demo/rec.png"));
        holdButton1.getChildren().add(b1);
        b1.setFitHeight(40);
        b1.setFitWidth(40);
        grid.add(holdButton1,5, 3);

        b2 = new ImageView();
        b2.setImage(new Image("file:src/main/resources/com/example/demo/rec.png"));
        holdButton1.getChildren().add(b2);
        b2.setFitHeight(40);
        b2.setFitWidth(40);

        b3 = new ImageView();
        b3.setImage(new Image("file:src/main/resources/com/example/demo/rec.png"));
        holdButton1.getChildren().add(b3);
        b3.setFitHeight(40);
        b3.setFitWidth(40);

        Alasclubbut.add(b1);
        Alasclubbut.add(b2);
        Alasclubbut.add(b3);

        StackPane betsp4 = new StackPane();

        Image betImg4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("BET.png")));
        ImageView imageBet4 = new ImageView(betImg4);
        imageBet4.setFitWidth(100);
        imageBet4.setFitHeight(47);
        betsp4.getChildren().add(imageBet4);

        Button bet_club8 = new Button();
        bet_club8.setText("Bet");
        bet_club8.setOnAction(event -> openBetStage(3));
        bet_club8.setPrefSize(100,44);
        bet_club8.setOpacity(0);
        betsp4.getChildren().add(bet_club8);

        grid.add(betsp4, 5, 7, 2, 1);
        GridPane.setHalignment(betsp4, HPos.CENTER);

        ImageView imageCoin4 = new ImageView(coin);
        imageCoin4.setFitWidth(100);
        imageCoin4.setFitHeight(100);
        imageCoin4.setOpacity(0);
        grid.add(imageCoin4, 5,5,2,1);
        GridPane.setHalignment(imageCoin4, HPos.CENTER);
        coinList.add(imageCoin4);

        TextField club8_betInput = new TextField();
        club8_betInput.setEditable(false);
        club8_betInput.setAlignment(Pos.CENTER);
        club8_betInput.getStyleClass().add("bet-input");
        grid.add(club8_betInput, 5, 5,2,1);
        GridPane.setHalignment(club8_betInput, HPos.CENTER);
        betFields[3] = club8_betInput;


        Rectangle rectangle6 = new Rectangle();
        rectangle6.setHeight(210);
        rectangle6.setWidth(150);
        rectangle6.setFill(Color.BLACK);
        rectangle6.getStyleClass().add("rectangle_glow");
        rectangle6.setOpacity(0);
        rectangle6.setArcWidth(20); // Set arc width for rounded corners
        rectangle6.setArcHeight(20);
        grid.add(rectangle6,14,3,5,1);
        glowCard.add(rectangle6);

        Image heartAce = new Image(Objects.requireNonNull(getClass().getResourceAsStream("6.png")));
        ImageView imageHeartAce = new ImageView(heartAce);
        imageHeartAce.setFitWidth(150);
        imageHeartAce.setFitHeight(210);
        Rectangle clip6 = new Rectangle(150, 210);
        clip6.setArcWidth(20); // Set arc width for rounded corners
        clip6.setArcHeight(20); // Set arc height for rounded corners
        imageHeartAce.setClip(clip6);
        grid.add(imageHeartAce,14, 3);
        cardList.add(imageHeartAce);

        holdButton1 = new HBox();
        b1 = new ImageView();
        b1.setImage(new Image("file:src/main/resources/com/example/demo/rec.png"));
        holdButton1.getChildren().add(b1);
        b1.setFitHeight(40);
        b1.setFitWidth(40);
        grid.add(holdButton1,14, 1);

        b2 = new ImageView();
        b2.setImage(new Image("file:src/main/resources/com/example/demo/rec.png"));
        holdButton1.getChildren().add(b2);
        b2.setFitHeight(40);
        b2.setFitWidth(40);

        b3 = new ImageView();
        b3.setImage(new Image("file:src/main/resources/com/example/demo/rec.png"));
        holdButton1.getChildren().add(b3);
        b3.setFitHeight(40);
        b3.setFitWidth(40);

        eightspeedbut.add(b1);
        eightspeedbut.add(b2);
        eightspeedbut.add(b3);

        StackPane betsp6 = new StackPane();

        Image betImg6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("BET.png")));
        ImageView imageBet6 = new ImageView(betImg6);
        imageBet6.setFitWidth(100);
        imageBet6.setFitHeight(47);
        betsp6.getChildren().add(imageBet6);

        Button bet_heartAce = new Button();
        bet_heartAce.setText("Bet");
        bet_heartAce.setOnAction(event -> openBetStage(4));
        bet_heartAce.setPrefSize(100,44);
        bet_heartAce.setOpacity(0);
        betsp6.getChildren().add(bet_heartAce);

        grid.add(betsp6, 14, 5, 2, 1);
        GridPane.setHalignment(betsp6, HPos.CENTER);

        ImageView imageCoin6 = new ImageView(coin);
        imageCoin6.setFitWidth(100);
        imageCoin6.setFitHeight(100);
        imageCoin6.setOpacity(0);
        grid.add(imageCoin6, 14,3,2,1);
        GridPane.setHalignment(imageCoin6, HPos.CENTER);
        coinList.add(imageCoin6);


        TextField heartAce_betInput = new TextField();
        heartAce_betInput.setEditable(false);
        heartAce_betInput.setAlignment(Pos.CENTER);
        heartAce_betInput.getStyleClass().add("bet-input");
        grid.add(heartAce_betInput, 14, 3,2,1);
        GridPane.setHalignment(heartAce_betInput, HPos.CENTER);
        betFields[4] = heartAce_betInput;




        StackPane randomizerSP = new StackPane();

        Image randomImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("playbg.png")));
        ImageView imageRandom = new ImageView(randomImg);
        imageRandom.setFitWidth(350);
        imageRandom.setFitHeight(120);
        randomizerSP.getChildren().add(imageRandom);

        //Btn for randomizer chooser
        Button rando_btn = new Button();
        rando_btn.setOnAction(event -> startRandomizer());
        rando_btn.setPrefSize(340,120);
        rando_btn.getStyleClass().add("randomPlaybtn");
        rando_btn.setOpacity(0);
        randomizerSP.getChildren().add(rando_btn);

        grid.add(randomizerSP, 9, 0, 1, 2);
        GridPane.setHalignment(randomizerSP, HPos.CENTER);

        for(int loop = 0; loop<3; loop++){
            System.out.println(eightspeedbut.size());
            eightspeedbut.get(loop).setOpacity(0);
            eightclubbut.get(loop).setOpacity(0);
            diamondAlasbut.get(loop).setOpacity(0);
            Alasclubbut.get(loop).setOpacity(0);
            AlasSpeedbut.get(loop).setOpacity(0);
        }

        Scene scene = new Scene(pnMain, 1200, 700);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("Perya_DropBall");
        stage.setScene(scene);
        stage.show();
    }//start


    private void startRandomizer() {
        System.out.println("randomizer button clicked");

        // Track the start time
        /*long startTime = System.currentTimeMillis();

        // Declare the timeline variable
        Timeline[] timelineHolder = new Timeline[1];

        // Create a Timeline to repeatedly execute the logic
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            // Check if 3 seconds have passed
            if (System.currentTimeMillis() - startTime >= 2300) {
                // Stop the timeline
                timelineHolder[0].stop();

                // Determine the winning card index
                int winningIndex = lastIndex[0];

                // Check if the user has placed a bet on the winning card
                if (userInputBets[winningIndex] > 0) {
                    userWins(winningIndex);
                } else {
                    Platform.runLater(() -> {
                        // Show a message if no bets were placed on the winning card
                        Stage loseStage = new Stage();
                        loseStage.initModality(Modality.APPLICATION_MODAL);
                        loseStage.initOwner(getStage());

                        VBox loseBox = new VBox();
                        loseBox.setAlignment(Pos.CENTER);
                        loseBox.setSpacing(10);
                        loseBox.setPadding(new Insets(20));

                        Label loseLabel = new Label("No winning bet! Better luck next time.");
                        loseLabel.setWrapText(true);
                        loseLabel.setTextAlignment(TextAlignment.CENTER);

                        Label pointsLabel = new Label("Your current points: " + userCurrentPoints);
                        pointsLabel.setWrapText(true);
                        pointsLabel.setTextAlignment(TextAlignment.CENTER);

                        Button closeButton = new Button("Close");
                        closeButton.setOnAction(e -> loseStage.close());

                        for (int i = 0; i < 5; i++){
                            coinList.get(i).setOpacity(0);
                        }



                        loseBox.getChildren().addAll(loseLabel, pointsLabel, closeButton);

                        Scene loseScene = new Scene(loseBox, 300, 200);
                        loseStage.setScene(loseScene);
                        loseStage.setTitle("No Win");
                        loseStage.show();
                    });
                }

                // Deduct all current bets after checking
                for (int i = 0; i < userInputBets.length; i++) {
                    //userCurrentPoints -= userInputBets[i]; naa na sa openBetStage ang kani na logic
                    userInputBets[i] = 0; // Reset user bets after deduction
                    betFields[i].clear(); // Clear the bet fields
                }

                //updatePointsLabel();
                return;
            }

            // Get a random index
            int randomIndex = random.nextInt(cardList.size());

            // Iterate over cardList and update the opacity
            for (int i = 0; i < cardList.size(); i++) {
                final int index = i;

                Platform.runLater(() -> {
                    if (index == randomIndex) {
                        glowCard.get(index).setOpacity(1);
                        System.out.println("current index: " + index);
                        lastIndex[0] = index;
                    } else {
                        glowCard.get(index).setOpacity(0);
                    }
                });

            }
        }));

        // Set the timeline to repeat indefinitely
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Store the timeline in the holder
        timelineHolder[0] = timeline;

        // Start the timeline
        timeline.play();*/

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            Random rand = new Random();

            int randomInt = rand.nextInt(5) + 1;
//            diamondAlasbut = new ArrayList<>();
//            private ArrayList<ImageView> eightclubbut = new ArrayList<>();
//            private ArrayList<ImageView> Alasclubbut = new ArrayList<>();
//            private ArrayList<ImageView> AlasSpeedbut = new ArrayList<>();
//            private ArrayList<ImageView> eightspeedbut = new ArrayList<>();

            if(randomInt == 1){
                diamondAlasbut.get(0).setOpacity(1);
                eightclubbut.get(0).setOpacity(0);
                Alasclubbut.get(0).setOpacity(0);
                AlasSpeedbut.get(0).setOpacity(0);
                eightspeedbut.get(0).setOpacity(0);
            }
            else if(randomInt == 2){
                diamondAlasbut.get(0).setOpacity(0);
                eightclubbut.get(0).setOpacity(1);
                Alasclubbut.get(0).setOpacity(0);
                AlasSpeedbut.get(0).setOpacity(0);
                eightspeedbut.get(0).setOpacity(0);
            }
            else if(randomInt == 3){
                diamondAlasbut.get(0).setOpacity(0);
                eightclubbut.get(0).setOpacity(0);
                Alasclubbut.get(0).setOpacity(1);
                AlasSpeedbut.get(0).setOpacity(0);
                eightspeedbut.get(0).setOpacity(0);
            }
            else if(randomInt == 4){
                diamondAlasbut.get(0).setOpacity(0);
                eightclubbut.get(0).setOpacity(0);
                Alasclubbut.get(0).setOpacity(0);
                AlasSpeedbut.get(0).setOpacity(1);
                eightspeedbut.get(0).setOpacity(0);
            }
            else if(randomInt == 5){
                diamondAlasbut.get(0).setOpacity(0);
                eightclubbut.get(0).setOpacity(0);
                Alasclubbut.get(0).setOpacity(0);
                AlasSpeedbut.get(0).setOpacity(0);
                eightspeedbut.get(0).setOpacity(1);
            }
        }));

        timeline.setCycleCount(10);
        timeline.play();


        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            Random rand = new Random();

            int randomInt = rand.nextInt(5) + 1;
//            diamondAlasbut = new ArrayList<>();
//            private ArrayList<ImageView> eightclubbut = new ArrayList<>();
//            private ArrayList<ImageView> Alasclubbut = new ArrayList<>();
//            private ArrayList<ImageView> AlasSpeedbut = new ArrayList<>();
//            private ArrayList<ImageView> eightspeedbut = new ArrayList<>();

            if(randomInt == 1){
                diamondAlasbut.get(1).setOpacity(1);
                eightclubbut.get(1).setOpacity(0);
                Alasclubbut.get(1).setOpacity(0);
                AlasSpeedbut.get(1).setOpacity(0);
                eightspeedbut.get(1).setOpacity(0);
            }
            else if(randomInt == 2){
                diamondAlasbut.get(1).setOpacity(0);
                eightclubbut.get(1).setOpacity(1);
                Alasclubbut.get(1).setOpacity(0);
                AlasSpeedbut.get(1).setOpacity(0);
                eightspeedbut.get(1).setOpacity(0);
            }
            else if(randomInt == 3){
                diamondAlasbut.get(1).setOpacity(0);
                eightclubbut.get(1).setOpacity(0);
                Alasclubbut.get(1).setOpacity(1);
                AlasSpeedbut.get(1).setOpacity(0);
                eightspeedbut.get(1).setOpacity(0);
            }
            else if(randomInt == 4){
                diamondAlasbut.get(1).setOpacity(0);
                eightclubbut.get(1).setOpacity(0);
                Alasclubbut.get(1).setOpacity(0);
                AlasSpeedbut.get(1).setOpacity(1);
                eightspeedbut.get(1).setOpacity(0);
            }
            else if(randomInt == 5){
                diamondAlasbut.get(1).setOpacity(0);
                eightclubbut.get(1).setOpacity(0);
                Alasclubbut.get(1).setOpacity(0);
                AlasSpeedbut.get(1).setOpacity(0);
                eightspeedbut.get(1).setOpacity(1);
            }
        }));

        timeline1.setCycleCount(10);
        timeline1.play();

        Timeline timeline3 = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            Random rand = new Random();

            int randomInt = rand.nextInt(5) + 1;
//            diamondAlasbut = new ArrayList<>();
//            private ArrayList<ImageView> eightclubbut = new ArrayList<>();
//            private ArrayList<ImageView> Alasclubbut = new ArrayList<>();
//            private ArrayList<ImageView> AlasSpeedbut = new ArrayList<>();
//            private ArrayList<ImageView> eightspeedbut = new ArrayList<>();

            if(randomInt == 1){
                diamondAlasbut.get(2).setOpacity(1);
                eightclubbut.get(2).setOpacity(0);
                Alasclubbut.get(2).setOpacity(0);
                AlasSpeedbut.get(2).setOpacity(0);
                eightspeedbut.get(2).setOpacity(0);
            }
            else if(randomInt == 2){
                diamondAlasbut.get(2).setOpacity(0);
                eightclubbut.get(2).setOpacity(1);
                Alasclubbut.get(2).setOpacity(0);
                AlasSpeedbut.get(2).setOpacity(0);
                eightspeedbut.get(2).setOpacity(0);
            }
            else if(randomInt == 3){
                diamondAlasbut.get(2).setOpacity(0);
                eightclubbut.get(2).setOpacity(0);
                Alasclubbut.get(2).setOpacity(1);
                AlasSpeedbut.get(2).setOpacity(0);
                eightspeedbut.get(2).setOpacity(0);
            }
            else if(randomInt == 4){
                diamondAlasbut.get(2).setOpacity(0);
                eightclubbut.get(2).setOpacity(0);
                Alasclubbut.get(2).setOpacity(0);
                AlasSpeedbut.get(2).setOpacity(1);
                eightspeedbut.get(2).setOpacity(0);
            }
            else if(randomInt == 5){
                diamondAlasbut.get(2).setOpacity(0);
                eightclubbut.get(2).setOpacity(0);
                Alasclubbut.get(2).setOpacity(0);
                AlasSpeedbut.get(2).setOpacity(0);
                eightspeedbut.get(2).setOpacity(1);
            }
        }));

        timeline3.setCycleCount(10);
        timeline3.play();

        timeline3.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int counter[] = {0,0,0,0,0};
                for(int loop = 0; loop<3; loop++){
                    if(eightclubbut.get(loop).getOpacity() == 1){
                        counter[0]++;
                    }
                    else if(diamondAlasbut.get(loop).getOpacity() == 1){
                        counter[1]++;
                    }
                    else if(AlasSpeedbut.get(loop).getOpacity() == 1){
                        counter[2]++;
                    }
                    else if(Alasclubbut.get(loop).getOpacity() == 1){
                        counter[3]++;
                    }
                    else if(eightspeedbut.get(loop).getOpacity() == 1){
                        counter[4]++;
                    }

                }
                System.out.println(userInputBets.length);
                double counterme = 0;
                for(int loop = 0; loop<userInputBets.length; loop++){
                    counterme += userInputBets[loop]*counter[loop];
                }
                userCurrentPoints+=counterme;
                ;

                double finalCounterme = counterme;
                Platform.runLater(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    pnMain.setDisable(true);
                    Stage winStage = new Stage();
                    winStage.initModality(Modality.APPLICATION_MODAL);
                    winStage.initOwner(getStage());


                    VBox winBox = new VBox();
                    winBox.setAlignment(Pos.CENTER);
                    winBox.setSpacing(10);
                    winBox.setPadding(new Insets(20));

                    winBox.setStyle("-fx-background-color: linear-gradient(to right, rgb(242, 112, 156), rgb(255, 148, 114));");

                    Label winLabel = new Label("You have won " + finalCounterme + "!");
                    winLabel.setWrapText(true);
                    winLabel.setFont(Font.font("Tahoma", 15));
                    winLabel.setTextAlignment(TextAlignment.CENTER);

                    Label pointsLabel = new Label("Your current points: " + userCurrentPoints);
                    pointsLabel.setWrapText(true);
                    pointsLabel.setTextAlignment(TextAlignment.CENTER);

                    updatePointsLabel();

                    Button closeButton = new Button("Close");
                    closeButton.setOnAction(e -> {
                        for (int i = 0; i < 5; i++){
                            coinList.get(i).setOpacity(0);
                            betFields[i].setText("");
                            userInputBets[i] = 0;
                        }

                        for(int loop = 0; loop<3; loop++){
                            System.out.println(eightspeedbut.size());
                            eightspeedbut.get(loop).setOpacity(0);
                            eightclubbut.get(loop).setOpacity(0);
                            diamondAlasbut.get(loop).setOpacity(0);
                            Alasclubbut.get(loop).setOpacity(0);
                            AlasSpeedbut.get(loop).setOpacity(0);
                        }
                        pnMain.setDisable(false);
                        winStage.close();
                    });

                    winStage.setOnCloseRequest(eventt->{
                        eventt.consume();
                        for (int i = 0; i < 5; i++){
                            coinList.get(i).setOpacity(0);
                            betFields[i].setText("");
                            userInputBets[i] = 0;
                        }

                        for(int loop = 0; loop<3; loop++){
                            System.out.println(eightspeedbut.size());
                            eightspeedbut.get(loop).setOpacity(0);
                            eightclubbut.get(loop).setOpacity(0);
                            diamondAlasbut.get(loop).setOpacity(0);
                            Alasclubbut.get(loop).setOpacity(0);
                            AlasSpeedbut.get(loop).setOpacity(0);
                        }
                        pnMain.setDisable(false);
                        winStage.close();
                    });

                    winBox.getChildren().addAll(winLabel, pointsLabel, closeButton);

                    Scene winScene = new Scene(winBox, 300, 200);
                    winStage.setScene(winScene);
                    winStage.setTitle("You Win!");
                    winStage.show();
                });

            }
        });


    }



    private void userWins(int winningIndex) {
        int betAmount = userInputBets[winningIndex];
        int winnings = betAmount * 2;

        // Add winnings to current points
        userCurrentPoints += winnings;


        // Display a message to the user
        Platform.runLater(() -> {
            Stage winStage = new Stage();
            winStage.initModality(Modality.APPLICATION_MODAL);
            winStage.initOwner(getStage());


            VBox winBox = new VBox();
            winBox.setAlignment(Pos.CENTER);
            winBox.setSpacing(10);
            winBox.setPadding(new Insets(20));

            winBox.setStyle("-fx-background-color: linear-gradient(to right, rgb(242, 112, 156), rgb(255, 148, 114));");

            Label winLabel = new Label("Congratulations! You have won " + winnings + "!");
            winLabel.setWrapText(true);
            winLabel.setFont(Font.font("Tahoma", 15));
            winLabel.setTextAlignment(TextAlignment.CENTER);

            Label pointsLabel = new Label("Your current points: " + userCurrentPoints);
            pointsLabel.setWrapText(true);
            pointsLabel.setTextAlignment(TextAlignment.CENTER);

            updatePointsLabel();

            Button closeButton = new Button("Close");
            for (int i = 0; i < 5; i++){
                coinList.get(i).setOpacity(0);
            }
            closeButton.setOnAction(e -> winStage.close());

            winBox.getChildren().addAll(winLabel, pointsLabel, closeButton);

            Scene winScene = new Scene(winBox, 300, 200);
            winStage.setScene(winScene);
            winStage.setTitle("You Win!");
            winStage.show();
        });
    }



    private void openBetStage(int index) {
        pnMain.setDisable(true);
        Stage stage = new Stage();

        // Set the modality to block input events from being delivered to other stages
        stage.initModality(Modality.WINDOW_MODAL);

        // Make the bet window modal to the main application window
        stage.initOwner(getStage());

        AnchorPane pnBet = new AnchorPane();
        pnBet.getStyleClass().add("betStage_bg");
        GridPane grid = new GridPane();


        pnBet.getChildren().add(grid);
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(false); //SET IT TO FALSE IF MANA OVERALL PROJECT!!!!

        grid.getColumnConstraints().add(new ColumnConstraints(35));
        grid.getColumnConstraints().add(new ColumnConstraints(35));
        grid.getColumnConstraints().add(new ColumnConstraints(20));
        grid.getColumnConstraints().add(new ColumnConstraints(20));
        grid.getColumnConstraints().add(new ColumnConstraints(35));
        grid.getColumnConstraints().add(new ColumnConstraints(35));
        grid.getColumnConstraints().add(new ColumnConstraints(35));
        grid.getColumnConstraints().add(new ColumnConstraints(30));
        grid.getColumnConstraints().add(new ColumnConstraints(15));
        grid.getColumnConstraints().add(new ColumnConstraints(35));

        grid.getRowConstraints().add(new RowConstraints(35));
        grid.getRowConstraints().add(new RowConstraints(35));
        grid.getRowConstraints().add(new RowConstraints(35));
        grid.getRowConstraints().add(new RowConstraints(35));
        grid.getRowConstraints().add(new RowConstraints(35));
        grid.getRowConstraints().add(new RowConstraints(30));
        grid.getRowConstraints().add(new RowConstraints(28));
        grid.getRowConstraints().add(new RowConstraints(35));
        grid.getRowConstraints().add(new RowConstraints(35));
        grid.getRowConstraints().add(new RowConstraints(35));

        //set background image using the given parameters
        BackgroundImage myBI = new BackgroundImage(
                new Image(getClass().getResource("EnterBet.png").toExternalForm(),
                        350, 350, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pnBet.setBackground(new Background(myBI));

        //TextField for Bet Amount
        TextField betField = new TextField ();
        betField.setPromptText("Set Amount");
        betField.setAlignment(Pos.CENTER);
        betField.setMinHeight(30);
        betField.getStyleClass().add("betField");
        grid.add(betField,3, 5,6,2);

        Button submitButton = new Button("Submit");
        submitButton.setMinWidth(130);
        submitButton.setMinHeight(30);
        submitButton.setOpacity(0);
        submitButton.setOnAction(event -> {
            String betText = betField.getText();
            if (!betText.isEmpty()) {
                try {
                    int betAmount = Integer.parseInt(betText);

                    if (betAmount > userCurrentPoints || betAmount <= 0) {
                        betField.clear();
                        betField.setPromptText("Invalid Input");
                    } else {
                        userInputBets[index] = betAmount;
                        betFields[index].setText("" + betAmount);
                        userCurrentPoints -= betAmount;
                        updatePointsLabel();
                        coinList.get(index).setOpacity(1);
                        stage.close();
                    }
                } catch (NumberFormatException e) {
                    betField.clear();
                    betField.setPromptText("Invalid Input");
                }
            } else {
                betField.clear();
                betField.setPromptText("Set Amount");
            }
            pnMain.setDisable(false);
        });

        stage.setOnCloseRequest(event->{
            event.consume();
            pnMain.setDisable(false);
            stage.close();
        });
        grid.add(submitButton,4, 8,8,1);


        Scene scene = new Scene(pnBet, 350, 350);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("Bet Forms");
        stage.setScene(scene);
        stage.show();

    }

    private void updatePointsLabel() {
        pointsLabel.setText("" + userCurrentPoints);
    }

    // Helper method to get the primary stage
    private Stage getStage() {
        return (Stage) cardList.get(0).getScene().getWindow();
    }


    public void UpdatedbWallet(WalletClass ww){
        double newbalance = Double.parseDouble(userCurrentPoints+"");
        String newstring = String.format("%.2f",newbalance);
        double origbal = Double.parseDouble(newstring);

        ww.setBalance(origbal);

        try (Connection connection = SqlConnect.getConnection()) {
            PreparedStatement pstatement = connection.prepareStatement("UPDATE wallet SET balance = ? WHERE walletid = ?");

            int wid = ww.getWalletid();
            double bb = ww.getBalance();
            pstatement.setDouble(1,bb);
            pstatement.setInt(2,wid);


            int resultSet = pstatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}//HelloApplication