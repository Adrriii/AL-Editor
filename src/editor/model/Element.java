package editor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import editor.application.App;
import editor.model.elementproperty.ColorProperty;
import editor.model.menu.Interaction;
import editor.model.menu.interactionmenus.*;

/**
* A displayable object.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class Element extends Drawable {

    private static final long serialVersionUID = 1L;

    /**
     * The properties helping to customize the element
     */
    public HashMap<String, ElementProperty> properties;

    /**
     * Whether the element is currently selected by the user
     */
    protected boolean selected = false;

    /**
     * The minimum position this element is allowed to be
     */
    protected int min_x = 0, min_y = 0;

    /**
     * The current rotation of this element
     */
    public int rotation = 0;

    /**
     * Create a new Element according to a set of properties
     * @param properties The properties to copy for this new element
     */
    public Element(HashMap<String,ElementProperty> properties) {
        super();

        this.properties = new HashMap<>();

        for(Map.Entry<String, ElementProperty> entry : properties.entrySet()) {
            this.properties.put(entry.getKey(), entry.getValue().Clone());
        }
    }

    /**
     * Create a new element with no special properties
     */
    public Element() {
        this(new HashMap<String,ElementProperty>());
    }

    /**
     * Compose the element with another.
     * 
     * @param element The element to compose
     * @throws InvalidCompositeAddition
     */
    public void Add(Element element) throws InvalidCompositeAddition {
        throw new InvalidCompositeAddition();
    }
    

    /**
     * Decompose the element with another.
     * 
     * @param element The element to decompose
     * @throws InvalidCompositeAddition
     */
    public void Remove(Element element) throws InvalidCompositeAddition {
        throw new InvalidCompositeAddition();
    }

    /**
     * Signify to this element that it has been updated, 
     * so that it can perform necessary tasks.
     */
    public void Update() {
        Notify();
    }

    /**
     * Create an exact copy of this element
     * 
     * @return A new element
     */
    public abstract Element Clone();

    /**
     * Compute the actual surface width of the element
     * 
     * @return the width of the element
     */
    abstract public int getSurfaceWidth();

    /**
     * Compute the actual surface height of the element
     * 
     * @return the height of the element
     */
    abstract public int getSurfaceHeight();

    /**
     * Constructs an initialized list of all interactions that can be performed on this element
     * @return An interaction list, ready for use
     */
    public ArrayList<Interaction> getAvailableInteractions() {
        ArrayList<Interaction> list = new ArrayList<>();
        
        if(new ArrayList<Element>(App.model.getSelectedElements()).size() > 1) {
            list.add(new GroupElementsInteraction(this));
        } 

        list.add(new ColorChangeInteraction(this));
        list.add(new RotationChangeInteraction(this));
        list.add(new DeleteElementInteraction(this));

        return list;
    }

    /**
     * Change the position and/or dimensions of the element
     * 
     * @param new_pos The new element's position
     * @param width The new element's width
     * @param height The new element's height
     * @return The position that was actually possible to go to
     */
    public Position Update(Position new_pos, int width, int height) {
        if(new_pos.x > min_x) {
            pos.x = new_pos.x;
        } else {
            pos.x = min_x;
        }
        if(new_pos.y > min_y) {
            pos.y = new_pos.y;
        } else {
            pos.y = min_y;
        }

        this.width = width;
        this.height = height;

        Update();

        return new Position(pos.x, pos.y);
    }

    /**
     * Returns the color of this element.
     * 
     * @return A ColorProperty
     */
    public ColorProperty getColorProperty() {
        return (ColorProperty) properties.get("color");
    }

    /**
     * Update only the position
     * 
     * @param new_pos The new position
     * @return A new position
     * @see {@link #Update(Position, int, int)}
     */
    public Position Update(Position new_pos) {
        return Update(new_pos, this.getSurfaceWidth(), this.getSurfaceHeight());
    }

    /**
     * Defines how the element can be drawn when it needs to fit certain dimensions
     * 
     * @param viewName The view to drawn in
     * @param pos The position to draw the element at
     * @param fit_width The width that needs to be fit
     * @param fit_height The height that needs to be fit
     */
    abstract public void Draw(String viewName, Position pos, int fit_width, int fit_height);

    @Override
    public void Draw(String viewName, Position pos) {
        Draw(viewName, pos, 1);
    }
    
    /**
     * Draw this element according to some scaling factor
     * 
     * @param viewName The view to drawn in
     * @param pos The position to draw the element at
     * @param scale The scale factor
     */
    public void Draw(String viewName, Position pos, double scale) {
        Draw(viewName, pos, (int) (width * scale), (int) (height * scale));
    }

    /**
     * Whether this element is currently being selected
     * 
     * @return Whether this element is currently being selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Changes this element's selected value to a new state
     * @param state Whether this element is currently selected
     */
    public void setSelected(boolean state) {
        selected = state;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+ ": " + super.toString();
    }
    
    @Override
    public boolean isClicked(Position pos) {
        return super.isClicked(pos);
    }
    
    /**
     * Whether this element is clicked at this x and y coordinate
     */
    public boolean isClicked(int x, int y) {
        return isClicked(new Position(x, y));
    }
}