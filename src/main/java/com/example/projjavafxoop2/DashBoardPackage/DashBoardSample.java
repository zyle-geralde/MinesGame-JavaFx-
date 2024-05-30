package com.example.projjavafxoop2.DashBoardPackage;

import com.example.casino_finalproject_oop.HelloApplication;
import com.example.casino_finalproject_oop.HelloApplicationV2;
import com.example.demo.HelloApplicationV3;
import com.example.projjavafxoop2.LogSignPackage.ClickSoundThread;
import com.example.projjavafxoop2.LogSignPackage.LogInSignUpApplication;
import com.example.projjavafxoop2.MineGamePackage.MinegameApplication;
import com.example.projjavafxoop2.SqlConnect;
import com.example.projjavafxoop2.WalletClass;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DashBoardSample{

    int userid;
    String username;

    int walletid;

    double balance;

    VBox notificationsContainer;

    WalletClass userwallet = null;

    Stage primaryStage = null;

    public DashBoardSample(int userid,String username,int walletid,double balance){
        this.username = username;
        this.userid = userid;
        this.walletid = walletid;
        this.balance = balance;
        userwallet = new WalletClass(balance,walletid,userid,username);
    }
    public DashBoardSample(WalletClass userwallet){
        this.userwallet = userwallet;
    }
    public DashBoardSample(){

    }

    ArrayList<Shape> btnsidbar = new ArrayList<>();
    ArrayList<AnchorPane> anchorGameCov = new ArrayList<>();
    ArrayList<ImageView> imgArrow = new ArrayList<>();
    Label amountLabel = null;

    public void refresh(Stage stage) throws Exception {
        Scene scene = stage.getScene();

        amountLabel = (Label) scene.lookup("#amountLabel");
        amountLabel.setText(String.format("%.2f",userwallet.getBalance()));

        Thread curvecornerthread = new Thread(new CurveCornerThread(scene));
        curvecornerthread.start();

        AnchorPane sideBar = (AnchorPane) scene.lookup("#sideBar");
        Thread dsp1 = new Thread(new DisplayAnimateThread(sideBar,0,0.2,0.3));
        dsp1.start();

        AnchorPane moneyBar = (AnchorPane) scene.lookup("#moneyBar");
        Thread dsp2 = new Thread(new DisplayAnimateThread(moneyBar,1,0.3,0.3));
        dsp2.start();

        AnchorPane bannerBar = (AnchorPane) scene.lookup("#bannerBar");
        Thread dsp3 = new Thread(new DisplayAnimateThread(bannerBar,1,0.4,0.3));
        dsp3.start();

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
            GameButtonClass gba = new GameButtonClass(durdelay,0.1,anch);
            gba.getAnimate(1);
            durdelay+=0.1;
            anch.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Thread hoversound = new Thread(new ClickSoundThread());
                    hoversound.start();
                    int indx = anchorGameCov.indexOf(anch);
                    gba.actionEntered(imgArrow.get(indx));
                }
            });
            anch.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int indx = anchorGameCov.indexOf(anch);
                    gba.actionExit(imgArrow.get(indx));
                }
            });
        }

        btnsidbar.add((Shape) scene.lookup("#Rect1"));
        btnsidbar.add((Shape) scene.lookup("#Rect2"));
        btnsidbar.add((Shape) scene.lookup("#Rect3"));
        btnsidbar.add((Shape) scene.lookup("#Rect4"));
        btnsidbar.add((Shape) scene.lookup("#Rect5"));

        for(Shape btnsb: btnsidbar){
            GameButtonClass shapbut = new GameButtonClass(0.1);
            btnsb.setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {
                    Thread hoversound = new Thread(new ClickSoundThread());
                    hoversound.start();
                    shapbut.actionEntered(btnsb);}
            });

            btnsb.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    shapbut.actionExit(btnsb);
                }
            });
        }

        AnchorPane ViewWallet  = (AnchorPane) scene.lookup("#ViewWallet");
        ImageView walletBut = (ImageView) scene.lookup("#walletBut");
        walletBut.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                OpenWallet opn = new OpenWallet(ViewWallet);
                opn.onClickfunc();
            }
        });

        walletBut.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread hoversound = new Thread(new ClickSoundThread());
                hoversound.start();
            }
        });

        ImageView ExitWallet = (ImageView) scene.lookup("#ExitWallet");
        AnchorPane DepositBut = (AnchorPane) scene.lookup("#DepositBut");
        AnchorPane WithdrawBut = (AnchorPane) scene.lookup("#WithdrawBut");
        TextField AmountField = (TextField) scene.lookup("#AmountField");
        TextField AccountIdField = (TextField) scene.lookup("#AccountIdField");
        Label InvalidLabel= (Label) scene.lookup("#InvalidLabel");

        ExitWallet.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                ExitWalletClass ext = new ExitWalletClass(ExitWallet,DepositBut,WithdrawBut,AmountField,AccountIdField,InvalidLabel,ViewWallet);
                ext.onClickfunc();
            }
        });

        ExitWallet.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread hoversound = new Thread(new ClickSoundThread());
                hoversound.start();
            }
        });


        DepostWithdrawClass dpwth = new DepostWithdrawClass(ExitWallet,DepositBut,WithdrawBut,AmountField,AccountIdField,InvalidLabel,ViewWallet,userwallet,amountLabel);
        DepositBut.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                dpwth.onClickfunc(0,userid);

            }
        });

        DepositBut.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Thread hoversound = new Thread(new ClickSoundThread());
                hoversound.start();
            }
        });

        WithdrawBut.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                dpwth.onClickfunc(1,userid);
            }
        });

        WithdrawBut.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread hoversound = new Thread(new ClickSoundThread());
                hoversound.start();
            }
        });

        Shape LogOut = (Shape) scene.lookup("#Rect5");
        LogOut.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                LocalDateTime currentDateTime = LocalDateTime.now();

                // Define the format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

                // Format the current date and time
                String formattedDateTime = currentDateTime.format(formatter);
                appendTransaction("User Logged Out "+formattedDateTime,userid);

                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                try {
                    LogInSignUpApplication.AppInstance.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Shape MinesBut = (Shape) scene.lookup("#Rect4");
        MinesBut.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                MinegameApplication mineg = new MinegameApplication();
                try {
                    mineg.refresh(stage,userwallet);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        minegamebar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                MinegameApplication mineg = new MinegameApplication();
                try {
                    mineg.refresh(stage,userwallet);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        SlotMachBar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                HelloApplication slotmach = new HelloApplication();
                try {
                    slotmach.refresh(stage,userwallet);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Shape slotbut = (Shape) scene.lookup("#Rect1");
        slotbut.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                HelloApplication slotmach = new HelloApplication();
                try {
                    slotmach.refresh(stage,userwallet);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        colorgamebar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                HelloApplicationV2 colorgame= new HelloApplicationV2();
                try {
                    colorgame.refresh(stage,userwallet);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Shape colorgbot = (Shape) scene.lookup("#Rect3");
        colorgbot.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                HelloApplicationV2 colorgame= new HelloApplicationV2();
                try {
                    colorgame.refresh(stage,userwallet);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        cardsbar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                HelloApplicationV3 cardgame= new HelloApplicationV3();
                try {
                    cardgame.refresh(stage,userwallet);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Shape cardbot = (Shape) scene.lookup("#Rect2");
        cardbot.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread clickefect = new Thread(new ClickSelectedEffectThread());
                clickefect.start();
                HelloApplicationV3 cardgame= new HelloApplicationV3();
                try {
                    cardgame.refresh(stage,userwallet);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ImageView transbut = (ImageView) scene.lookup("#transbut");
        transbut.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                notificationsContainer = new VBox(20);
                notificationsContainer.setPadding(new Insets(5));
                notificationsContainer.setStyle("-fx-background-color: white; -fx-border-color: black;");

                // Create a scroll pane for the notifications container
                ScrollPane scrollPane = new ScrollPane(notificationsContainer);
                scrollPane.setPrefSize(700, 450);
                scrollPane.setFitToWidth(true);

                try (Connection connection = SqlConnect.getConnection();) {
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM transaction WHERE userid = ?");
                    statement.setInt(1,userid);
                    ResultSet resultSet = statement.executeQuery();

                    while (resultSet.next()) {
                        Label lbl = new Label(resultSet.getString("transString"));
                        notificationsContainer.getChildren().add(lbl);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


                if(primaryStage!=null){
                    primaryStage.close();
                }

                primaryStage = new Stage();
                primaryStage.setTitle("Transaction");
                primaryStage.setWidth(700);
                primaryStage.setHeight(450);
                primaryStage.setScene(new Scene(scrollPane,700,450));
                primaryStage.show();
            }
        });
    }


    public void appendTransaction(String trans,int uid){
        try (Connection connection = SqlConnect.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO transaction (userid, transString) VALUES (?, ?)");
            preparedStatement.setInt(1, uid);
            preparedStatement.setString(2, trans);

            int rr = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
