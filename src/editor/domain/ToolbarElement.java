package editor.domain;

public class ToolbarElement extends Drawable implements ISerializable {

    private Element element;

    public ToolbarElement(Element element) {
        this.element = element.Clone();
    }

    public Element cloneElement() {
        return element.Clone();
    }

    public Element getElement() {
        return this.element;
    }

}