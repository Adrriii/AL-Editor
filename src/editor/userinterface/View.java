package editor.userinterface;

import editor.domain.element.Rectangle;

public interface View {

    public void Update();

    public ViewScope getMainView();
    public ViewScope getCanvasView();
    public ViewScope getToolbarView();
    public ViewScope getTopMenuView();

    public void drawRectangle(Rectangle rectangle, int ref_x, int ref_y, double scale);
    public void drawRectangle(Rectangle rectangle, int ref_x, int ref_y);
    public void drawText(String text, int x, int y, int size);

}