package editor.userinterface.javafximpl;

import javafx.stage.Stage;

import editor.model.Model;
import editor.userinterface.View;

public class JavaFXView implements View {

    private JavaFXApplication application;

    public JavaFXView() {
        this.application = new JavaFXApplication();

        JavaFXApplication.main(new String[0]);
    }

    @Override
    public void Update() {
        drawCanvas();
        drawToolBar();
        drawMenu();
    }

    public void drawCanvas() {
        
    }

    public void drawToolBar() {

    }

    public void drawMenu() {

    }

}