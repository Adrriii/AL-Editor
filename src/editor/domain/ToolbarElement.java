package editor.domain;

public class ToolbarElement extends Drawable implements ISerializable {

    private Element element;

    public ToolbarElement(Element element, int pos_x, int pos_y, int side) {
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

}