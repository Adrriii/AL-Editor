package editor.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import editor.application.App;
import editor.domain.menu.Interaction;
import editor.domain.menu.interactionmenus.*;

/**
* A displayable object.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class Element extends Drawable {

    private static final long serialVersionUID = 1L;

    public HashMap<String, ElementProperty> properties;

    protected boolean selected = false;

    protected int min_x = 0;
    protected int min_y = 0;

    public int rotation = 0;

    public Element(HashMap<String,ElementProperty> properties) {
        super();

        this.properties = new HashMap<>();

        for(Map.Entry<String, ElementProperty> entry : properties.entrySet()) {
            this.properties.put(entry.getKey(), entry.getValue().Clone());
        }
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

    public ArrayList<Interaction> getAvailableInteractions() {
        ArrayList<Interaction> list = new ArrayList<>();
        
        if(new ArrayList<Element>(App.model.getSelectedElements()).size() > 1) {
            list.add(new GroupElementsInteraction(this));
        } 

        list.add(new ColorChangeInteraction(this));
        list.add(new RotationChangeInteraction(this));
        list.add(new DeleteElementInteraction(this));

        return list;
    }

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

    abstract public void Draw(String viewName, Position pos, int fit_width, int fit_height);

    @Override
    public void Draw(String viewName, Position pos) {
        Draw(viewName, pos, 1);
    }
    
    public void Draw(String viewName, Position pos, double scale) {
        Draw(viewName, pos, (int) (width * scale), (int) (height * scale));
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
    
    @Override
    public boolean isClicked(Position pos) {
        return super.isClicked(pos);
    }
    
    public boolean isClicked(int x, int y) {
        return isClicked(new Position(x, y));
    }
}