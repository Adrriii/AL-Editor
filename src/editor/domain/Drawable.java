package editor.domain;

import java.util.ArrayList;

import editor.application.App;
import editor.userinterface.ViewScope;

public abstract class Drawable {

    public int pos_x = 0;
    public int pos_y = 0;

    public int width = 0;
    public int height = 0;

    protected ArrayList<ViewScope> views;

    public Drawable() {
        views = new ArrayList<ViewScope>();
    }

    public void Draw() {
        Draw(pos_x, pos_y);
    }

    public void DrawRef(int ref_x, int ref_y) {
        Draw(pos_x + ref_x, pos_y + ref_y);
    }

    abstract public void Draw(int x, int y);

    public void Attach(ViewScope view) {
        views.add(view);
    }

    public void Detach(ViewScope view) {
        views.remove(view);
    }

    public void Notify() {
        views.forEach(view -> view.Update());
    }
    
    public boolean isClicked(int x, int y) {
        return x > pos_x && x < pos_x + width && y > pos_y && y < pos_y + height;
    }
    
    public boolean intersects(int rx, int ry, int rwidth, int rheight) {

        return !(pos_x + width <= rx ||
                 pos_y + height <= ry ||
                 pos_x >= rx + rwidth || 
                 pos_y >= ry + rheight); 
    }

}