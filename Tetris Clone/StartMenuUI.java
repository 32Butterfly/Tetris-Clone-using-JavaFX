package org.example.tetrisclone;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartMenuUI {

    private final TextFlow title = new TextFlow();
    private Button start;
    private Button close;
    private Button options;
    private Label highScore;
    private final Pane leftAnimationPane = new Pane();
    private final Pane rightAnimationPane = new Pane();
    private HBox layout;
    private final Stage primaryStage; // Reference to the primary stage

    public StartMenuUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
        createTitle();
        createButtons();
        createHighScore();
        createLayout();
        createFallingBlocksAnimation();
    }

    public HBox getLayout() {
        return layout;
    }

    private void createTitle() {
        String labelText = "Tetris";

        title.setTextAlignment(TextAlignment.CENTER);
        title.getStyleClass().add("title");

        for (int i = 0; i < labelText.length(); ++i) {
            Text letter = new Text(String.valueOf(labelText.charAt(i)));
            letter.getStyleClass().add("title-" + i); // Add unique colour for each letter
            title.getChildren().add(letter);
        }
        // Schedule the animation to start after the whole UI is loaded
        Platform.runLater(() -> {
            SequentialTransition sequentialTransition = getSequentialTransition();
            sequentialTransition.play();
        });
    }

    private SequentialTransition getSequentialTransition() {
        TranslateTransition upTransition = new TranslateTransition(Duration.seconds(2), title);
        upTransition.setByY(-50); // Move up by 50 pixels

        TranslateTransition downTransition = new TranslateTransition(Duration.seconds(2), title);
        downTransition.setByY(50); // Move down by 50 pixels

        // Create a sequential transition for up and down movement
        SequentialTransition sequentialTransition = new SequentialTransition(upTransition, downTransition);
        sequentialTransition.setCycleCount(SequentialTransition.INDEFINITE); // Repeat indefinitely
        return sequentialTransition;
    }

    private void createButtons() {
        start = createButton("Start", event -> {
            GameUI gameUI = new GameUI();
            primaryStage.setScene(gameUI.getGame());
        });

        close = createButton("Close", event -> Platform.exit());

        options = createButton("Options", null);
    }

    //pass in the text that the button will have and what happens when the button is pressed, null = nothing happens
    private Button createButton(String text, EventHandler<ActionEvent> eventHandler) {
        Button button = new Button(text);
        if (eventHandler != null) {
            button.setOnAction(eventHandler);
        }
        setButtonProperties(button);
        return button;
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
        VBox menuLayout = new VBox(30);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.getChildren().addAll(title, start, options, close, highScore);
        menuLayout.setPrefSize(660,1080);
        menuLayout.getStyleClass().add("menu-background");

        leftAnimationPane.setPrefSize(660,1080);
        leftAnimationPane.getStyleClass().add("pane-background");

        rightAnimationPane.setPrefSize(660,1080);
        rightAnimationPane.getStyleClass().add("pane-background");

        layout = new HBox(0);
        layout.getChildren().addAll(leftAnimationPane, menuLayout, rightAnimationPane);
        layout.setAlignment(Pos.CENTER);
    }

    private void createFallingBlocksAnimation() {
        byte leftAnimation = 1;
        byte rightAnimation = 2;
        BlockFallAnimationMenu blockFallAnimationMenu = new BlockFallAnimationMenu(
                leftAnimationPane, rightAnimationPane, leftAnimation, rightAnimation);
        blockFallAnimationMenu.createFallingBlocksAnimation();
    }
}
