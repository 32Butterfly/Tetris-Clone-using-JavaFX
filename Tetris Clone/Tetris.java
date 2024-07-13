/*
Evaldas Dmitrišin MIF VU Information Technologies upcoming 2nd year 3rd group student
Solo project recreating the infamous game tetris using JavaFX

Update 1:
-Added css stylesheet since I will need lots of css I figured making a separate file would be better than
hard-coding all the values
-Animation for the title screen aka 'Tetris' it now bops up and down creating more lively UI.
-Refactored the code making a new class which is responsible for the animation of blocks falling in
the menu UI.
-Removed main since it served no purpose considering you can run the project with the Tetris class.
 */

package org.example.tetrisclone;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class Tetris extends Application {

    @Override
    public void start(Stage primaryStage) {
        StartMenuUI startMenuUI = new StartMenuUI();

        Scene scene = new Scene(startMenuUI.getLayout(), 1920, 1080);
        //add style sheet the css file
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                "/css/styles.css")).toExternalForm());

        primaryStage.setTitle("Tetris Clone");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
