package org.example.tetrisclone;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class BlockFallAnimationMenu {

    private static final byte BLOCK_SIZE = 50;
    private static final Duration ANIMATION_DURATION = Duration.seconds(10);

    private final Pane leftAnimationPane;
    private final Pane rightAnimationPane;
    private final byte leftAnimation;
    private final byte rightAnimation;
    private final Random random = new Random();

    public BlockFallAnimationMenu(Pane leftAnimationPane, Pane rightAnimationPane, byte leftAnimation, byte rightAnimation) {
        this.leftAnimationPane = leftAnimationPane;
        this.rightAnimationPane = rightAnimationPane;
        this.leftAnimation = leftAnimation;
        this.rightAnimation = rightAnimation;
    }

    public void createFallingBlocksAnimation() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2.5), event -> {
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

    private void animateFallingBlock(TetrisBlock block, Pane animationPane, byte animation) {
        double startX = 0.0;

        if (animation == 1){
            startX = leftSideAnimation(animationPane, block);
        }
        else if (animation == 2){
            startX = rightSideAnimation(animationPane, block);
        }

        block.setLayoutX(startX);
        block.setLayoutY(-BLOCK_SIZE);

        TranslateTransition transition = new TranslateTransition(ANIMATION_DURATION, block);
        transition.setToY(animationPane.getHeight() + BLOCK_SIZE);
        transition.setOnFinished(event -> Platform.runLater(() -> {
            animationPane.getChildren().remove(block);
            block.cleanup();
            System.gc(); // Explicitly call garbage collection for removing and collecting from the block memory
        }));
        transition.play();
    }

    private double leftSideAnimation(Pane animationPane, TetrisBlock block){
        double maxRange;
        if (block.getBlockType() == 'I') {
            maxRange = animationPane.getWidth() - (4 * BLOCK_SIZE ); // Adjust max range for I block
        }
        else{
            maxRange = animationPane.getWidth() - (3 * BLOCK_SIZE + 25);
        }

        double startX = random.nextFloat() * maxRange + BLOCK_SIZE;

        if (startX < 4 * BLOCK_SIZE ) {
            startX = 4 * BLOCK_SIZE;
        }

        if (startX > maxRange) {
            startX = maxRange;
        }

        return startX;
    }

    private double rightSideAnimation(Pane animationPane, TetrisBlock block) {
        double maxRange;
        if (block.getBlockType() == 'I') {
            maxRange = animationPane.getWidth() - (4 * BLOCK_SIZE ); // Adjust max range for I block
        }
        else{
            maxRange = animationPane.getWidth() - (3 * BLOCK_SIZE);
        }

        double startX = random.nextFloat() * maxRange + BLOCK_SIZE;

        if (startX < 0 ) {
            startX = 0;
        }

        if (startX > maxRange - 4 * BLOCK_SIZE) {
            startX = maxRange - 4 * BLOCK_SIZE;
        }

        return startX;
    }
}