package editor.userinterface.javafximpl;

import editor.userinterface.View;

public class JavaFXView implements View {

    private JavaFXApplication application;

    private boolean started;

    public JavaFXView() {
        this.application = new JavaFXApplication();

        this.started = false;
    }

    @Override
    public void Update() {
        if(!this.started) {
            JavaFXApplication.main(new String[0]);
            this.started = true;
        }
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