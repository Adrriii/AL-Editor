package editor.model;

import java.util.ArrayList;
import java.util.Iterator;

import editor.application.App;
import editor.model.menu.Interaction;
import editor.model.menu.interactionmenus.*;

/**
* A group of elements.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ElementGroup extends Element {

    private static final long serialVersionUID = 1L;

    /**
     * The elements that compose this group
     */
    public ArrayList<Element> elements;

    /**
     * The bottom right corner of this group
     */
    public Position furthest;

    /**
     * Create an empty element group
     */
    public ElementGroup() {
        super();

        elements = new ArrayList<>();

        width = 0;
        height = 0;

        furthest = new Position(0,0);
    }

    @Override
    public void Add(Element element) throws InvalidCompositeAddition {
        elements.add(element);
        UpdateReferencePos();
    }

    @Override
    public void Remove(Element element) throws InvalidCompositeAddition {
        elements.remove(element);
        element.min_x = 0;
        element.min_y = 0;
        UpdateReferencePos();
    }

    /**
     * Get all the elements composing this group
     * @return A list of elements
     */
    public ArrayList<Element> getElements() {
        return this.elements;
    }

    @Override
    public void Update() {
        elements.forEach(element -> element.Update());
        Notify();
    }

    @Override
    public Element Clone() {
        ElementGroup group = new ElementGroup();

        this.elements.forEach(element -> {
            try {
                group.Add(element.Clone());
            } catch (InvalidCompositeAddition e) {
                e.printStackTrace();
            }
        });

        group.furthest = furthest;

        return group;
    }

    @Override
    public ArrayList<Interaction> getAvailableInteractions() {
        ArrayList<Interaction> list = new ArrayList<>();
        
        
        if(new ArrayList<Element>(App.model.getSelectedElements()).size() > 1) {
            list.add(new GroupElementsInteraction(this));
        } 
        if (new ArrayList<Element>(App.model.getSelectedElements()).size() == 1) {
            list.add(new DegroupElementsInteraction(this));
        }

        list.add(new DeleteElementInteraction(this));

        return list;
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
    public void Draw(String viewName, Position pos) {
        Draw(viewName, pos, width, height);
    }

    @Override
    public void Draw(String viewName, Position pos, int fit_width, int fit_height) {
        final double scale = Math.min(fit_width / (double) width, fit_height / (double) height);
        
        this.elements.forEach(element ->  {
            element.Draw(viewName, new Position((int) (pos.x - this.pos.x + element.pos.x * scale),(int) (pos.y - this.pos.y +  element.pos.y * scale)), scale);
        });
    }

    @Override
    public Position Update(Position new_pos, int width, int height) {
        int diff_x = 0;
        int diff_y = 0;
        if(new_pos.x > min_x) {
            diff_x = new_pos.x - pos.x;
            pos.x = new_pos.x;
        } else {
            diff_x = min_x - pos.x;
            pos.x = min_x;
        }
        if(new_pos.y > min_y) {
            diff_y = new_pos.y - pos.y;
            pos.y = new_pos.y;
        } else {
            diff_y = min_y - pos.y;
            pos.y = min_y;
        }
        
        for(Element elem : this.elements) {
            elem.Update(new Position(elem.pos.x + diff_x, elem.pos.y + diff_y));
        }
        
        Update();

        return new Position(pos.x, pos.y);
    }

    @Override
    public Position Update(Position new_pos) {        
        return Update(new_pos, width, height);
    }

    /**
     * Compute the current top left and bottom right positions of the group,
     * as well as updating each element's minimum position.
     */
    public void UpdateReferencePos() {
        this.elements.forEach(element -> {
            if(pos.x == 0 || element.pos.x < pos.x) {
                pos.x = element.pos.x;
            }

            if(pos.y == 0 || element.pos.y < pos.y) {
                pos.y = element.pos.y;
            }

            if(furthest.x == 0 || element.pos.x + element.width > furthest.x) {
                furthest.x = element.pos.x + element.width;
            }

            if(furthest.y == 0 || element.pos.y + element.height > furthest.y) {
                furthest.y = element.pos.y + element.height;
            }

            if(width == 0 || furthest.x - pos.x > width) {
                width = furthest.x - pos.x;
            }

            if(height == 0 || furthest.y - pos.y > height) {
                height = furthest.y - pos.y;
            }
        });
        
        this.elements.forEach(element -> {
            element.min_x = min_x + (element.pos.x - pos.x);
            element.min_y = min_y + (element.pos.y - pos.y);
        });
    }

    @Override
    public void setSelected(boolean state) {
        selected = state;
        this.elements.forEach(element -> element.setSelected(state));
    }

    @Override
    public boolean isClicked(Position pos) {
        return intersects(pos, 1, 1);
    }
    
    @Override
    public boolean intersects(int rx, int ry, int rwidth, int rheight) {
        Iterator<Element> iter = elements.iterator();
        while(iter.hasNext()) {
            Element nex = iter.next();
            if(nex.intersects(rx, ry, rwidth, rheight)) return true;
        }
        return false;
    }
    
    @Override
    public boolean intersects(Position pos, int rwidth, int rheight) {
        return intersects(pos.x, pos.y, rwidth, rheight);
    }
}