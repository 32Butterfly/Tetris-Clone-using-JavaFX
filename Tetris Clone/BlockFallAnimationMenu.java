package org.example.tetrisclone;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class BlockFallAnimationMenu {

    private static final int BLOCK_SIZE = 50;
    private static final Duration ANIMATION_DURATION = Duration.seconds(10);

    private final Pane leftAnimationPane;
    private final Pane rightAnimationPane;
    private final int leftAnimation;
    private final int rightAnimation;
    private Random random = new Random();

    public BlockFallAnimationMenu(Pane leftAnimationPane, Pane rightAnimationPane, int leftAnimation, int rightAnimation) {
        this.leftAnimationPane = leftAnimationPane;
        this.rightAnimationPane = rightAnimationPane;
        this.leftAnimation = leftAnimation;
        this.rightAnimation = rightAnimation;
    }

    public void createFallingBlocksAnimation() {
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
        double startX = 0.0;

        if (animation == 1 || animation == 2) {
            double maxRange = animationPane.getWidth() - (4 * BLOCK_SIZE);
            startX = random.nextFloat() * maxRange + BLOCK_SIZE;

            // Ensure startX is at least 4 * BLOCK_SIZE
            if (startX < 4 * BLOCK_SIZE ) {
                startX = 4 * BLOCK_SIZE;
            }
            // Ensure startX does not exceed animationPane's width minus 4 * BLOCK_SIZE
            if (startX > animationPane.getWidth() - 4 * BLOCK_SIZE ) {
                startX = animationPane.getWidth() - 4 * BLOCK_SIZE;
                System.out.println(startX);
            }
        }

        block.setLayoutX(startX);
        block.setLayoutY(-BLOCK_SIZE);

        TranslateTransition transition = new TranslateTransition(ANIMATION_DURATION, block);
        transition.setToY(animationPane.getHeight() + BLOCK_SIZE);
        transition.setOnFinished(event -> animationPane.getChildren().remove(block));
        transition.play();
    }
}
