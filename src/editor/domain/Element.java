package editor.domain;

import java.util.HashMap;

public abstract class Element extends Drawable implements ISerializable, IControllable {

    public HashMap<String,ElementProperty> properties;

    protected boolean selected = false;

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

    public void Update(int new_pos_x, int new_pos_y, int width, int height) {
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

        this.width = width;
        this.height = height;

        Update();
    }

    public void Update(int new_pos_x, int new_pos_y) {
        Update(new_pos_x, new_pos_y, this.getSurfaceWidth(), this.getSurfaceHeight());
    }

    abstract public void Draw(int x, int y, int fit_width, int fit_height);

    @Override
    public void Draw(int x, int y) {
        Draw(x, y, -1, -1);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean state) {
        selected = state;
    }
}