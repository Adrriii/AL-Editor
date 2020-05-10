package editor.domain;

import java.io.Serializable;
import java.util.ArrayList;

import editor.application.App;
import editor.userinterface.ViewScope;

public abstract class Drawable implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Position pos;

    public int width = 0;
    public int height = 0;

    public transient ArrayList<ViewScope> views;

    public Drawable() {
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
        if(views == null) views = new ArrayList<>();
        views.add(view);
    }

    public void Detach(ViewScope view) {
        if(views == null) views = new ArrayList<>();
        views.remove(view);
    }

    public void Notify() {
        if(views == null) views = new ArrayList<>();
        views.forEach(view -> view.Update());
    }
    
    public boolean isClicked(int x, int y) {
        return x > pos.x && x < pos.x + width && y > pos.y && y < pos.y + height;
    }
    
    public boolean isClicked(Position click) {
        return click.x > pos.x && click.x < pos.x + width && click.y > pos.y && click.y < pos.y + height;
    }
    
    public boolean intersects(int rx, int ry, int rwidth, int rheight) {

        return !(pos.x + width <= rx ||
                 pos.y + height <= ry ||
                 pos.x >= rx + rwidth || 
                 pos.y >= ry + rheight); 
    }
    
    public boolean intersects(Position pos, int rwidth, int rheight) {

        return intersects(pos.x, pos.y, rwidth, rheight);
    }

    @Override
    public String toString() {
         return "x: "+pos.x+" y: "+pos.y+" w: "+width+" h: "+height;
    }

}