package com.blockforge;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BlockForgeApp extends Application {

    @Override
    public void start(Stage stage) {

        MainWindow window = new MainWindow();

        Scene scene = new Scene(
                window.getRoot(),
                1200,
                800
        );

        scene.getStylesheets().add(
                Theme.createTempStylesheet()
        );

        stage.setTitle("BlockForge");
        stage.setMinWidth(1000);
        stage.setMinHeight(700);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}