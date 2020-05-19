package editor.domain.menu;

import editor.domain.Drawable;

/**
* A button that will perform an action on click.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class TopMenuElement extends Drawable {
    
    private static final long serialVersionUID = 1L;

    public TopMenuElement(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract void onClick();
    
}