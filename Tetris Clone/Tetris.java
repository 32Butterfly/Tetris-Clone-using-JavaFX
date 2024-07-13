package org.example.tetrisclone;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Tetris extends Application {

    @Override
    public void start(Stage primaryStage) {
        StartMenuUI startMenuUI = new StartMenuUI();

        Scene scene = new Scene(startMenuUI.getLayout(), 1920, 1080);
        primaryStage.setTitle("Tetris Clone");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
