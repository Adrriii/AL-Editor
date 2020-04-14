package editor.domain;

public class ToolbarElement extends Drawable implements ISerializable {

    private Toolbar parent;
    private Element element;

    public ToolbarElement(Toolbar parent, Element element, int pos_x, int pos_y, int side) {
        this.parent = parent;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.width = side;
        this.height = side;
        this.element = element.Clone();
    }

    public Element cloneElement() {
        return element.Clone();
    }

    public Element getElement() {
        return this.element;
    }

    @Override
    public void Draw(int x, int y) {
        this.element.Draw(pos_x, pos_y, this.parent.getElementSideSize(), this.parent.getElementSideSize());
    }

}