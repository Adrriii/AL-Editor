package editor.domain;

import java.util.HashMap;

public abstract class Element extends Drawable implements ISerializable, IControllable {

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

    public abstract Element Clone();

    abstract public int getSurfaceWidth();

    abstract public int getSurfaceHeight();

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