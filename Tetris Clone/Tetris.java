/*
Evaldas Dmitrišin MIF VU Information Technologies upcoming 2nd year 3rd group student
Solo project recreating the infamous game tetris using JavaFX

Update 4:
-New class for initializing the stylesheet
-Started working on the game. Yet there's hardly any logic only being able to move the blocks
not rotate them or anything.
-Further optimization where possible.
-The blocks have different ranges / boundaries since each is different in length they need their own border max
so far only made the I vertical
-Remade the Menu button creation now all of them use 1 method to be created just need their names and what they
will do to be passed as parameters
 */

package org.example.tetrisclone;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Tetris extends Application {

    @Override
    public void start(Stage primaryStage) {
        StartMenuUI startMenuUI = new StartMenuUI(primaryStage);

        Scene scene = new Scene(startMenuUI.getLayout(), 1920, 1080);
        new StyleSheetInitialize(scene);

        primaryStage.setTitle("Tetris Clone");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
