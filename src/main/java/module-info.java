/**
 * This file is part of CS230Assignment. This is needed to run the program
 * 
 * @author intellij
 */
module com.example.cs230assignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.net.http;

    opens com.example.cs230assignment to javafx.fxml;

    exports com.example.cs230assignment;
}