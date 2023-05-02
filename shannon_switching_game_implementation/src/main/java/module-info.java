module com.example.shannon_switching_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires eu.hansolo.fx.countries;
    requires com.almasb.fxgl.all;

    opens com.example.shannon_switching_game to javafx.fxml;
    exports com.example.shannon_switching_game;
}