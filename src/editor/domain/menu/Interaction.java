package editor.domain.menu;

import editor.application.App;
import editor.domain.Drawable;
import editor.domain.Position;
import editor.domain.element.Rectangle;

/**
* A visible area that will trigger an action on click.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class Interaction extends Drawable {

    private static final long serialVersionUID = 1L;

    /**
     * The width of the border surrounding the interaction
     */
    private int border_width = 2;

    /**
     * A rectangle drawn behind the interaction's background to create a border
     */
    private Rectangle border;

    /**
     * A rectangle drawn behind the interaction's label to create a background
     */
    private Rectangle background;

    /**
     * A label written on top of the interaction to indicate its purpose
     */
    private String label;

    /**
     * The display size of the interaction's label
     */
    public int fontSize = 18;
    
    /**
     * Create a new available interaction
     * 
     * @param label The text to display
     */
    public Interaction(String label) {
        super();

        this.pos.x = 0;
        this.pos.y = 0;

        this.width = 150;
        this.height = 27;

        this.background = new Rectangle(pos.x, pos.y, width, height, 231, 231, 231, 255);
        this.border = new Rectangle(pos.x, pos.y, width + border_width * 2, height + border_width * 2, 30, 30, 30, 255);
        this.label = label;
    }

    /**
     * Any action that will be performed when this interaction is clicked
     */
    public abstract void onClick();

    @Override
    public void Draw(String viewName, Position pos) {
        App.view.drawRectangle(viewName, this.border, pos.x - border_width, pos.y - border_width);
        App.view.drawRectangle(viewName, this.background, pos.x, pos.y);
        App.view.drawText(viewName, this.label, pos.x, pos.y + this.fontSize, this.fontSize);
    }

}