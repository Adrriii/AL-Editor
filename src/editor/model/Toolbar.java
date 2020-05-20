package editor.model;

import java.util.ArrayList;
import java.util.Iterator;

import editor.application.App;
import editor.model.element.Polygon;
import editor.model.element.Rectangle;
import editor.model.element.RegularPolygon;

/**
* Holds all elements and behaviors of the toolbar.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class Toolbar extends Drawable {

    private static final long serialVersionUID = 1L;

    /**
     * The tools that are contained by the toolbar
     */
    private ArrayList<ToolbarElement> toolbarElements;

    /**
     * A special tool used to delete elements
     */
    private DeleteElementTool deleteElementTool;

    /**
     * The background of the toolbar
     */
    private Rectangle background;

    /**
     * Padding values for the tools display
     */
    private int element_padding, element_height;

    /**
     * Create a new empty toolbar
     */
    public Toolbar() {
        super();

        Attach(App.view.getToolbarView());

        toolbarElements = new ArrayList<>();

        this.pos.x = 0;
        this.pos.y = 75; // Height of the TopMenu

        this.width = 75; // Constant
        this.height = 600 - this.pos.y; // Resizable

        this.deleteElementTool = new DeleteElementTool(new Position(pos.x + element_padding, this.height - pos.y - 2*element_padding) , element_height, element_height);

        this.element_padding = 10;

        this.element_height = this.width - this.element_padding * 2;

        this.background = new Rectangle(0, 0, width, height, 80, 80, 80, 255);
    }

    @Override
    public void Reattach() {
        super.Reattach();
        Attach(App.view.getToolbarView());
    }

    /**
     * Adds the default tools to the toolbar
     */
    public void LoadDefaultTools() {
        addElement(new Rectangle(130,160));
        addElement(new Rectangle(190,130, 255, 0, 0));
        
        ArrayList<Position> points = new ArrayList<Position>();
        points.add(new Position(0,0));
        points.add(new Position(75,10));
        points.add(new Position(80,50));
        points.add(new Position(10,40));
        addElement(new Polygon(points));

        addElement(new RegularPolygon(70, 4));
    }    

    /**
     * Change the dimensions of the toolbar
     * 
     * @param width The new width (Caution: Should not change !)
     * @param height The new height 
     */
    public void Resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.background.Update(this.pos, width, height);

        this.deleteElementTool = new DeleteElementTool(new Position(pos.x + element_padding, this.height - pos.y - 2*element_padding) , element_height, element_height);
    }

    /**
     * Get the current padding separating the tools
     * @return The current padding separating the tools
     */
    public int getElementPadding() {
        return element_padding;
    }

    /**
     * Add a new tool to the toolbar using an element as the pattern
     * 
     * @param element The element to use when creating the tool
     * @return The created tool
     */
    public ToolbarElement addElement(Element element) {
        ToolbarElement created = new ToolbarElement(this,
            element, 
            this.pos.x + this.element_padding, 
            this.pos.y + this.element_padding + (this.element_height + this.element_padding) * toolbarElements.size(), 
            this.element_height
        );

        toolbarElements.add(created);

        Notify();

        return created;
    }

    /**
     * Remove a tool from the toolbar and updates the others
     * 
     * @param element The tool to remove
     */
    public void removeElement(ToolbarElement element) {

        boolean move = false;

        Iterator<ToolbarElement> iter = getToolbarElements().iterator();

        while(iter.hasNext()) {
            ToolbarElement now = iter.next();
            
            if(move) {
                now.pos.y -= this.element_height + this.element_padding;
            }

            if(now == element) {
                move = true;
            }
        }
        

        toolbarElements.remove(element);

        Notify();
    }

    /**
     * Obtain a copy of all the available tools in the toolbar
     * @return A list of tools
     */
    public ArrayList<ToolbarElement> getToolbarElements() {
        return new ArrayList<ToolbarElement>(toolbarElements);
    }

    /**
     * Get the display size of the tools inside the toolbar
     * @return The display size of the tools inside the toolbar
     */
    public int getElementSideSize() {
        return width - getElementPadding()*2;
    }

    @Override
    public void Draw(String viewName, Position pos) {
        App.view.drawRectangle(viewName, this.background, pos.x, pos.y);

        int curr_y = pos.y;

        Iterator<ToolbarElement> iter = getToolbarElements().iterator();

        while(iter.hasNext()) {
            iter.next().Draw(viewName, new Position(pos.x + getElementPadding(), curr_y + getElementPadding()));
            curr_y += element_height + getElementPadding();
        }

        this.deleteElementTool.Draw(viewName);
    }

    @Override
    public String toString() {
        String str = super.toString();

        Iterator<ToolbarElement> iter = getToolbarElements().iterator();

        while(iter.hasNext()) {
            str += "\n" + iter.next().toString();
        }

        return str;
    }

    /**
     * Obtain the special tool used to delete elements
     * @return The special tool used to delete elements
     */
    public DeleteElementTool getDeleteElementTool() {
        return deleteElementTool;
    }

}