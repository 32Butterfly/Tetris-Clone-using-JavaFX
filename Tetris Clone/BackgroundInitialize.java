package org.example.tetrisclone;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Objects;

public class BackgroundInitialize {
    public void createBackgroundForUI(VBox menuLayout){
        double vboxWidth = 660;
        double vboxHeight = 1080;

        try {
            // Load the image from resources folder
            Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    "/org/example/tetrisclone/Wallpaper.jpg")));

            // Create the background size to cover the VBox completely
            BackgroundSize backgroundSize = new BackgroundSize(vboxWidth, vboxHeight, false, false, true, true);

            // Create the background image
            BackgroundImage background = new BackgroundImage(
                    backgroundImage,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, backgroundSize);  // Use the custom BackgroundSize

            // Apply the background to your layout or pane
            menuLayout.setBackground(new Background(background));
        } catch (Exception e) {
            System.err.println("Failed to load background image: " + e.getMessage());
            // Handle the exception as needed
            Platform.exit(); //aka just close the program
        }
    }
}
