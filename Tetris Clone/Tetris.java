/*
Evaldas Dmitrišin MIF VU Information Technologies upcoming 2nd year 3rd group student
Solo project recreating the infamous game tetris using JavaFX

Update 3:
-Huge update on the memory usage:
-The code now when left running won't hoard not needed ram memory. Previously it would take 30mb each
30 seconds leading to any computer eventually falling victim to this not the best piece of software
-Adjusted the data types where needed instead of int usage of byte and making things final.
-Update how the blocks fall. Both sides blocks now should not clip through the side or spawn outside, of
visible area.
-The title now is made completely from css instead of hard-coding the logic
Further optimization coming soon as well as start of the actual game!
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
