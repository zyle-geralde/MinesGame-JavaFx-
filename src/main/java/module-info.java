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
}