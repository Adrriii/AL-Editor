package editor.domain;

import java.util.ArrayList;
import java.util.Iterator;

public class ElementGroup extends Element {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ArrayList<Element> elements;

    private Position furthest;

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

        return group;
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
    public void Draw(Position pos) {
        Draw(pos, width, height);
    }

    @Override
    public void Draw(Position pos, int fit_width, int fit_height) {
        final double scale = Math.min(fit_width / (double) width, fit_height / (double) height);
        
        this.elements.forEach(element ->  {
            element.Draw(new Position((int) (pos.x - this.pos.x + element.pos.x * scale),(int) (pos.y - this.pos.y +  element.pos.y * scale)), scale);
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
        final int dx = diff_x;
        final int dy = diff_y;
        
        this.elements.forEach(element -> element.Update(new Position(element.pos.x + dx, element.pos.y + dy)));
        
        Update();

        return new Position(pos.x, pos.y);
    }

    @Override
    public Position Update(Position new_pos) {        
        return Update(new_pos, width, height);
    }

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