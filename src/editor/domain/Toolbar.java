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

        this.pos_x = 0;
        this.pos_y = 75; // Height of the TopMenu

        this.width = 75; // Constant
        this.height = 600 - this.pos_y; // Resizable

        this.element_padding = 10;

        this.element_height = this.width - this.element_padding * 2;

        this.background = new Rectangle(0, 0, width, height, 80, 80, 80, 255);

        addElement(new Rectangle(130,160));
        addElement(new Rectangle(190,130, 255, 0, 0));
    }

    public int getElementPadding() {
        return element_padding;
    }

    public void addElement(Element element) {
        toolbarElements.add(new ToolbarElement(this,
                element.Clone(), 
                this.pos_x + this.element_padding, 
                this.pos_y + this.element_padding + (this.element_height + this.element_padding) * toolbarElements.size(), 
                this.element_height
            ));
    }

    public ArrayList<ToolbarElement> getToolbarElements() {
        return new ArrayList<ToolbarElement>(toolbarElements);
    }

    public int getElementSideSize() {
        return width - getElementPadding()*2;
    }

    @Override
    public void Draw(int x, int y) {
        App.view.drawRectangle(this.background, x, y);

        int curr_y = y;

        Iterator<ToolbarElement> iter = getToolbarElements().iterator();

        while(iter.hasNext()) {
            iter.next().Draw(x + getElementPadding(), curr_y + getElementPadding());
            curr_y += width;
        }
    }

}