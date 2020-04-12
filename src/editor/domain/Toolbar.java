package editor.domain;

import java.util.ArrayList;

public class Toolbar extends Drawable implements ISerializable {

    private ArrayList<ToolbarElement> toolbarElements;

    public Toolbar() {
        super();

        toolbarElements = new ArrayList<>();

        this.pos_x = 0;
        this.pos_y = 75; // Height of the TopMenu

        this.width = 75; // Constant
        this.height = 600 - this.pos_y; // Resizable
    }

    public ArrayList<ToolbarElement> getToolbarElements() {
        return new ArrayList<ToolbarElement>(toolbarElements);
    }

}