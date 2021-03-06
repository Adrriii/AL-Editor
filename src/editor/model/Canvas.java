package editor.model;

import java.util.ArrayList;
import java.util.Optional;

import editor.application.App;
import editor.model.element.Rectangle;


/**
* Holds all elements and behaviors of the whiteboard.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class Canvas extends Drawable {

    private static final long serialVersionUID = 1L;

    /**
     * The elements currently present in the whiteboard
     */
    private ArrayList<Element> elements;

    /**
     * The background of the whiteboard. Should be white, obviously :)
     */
    private Rectangle background;

    /**
     * Create a new empty canvas
     */
    public Canvas() {
        super();

        Attach(App.view.getCanvasView());

        elements = new ArrayList<>();

        this.pos.x = 75; // Width of the Toolbar
        this.pos.y = 75; // Height of the TopMenu

        this.width = 800 - this.pos.x; // Resizable
        this.height = 600 - this.pos.y; // Resizable

        this.background = new Rectangle(0, 0, width, height, 255, 255, 255, 255);
    }

    @Override
    public void Reattach() {
        super.Reattach();
        Attach(App.view.getCanvasView());
        this.elements.forEach(element -> element.Reattach(App.view.getCanvasView()));
    }

    /**
     * Obtain a copy of all the elements present in the whiteboard
     * @return A list of Elements
     */
    public ArrayList<Element> getElements() {
        return new ArrayList<Element>(elements);
    }

    /**
     * Remove an element from the whiteboard
     * 
     * @param element The element to remove
     */
    public void removeElement(Element element) {
        this.elements.remove(element);
    }

    /**
     * Add an element to the whiteboard
     * 
     * @param element The element to add
     */
    public void addElement(Element element) {
        element.Detach(App.view.getCanvasView());
        element.Attach(App.view.getCanvasView());
        this.elements.add(element);
    }

    /**
     * Create a new element inside the whiteboard
     * 
     * @param element The pattern to use
     * @param pos The position to place the element
     * @return The created element
     */
    public Element newElement(Element element, Position pos) {
        Element newElement = element.Clone();
        newElement.Attach(App.view.getCanvasView());
        newElement.Update(pos);
        elements.add(newElement);
        Notify();
        return newElement;
    }
    
    /**
     * Change the whiteboard's dimensions
     * 
     * @param width The new width
     * @param height The new height
     */
    public void Resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.background.Update(this.pos, width, height);
    }

    @Override
    public void Draw(String viewName, Position pos) {
        App.view.drawRectangle(viewName, this.background, pos.x, pos.y);

        getElements().forEach(element -> element.Draw(viewName, new Position(pos.x + element.pos.x, pos.y + element.pos.y)));

        if(App.model.getSelectionRectangle() != null) {
            App.model.getSelectionRectangle().Draw(viewName, new Position(pos.x + App.model.getSelectionRectangle().pos.x, pos.y + App.model.getSelectionRectangle().pos.y));
        }
    }

    /**
     * Get the first element found at this position (Behind all elements)
     * 
     * @param x The x position
     * @param y The y position
     * @return An optional element
     */
    public Optional<Element> getElementAt(int x, int y) {
        return this.elements.stream().filter(element -> element.isClicked(x, y)).findFirst();
    }

    /**
     * Set any element on top of all the others in the whiteboard (Drawn above/last)
     * 
     * @param element The element to elevate
     */
    public void SetOnTop(Element element) {
        this.elements.remove(element);
        this.elements.add(element);
    }

    @Override
    public String toString() {
        String str = "Canvas";
        for(Element elem: elements) str += "\n" + elem.toString();
        return str;
    }

}