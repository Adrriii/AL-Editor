package editor.userinterface.javafximpl;

import editor.application.App;
import editor.domain.Canvas;
import editor.userinterface.View;
import javafx.scene.paint.Color;

public class JavaFXView implements View {

    private boolean started;
    private Thread JavaFXThread;

    public JavaFXView() {
        this.started = false;
        this.JavaFXThread = new Thread(new JavaFXApplication());;
    }

    @Override
    public void Update() {

        if(!this.started) {
            this.JavaFXThread.start();

            while(!JavaFXApplication.ready) {};
            System.out.println("created javafx thread");

            this.started = true;
        }
        drawCanvas();
        drawToolBar();
        drawMenu();
    }

    public void drawCanvas() {
        Canvas canvas = App.model.getCanvas();

        javafx.scene.shape.Rectangle canvasRectangle = new javafx.scene.shape.Rectangle();
        
        canvasRectangle.setX(canvas.pos_x);
        canvasRectangle.setY(canvas.pos_y);
        canvasRectangle.setWidth(canvas.width);
        canvasRectangle.setHeight(canvas.height);
        canvasRectangle.setFill(Color.BLACK);
        
        JavaFXApplication.addToRoot(canvasRectangle);
    }

    public void drawToolBar() {

    }

    public void drawMenu() {

    }

}