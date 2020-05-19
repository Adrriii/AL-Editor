package editor.domain;

import editor.application.App;
import editor.domain.element.Rectangle;

public class ToolbarElement extends Drawable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Toolbar parent;
    private Element element;
    private int border_width;
    private int padding;

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

    public Element cloneElement() {
        return element.Clone();
    }

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