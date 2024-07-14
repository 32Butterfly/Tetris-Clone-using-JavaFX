/*
Evaldas Dmitrišin MIF VU Information Technologies upcoming 2nd year 3rd group student
Solo project recreating the infamous game tetris using JavaFX

Update 2:
-Background images now use css instead of being hardcoded. Easier to change and maintain
-Falling blocks now have a little safeguard making sure they don't spawn half on the screen or outside
completely. However, the rightpane seems to not work currently will fix it later.
-Pressing start now acts as a way to switch scenes however, there's nothing there yet.
-Refactored the code slightly making the performance a little bit better.
 */

package org.example.tetrisclone;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class Tetris extends Application {

    @Override
    public void start(Stage primaryStage) {
        StartMenuUI startMenuUI = new StartMenuUI(primaryStage);

        Scene scene = new Scene(startMenuUI.getLayout(), 1920, 1080);
        //add style sheet the css file
        try {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                    "/styles.css")).toExternalForm());
        } catch (NullPointerException e) {
            System.err.println("Resource 'styles.css' not found.");
            Platform.exit();
        } catch (Exception e) {
            System.err.println("Error loading 'styles.css': " + e.getMessage());
            Platform.exit();
        }

        primaryStage.setTitle("Tetris Clone");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
