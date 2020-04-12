package editor.domain;

import java.util.HashMap;

public class Element extends Drawable implements ISerializable, IControllable, IClonable {

    public HashMap<String,ElementProperty> properties;

    public Element(HashMap<String,ElementProperty> properties) {
        super();

        this.properties = new HashMap<>(properties);
    }

    public Element() {
        this(new HashMap<String,ElementProperty>());
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

    @Override
    public boolean isClicked(int x, int y) {
        return x > pos_x && x < pos_x + width && y > pos_y && y < pos_y + height; // Base hitbox, redefine for more complex objects.
    }

    public void Update(int new_pos_x, int new_pos_y) {
        if(new_pos_x > 0) {
            pos_x = new_pos_x;
        } else {
            pos_x = 0;
        }
        if(new_pos_y > 0) {
            pos_y = new_pos_y;
        } else {
            pos_y = 0;
        }

        Update();
    }
}