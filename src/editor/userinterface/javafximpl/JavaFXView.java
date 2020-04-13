package editor.userinterface.javafximpl;

import java.util.Iterator;

import editor.application.App;
import editor.domain.Canvas;
import editor.domain.Element;
import editor.domain.Toolbar;
import editor.domain.ToolbarElement;
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

            this.started = true;
        }

        Canvas canvas = App.model.getCanvas();

        drawCanvas(canvas);
        drawToolBar(App.model.getToolbar());
        drawMenu(App.model.getTopMenu());
    }

    public void drawCanvas(Canvas canvas) {
        drawJavaFXRectangle(canvas.pos_x, canvas.pos_y, canvas.width, canvas.height, Color.WHITE);
        
        canvas.getElements().forEach(element -> {
            drawElement(element, canvas.pos_x, canvas.pos_y);
        });
    }

    public void drawToolBar(Toolbar toolbar) {
        drawJavaFXRectangle(toolbar.pos_x, toolbar.pos_y, toolbar.width, toolbar.height, Color.GREY);

        drawToolbarElements(toolbar);
    }

    public void drawMenu(TopMenu menu) {
        drawJavaFXRectangle(menu.pos_x, menu.pos_y, menu.width, menu.height, Color.LIGHTGREY);
    }

    public void drawElement(Element element, int ref_x, int ref_y, int fit_width, int fit_height) {        
        String type = element.getClass().getSimpleName();

        double scale = 1;
        
        if(fit_width >= 0) {
            scale = fit_width / (double) element.getSurfaceWidth();
        }
        if(fit_height >= 0) {
            scale = Math.min(scale, fit_height / (double) element.getSurfaceHeight());
        }

        switch(type) {
            case "Rectangle":
                Rectangle rec = (Rectangle) element;
                
                drawRectangle(rec, ref_x, ref_y, scale < 1 ? scale : 1);
                break;
            default:
            System.out.println("Unsupported element type : "+type);
        }
    }
    public void drawElement(Element element, int ref_x, int ref_y) {
        drawElement(element, ref_x, ref_y, -1, -1);
    }

    public void drawJavaFXRectangle(int pos_x, int pos_y, int width, int height, Color color, int ref_x, int ref_y, double scale) {
        javafx.scene.shape.Rectangle JavaFXRectangle = new javafx.scene.shape.Rectangle();
        
        JavaFXRectangle.setX(ref_x + pos_x); 
        JavaFXRectangle.setY(ref_y + pos_y);
        JavaFXRectangle.setWidth(width * scale);
        JavaFXRectangle.setHeight(height * scale);

        JavaFXRectangle.setFill(color);
        
        JavaFXApplication.addToRoot(JavaFXRectangle);

    }

    public void drawJavaFXRectangle(int pos_x, int pos_y, int width, int height, Color color) {
        drawJavaFXRectangle(pos_x, pos_y, width, height, color, 0, 0, 1);
    }

    public void drawRectangle(Rectangle rectangle, int ref_x, int ref_y, double scale) {
        ColorProperty colors = (ColorProperty) rectangle.properties.get("color");

        drawJavaFXRectangle(rectangle.pos_x, rectangle.pos_y, rectangle.width, rectangle.height, new Color(colors.r / 255.0,colors.g / 255.0,colors.b / 255.0,colors.a / 255.0), ref_x, ref_y, scale);
    }
    public void drawRectangle(Rectangle rectangle, int ref_x, int ref_y) {
        drawRectangle(rectangle, ref_x, ref_y, 1);
    }

    public void drawToolbarElements(Toolbar toolbar) {
        int y = toolbar.pos_y;

        Iterator<ToolbarElement> iter = toolbar.getToolbarElements().iterator();

        int elementSideSize = toolbar.width - toolbar.getElementPadding()*2;

        while(iter.hasNext()) {
            drawElement(iter.next().getElement(), toolbar.pos_x + toolbar.getElementPadding(), y + toolbar.getElementPadding(), elementSideSize, elementSideSize);
            y += toolbar.width;
        }
    }

}