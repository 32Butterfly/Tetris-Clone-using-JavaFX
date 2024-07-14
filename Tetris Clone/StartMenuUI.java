package org.example.tetrisclone;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartMenuUI {

    private TextFlow title;
    private Button start;
    private Button close;
    private Button options;
    private Label highScore;
    private VBox menuLayout;
    private Pane leftAnimationPane;
    private Pane rightAnimationPane;
    private HBox layout;
    private final int leftAnimation = 1;
    private final int rightAnimation = 2;
    private final Stage primaryStage; // Reference to the primary stage

    public StartMenuUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
        createStartButton();
        createTitle();
        createOptionButton();
        createCloseButton();
        createHighScore();
        createLayout();
        createFallingBlocksAnimation();
    }

    public HBox getLayout() {
        return layout;
    }

    private void createTitle() {
        String labelText = "Tetris";
        Color[] colors = new Color[]{
                Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE,
                Color.INDIGO, Color.VIOLET
        };

        title = new TextFlow();
        title.setTextAlignment(TextAlignment.CENTER);

        for (int i = 0; i < labelText.length(); ++i) {
            Text letter = new Text(String.valueOf(labelText.charAt(i)));
            letter.getStyleClass().add("title");
            letter.setFill(colors[i % colors.length]);
            title.getChildren().add(letter);
        }

        SequentialTransition sequentialTransition = getSequentialTransition();
        sequentialTransition.play();
    }

    private SequentialTransition getSequentialTransition() {
        TranslateTransition upTransition = new TranslateTransition(Duration.seconds(1), title);
        upTransition.setByY(-50); // Move up by 50 pixels

        TranslateTransition downTransition = new TranslateTransition(Duration.seconds(1), title);
        downTransition.setByY(50); // Move down by 50 pixels

        // Create a sequential transition for up and down movement
        SequentialTransition sequentialTransition = new SequentialTransition(title, upTransition, downTransition);
        sequentialTransition.setCycleCount(SequentialTransition.INDEFINITE); // Repeat indefinitely
        return sequentialTransition;
    }

    private void createStartButton() {
        start = new Button("Start");
        start.setOnAction(event -> {
            GameUI gameUI = new GameUI();
            Scene gameScene = gameUI.createGameScene();
            primaryStage.setScene(gameScene);
        });
        setButtonProperties(start);
    }

    private void createCloseButton() {
        close = new Button("Close");
        close.setOnAction(event -> Platform.exit());

        setButtonProperties(close);
    }

    private void createOptionButton() {
        options = new Button("Options");
        setButtonProperties(options);
    }

    private void setButtonProperties(Button button) {
        button.getStyleClass().add("custom-button");
        button.setPadding(new Insets(10));
        button.setMinWidth(200);
        button.setMaxWidth(200);
        button.setPrefHeight(60);
        button.setAlignment(Pos.CENTER);
        buttonHoverEffect(button);
    }

    private void buttonHoverEffect(Button button) {
        button.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                button.setScaleX(1.10);
                button.setScaleY(1.10);
            } else {
                button.setScaleX(1.0);
                button.setScaleY(1.0);
            }
        });
    }

    private void createHighScore() {
        highScore = new Label("Highscore: 0 points");
        highScore.getStyleClass().add("highscore");
        highScore.setPadding(new Insets(30, 0, 0, 0));
    }

    private void createLayout() {
        menuLayout = new VBox(30);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.getChildren().addAll(title, start, options, close, highScore);
        menuLayout.setPrefSize(640, 1080);
        menuLayout.getStyleClass().add("menu-background");

        leftAnimationPane = new Pane();
        leftAnimationPane.setPrefSize(640, 1080);
        leftAnimationPane.getStyleClass().add("pane-background");

        rightAnimationPane = new Pane();
        rightAnimationPane.setPrefSize(640, 1080);
        rightAnimationPane.getStyleClass().add("pane-background");

        layout = new HBox(0);
        layout.getChildren().addAll(leftAnimationPane, menuLayout, rightAnimationPane);
        layout.setAlignment(Pos.CENTER);
    }

    private void createFallingBlocksAnimation() {
        BlockFallAnimationMenu blockFallAnimationMenu = new BlockFallAnimationMenu(
                leftAnimationPane, rightAnimationPane, leftAnimation, rightAnimation);
        blockFallAnimationMenu.createFallingBlocksAnimation();
    }
}
