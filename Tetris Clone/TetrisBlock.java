package org.example.tetrisclone;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import java.util.Random;

public class TetrisBlock extends Group {

    private static final int SIZE = 50;  // Size of each square block

    public TetrisBlock(Color color, Rectangle... rectangles) {
        for (Rectangle rectangle : rectangles) {
            rectangle.setFill(color);
            getChildren().add(rectangle);
        }
    }

    public static TetrisBlock createRandomBlock() {
        Random random = new Random();
        Color color = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
        return switch (random.nextInt(7)) {
            case 1 -> createJBlock(color);
            case 2 -> createLBlock(color);
            case 3 -> createOBlock(color);
            case 4 -> createSBlock(color);
            case 5 -> createTBlock(color);
            case 6 -> createZBlock(color);
            default -> createIBlock(color);
        };
    }

    private static TetrisBlock createIBlock(Color color) {
        return new TetrisBlock(color,
                new Rectangle(0, 0, SIZE, SIZE),
                new Rectangle(SIZE, 0, SIZE, SIZE),
                new Rectangle(2 * SIZE, 0, SIZE, SIZE),
                new Rectangle(3 * SIZE, 0, SIZE, SIZE));
    }

    private static TetrisBlock createJBlock(Color color) {
        return new TetrisBlock(color,
                new Rectangle(0, 0, SIZE, SIZE),
                new Rectangle(0, SIZE, SIZE, SIZE),
                new Rectangle(SIZE, SIZE, SIZE, SIZE),
                new Rectangle(2 * SIZE, SIZE, SIZE, SIZE));
    }

    private static TetrisBlock createLBlock(Color color) {
        return new TetrisBlock(color,
                new Rectangle(2 * SIZE, 0, SIZE, SIZE),
                new Rectangle(0, SIZE, SIZE, SIZE),
                new Rectangle(SIZE, SIZE, SIZE, SIZE),
                new Rectangle(2 * SIZE, SIZE, SIZE, SIZE));
    }

    private static TetrisBlock createOBlock(Color color) {
        return new TetrisBlock(color,
                new Rectangle(0, 0, SIZE, SIZE),
                new Rectangle(SIZE, 0, SIZE, SIZE),
                new Rectangle(0, SIZE, SIZE, SIZE),
                new Rectangle(SIZE, SIZE, SIZE, SIZE));
    }

    private static TetrisBlock createSBlock(Color color) {
        return new TetrisBlock(color,
                new Rectangle(SIZE, 0, SIZE, SIZE),
                new Rectangle(2 * SIZE, 0, SIZE, SIZE),
                new Rectangle(0, SIZE, SIZE, SIZE),
                new Rectangle(SIZE, SIZE, SIZE, SIZE));
    }

    private static TetrisBlock createTBlock(Color color) {
        return new TetrisBlock(color,
                new Rectangle(SIZE, 0, SIZE, SIZE),
                new Rectangle(0, SIZE, SIZE, SIZE),
                new Rectangle(SIZE, SIZE, SIZE, SIZE),
                new Rectangle(2 * SIZE, SIZE, SIZE, SIZE));
    }

    private static TetrisBlock createZBlock(Color color) {
        return new TetrisBlock(color,
                new Rectangle(0, 0, SIZE, SIZE),
                new Rectangle(SIZE, 0, SIZE, SIZE),
                new Rectangle(SIZE, SIZE, SIZE, SIZE),
                new Rectangle(2 * SIZE, SIZE, SIZE, SIZE));
    }
}
