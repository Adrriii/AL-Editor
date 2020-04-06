package editor.userinterface.javafximpl;

import editor.application.App;
import editor.model.Model;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class JavaFXApplication extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                App.model.Stop();
            }
        });

        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("Editor");
        stage.setScene(scene);
        stage.show();

    }

}