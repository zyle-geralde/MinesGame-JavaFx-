package com.example.casino_finalproject_oop;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class BetInputHandler {
    private boolean inputFlag = false;
    private boolean tokenFlag = false;
    private HBox hbBetInput;
    private TextField bet_amount;
    private Image bg;
    private Label lblAmount;
    private ImageView imgToken;
    private GridPane grid;

    public static Rectangle container = new Rectangle(1200, 770);
    static Image maxBet;
    static ImageView Max;

    public BetInputHandler(GridPane gridpane, ImageView imgColor, String imgPath, int col, int row, int colSpan, int rowSpan, int index) {
        this.grid = gridpane;

        hbBetInput = new HBox();
//        hbBetInput.setSpacing(3);
        hbBetInput.setAlignment(Pos.CENTER);

        bg = new Image(getClass().getResourceAsStream("input-field.png"));
        ImageView imgBG = new ImageView(bg);
        StackPane spInput = new StackPane(imgBG);
        spInput.setPadding(new Insets(0, 0, 0, 16));

        bet_amount = new TextField();
        bet_amount.setFont(Font.font(15));
        bet_amount.setPrefWidth(300);
//        bet_amount.setMaxWidth(Double.MAX_VALUE);
        bet_amount.setPromptText("Set your bet");
        bet_amount.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-text-fill: black;");

        Image image = new Image(getClass().getResourceAsStream("enter-button.png"));
        ImageView btnconfirm = new ImageView(image);

        hbBetInput.getChildren().addAll(bet_amount, btnconfirm);

        lblAmount = new Label();
        lblAmount.getStyleClass().add("game-text");
        lblAmount.setFont(HelloApplicationV2.setCustomFont(20));
        Font font = lblAmount.getFont();
        System.out.println("Font Family: " + font.getFamily());
        Insets tfMargin = new Insets(25);
        lblAmount.setTextAlignment(TextAlignment.CENTER);
        grid.setHalignment(lblAmount, HPos.CENTER);
        lblAmount.setVisible(false);
        grid.add(lblAmount, col, row, colSpan, rowSpan);
        GridPane.setMargin(lblAmount, tfMargin);

        Image token = new Image(getClass().getResourceAsStream(imgPath));
        imgToken = new ImageView(token);
        imgToken.setFitWidth(120);
        imgToken.setFitHeight(120);
        imgToken.setVisible(false);
        grid.add(imgToken, col, row - 1, 3, 1);
        GridPane.setHalignment(imgToken, HPos.CENTER);

        imgColor.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(inputFlag);
                inputFlag = !inputFlag;
                if (inputFlag) {
                    spInput.setVisible(true);
                    hbBetInput.setVisible(true);
                    hbBetInput.setPadding(new Insets(0, 21, 0, 17));
                    grid.add(spInput, col, row, 1, 1);
                    grid.add(hbBetInput, col, row, colSpan, rowSpan);
                } else {
                    grid.getChildren().removeAll(hbBetInput, spInput);
                }
            }
        });

        bet_amount.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                if(bet_amount.getText().isEmpty())
//                    HelloApplication.count--;
                HelloApplicationV2.betsMap[index] = false;
                tokenFlag = false;
                imgToken.setVisible(false);
                lblAmount.setVisible(false);
            }
        });

        btnconfirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(HelloApplicationV2.count >= 3 && lblAmount.getText().isEmpty()) {
                    System.out.println("Maximum of 3 tile bets only!");
                    HelloApplicationV2.displayMessage("Maximum of 3 tile bets only!", grid);
                } else {
                    try{
                        String amount = bet_amount.getText();
                        String amount1 = lblAmount.getText();
                        if (!amount1.isEmpty()) {
                            HelloApplicationV2.sceneTitle.setFont(HelloApplicationV2.setCustomFont(35));
                            try{
                                HelloApplicationV2.total_bet -= Integer.parseInt(amount1);
                            } catch (Exception e) {
                                HelloApplicationV2.total_bet -= Integer.parseInt(amount1.substring(0, amount1.length() - 1));
                            }
                            HelloApplicationV2.txtTotalBet.setText(Integer.toString(HelloApplicationV2.total_bet));
                            HelloApplicationV2.count--;
                        }
                        grid.setRowSpan(HelloApplicationV2.sceneTitle, 1);
                        HelloApplicationV2.sceneTitle.setFont(HelloApplicationV2.setCustomFont(35));
                        HelloApplicationV2.sceneTitle.setText("Total Bet");
                        HelloApplicationV2.txtTotalBet.setVisible(true);
                        hbBetInput.setVisible(false);
                        spInput.setVisible(false);
                        lblAmount.setText(amount + "$");

                        tokenFlag = !tokenFlag;
                        if (!amount.isEmpty()) { // Check if amount is not empty
                            System.out.println(HelloApplicationV2.sceneTitle.getText());
                            HelloApplicationV2.count++;
                            imgToken.setVisible(true);
                            lblAmount.setVisible(true);
                            HelloApplicationV2.total_bet += Integer.parseInt(amount);
                            HelloApplicationV2.txtTotalBet.setText(Integer.toString(HelloApplicationV2.total_bet));
                            HelloApplicationV2.betsMap[index] = true;

                            // Pop animation
                            ScaleTransition st = new ScaleTransition(Duration.millis(200), imgToken);
                            st.setFromX(0.5);
                            st.setFromY(0.5);
                            st.setToX(1.0);
                            st.setToY(1.0);
                            st.setInterpolator(Interpolator.EASE_OUT);
                            st.play();
                        } else {
                            HelloApplicationV2.betsMap[index] = false;
                            imgToken.setVisible(false);
                            lblAmount.setVisible(false);
                        }
                    } catch (NumberFormatException e) {
                        HelloApplicationV2.displayMessage("BOGO KA", grid);
                        HelloApplicationV2.count--;
                        lblAmount.setText("");
                        imgToken.setVisible(false);
                    }
                }
            }
        });
    }

    public void setTokenFlag(boolean tokenFlag) {
        this.tokenFlag = tokenFlag;
    }

    public boolean isTokenVisible() {
        return tokenFlag;
    }

    public void clearAll() {
        imgToken.setVisible(false);
        lblAmount.setVisible(false);
        tokenFlag = false;
        lblAmount.setText("0");
        bet_amount.setText("");
    }

    public int getBetAmount() {
        String amount = lblAmount.getText();
        if(!amount.isEmpty()) {
            System.out.println("Not zero: " + Integer.parseInt(amount.substring(0, amount.length()-1)));
            return Integer.parseInt(amount.substring(0, amount.length()-1));
        }else {
            System.out.println("Zerooo");
            return 0;
        }
    }

    public void showMax() {
        if (!grid.getChildren().contains(container)) {
            container.setFill(new Color(0, 0, 0, 0.5));
            grid.add(container, 0, 4);
        }

        maxBet = new Image(HelloApplicationV2.class.getResourceAsStream("nice-one.png"));
        Max = new ImageView(maxBet);
        grid.add(Max,  0, 0, 18, 10);
        GridPane.setHalignment(Max, HPos.CENTER);
        GridPane.setValignment(Max, VPos.CENTER);
    }
}
