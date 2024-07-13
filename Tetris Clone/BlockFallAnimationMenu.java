package org.example.tetrisclone;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BlockFallAnimationMenu {

    private static final int BLOCK_SIZE = 50;

    private final Pane leftAnimationPane;
    private final Pane rightAnimationPane;
    private final int leftAnimation;
    private final int rightAnimation;

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
            startX = Math.random() * maxRange;
        }

        block.setLayoutX(startX);
        block.setLayoutY(-BLOCK_SIZE * 4);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(10), block);
        transition.setToY(animationPane.getHeight() + BLOCK_SIZE * 4);
        transition.setOnFinished(event -> animationPane.getChildren().remove(block));
        transition.play();
    }
}
