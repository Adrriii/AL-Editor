package editor.userinterface.javafximpl;

import editor.application.App;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class JavaFXApplication extends Application implements Runnable {

    static volatile private Group root;
    static volatile private Scene scene;
    static volatile private Stage stage;

    static volatile public boolean ready = false;
    static volatile public boolean update = true;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        JavaFXApplication.stage = stage;

        JavaFXApplication.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                App.model.Stop();
            }
        });

        JavaFXApplication.root = new Group();
        JavaFXApplication.scene = new Scene(root, 800, 600);

        JavaFXApplication.stage.setTitle("Editor");
        JavaFXApplication.stage.setScene(scene);
        
        JavaFXApplication.ready = true;
        
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                App.view.Update();
                if (JavaFXApplication.update) {
                    JavaFXApplication.stage.show();
                    JavaFXApplication.update = false;
                }
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
       
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 
                ((JavaFXController) App.controller).NotifyMouse(e);
            } 
        };  
        JavaFXApplication.scene.addEventFilter(MouseEvent.ANY, eventHandler);   
       
        EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() { 
            @Override 
            public void handle(KeyEvent e) { 
                ((JavaFXController) App.controller).NotifyKeyboard(e);
            } 
        };  
        JavaFXApplication.scene.addEventFilter(KeyEvent.ANY, keyEventHandler);   

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            ((JavaFXController) App.controller).NotifyWidth(newVal.intValue());
        });
        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            ((JavaFXController) App.controller).NotifyHeight(newVal.intValue());
        });
    }

    static public void addToRoot(Node node) {
        JavaFXApplication.root.getChildren().add(node);
        JavaFXApplication.update = true;
    }

    @Override
    public void run() {
        main(new String[0]);
    }

}