package editor.domain;

import java.io.Serializable;
import java.util.HashMap;

public abstract class Element extends Drawable implements IControllable {

    public HashMap<String, ElementProperty> properties;

    protected boolean selected = false;

    protected int min_x = 0;
    protected int min_y = 0;

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

    public Position Update(Position new_pos, int width, int height) {
        if(new_pos.x > min_x) {
            pos.x = new_pos.x;
        } else {
            pos.x = min_x;
        }
        if(new_pos.y > min_y) {
            pos.y = new_pos.y;
        } else {
            pos.y = min_y;
        }

        this.width = width;
        this.height = height;

        Update();

        return new Position(pos.x, pos.y);
    }

    public Position Update(Position new_pos) {
        return Update(new_pos, this.getSurfaceWidth(), this.getSurfaceHeight());
    }

    abstract public void Draw(Position pos, int fit_width, int fit_height);

    @Override
    public void Draw(Position pos) {
        Draw(pos, 1);
    }
    
    public void Draw(Position pos, double scale) {
        Draw(pos, (int) (width * scale), (int) (height * scale));
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean state) {
        selected = state;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+ ": " + super.toString();
    }
}