package tech.numind;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var bar1 = new Separator(Orientation.HORIZONTAL);
        var bar2 = new Separator(Orientation.HORIZONTAL);
        var bar3 = new Separator(Orientation.HORIZONTAL);

        var filteredFails = new FilteredListFails();
        var fails = filteredFails.create();

        var filteredWorks = new FilteredListWorks();
        var works = filteredWorks.create();

        var label = new Label("Hello, JavaFX " + javafxVersion +
                ", running on Java " + javaVersion + ".");
        var gc = new Button("run gc");

        gc.setOnAction(event -> {
            System.gc();
        });

        var root = new VBox(label, bar1, gc, bar2, fails, bar3, works);
        root.setPadding(new Insets(10));
        root.setSpacing(10d);

        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}