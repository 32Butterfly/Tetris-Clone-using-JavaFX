package org.example.tetrisclone;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import java.util.Random;

public class TetrisBlock extends Group {

    private static final byte SIZE = 50;  // Size of each square block
    private final char blockType;
    private static final Random random = new Random();
    private TetrisBlock self;

    public TetrisBlock(Color color, char blockType, Rectangle... rectangles) {
        this.blockType = blockType;  // Set the block type
        this.self = this;
        for (Rectangle rectangle : rectangles) {
            rectangle.setFill(color);
            getChildren().add(rectangle);
        }
    }

    public char getBlockType() {
        return blockType;
    }

    public static TetrisBlock createRandomBlock() {
        Color color = Color.color(random.nextFloat(), random.nextFloat(), random.nextFloat());
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
        return new TetrisBlock(color, 'I',
                new Rectangle(0, 0, SIZE, SIZE),
                new Rectangle(SIZE, 0, SIZE, SIZE),
                new Rectangle(2 * SIZE, 0, SIZE, SIZE),
                new Rectangle(3 * SIZE, 0, SIZE, SIZE));
    }

    private static TetrisBlock createJBlock(Color color) {
        return new TetrisBlock(color, 'J',
                new Rectangle(0, 0, SIZE, SIZE),
                new Rectangle(0, SIZE, SIZE, SIZE),
                new Rectangle(SIZE, SIZE, SIZE, SIZE),
                new Rectangle(2 * SIZE, SIZE, SIZE, SIZE));
    }

    private static TetrisBlock createLBlock(Color color) {
        return new TetrisBlock(color, 'L',
                new Rectangle(2 * SIZE, 0, SIZE, SIZE),
                new Rectangle(0, SIZE, SIZE, SIZE),
                new Rectangle(SIZE, SIZE, SIZE, SIZE),
                new Rectangle(2 * SIZE, SIZE, SIZE, SIZE));
    }

    private static TetrisBlock createOBlock(Color color) {
        return new TetrisBlock(color, 'O',
                new Rectangle(0, 0, SIZE, SIZE),
                new Rectangle(SIZE, 0, SIZE, SIZE),
                new Rectangle(0, SIZE, SIZE, SIZE),
                new Rectangle(SIZE, SIZE, SIZE, SIZE));
    }

    private static TetrisBlock createSBlock(Color color) {
        return new TetrisBlock(color, 'S',
                new Rectangle(SIZE, 0, SIZE, SIZE),
                new Rectangle(2 * SIZE, 0, SIZE, SIZE),
                new Rectangle(0, SIZE, SIZE, SIZE),
                new Rectangle(SIZE, SIZE, SIZE, SIZE));
    }

    private static TetrisBlock createTBlock(Color color) {
        return new TetrisBlock(color, 'T',
                new Rectangle(SIZE, 0, SIZE, SIZE),
                new Rectangle(0, SIZE, SIZE, SIZE),
                new Rectangle(SIZE, SIZE, SIZE, SIZE),
                new Rectangle(2 * SIZE, SIZE, SIZE, SIZE));
    }

    private static TetrisBlock createZBlock(Color color) {
        return new TetrisBlock(color, 'Z',
                new Rectangle(0, 0, SIZE, SIZE),
                new Rectangle(SIZE, 0, SIZE, SIZE),
                new Rectangle(SIZE, SIZE, SIZE, SIZE),
                new Rectangle(2 * SIZE, SIZE, SIZE, SIZE));
    }

    public void cleanup() {
        // Clear all children (Rectangles)
        getChildren().clear();
        self = null;
    }
}
