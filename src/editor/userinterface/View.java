package editor.userinterface;

import editor.model.element.Polygon;
import editor.model.element.Rectangle;

/**
* Provides all interactions with the display.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public interface View {

    public void Update();
    public void Clear(String scopeName);

    public ViewScope getMainView();
    public ViewScope getCanvasView();
    public ViewScope getToolbarView();
    public ViewScope getTopMenuView();

    public void drawRectangle(String viewName, Rectangle rectangle, int pos_x, int pos_y, double scale);
    public void drawRectangle(String viewName, Rectangle rectangle, int pos_x, int pos_y);
    public void drawRectangle(String viewName, Rectangle rectangle, double scale);
    public void drawRectangle(String viewName, Rectangle rectangle);
    public void drawPolygon(String viewName, Polygon polygon, int pos_x, int pos_y, double scale);
    public void drawImage(String viewName, String path, int pos_x, int pos_y, int width, int height);
    public void drawText(String viewName, String text, int x, int y, int size);

}