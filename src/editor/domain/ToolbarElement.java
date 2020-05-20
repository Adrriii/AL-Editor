package editor.domain;

import editor.application.App;
import editor.domain.element.Rectangle;

/**
* A tool inside a toolbar, composed of an element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ToolbarElement extends Drawable {

    private static final long serialVersionUID = 1L;

    /**
     * The toolbar containing this tool
     */
    private Toolbar parent;

    /**
     * The pattern that is used for this tool
     */
    private Element element;

    /**
     * Used for display
     */
    private int border_width, padding;

    /**
     * Create a new tool from an element
     * 
     * @param parent The toolbar that will contain this tool
     * @param element The element to replicate when used
     * @param pos_x The x position of the tool
     * @param pos_y The y position of the tool
     * @param side The size of each sides of the tool
     */
    public ToolbarElement(Toolbar parent, Element element, int pos_x, int pos_y, int side) {
        this.parent = parent;
        this.pos.x = pos_x;
        this.pos.y = pos_y;
        this.width = side;
        this.height = side;
        this.element = element.Clone();
        this.element.Update(new Position(0, 0));

        border_width = 2;
        padding = 2;
    }

    /**
     * Get a copy of this tool's element
     * 
     * @return A new element
     */
    public Element cloneElement() {
        return element.Clone();
    }

    /**
     * Get the instance of this tool's pattern
     * 
     * @return This tool's pattern
     */
    public Element getElement() {
        return this.element;
    }

    @Override
    public void Draw(String viewName, Position pos) {
        App.view.drawRectangle(viewName, new Rectangle(pos.x-border_width-padding, pos.y-border_width-padding, width+border_width*2+padding*2, height+border_width*2+padding*2,0,0,0));
        App.view.drawRectangle(viewName, new Rectangle(pos.x-padding, pos.y-padding, width+padding*2, height+padding*2,255,255,255));
        this.element.Draw(viewName, pos, this.parent.getElementSideSize(), this.parent.getElementSideSize());
    }

    @Override
    public String toString() {
        return "Tool "+ this.element.toString();
    }

}