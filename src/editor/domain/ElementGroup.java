package editor.domain;

import java.util.ArrayList;

public class ElementGroup extends Element {

    private ArrayList<Element> elements;

    public ElementGroup() {
        super();

        elements = new ArrayList<>();

        pos_x = 0;
        pos_y = 0;
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
        UpdateReferencePos();
    }

    public ArrayList<Element> getElements() {
        return this.elements;
    }

    @Override
    public void Update() {
        elements.forEach(element -> element.Update());
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
    public void Draw(int x, int y, int fit_width, int fit_height) {
        this.elements.forEach(element -> element.Draw(x, y));
    }

    @Override
    public void Update(int new_pos_x, int new_pos_y, int width, int height) {
        this.elements.forEach(element -> element.Update(new_pos_x + (element.pos_x - pos_x), new_pos_y + (element.pos_y - pos_y)));
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

            if(height == 0 || element.pos_x + element.getSurfaceHeight() - pos_y > height) {
                height = element.pos_x + element.getSurfaceHeight() - pos_y;
            }
        });
    }

    @Override
    public void setSelected(boolean state) {
        selected = state;
        this.elements.forEach(element -> element.setSelected(state));
    }
}