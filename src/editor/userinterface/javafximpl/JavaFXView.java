package editor.userinterface.javafximpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import editor.domain.Position;
import editor.domain.element.Rectangle;
import editor.domain.elementproperty.ColorProperty;
import editor.userinterface.View;
import editor.userinterface.ViewScope;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class JavaFXView implements View {

    private Thread JavaFXThread;

    private JavaFXViewMain mainView;
    private JavaFXViewCanvas canvasView;
    private JavaFXViewToolbar toolbarView;
    private JavaFXViewTopMenu topMenuView;

    private HashMap<String, Image> imageCache;

    public JavaFXView() {

        this.JavaFXThread = new Thread(new JavaFXApplication());

        this.mainView = new JavaFXViewMain();
        this.canvasView = new JavaFXViewCanvas();
        this.toolbarView = new JavaFXViewToolbar();
        this.topMenuView = new JavaFXViewTopMenu();

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

    private void loadImage(String path) {
        if (imageCache.containsKey(path))
            return;

        try {
            imageCache.put(path, new Image(new FileInputStream(path)));
        } catch (FileNotFoundException e) {
            System.out.println(path + " can't be found.");
        }
    }

    public void clearImageCache() {
        imageCache.clear();
    }

    public void drawJavaFXRectangle(int pos_x, int pos_y, int width, int height, Color color, double scale) {
        javafx.scene.shape.Rectangle JavaFXRectangle = new javafx.scene.shape.Rectangle();

        JavaFXRectangle.setX(pos_x);
        JavaFXRectangle.setY(pos_y);
        JavaFXRectangle.setWidth(width * scale);
        JavaFXRectangle.setHeight(height * scale);

        JavaFXRectangle.setFill(color);

        JavaFXApplication.addToRoot(JavaFXRectangle);

    }

    public void drawJavaFXText(String label, int x, int y, int size) {
        Text text = new Text(label);

        text.setFont(new Font(size));
        text.setX(x);
        text.setY(y);

        JavaFXApplication.addToRoot(text);
    }

    public void drawJavaFXImage(Image image, int x, int y, int width, int height) {
        ImageView img = new ImageView(image);

        img.setX(x);
        img.setY(y);
        img.setFitWidth(width);
        img.setFitHeight(height);

        JavaFXApplication.addToRoot(img);
    }

    public void drawJavaFXRectangle(int pos_x, int pos_y, int width, int height, Color color) {
        drawJavaFXRectangle(pos_x, pos_y, width, height, color, 1);
    }

    public void drawRectangle(Rectangle rectangle, int pos_x, int pos_y, double scale) {
        ColorProperty colors = (ColorProperty) rectangle.properties.get("color");

        drawJavaFXRectangle(pos_x, pos_y, rectangle.width, rectangle.height,
                new Color(colors.r / 255.0, colors.g / 255.0, colors.b / 255.0, colors.a / 255.0), scale);
    }

    public void drawRectangle(Rectangle rectangle, int pos_x, int pos_y) {
        drawRectangle(rectangle, pos_x, pos_y, 1);
    }

    public void drawRectangle(Rectangle rectangle, double scale) {
        drawRectangle(rectangle, rectangle.pos.x, rectangle.pos.y, scale);
    }

    public void drawRectangle(Rectangle rectangle) {
        drawRectangle(rectangle, rectangle.pos.x, rectangle.pos.y, 1);
    }

    @Override
    public void drawText(String text, int x, int y, int size) {
        drawJavaFXText(text, x, y, size);
    }

    @Override
    public void drawImage(String path, int pos_x, int pos_y, int width, int height) {
        loadImage(path);

        drawJavaFXImage(imageCache.get(path), pos_x, pos_y, width, height);
    }

}