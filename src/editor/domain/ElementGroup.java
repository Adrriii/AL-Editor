package editor.domain;

import java.util.ArrayList;
import java.util.Iterator;

public class ElementGroup extends Element {

    private ArrayList<Element> elements;

    public ElementGroup() {
        super();

        elements = new ArrayList<>();

        width = 0;
        height = 0;
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
    public void Draw(int x, int y) {
        this.elements.forEach(element -> element.Draw(x - pos_x + element.pos_x,y - pos_y +  element.pos_y));
    }

    @Override
    public void Draw(int x, int y, int fit_width, int fit_height) {
        final double scale = Math.min(fit_width / (double) width, fit_height / (double) height);
        
        this.elements.forEach(element ->  {
            element.Draw((int) (x - pos_x + element.pos_x * scale),(int) (y - pos_y +  element.pos_y * scale), scale);
        });
    }

    @Override
    public void Update(int new_pos_x, int new_pos_y, int width, int height) {
        int diff_x = 0;
        int diff_y = 0;
        if(new_pos_x > min_x) {
            diff_x = new_pos_x - pos_x;
            pos_x = new_pos_x;
        } else {
            diff_x = min_x - pos_x;
            pos_x = min_x;
        }
        if(new_pos_y > min_y) {
            diff_y = new_pos_y - pos_y;
            pos_y = new_pos_y;
        } else {
            diff_y = min_y - pos_y;
            pos_y = min_y;
        }
        final int dx = diff_x;
        final int dy = diff_y;
        
        this.elements.forEach(element -> element.Update(element.pos_x + dx, element.pos_y + dy));
        
        Update();
    }

    @Override
    public void Update(int new_pos_x, int new_pos_y) {        
        Update(new_pos_x, new_pos_y, width, height);
    }

    public void UpdateReferencePos() {
        this.elements.forEach(element -> {
            if(pos_x == 0 || element.pos_x < pos_x) {
                pos_x = element.pos_x;
            }

            if(pos_y == 0 || element.pos_y < pos_y) {
                pos_y = element.pos_y;
            }

            if(width == 0 || element.pos_x + element.getSurfaceWidth() - pos_x > width) {
                width = element.pos_x + element.getSurfaceWidth() - pos_x;
            }

            if(height == 0 || element.pos_y + element.getSurfaceHeight() - pos_y > height) {
                height = element.pos_y + element.getSurfaceHeight() - pos_y;
            }
        });
        
        this.elements.forEach(element -> {
            element.min_x = min_x + (element.pos_x - pos_x);
            element.min_y = min_y + (element.pos_y - pos_y);
        });
    }

    @Override
    public void setSelected(boolean state) {
        selected = state;
        this.elements.forEach(element -> element.setSelected(state));
    }

    @Override
    public boolean isClicked(int x, int y) {
        Iterator<Element> iter = elements.iterator();
        while(iter.hasNext()) {
            if(iter.next().isClicked(x, y)) return true;
        }
        return false;
    }
}