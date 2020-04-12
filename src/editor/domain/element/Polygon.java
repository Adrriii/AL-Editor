package editor.domain.element;

import editor.domain.Element;
import editor.domain.elementproperty.ColorProperty;

public class Polygon extends Element {

    public Polygon() {
        super();

        properties.put("color",new ColorProperty(0,0,0,255));
    }
}