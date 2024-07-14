package org.example.tetrisclone;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class GameUI {
    public Scene createGameScene() {
        StackPane gameLayout = new StackPane();
        Text gameText = new Text("Game Scene");
        gameLayout.getChildren().add(gameText);
        return new Scene(gameLayout, 1920, 1080);
    }
}
