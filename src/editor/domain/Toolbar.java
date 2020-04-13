package editor.domain;

import java.util.ArrayList;

import editor.domain.element.Rectangle;

public class Toolbar extends Drawable implements ISerializable {

    private ArrayList<ToolbarElement> toolbarElements;

    private int element_padding;

    public Toolbar() {
        super();

        toolbarElements = new ArrayList<>();
        toolbarElements.add(new ToolbarElement(new Rectangle(130,160)));
        toolbarElements.add(new ToolbarElement(new Rectangle(190,130, 255, 0, 0)));

        this.pos_x = 0;
        this.pos_y = 75; // Height of the TopMenu

        this.width = 75; // Constant
        this.height = 600 - this.pos_y; // Resizable

        this.element_padding = 10;
    }

    public int getElementPadding() {
        return element_padding;
    }

    public ArrayList<ToolbarElement> getToolbarElements() {
        return new ArrayList<ToolbarElement>(toolbarElements);
    }

}