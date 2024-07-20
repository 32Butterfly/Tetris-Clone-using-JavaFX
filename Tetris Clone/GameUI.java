package org.example.tetrisclone;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

import static javafx.geometry.Pos.*;

public class GameUI {
    private final Scene game;
    private Label scoreLabel;
    private Pane gameArea; // Playable area for the Tetris game
    private final Random random = new Random();
    private final byte BLOCK_SIZE = 50;

    private static final Duration ANIMATION_DURATION = Duration.seconds(10);

    public GameUI() {
        createGameScene();
        createFallingBlocksAnimation();
        game = createScene(); // Initialize the scene
        setupKeyEvents();
    }

    public Scene getGame() {
        return game;
    }

    private void createGameScene() {
        scoreLabel = new Label("Score: 0 points");
        scoreLabel.setAlignment(TOP_RIGHT);
        scoreLabel.getStyleClass().add("highscore");

        // Create the game area (where blocks will be displayed)
        gameArea = new Pane();
    }

    private Scene createScene() {
        // Create a root pane
        Pane rootPane = new Pane();

        new StyleSheetInitialize(rootPane);

        // Define the size for the game area
        double gameAreaWidth = 1920 * 0.4; // 50% of 1920
        double gameAreaHeight = 1080 * 0.78; // 70% of 920

        // Create and size the game area
        gameArea.setPrefSize(gameAreaWidth, gameAreaHeight);

        // Center the game area within the root pane
        gameArea.setLayoutX((1920 - gameAreaWidth) / 2); // Center horizontally
        gameArea.setLayoutY((1080 - gameAreaHeight - 90) / 2); // Center vertically
        gameArea.getStyleClass().add("game-area");

        scoreLabel.setLayoutX(1920 - 520); // Position the score label
        scoreLabel.setLayoutY((1080 - gameAreaHeight) / 2 ); // Position the score label above the game area

        // Add nodes to the root pane
        rootPane.getChildren().addAll(gameArea, scoreLabel);

        // Set the size of the entire layout
        rootPane.setPrefSize(1920, 1080); // Full screen size

        return new Scene(rootPane);
    }

    public void createFallingBlocksAnimation() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2.5), event -> {
            TetrisBlock block = TetrisBlock.createRandomBlock();
            gameArea.getChildren().add(block);
            animateFallingBlock(block, gameArea);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void animateFallingBlock(TetrisBlock block, Pane animationPane) {
        double startX = fallingAnimation(block);

        block.setLayoutX(startX);
        block.setLayoutY(0);

        TranslateTransition transition = new TranslateTransition(ANIMATION_DURATION, block);
        if (block.getBlockType() == 'I'){
            transition.setToY(animationPane.getHeight() - BLOCK_SIZE);
        }
        else{
            transition.setToY(animationPane.getHeight() - 2 * BLOCK_SIZE);
        }

        transition.setOnFinished(event -> Platform.runLater(() -> {
            animationPane.getChildren().remove(block);
            block.cleanup();
            System.gc(); // Explicitly call garbage collection for removing and collecting from the block memory
        }));
        transition.play();
    }

    private double fallingAnimation(TetrisBlock block) {
        double maxRange;
        if (block.getBlockType() == 'I') {
            maxRange = gameArea.getWidth() - (4 * BLOCK_SIZE); // Adjust max range for I block
        } else {
            maxRange = gameArea.getWidth() - (3 * BLOCK_SIZE);
        }

        double startX = random.nextFloat() * maxRange + BLOCK_SIZE;

        if (startX < 4 * BLOCK_SIZE) {
            startX = 4 * BLOCK_SIZE;
        }

        if (startX > maxRange) {
            startX = maxRange;
        }
        return startX;
    }

    private void setupKeyEvents() {
        game.setOnKeyPressed(this::handleKeyPress);
    }

    private void handleKeyPress(KeyEvent event) {
        TetrisBlock currentBlock = (TetrisBlock) gameArea.getChildren().get(gameArea.getChildren().size() - 1); // Get the current falling block
        if (currentBlock == null) {
            return;
        }

        double newX = currentBlock.getLayoutX();
        double newY = currentBlock.getLayoutY();

        if (event.getCode() == KeyCode.A) {
            newX -= BLOCK_SIZE; // Move left
        } else if (event.getCode() == KeyCode.D) {
            newX += BLOCK_SIZE; // Move right
        } else if (event.getCode() == KeyCode.S) {
            newY += BLOCK_SIZE; // Move down
        }
        if (newX >= 0 && newX <= gameArea.getWidth() - currentBlock.getWidth()) {
            currentBlock.setLayoutX(newX);
        }
        if (newY <= gameArea.getHeight() - currentBlock.getHeight()) {
            currentBlock.setLayoutY(newY);
        }
    }
}