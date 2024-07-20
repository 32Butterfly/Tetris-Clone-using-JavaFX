package org.example.tetrisclone;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class StyleSheetInitialize {
    public StyleSheetInitialize(Scene scene) {
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
    }

    //overloading the method so it makes the stylesheet usable in a pane
    public StyleSheetInitialize(Pane pane) {
        //add style sheet the css file
        try {
            pane.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                    "/styles.css")).toExternalForm());
        } catch (NullPointerException e) {
            System.err.println("Resource 'styles.css' not found.");
            Platform.exit();
        } catch (Exception e) {
            System.err.println("Error loading 'styles.css': " + e.getMessage());
            Platform.exit();
        }
    }
}
