package editor.domain;

import java.util.ArrayList;
import java.util.Iterator;

import editor.application.App;
import editor.domain.element.Rectangle;

public class Toolbar extends Drawable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ArrayList<ToolbarElement> toolbarElements;

    private Rectangle background;

    private int element_padding;
    private int element_height;

    public Toolbar() {
        super();

        Attach(App.view.getToolbarView());

        toolbarElements = new ArrayList<>();

        this.pos.x = 0;
        this.pos.y = 75; // Height of the TopMenu

        this.width = 75; // Constant
        this.height = 600 - this.pos.y; // Resizable

        this.element_padding = 10;

        this.element_height = this.width - this.element_padding * 2;

        this.background = new Rectangle(0, 0, width, height, 80, 80, 80, 255);
    }

    public void LoadDefaultTools() {
        addElement(new Rectangle(130,160));
        addElement(new Rectangle(190,130, 255, 0, 0));
    }    

    public void Resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.background.Update(this.pos, width, height);
    }

    public int getElementPadding() {
        return element_padding;
    }

    public ToolbarElement addElement(Element element) {
        ToolbarElement created = new ToolbarElement(this,
            element, 
            this.pos.x + this.element_padding, 
            this.pos.y + this.element_padding + (this.element_height + this.element_padding) * toolbarElements.size(), 
            this.element_height
        );

        toolbarElements.add(created);

        Notify();

        return created;
    }

    public void removeElement(ToolbarElement element) {

        boolean move = false;

        Iterator<ToolbarElement> iter = getToolbarElements().iterator();

        while(iter.hasNext()) {
            ToolbarElement now = iter.next();
            
            if(move) {
                now.pos.y -= this.element_height + this.element_padding;
            }

            if(now == element) {
                move = true;
            }
        }
        

        toolbarElements.remove(element);

        Notify();
    }

    public ArrayList<ToolbarElement> getToolbarElements() {
        return new ArrayList<ToolbarElement>(toolbarElements);
    }

    public int getElementSideSize() {
        return width - getElementPadding()*2;
    }

    @Override
    public void Draw(Position pos) {
        App.view.drawRectangle(this.background, pos.x, pos.y);

        int curr_y = pos.y;

        Iterator<ToolbarElement> iter = getToolbarElements().iterator();

        while(iter.hasNext()) {
            iter.next().Draw(new Position(pos.x + getElementPadding(), curr_y + getElementPadding()));
            curr_y += width;
        }
    }

    @Override
    public String toString() {
        String str = super.toString();

        Iterator<ToolbarElement> iter = getToolbarElements().iterator();

        while(iter.hasNext()) {
            str += "\n" + iter.next().toString();
        }

        return str;
    }

}