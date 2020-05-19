package editor.userinterface.javafximpl;

import java.io.FileNotFoundException;
import java.util.HashMap;

import editor.application.App;
import editor.domain.element.Polygon;
import editor.domain.element.Rectangle;
import editor.domain.elementproperty.ColorProperty;
import editor.domain.elementproperty.RoundedBorderProperty;
import editor.userinterface.View;
import editor.userinterface.ViewScope;
import editor.userinterface.views.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class JavaFXView implements View {

    private Thread JavaFXThread;

    private ViewMain mainView;
    private ViewCanvas canvasView;
    private ViewToolbar toolbarView;
    private ViewTopMenu topMenuView;

    private HashMap<String, Image> imageCache;

    public JavaFXView() {

        this.JavaFXThread = new Thread(new JavaFXApplication());

        this.mainView = new ViewMain();
        this.canvasView = new ViewCanvas();
        this.toolbarView = new ViewToolbar();
        this.topMenuView = new ViewTopMenu();

        this.JavaFXThread.start();

        this.imageCache = new HashMap<>();

        while (!JavaFXApplication.ready) {
        }
        ;
    }

    @Override
    public ViewScope getCanvasView() {
        return canvasView;
    }

    @Override
    public ViewScope getMainView() {
        return mainView;
    }

    @Override
    public ViewScope getToolbarView() {
        return toolbarView;
    }

    @Override
    public ViewScope getTopMenuView() {
        return topMenuView;
    }

    @Override
    public void Update() {
        mainView.Tick();
    }

    @Override
    public void Clear(String scopeName) {
        JavaFXApplication.clearGroup(scopeName);
    }

    private void loadImage(String path) {
        if (imageCache.containsKey(path))
            return;

        try {
            imageCache.put(path, new Image(App.load(path)));
        } catch (FileNotFoundException e) {
            System.out.println(path + " can't be found.");
        }
    }

    public void clearImageCache() {
        imageCache.clear();
    }

    public void drawJavaFXRectangle(String viewName, int pos_x, int pos_y, int width, int height, Color color, double scale,
            RoundedBorderProperty border, int rotation) {
        javafx.scene.shape.Rectangle JavaFXRectangle = new javafx.scene.shape.Rectangle();

        JavaFXRectangle.setX(pos_x);
        JavaFXRectangle.setY(pos_y);
        JavaFXRectangle.setWidth(width * scale);
        JavaFXRectangle.setHeight(height * scale);
        JavaFXRectangle.setArcWidth(border.w * scale);
        JavaFXRectangle.setArcHeight(border.h * scale);
        JavaFXRectangle.setRotate(rotation);

        JavaFXRectangle.setFill(color);

        JavaFXApplication.addToRoot(JavaFXRectangle, viewName);

    }

    public void drawJavaFXText(String viewName, String label, int x, int y, int size) {
        Text text = new Text(label);

        text.setFont(new Font(size));
        text.setX(x);
        text.setY(y);

        JavaFXApplication.addToRoot(text, viewName);
    }

    public void drawJavaFXImage(String viewName, Image image, int x, int y, int width, int height) {
        ImageView img = new ImageView(image);

        img.setX(x);
        img.setY(y);
        img.setFitWidth(width);
        img.setFitHeight(height);

        JavaFXApplication.addToRoot(img, viewName);
    }

    public void drawJavaFXRectangle(String viewName, int pos_x, int pos_y, int width, int height, Color color) {
        drawJavaFXRectangle(viewName, pos_x, pos_y, width, height, color, 1, new RoundedBorderProperty(0, 0), 0);
    }

    public void drawRectangle(String viewName, Rectangle rectangle, int pos_x, int pos_y, double scale) {
        ColorProperty colors = (ColorProperty) rectangle.properties.get("color");

        drawJavaFXRectangle(viewName, pos_x, pos_y, rectangle.width, rectangle.height,
                new Color(colors.r / 255.0, colors.g / 255.0, colors.b / 255.0, colors.a / 255.0), scale,
                (RoundedBorderProperty) rectangle.properties.get("roundedborders"), rectangle.rotation);
    }

    public void drawRectangle(String viewName, Rectangle rectangle, int pos_x, int pos_y) {
        drawRectangle(viewName, rectangle, pos_x, pos_y, 1);
    }

    public void drawRectangle(String viewName, Rectangle rectangle, double scale) {
        drawRectangle(viewName, rectangle, rectangle.pos.x, rectangle.pos.y, scale);
    }

    public void drawRectangle(String viewName, Rectangle rectangle) {
        drawRectangle(viewName, rectangle, rectangle.pos.x, rectangle.pos.y, 1);
    }

    @Override
    public void drawText(String viewName, String text, int x, int y, int size) {
        drawJavaFXText(viewName, text, x, y, size);
    }

    @Override
    public void drawImage(String viewName, String path, int pos_x, int pos_y, int width, int height) {
        loadImage(path);

        drawJavaFXImage(viewName, imageCache.get(path), pos_x, pos_y, width, height);
    }

    @Override
    public void drawPolygon(String viewName, Polygon polygon, int pos_x, int pos_y, double scale) {
        javafx.scene.shape.Polygon poly = new javafx.scene.shape.Polygon();

        polygon.getPoints().forEach(polypoint -> {
            poly.getPoints().add(pos_x + polypoint.x * scale);
            poly.getPoints().add(pos_y + polypoint.y * scale);
        });

        poly.setRotate(polygon.rotation);

        ColorProperty colors = polygon.getColorProperty();
        poly.setFill(new Color(colors.r / 255.0, colors.g / 255.0, colors.b / 255.0, colors.a / 255.0));

        JavaFXApplication.addToRoot(poly, viewName);
    }

}