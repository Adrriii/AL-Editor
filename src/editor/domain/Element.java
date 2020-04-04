package editor.domain;

import java.util.ArrayList;

public class Element extends Drawable implements ISerializable, IControllable, IClonable {

    private ArrayList<ElementProperty> properties;

    public Element(ArrayList<ElementProperty> properties) {
        super();

        properties = new ArrayList<>(properties);
    }

    public Element() {
        this(new ArrayList<ElementProperty>());
    }

    public void Add(Element element) throws InvalidCompositeAddition {
        throw new InvalidCompositeAddition();
    }
    
    public void Remove(Element element) throws InvalidCompositeAddition {
        throw new InvalidCompositeAddition();
    }

    public void Update() {
        Notify();
    }

    @Override
    public Object clone() {
        Element newElement = new Element(properties);
        newElement.pos_x = pos_x;
        newElement.pos_y = pos_y;
        newElement.width = width;
        newElement.height = height;

        return newElement;
    }
}