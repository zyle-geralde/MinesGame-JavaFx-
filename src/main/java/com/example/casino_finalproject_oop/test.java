package com.example.casino_finalproject_oop;//import javafx.geometry.Insets;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.text.Font;
//
//public class BetInputHandler {
//
//    private boolean isVisible = false;
//    private HBox hbBetInput;
//    private TextField bet_amount;
//    private Label lblAmount;
//    private ImageView imgBToken;
//
//    public BetInputHandler(GridPane grid, String imageName, int col, int row, int colSpan, int rowSpan) {
//        hbBetInput = new HBox();
//        hbBetInput.setSpacing(5);
//        hbBetInput.setAlignment(Pos.CENTER);
//
//        bet_amount = new TextField();
//        bet_amount.setFont(Font.font(15));
//        bet_amount.setPrefWidth(500);
//        bet_amount.setMaxWidth(Double.MAX_VALUE);
//        bet_amount.setPromptText("Set your bet");
//
//        Image image = new Image(getClass().getResourceAsStream(imageName));
//        ImageView btnConfirm = new ImageView(image);
//
//        hbBetInput.getChildren().addAll(bet_amount, btnConfirm);
//
//        lblAmount = new Label();
//        Insets tfMargin = new Insets(25);
//        lblAmount.setVisible(false);
//        grid.add(lblAmount, col, row, colSpan, rowSpan);
//        GridPane.setMargin(lblAmount, tfMargin);
//
//        Image blueToken = new Image(getClass().getResourceAsStream("blue-token.png"));
//        imgBToken = new ImageView(blueToken);
//        imgBToken.setFitWidth(120);
//        imgBToken.setFitHeight(120);
//        imgBToken.setVisible(false);
//        grid.add(imgBToken, col, row - 1);
//
//        // Event handling
//        bet_amount.setOnMouseClicked(e -> {
//            imgBToken.setVisible(false);
//            lblAmount.setVisible(false);
//        });
//
//        btnConfirm.setOnMouseClicked(e -> {
//            String amount = bet_amount.getText();
//            hbBetInput.setVisible(false);
//            lblAmount.setText(amount);
//            if (!amount.isEmpty()) {
//                imgBToken.setVisible(true);
//                lblAmount.setVisible(true);
//            } else {
//                imgBToken

/*

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

HBox hbBetInput = new HBox();
        hbBetInput.setSpacing(3); // Set spacing between blocks
        hbBetInput.setAlignment(Pos.CENTER);

TextField bet_amount = new TextField();
        bet_amount.setFont(Font.font(15));
        bet_amount.setPrefWidth(500); // Set preferred width
        bet_amount.setMaxWidth(Double.MAX_VALUE);
        bet_amount.setPromptText("Set your bet");

Image image = new Image(getClass().getResourceAsStream("confirm.png"));
ImageView btnconfirm = new ImageView(image);

        hbBetInput.getChildren().addAll(bet_amount, btnconfirm);

Label lblAmount = new Label();
Insets tfMargin = new Insets(25);
        lblAmount.setVisible(false);
        grid.add(lblAmount, 1, 5, 3, 1);
        GridPane.setMargin(lblAmount, tfMargin);

Image blue_token = new Image(getClass().getResourceAsStream("blue-token.png"));
ImageView imgBToken = new ImageView(blue_token);
        imgBToken.setFitWidth(120);
        imgBToken.setFitHeight(120);
        imgBToken.setVisible(false);
        grid.add(imgBToken, 1, 4);

        imageBlue.setOnMouseClicked(new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println(hbBlueFlag);
        hbBlueFlag = !hbBlueFlag;
        if (hbBlueFlag) {
            hbBetInput.setVisible(true);
            hbBetInput.setPadding(new Insets(0, 15, 0, 15));
            grid.add(hbBetInput, 1, 5, 3, 1);
        } else {
            grid.getChildren().remove(hbBetInput);
        }
    }
});

        bet_amount.setOnMouseClicked(new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent mouseEvent) {
        imgGTFlag = false;
        imgBToken.setVisible(false);
        lblAmount.setVisible(false);
    }
});

        btnconfirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent mouseEvent) {
        String amount = bet_amount.getText();
        hbBetInput.setVisible(false);
        lblAmount.setText(amount);

        imgGTFlag = !imgGTFlag;
        if (!amount.isEmpty()) { // Check if amount is not empty
            imgBToken.setVisible(true);
            lblAmount.setVisible(true);
        } else {
            imgBToken.setVisible(false);
            lblAmount.setVisible(false);
        }
    }
});



roll.setOnAction(e -> {
            for (int i = 0; i < NUM_BLOCK; i++) {
                RotateTransition rt = new RotateTransition();
                rt.setByAngle(360);
                rt.setNode(blockList.get(i));
                rt.setDuration(Duration.millis(600));
                rt.play();
                int x = i;
                rt.setOnFinished(j -> blockList.get(x).setImage(new Image(getClass().getResourceAsStream(rollBlock() + ".png"), 100, 0, true, true)));
            }
        });
 */

/*
roll.setOnAction(e -> {
    boolean isAnyTokenVisible = false;
    for (BetInputHandler handler : handlers) {
        if (handler.isTokenVisible()) {
            isAnyTokenVisible = true;
            break;
        }
    }
    if (isAnyTokenVisible) {
        // Roll action logic
        for (int i = 0; i < NUM_BLOCK; i++) {
            RotateTransition rt = new RotateTransition();
            rt.setByAngle(360);
            rt.setNode(blockList.get(i));
            rt.setDuration(Duration.millis(600));
            rt.play();
            int x = i;
            rt.setOnFinished(j -> blockList.get(x).setImage(new Image(getClass().getResourceAsStream(rollBlock() + ".png"), 100, 0, true, true)));
        }
    } else {
        // Inform the user that they need to place a bet first
    }
});

 */



//        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                Node target = (Node) event.getTarget();
////                if (target != imageBlue || target != imageGreen || target != imagePink ||
////                        target != imageRed || target != imageWhite || target != imageYellow) {
////                    hbBetInput.setVisible(false); // Hide hbBetInput
////                }
//                if (target != hbBetInput) {
//                    hbBetInput.setVisible(false); // Hide hbBetInput
//                }
//            }
//        });


//                    RotateTransition rt = new RotateTransition();
//                    rt.setByAngle(360);
//                    rt.setNode(blockList.get(i));
//                    rt.setDuration(Duration.millis(600));
//                    rt.setInterpolator(Interpolator.EASE_OUT);
//                    rt.setAutoReverse(false);
//                    rt.play();
//                    int x = i;
//                    rt.setOnFinished(new EventHandler<ActionEvent>() {
//                        @Override
//                        public void handle(ActionEvent event) {
//                            Image img = new Image(String.format("file:src/main/resources/com/example/casino_finalproject_oop/%s.png",rollBlock()));
//                            blockList.get(x).setImage(img);
//                            String imageUrl = img.getUrl();
//                            int numberRep = Character.getNumericValue(imageUrl.charAt(imageUrl.length() - 5));
//                            rolledFaces[x] = numberRep - 1;
//                            System.out.println("Rolled face " + (x + 1) + ": " + (numberRep - 1));
//
//                            finishedAnimations[0]++; // Increment the count of finished animations
//                            if (finishedAnimations[0] == NUM_BLOCK) {
//                                calculateWin();
//                            }
//                        }
//                    });


//                Platform.runLater(() -> calculateWin());