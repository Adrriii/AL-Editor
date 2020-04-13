package editor.domain.element;

import java.util.HashMap;

import editor.domain.Element;
import editor.domain.ElementProperty;
import editor.domain.elementproperty.ColorProperty;

public class Polygon extends Element {

    public Polygon() {
        super();

        properties.put("color",new ColorProperty(0,0,0,255));
    }

    public Polygon(HashMap<String,ElementProperty> properties) {
        super(properties);
    }

    public ColorProperty getColorProperty() {
        return (ColorProperty) properties.get("color");
    }

    @Override
    public Polygon Clone() {
        Polygon newElement = new Polygon(properties);
        newElement.pos_x = pos_x;
        newElement.pos_y = pos_y;
        newElement.width = width;
        newElement.height = height;

        return newElement;
    }

    @Override
    public int getSurfaceWidth() {
        return width;
    }

    @Override
    public int getSurfaceHeight() {
        return height;
    }
}