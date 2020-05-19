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
    private int border_width = 2;
    private Rectangle border;
    private Rectangle background;
    private String label;

    public int fontSize = 18;
    
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

    public abstract void onClick();

    @Override
    public void Draw(String viewName, Position pos) {
        App.view.drawRectangle(viewName, this.border, pos.x - border_width, pos.y - border_width);
        App.view.drawRectangle(viewName, this.background, pos.x, pos.y);
        App.view.drawText(viewName, this.label, pos.x, pos.y + this.fontSize, this.fontSize);
    }

}