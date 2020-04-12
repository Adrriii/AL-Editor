package editor.userinterface.javafximpl;

import editor.application.App;
import editor.domain.Canvas;
import editor.domain.Toolbar;
import editor.domain.menu.TopMenu;
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
        drawCanvas(App.model.getCanvas());
        drawToolBar(App.model.getToolbar());
        drawMenu(App.model.getTopMenu());
    }

    public void drawCanvas(Canvas canvas) {
        javafx.scene.shape.Rectangle canvasRectangle = new javafx.scene.shape.Rectangle();
        
        canvasRectangle.setX(canvas.pos_x);
        canvasRectangle.setY(canvas.pos_y);
        canvasRectangle.setWidth(canvas.width);
        canvasRectangle.setHeight(canvas.height);
        canvasRectangle.setFill(Color.WHITE);
        
        JavaFXApplication.addToRoot(canvasRectangle);
    }

    public void drawToolBar(Toolbar toolbar) {
        javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle();
        
        rectangle.setX(toolbar.pos_x);
        rectangle.setY(toolbar.pos_y);
        rectangle.setWidth(toolbar.width);
        rectangle.setHeight(toolbar.height);
        rectangle.setFill(Color.GREY);
        
        JavaFXApplication.addToRoot(rectangle);
    }

    public void drawMenu(TopMenu menu) {
        javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle();
        
        rectangle.setX(menu.pos_x);
        rectangle.setY(menu.pos_y);
        rectangle.setWidth(menu.width);
        rectangle.setHeight(menu.height);
        rectangle.setFill(Color.LIGHTGREY);
        
        JavaFXApplication.addToRoot(rectangle);

    }

}