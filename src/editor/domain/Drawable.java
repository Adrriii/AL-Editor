package editor.domain;

import java.util.ArrayList;

import editor.application.App;
import editor.userinterface.ViewScope;

public abstract class Drawable {

    public Position pos;

    public int width = 0;
    public int height = 0;

    protected ArrayList<ViewScope> views;

    public Drawable() {
        views = new ArrayList<ViewScope>();
        pos = new Position(0,0);
    }

    public void Draw() {
        Draw(pos);
    }

    public void DrawRef(Position ref) {
        Draw(ref);
    }

    abstract public void Draw(Position ref);

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
        return x > pos.x && x < pos.x + width && y > pos.y && y < pos.y + height;
    }
    
    public boolean intersects(int rx, int ry, int rwidth, int rheight) {

        return !(pos.x + width <= rx ||
                 pos.y + height <= ry ||
                 pos.x >= rx + rwidth || 
                 pos.y >= ry + rheight); 
    }

}