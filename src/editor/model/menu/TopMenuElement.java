package editor.model.menu;

import editor.model.Drawable;

/**
* A button that will perform an action on click.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class TopMenuElement extends Drawable {
    
    private static final long serialVersionUID = 1L;

    /**
     * An element that can be clicked on inside the topmenu.
     * 
     * @param width Should respect the dimensions of the topmenu's elements
     * @param height Should respect the dimensions of the topmenu's elements
     */
    public TopMenuElement(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Any action performed when clicked on in the menu.
     */
    public abstract void onClick();
    
}