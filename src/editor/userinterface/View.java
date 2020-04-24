package editor.userinterface;

import editor.domain.element.Rectangle;

public interface View {

    public void Update();

    public ViewScope getMainView();
    public ViewScope getCanvasView();
    public ViewScope getToolbarView();
    public ViewScope getTopMenuView();

    public void drawRectangle(Rectangle rectangle, int pos_x, int pos_y, double scale);
    public void drawRectangle(Rectangle rectangle, int pos_x, int pos_y);
    public void drawRectangle(Rectangle rectangle, double scale);
    public void drawRectangle(Rectangle rectangle);
    public void drawText(String text, int x, int y, int size);

}