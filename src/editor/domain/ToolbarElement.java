package editor.domain;

public class ToolbarElement extends Drawable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Toolbar parent;
    private Element element;

    public ToolbarElement(Toolbar parent, Element element, int pos_x, int pos_y, int side) {
        this.parent = parent;
        this.pos.x = pos_x;
        this.pos.y = pos_y;
        this.width = side;
        this.height = side;
        this.element = element.Clone();
        this.element.Update(new Position(0, 0));
    }

    public Element cloneElement() {
        return element.Clone();
    }

    public Element getElement() {
        return this.element;
    }

    @Override
    public void Draw(Position pos) {
        this.element.Draw(pos, this.parent.getElementSideSize(), this.parent.getElementSideSize());
    }

    @Override
    public String toString() {
        return "Tool "+ this.element.toString();
    }

}