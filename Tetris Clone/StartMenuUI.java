package org.example.tetrisclone;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
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
    private static final int BLOCK_SIZE = 60;

    public StartMenuUI() {
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
            letter.setFont(Font.font("Impact", FontWeight.BOLD, 174));
            letter.setFill(colors[i % colors.length]);
            title.getChildren().add(letter);
        }

        title.setPadding(new Insets(0, 0, 0, 0));
    }

    private void createStartButton() {
        start = new Button("Start");
        setButtonProperties(start);
    }

    private void createCloseButton(){
        close = new Button("Close");
        close.setOnAction(event -> {
            Platform.exit(); // Close the JavaFX application
        });

        setButtonProperties(close);
    }

    private void createOptionButton(){
        options = new Button("Options");
        setButtonProperties(options);
    }

    private void setButtonProperties(Button button) {
        button.setFont(Font.font("Impact", FontWeight.NORMAL, 44));
        button.setStyle("-fx-focus-color: transparent; " +
                "-fx-faint-focus-color: transparent;" +
                "-fx-background-color: white;" +
                "-fx-text-fill: black;" +
                "-fx-background-radius: 30;");
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
                // Mouse entered button

                button.setStyle("-fx-background-color: #CCCCCC; -fx-text-fill: black; -fx-background-radius: 30;");
                button.setScaleX(1.10); // Increase size by 10% horizontally
                button.setScaleY(1.10); // Increase size by 10% vertically
            }
            else {
                // Mouse exited button
                button.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-background-radius: 30;");
                button.setScaleX(1.0); // Reset size
                button.setScaleY(1.0); // Reset size
            }
        });
    }

    private void createHighScore(){
        highScore = new Label("Highscore: 0 points");
        highScore.setFont(new Font("Impact", 44));
        highScore.setPadding(new Insets(30, 0, 0, 0));
    }

    private void createLayout() {
        menuLayout = new VBox(30); // Spacing of 20 between elements
        menuLayout.setAlignment(Pos.CENTER); // Center align the VBox
        menuLayout.getChildren().addAll(title, start, options, close, highScore);
        menuLayout.setPrefSize(660, 1080);
        BackgroundInitialize backgroundUI = new BackgroundInitialize();
        backgroundUI.createBackgroundForUI(menuLayout);

        leftAnimationPane = new Pane();
        leftAnimationPane.setPrefSize(660, 1080); // Set a preferred size for the left animation pane

        rightAnimationPane = new Pane();
        rightAnimationPane.setPrefSize(660, 1080); // Set a preferred size for the right animation pane

        layout = new HBox(20);
        layout.getChildren().addAll(leftAnimationPane, menuLayout, rightAnimationPane);
        layout.setAlignment(Pos.CENTER);

       layout.setStyle("-fx-background-color: #444444;");
    }

    private void createFallingBlocksAnimation() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
            TetrisBlock leftBlock = TetrisBlock.createRandomBlock();
            leftAnimationPane.getChildren().add(leftBlock);
            animateFallingBlock(leftBlock, leftAnimationPane, leftAnimation);

            TetrisBlock rightBlock = TetrisBlock.createRandomBlock();
            rightAnimationPane.getChildren().add(rightBlock);
            animateFallingBlock(rightBlock, rightAnimationPane, rightAnimation);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void animateFallingBlock(TetrisBlock block, Pane animationPane, int animation) {
        // Initialize startX with a default value
        double startX = 0.0;

        if (animation == 1 || animation == 2) {
            //Calculate the maximum range within which startX can vary
            double maxRange = animationPane.getWidth() - (4 * BLOCK_SIZE);

            // Randomly determine the position of startX within the maximum range
            startX = Math.random() * maxRange;
        }

        block.setLayoutX(startX);
        block.setLayoutY(-BLOCK_SIZE * 4); // Start above the visible area

        TranslateTransition transition = new TranslateTransition(Duration.seconds(15), block);
        transition.setToY(animationPane.getHeight() + BLOCK_SIZE * 4); // End below the visible area
        transition.setOnFinished(event -> animationPane.getChildren().remove(block));
        transition.play();
    }
}
