package editor.domain.menu;

import java.util.ArrayList;
import java.util.Iterator;

import editor.application.App;
import editor.domain.Menu;
import editor.domain.Position;
import editor.domain.element.Rectangle;
import editor.domain.menu.topmenuelements.*;

/**
* Holds all elements and behaviors of the top menu, horizontally.
* It differs from the toolbar in the sense that it won't hold whiteboard elements.
* It differs from an interaction menu in the sense that i won't display labels vertically.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class TopMenu extends Menu {

    private static final long serialVersionUID = 1L;

    /**
     * The background of the menu.
     */
    private Rectangle background;

    /**
     * Elements that can be clicked inside the menu
     */
    private ArrayList<TopMenuElement> elements;

    /** 
     * The x position that will be taken by the next element
     */
    private int nextPosX;

    /**
     * All elements are rectangles of the same dimensions with padding
     */
    private int elementWidth, elementHeight, elementPaddingY, elementPaddingX;
    
    /**
     * Create a new empty TopMenu
     */
    public TopMenu() {
        super();

        Attach(App.view.getTopMenuView());

        this.pos.x = 0;
        this.pos.y = 0;

        this.nextPosX = 0;

        this.width = 800; // Resizable
        this.height = 75; // Constant

        elementPaddingX = 10;
        elementPaddingY = 10;
        elementHeight = height - elementPaddingY * 2;
        elementWidth = elementHeight;

        this.background = new Rectangle(pos.x, pos.y, width, height, 211, 211, 211, 255);
        this.elements = new ArrayList<>();
    
        addElement(new TopMenuLoad(elementWidth, elementHeight));
        addElement(new TopMenuSave(elementWidth, elementHeight));
        addElement(new TopMenuSaveNew(elementWidth, elementHeight));
        addElement(new TopMenuUndo(elementWidth, elementHeight));
        addElement(new TopMenuRedo(elementWidth, elementHeight));
    }

    @Override
    public void Reattach() {
        super.Reattach();
        Attach(App.view.getTopMenuView());
    }

    /**
     * Update the dimensions of the TopMenu
     * 
     * @param width The new width
     * @param height The new height (Careful: It shouldn't change !)
     */
    public void Resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.background.Update(this.pos, width, height);
    }

    /**
     * When the menu is clicked, it will propagate the click to the concerned element(s).
     * Overlap authorized.
     * 
     * @param x The location of the click relative to the window
     * @param y The location of the click relative to the window
     */
    public void onClick(int x, int y) {
        elements.forEach(element -> { if (element.isClicked(x, y)) element.onClick(); });
    }

    /**
     * Add a new element to the top menu
     * 
     * @param element The element should be an image, but it can be anything in a rectangle shape
     */
    public void addElement(TopMenuElement element) {
        element.pos.x = nextPosX + elementPaddingX;
        element.pos.y = pos.y + elementPaddingY;
        nextPosX = element.pos.x + element.width;

        this.elements.add(element);

        this.width = nextPosX - pos.x;
    }

    /**
     * Remove the element from the list of elements
     * @param element
     */
    public void removeElement(TopMenuElement element) {
        this.elements.remove(element);
        this.width -= element.width + elementPaddingX;
    }

    @Override
    public void Draw(String viewName, Position pos) {
        App.view.drawRectangle(viewName, this.background, pos.x, pos.y);
        
        int curr_x = pos.x;

        Iterator<TopMenuElement> iter = elements.iterator();

        while(iter.hasNext()) {
            TopMenuElement item = iter.next();
            item.Draw(viewName, new Position(curr_x + elementPaddingX, pos.y + elementPaddingY));
            curr_x += item.width + elementPaddingX;
        }
    }
}