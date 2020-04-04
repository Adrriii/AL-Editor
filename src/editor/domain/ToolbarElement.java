package editor.domain;

public class ToolbarElement extends Drawable implements ISerializable {

    private Element element;

    public Element getElement() {
        return (Element) element.clone();
    }

}