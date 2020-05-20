package editor.domain;

import java.io.Serializable;
import java.util.ArrayList;

import editor.userinterface.ViewScope;

/**
* Represents an object that can be displayed
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class Drawable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The position of this object in memory.
     * By default, it will be drawn at this position relative to the screen.
     */
    public Position pos;

    /**
     * The dimensions of this object in memory
     * By default, it will try to be drawn at this size.
     */
    public int width = 0, height = 0;

    /**
     * The views interested by an update to this drawable object.
     * They will usually invalidate themselves so that they can be 
     * redrawn when any drawable inside them has changed.
     */
    public transient ArrayList<ViewScope> views;

    /**
     * Create a new object that can be drawn
     */
    public Drawable() {
        pos = new Position(0,0);
    }

    /**
     * Draw this object inside a view
     * 
     * @param viewName The view that needs it to be drawn
     */
    public void Draw(String viewName) {
        Draw(viewName, pos);
    }

    /**
     * Draw this object according to a referential
     * 
     * @param viewName The view that needs it to be drawn
     * @param ref The position to refer to
     */
    public void DrawRef(String viewName, Position ref) {
        Draw(viewName, ref);
    }

    /**
     * Defines how this object should be drawn by the view, when using a referential.
     * 
     * @param viewName The view that needs it to be drawn
     * @param ref The position to refer to
     */
    abstract public void Draw(String viewName, Position ref);

    /**
     * Add a new view listening for updates.
     * 
     * @param view A reference to the view
     */
    public void Attach(ViewScope view) {
        if(views == null) views = new ArrayList<>();
        views.add(view);
    }

    /**
     * Remove a view from the listeners
     * 
     * @param view A reference to the view
     */
    public void Detach(ViewScope view) {
        if(views == null) views = new ArrayList<>();
        views.remove(view);
    }

    /**
     * Cut all listeners
     */
    public void Reattach() {
        views = new ArrayList<>();
    }

    /**
     * Cut all listeners and start with a new listener
     * 
     * @param view The only view that will listen now
     */
    public void Reattach(ViewScope view) {
        Reattach();
        Attach(view);
    }

    /**
     * Tell all listeners about the drawable's update
     */
    public void Notify() {
        if(views == null) views = new ArrayList<>();
        views.forEach(view -> view.Update());
    }
    
    /**
     * Performs a basic check on the drawable on whether it is clicked according to its dimensions.
     * @param x The x position of the click
     * @param y The y position of the click
     * @return Whether the position is inside the drawable
     */
    public boolean isClicked(int x, int y) {
        return x > pos.x && x < pos.x + width && y > pos.y && y < pos.y + height;
    }
    
    /**
     * Performs a basic check on the drawable on whether it is clicked according to its dimensions.
     * @param click The position of the click
     * @return Whether the position is inside the drawable
     */
    public boolean isClicked(Position click) {
        return click.x > pos.x && click.x < pos.x + width && click.y > pos.y && click.y < pos.y + height;
    }
    
    /**
     * Performs a basic check on the drawable on whether it is intersected according to its dimensions.
     * 
     * @param rx The rectangle's top left x
     * @param ry The rectangle's top left y
     * @param rwidth The rectangle's width
     * @param rheight The rectangle's height
     * @return Whether the rectangle intersects with the drawable
     */
    public boolean intersects(int rx, int ry, int rwidth, int rheight) {

        return !(pos.x + width <= rx ||
                 pos.y + height <= ry ||
                 pos.x >= rx + rwidth || 
                 pos.y >= ry + rheight); 
    }
    
    /**
     * Performs a basic check on the drawable on whether it is intersected according to its dimensions.
     * 
     * @param pos The rectangle's top left position
     * @param rwidth The rectangle's width
     * @param rheight The rectangle's height
     * @return Whether the rectangle intersects with the drawable
     */
    public boolean intersects(Position pos, int rwidth, int rheight) {

        return intersects(pos.x, pos.y, rwidth, rheight);
    }

    @Override
    public String toString() {
         return "x: "+pos.x+" y: "+pos.y+" w: "+width+" h: "+height;
    }

}