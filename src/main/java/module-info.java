module com.example.projjavafxoop2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires javafx.media;
    requires java.sql;

    opens com.example.projjavafxoop2 to javafx.fxml;
    exports com.example.projjavafxoop2;
    exports com.example.projjavafxoop2.LogSignPackage;
    opens com.example.projjavafxoop2.LogSignPackage to javafx.fxml;
    exports com.example.projjavafxoop2.DashBoardPackage;
    opens com.example.projjavafxoop2.DashBoardPackage to javafx.fxml;
    exports com.example.projjavafxoop2.MineGamePackage;
    opens com.example.projjavafxoop2.MineGamePackage to javafx.fxml;

    exports com.example.casino_finalproject_oop to javafx.graphics;

    opens com.example.casino_finalproject_oop to javafx.fxml;

    opens com.example.demo to javafx.graphics;
    exports com.example.demo;
}