package editor.userinterface.javafximpl;

import editor.application.App;
import editor.domain.Canvas;
import editor.domain.Toolbar;
import editor.domain.element.Rectangle;
import editor.domain.elementproperty.ColorProperty;
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

        Canvas canvas = App.model.getCanvas();

        drawCanvas(canvas);
        drawToolBar(App.model.getToolbar());
        drawMenu(App.model.getTopMenu());
        
        canvas.getElements().forEach(element -> {
            String type = element.getClass().getSimpleName();

            switch(type) {
                case "Rectangle":
                    drawRectangle((Rectangle) element, canvas);
                    break;
                default:
                    System.out.println("Unsupported element type : "+type);
            }
        });
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

    public void drawRectangle(Rectangle rectangle, Canvas canvas) {
        javafx.scene.shape.Rectangle JavaFXRectangle = new javafx.scene.shape.Rectangle();
        
        JavaFXRectangle.setX(canvas.pos_x + rectangle.pos_x);
        JavaFXRectangle.setY(canvas.pos_y + rectangle.pos_y);
        JavaFXRectangle.setWidth(rectangle.width);
        JavaFXRectangle.setHeight(rectangle.height);

        ColorProperty colors = (ColorProperty) rectangle.properties.get("color");

        JavaFXRectangle.setFill(new Color(colors.r / 255.0,colors.g / 255.0,colors.b / 255.0,colors.a / 255.0));
        
        JavaFXApplication.addToRoot(JavaFXRectangle);

    }

}