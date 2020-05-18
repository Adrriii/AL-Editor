package editor.userinterface.testimpl;

import editor.domain.element.Polygon;
import editor.domain.element.Rectangle;
import editor.userinterface.View;
import editor.userinterface.ViewScope;
import editor.userinterface.views.*;

public class TestView implements View {

    private ViewMain mainView;
    private ViewCanvas canvasView;
    private ViewToolbar toolbarView;
    private ViewTopMenu topMenuView;

    public TestView() {

        this.mainView = new ViewMain();
        this.canvasView = new ViewCanvas();
        this.toolbarView = new ViewToolbar();
        this.topMenuView = new ViewTopMenu();
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
        // TODO Auto-generated method stub

    }

    @Override
    public void drawRectangle(Rectangle rectangle, int pos_x, int pos_y, double scale) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawRectangle(Rectangle rectangle, int pos_x, int pos_y) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawRectangle(Rectangle rectangle, double scale) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawRectangle(Rectangle rectangle) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawPolygon(Polygon polygon, int pos_x, int pos_y, double scale) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawImage(String path, int pos_x, int pos_y, int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawText(String text, int x, int y, int size) {
        // TODO Auto-generated method stub

    }

}