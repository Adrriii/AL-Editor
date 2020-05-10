package editor.domain.element;

import java.util.HashMap;

import editor.domain.Element;
import editor.domain.ElementProperty;
import editor.domain.Position;
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
        newElement.pos.x = pos.x;
        newElement.pos.y = pos.y;
        newElement.width = width;
        newElement.height = height;
        newElement.min_x = min_x;
        newElement.min_y = min_y;

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

    @Override
    public void Draw(Position pos, int fit_width, int fit_height) {
        // TODO Auto-generated method stub

    }
}