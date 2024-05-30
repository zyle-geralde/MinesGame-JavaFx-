package com.example.casino_finalproject_oop;

import com.example.projjavafxoop2.DashBoardPackage.ClickSelectedEffectThread;
import com.example.projjavafxoop2.DashBoardPackage.DashBoardSample;
import com.example.projjavafxoop2.SqlConnect;
import com.example.projjavafxoop2.WalletClass;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Handler;

public class HelloApplicationV2 {
    public static final int NUM_BLOCK = 3;

    public static final int num_block = 1;
    public static int total_winnings = 0;
    public static int total_bet = 0;
    public static volatile int count = 0;
    public static Label txtTotalBet;
    public static Font font;
    public static boolean[] betsMap = new boolean[] {false, false, false, false, false, false};
    ArrayList<ImageView> blockList = new ArrayList<>();
    ImageView[] colorBlocks = new ImageView[6];
    public static int[] rolledFaces = new int[NUM_BLOCK];
    static Random random = new Random();
    public static BetInputHandler[] handlers = new BetInputHandler[6];
    static ArrayList<Thread> threads = new ArrayList<Thread>();
    public static final int[] finishedAnimations = {0};
    public static GridPane gridPane;
    public static Rectangle container = new Rectangle(1200, 770);
    public  static Text txtBalance;
    public static Label sceneTitle;
    public static Media media;
    public static Media media1;
    public static Media media2;
    public static MediaPlayer mediaPlayer;
    public static MediaPlayer mpWin;
    public static MediaPlayer mpNoWin;
    public static AudioClip clickSound;
    static Text txtWon;
    static Image try_again;
    static ImageView imgTry;
    static int num = 0;
    static int balance = 1000;    // ------------------------------TOTAL COIN BALANCE----------------------------
    boolean isAnyTokenVisible;


    public void refresh(Stage stage, WalletClass wallet) throws IOException {
//        font = Font.loadFont(getClass().getResourceAsStream("walibi-holland.ttf"), 20);
////        txtTotalBet.getStyleClass().add("game-text")
        AnchorPane pnMain = new AnchorPane();
        GridPane grid = new GridPane();
        gridPane = grid;
        pnMain.getChildren().add(grid);
        grid.setAlignment(Pos.CENTER);

        balance = (int)Math.floor(wallet.getBalance());

        media = new Media(getClass().getResource("/com/example/casino_finalproject_oop/background_music.mp3").toExternalForm());
        MediaPlayer mmediaPlayer = new MediaPlayer(media);
        mmediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mmediaPlayer.setVolume(1);
        mmediaPlayer.play();

        media1 = new Media(getClass().getResource("/com/example/casino_finalproject_oop/win_sound.mp3").toExternalForm());
        mpWin = new MediaPlayer(media1);
        mpWin.setCycleCount(1);

        media2 = new Media(getClass().getResource("/com/example/casino_finalproject_oop/lose_sound.mp3").toExternalForm());
        mpNoWin = new MediaPlayer(media2);
        mpNoWin.setCycleCount(1);
        mpNoWin.setVolume(0.2);

        clickSound = new AudioClip(getClass().getResource("/com/example/casino_finalproject_oop/click_sound.mp3").toString());

        grid.getColumnConstraints().add(new ColumnConstraints(40));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(40));

        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));
        grid.getRowConstraints().add(new RowConstraints(70));

        Image bg = new Image(getClass().getResourceAsStream("test2.png"));
        ImageView imgBG = new ImageView(bg);
        StackPane stackPane = new StackPane(imgBG);
        stackPane.setPadding(new Insets(65, 0, 0, 0));
        grid.add(stackPane,  0, 4);

        Image board = new Image(getClass().getResourceAsStream("casino-board.png"));
        ImageView casinoBoard = new ImageView(board);
        StackPane spBoard = new StackPane(casinoBoard);
        spBoard.setPadding(new Insets(140, 0, 0, 50));
        grid.add(spBoard,  10, 3);

        Image bar = new Image(getClass().getResourceAsStream("coin-bar.png"));
        ImageView coin_bar = new ImageView(bar);
        StackPane spCoin = new StackPane(coin_bar);
        spCoin.setPadding(new Insets(35, 0, 0, 38));
        grid.add(spCoin,  13, 0);

        sceneTitle = new Label("Total Bet");
        sceneTitle.setTextAlignment(TextAlignment.CENTER);
//        sceneTitle.setStyle("-fx-text-stroke-width: 2px;");
//        sceneTitle.setFill(Paint.valueOf("#325622"));
        sceneTitle.setTextFill(Color.BROWN);
        sceneTitle.setFont(setCustomFont(35));
        grid.setHalignment(sceneTitle, HPos.CENTER);
        grid.add(sceneTitle, 11, 3, 3, 1);

        txtBalance = new Text();
        txtBalance.setText(balance + "$");
        txtBalance.setStrokeWidth(20);
        txtBalance.setFill(Color.BLACK);
        txtBalance.setFont(setCustomFont(25));
        StackPane spBalance = new StackPane(txtBalance);
        spBalance.setPadding(new Insets(30, 0, 0, 26));
        grid.add(spBalance, 14, 0);

        ImageView homebut = new ImageView();
        homebut.setImage(new Image("file:src/main/resources/com/example/casino_finalproject_oop/home (1).png"));
        grid.add(homebut,1,1);
        homebut.setFitWidth(75);
        homebut.setFitHeight(75);

        homebut.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                UpdatedbWallet(wallet);
                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                DashBoardSample dsh = new DashBoardSample(wallet);
                mmediaPlayer.stop();
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

//        Image tileBackground = new Image(getClass().getResourceAsStream("blocks-bg.png"));
//        ImageView tileBG = new ImageView(tileBackground);
//        StackPane stack = new StackPane(tileBG);
//        stack.setPadding(new Insets(60, 0, 0, 30));  //double top, double right, double bottom, double left
//        grid.add(stack, 0, 5);

//        Image gameTitle = new Image(getClass().getResourceAsStream("Color Game Blocks.png"));
        Image gameTitle = new Image(getClass().getResourceAsStream("casino1.png"));
        ImageView imageTitle = new ImageView(gameTitle);
        grid.add(imageTitle,  2, 1);

        Image rectBlue = new Image(getClass().getResourceAsStream("12.png"));
        ImageView imageBlue = new ImageView(rectBlue);
        imageBlue.setFitWidth(210);
        imageBlue.setFitHeight(210);
        grid.add(imageBlue,  1, 4);

        Image rectGreen = new Image(getClass().getResourceAsStream("10.png"));
        ImageView imageGreen = new ImageView(rectGreen);
        imageGreen.setFitWidth(210);
        imageGreen.setFitHeight(210);
        grid.add(imageGreen,  4, 4);

        Image rectRed = new Image(getClass().getResourceAsStream("8.png"));
        ImageView imageRed = new ImageView(rectRed);
        imageRed.setFitWidth(210);
        imageRed.setFitHeight(210);
        grid.add(imageRed,  7, 4);

        Image rectWhite = new Image(getClass().getResourceAsStream("11.png"));
        ImageView imageWhite = new ImageView(rectWhite);
        imageWhite.setFitWidth(210);
        imageWhite.setFitHeight(210);
        grid.add(imageWhite,  1, 7);

        Image rectYellow = new Image(getClass().getResourceAsStream("7.png"));
        ImageView imageYellow = new ImageView(rectYellow);
        imageYellow.setFitWidth(210);
        imageYellow.setFitHeight(210);
        grid.add(imageYellow,  4, 7);

        Image rectPink = new Image(getClass().getResourceAsStream("9.png"));
        ImageView imagePink = new ImageView(rectPink);
        imagePink.setFitWidth(210);
        imagePink.setFitHeight(210);
        grid.add(imagePink,  7, 7);

        txtTotalBet = new Label("0");
        Insets tfMargin = new Insets(25);
        txtTotalBet.setTextAlignment(TextAlignment.CENTER);
        txtTotalBet.setTextFill(Color.BROWN);
        grid.setHalignment(txtTotalBet, HPos.CENTER);
        txtTotalBet.setFont(setCustomFont(50));
        grid.add(txtTotalBet, 11, 4, 3, 2);
        GridPane.setMargin(txtTotalBet, tfMargin);

        BetInputHandler blueHandler = new BetInputHandler(grid, imageBlue, "blue-token.png", 1, 5, 3, 1, 5);
        BetInputHandler greenHandler = new BetInputHandler(grid, imageGreen, "green-token.png", 4, 5, 3, 1, 3);
        BetInputHandler redHandler = new BetInputHandler(grid, imageRed, "red-token.png", 7, 5, 3, 1, 1);
        BetInputHandler whiteHandler = new BetInputHandler(grid, imageWhite, "white-token.png", 1, 8, 3, 1, 4);
        BetInputHandler yellowHandler = new BetInputHandler(grid, imageYellow, "yellow-token.png", 4, 8, 3, 1, 0);
        BetInputHandler pinkHandler = new BetInputHandler(grid, imagePink, "pink-token.png", 7, 8, 3, 1, 2);
        handlers[0] = yellowHandler;
        handlers[1] = redHandler;
        handlers[2] = pinkHandler;
        handlers[3] = greenHandler;
        handlers[4] = whiteHandler;
        handlers[5] = blueHandler;

        /* -------------------------------------- ROLLING BLOCKS SECTION ------------------------------------------------- */

        for(int i = 0; i < 6; i++) {
            colorBlocks[i] = new ImageView(new Image(String.format("file:src/main/resources/com/example/casino_finalproject_oop/%s.png",i)));
        }
        VBox blockGroup = new VBox();
        blockGroup.setSpacing(20); // Set spacing between blocks
        blockGroup.setAlignment(Pos.CENTER);
        blockGroup.setPadding(new Insets(30, 0, 0, 0));

        //set the initial faces of the blocks
        for(int i = 0; i < NUM_BLOCK; i++) {
            ImageView imageView = new ImageView(new Image(String.format("file:src/main/resources/com/example/casino_finalproject_oop/%s.png",rollBlock())));
            imageView.setFitWidth(140);
            imageView.setFitHeight(140);
            blockList.add(i,imageView);
            blockGroup.getChildren().add(imageView);
        }

        for (int i = 0; i < NUM_BLOCK; i++) {
            Runnable r = new ThreadBlock(blockList, i);
            threads.add(new Thread(r));
        }

        /* --------------- Clear and Roll Buttons ---------------- */

        Button clear = new Button("Clear all bets");
        clear.setMinSize(15, 25);
        clear.setMinWidth(210);
        clear.setMinHeight(60);
        clear.fontProperty().setValue(Font.font(18));

        Button roll = new Button("Roll Blocks");
        roll.setMinSize(15, 25);
        roll.setMinWidth(210);
        roll.setMinHeight(60);
        roll.fontProperty().setValue(Font.font(18));

        Image rollBtn = new Image(getClass().getResourceAsStream("roll-button.png"));
        ImageView rollBTN = new ImageView(rollBtn);
        StackPane spRoll = new StackPane(rollBTN);
        spRoll.setPadding(new Insets(0, 0, 0, 5));

        Image clearBtn = new Image(getClass().getResourceAsStream("clear-button.png"));
        ImageView clearBTN = new ImageView(clearBtn);
        StackPane spClear = new StackPane(clearBTN);
        spClear.setPadding(new Insets(0, 0, 0, 5));

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100));
        scaleTransition.setToX(0.9);
        scaleTransition.setToY(0.9);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);

        roll.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clickSound.play();
                scaleTransition.setNode(rollBTN);
                scaleTransition.play();
                for (BetInputHandler handler : handlers) {
                    if (handler.isTokenVisible()) {
                        isAnyTokenVisible = true;
                        break;
                    }
                }
                if(total_bet > balance) {
                    displayMessage("You don't have enough coins!", grid);
                } else {
                    if(finishedAnimations[0] == 0) {
                        balance -= total_bet;
                        txtBalance.setText(balance + "$");
//                    decrementBalance(total_bet);
                        if (isAnyTokenVisible) {
                            // Roll action logic
                            for (int i = 0; i < num_block; i++) {
                                //threads.get(i).start();
                                Runnable r = new ThreadBlock(blockList, i);
                                Thread thr = new Thread(r);
                                //threads.add(new Thread(r));
                                thr.start();

                            }
                        } else {
                            displayMessage("Put your chips down, you bold player!", grid);
                        }
                    } else {
                        displayMessage("Start a new game instead", grid);
                    }
                }
            }
        });
        /*roll.setOnAction(e -> {
            clickSound.play();
            scaleTransition.setNode(rollBTN);
            scaleTransition.play();
            for (BetInputHandler handler : handlers) {
                if (handler.isTokenVisible()) {
                    isAnyTokenVisible = true;
                    break;
                }
            }
                if(total_bet > balance) {
                    displayMessage("You don't have enough coins!", grid);
                } else {
                    if(finishedAnimations[0] == 0) {
                        balance -= total_bet;
                        txtBalance.setText(balance + "$");
//                    decrementBalance(total_bet);
                        if (isAnyTokenVisible) {
                            // Roll action logic
                            for (int i = 0; i < NUM_BLOCK; i++) {
                                threads.get(i).start();
                            }
                        } else {
                            displayMessage("Put your chips down, you bold player!", grid);
                        }
                    } else {
                        displayMessage("Start a new game instead", grid);
                    }
                }
            }
        );*/

        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clickSound.play();
                scaleTransition.setNode(clearBTN);
                scaleTransition.play();
                clearGame(grid, "clear");
            }
        });

        txtWon = new Text(); // Initialize txtWon
//        txtWon.setVisible(false); // Set visibility to false initially
        txtWon.setFont(Font.font("Britannic Bold", FontWeight.BOLD, 60));
        txtWon.setFill(Color.YELLOW);
//        gridPane.add(txtWon, 0, 3, 18, 10);
        GridPane.setHalignment(txtWon, HPos.CENTER);
        GridPane.setValignment(txtWon, VPos.CENTER);

        container.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearGame(grid, "container");
                mpNoWin.stop();
                mpWin.stop();
                isAnyTokenVisible = false;
                balance += total_winnings;
                total_winnings = 0;
                txtBalance.setText(balance + "$");
//                incrementBalance(total_winnings);
                imgTry.setVisible(false);
                grid.getChildren().remove(container);
                txtWon.setVisible(false);
            }
        });

        grid.add(blockGroup, 15, 5);
        grid.add(spClear,  11, 7);
        grid.add(clear, 11, 7);
        clear.setOpacity(0);
        grid.setAlignment(Pos.CENTER);

        grid.add(spRoll,  11, 8);
        grid.add(roll, 11, 8);
        roll.setOpacity(0);
        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(pnMain, 1200, 700);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("Bingo Game");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                mediaPlayer.stop();
            }
        });

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clickSound.play();
            }
        });
    }

    public static void displayMessage(String message, GridPane gridPane) {
        txtTotalBet.setVisible(false);
        sceneTitle.setWrapText(true);
        sceneTitle.setFont(setCustomFont(25));
        gridPane.setRowSpan(sceneTitle, 3);
        sceneTitle.setText(message);
    }

    private void clearGame(GridPane gridPane, String str) {
        for (BetInputHandler handler : handlers) {
            handler.clearAll();
        }

        for (BetInputHandler handler : handlers) {
            handler.setTokenFlag(false);
        }
        gridPane.setRowSpan(sceneTitle, 1);
        sceneTitle.setFont(setCustomFont(35));
        sceneTitle.setText("Total Bet");
        txtTotalBet.setVisible(true);
        gridPane.getChildren().remove(txtWon);
        threads.clear();
        for (int i = 0; i < NUM_BLOCK; i++) {
            Runnable r = new ThreadBlock(blockList, i);
            threads.add(new Thread(r));
        }
        finishedAnimations[0] = 0;
        betsMap = new boolean[] {false, false, false, false, false, false};
        count = 0;
        if(!str.equals("container")) {
            total_winnings = 0;
        }
        num = 0;
        total_bet = 0;
        txtTotalBet.setText(Integer.toString(total_bet));
    }

    public static void calculateWin() throws InterruptedException {
        String[] colorBlockString = new String[6];
        colorBlockString[0] = "yellow";
        colorBlockString[1] = "red";
        colorBlockString[2] = "pink";
        colorBlockString[3] = "green";
        colorBlockString[4] = "white";
        colorBlockString[5] = "blue";
        int[] colorCount = new int[]{0,0,0,0,0,0};
        for (int face : rolledFaces) {
             colorCount[face] += 1;
        }

        boolean flag = false;

        for (int i = 0; i < 3; i++) {
            System.out.println("Rolled faces: " + rolledFaces[i]);
        }

        for (int i = 0; i < 6; i++) {
            if (betsMap[i]) {
                int betAmount = handlers[i].getBetAmount();
                if (colorCount[i] == 1) {
                    flag = true;
                    int payout = betAmount;
                    total_winnings += payout;
                    System.out.println("Player keeps their bet on color " + colorBlockString[i] + ": " + payout);
                    num++;
                }
                if (colorCount[i] == 2) {
                    flag = true;
                    int payout = 2 * betAmount;
                    total_winnings += payout;
                    System.out.println("Player wins 2X on color " + colorBlockString[i] + ": " + payout);
                    num += 2;
                }
                if (colorCount[i] == 3) {
                    flag = true;
                    int payout = 3 * betAmount;
                    total_winnings += payout;
                    System.out.println("Player wins 3X on color " + colorBlockString[i] + ": " + payout);
                    num += 3;
                }

            }
        }

        if(!flag) {
            System.out.println("No winnings!");
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(1000), // 1000 milliseconds = 1 second
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            showWinnings(0);
                        }
                    }
            ));
            timeline.setCycleCount(1); // Execute once
            timeline.play();
            //Thread.sleep(1000);
            //showWinnings(0);
        } else {
            //Thread.sleep(1000);
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(1000), // 1000 milliseconds = 1 second
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            showWinnings(total_winnings);
                        }
                    }
            ));
            //showWinnings(total_winnings);
            timeline.setCycleCount(1); // Execute once
            timeline.play();
        }
        txtTotalBet.setText(Integer.toString(total_bet));
    }

    public static Font setCustomFont(int fontsize) {
        return Font.loadFont("file:src/main/resources/com/example/casino_finalproject_oop/walibi-holland.ttf", fontsize);
    }

    public static int rollBlock() {
        return random.nextInt(0, 6)+1;
    }

    public static void showWinnings(int win) {container.setVisible(false);
//        mediaPlayer.pause();

        if(win <= 0) {
            mpNoWin.play();
        } else {
            mpWin.play();
        }
        if(!gridPane.getChildren().contains(container)) {
            container.setFill(new Color(0, 0, 0, 0.5));
            gridPane.add(container, 0, 4);
        }
//        container.toBack();
        container.setVisible(true);
        if(num == 1) {
            try_again = new Image(HelloApplicationV2.class.getResourceAsStream("nice-one.png"));
        } else if(num == 2) {
            try_again = new Image(HelloApplicationV2.class.getResourceAsStream("winner.png"));
        } else if(num == 3) {
            try_again = new Image(HelloApplicationV2.class.getResourceAsStream("jackpot.png"));
        } else {
            try_again = new Image(HelloApplicationV2.class.getResourceAsStream("try-again.png"));
        }

        imgTry = new ImageView(try_again);
        if(num == 0) {
            gridPane.add(imgTry,  0, 0, 18, 10);
        } else {
            gridPane.add(imgTry,  0, 0, 18, 7);
        }
        GridPane.setHalignment(imgTry, HPos.CENTER);
        GridPane.setValignment(imgTry, VPos.CENTER);
        imgTry.toFront();

        ScaleTransition scale1 = new ScaleTransition(Duration.millis(500), imgTry);
        scale1.setFromX(0);
        scale1.setFromY(0);
        scale1.setToX(1);
        scale1.setToY(1);
        scale1.setCycleCount(1);

        FadeTransition fade1 = new FadeTransition(Duration.millis(500), imgTry);
        fade1.setFromValue(0.60);
        fade1.setToValue(1);
        fade1.setCycleCount(Timeline.INDEFINITE);
        fade1.setAutoReverse(true);

        SequentialTransition seqTransition1 = new SequentialTransition(scale1, fade1);
        seqTransition1.play();


        if(num == 1 || num == 2 || num == 3) {
            txtWon.setText("You won " + win + "$");
            txtWon.getStyleClass().add("win-text");
            txtWon.setVisible(true);
            gridPane.add(txtWon, 0, 4, 18, 10);
            txtWon.toFront();
            ScaleTransition scale2 = new ScaleTransition(Duration.millis(500), txtWon);
            scale2.setFromX(0);
            scale2.setFromY(0);
            scale2.setToX(1);
            scale2.setToY(1);
            scale2.setCycleCount(1);

            FadeTransition fade2 = new FadeTransition(Duration.millis(500), txtWon);
            fade2.setFromValue(0.60);
            fade2.setToValue(1);
            fade2.setCycleCount(Timeline.INDEFINITE);
            fade2.setAutoReverse(true);

            SequentialTransition seqTransition2 = new SequentialTransition(scale2, fade2);
            seqTransition2.play();
        }
    }

    private void decrementBalance(double amount) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(100), event -> {
                    double currentBalance = Double.parseDouble(txtBalance.getText().replace("$", ""));
                    if (currentBalance > 0) {
                        double decrement = Math.min(amount, currentBalance); // Ensure decrement doesn't go below zero
                        balance -= decrement;
                        txtBalance.setText(balance + "$");
                    }
                })
        );
        timeline.setCycleCount((int) Math.ceil(balance / amount)); // Number of cycles needed to decrement to 0
        timeline.play();
    }

    private void incrementBalance(double amount) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(100), event -> {
                    double currentBalance = Double.parseDouble(txtBalance.getText().replace("$", ""));
                    double targetBalance = currentBalance + amount;
                    if (currentBalance < targetBalance) {
                        double increment = Math.min(amount, targetBalance - currentBalance);
                        balance += increment;
                        txtBalance.setText(balance + "$");
                    }
                })
        );
        timeline.setCycleCount((int) Math.ceil(amount/1));
        timeline.play();
    }

    public void UpdatedbWallet(WalletClass ww){
        double newbalance = Double.parseDouble(balance+"");
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
}