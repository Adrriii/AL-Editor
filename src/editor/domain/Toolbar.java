package editor.domain;

import java.util.ArrayList;
import java.util.Iterator;

import editor.application.App;
import editor.domain.element.Rectangle;
import javafx.scene.paint.Color;

public class Toolbar extends Drawable implements ISerializable {

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
    
    public void Resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.background.Update(this.pos, width, height);
    }

    public int getElementPadding() {
        return element_padding;
    }

    public void addElement(Element element) {
        toolbarElements.add(new ToolbarElement(this,
                element, 
                this.pos.x + this.element_padding, 
                this.pos.y + this.element_padding + (this.element_height + this.element_padding) * toolbarElements.size(), 
                this.element_height
            ));

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

}