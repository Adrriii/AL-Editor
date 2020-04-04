package editor.domain;

import java.util.ArrayList;

public class Toolbar extends Drawable implements ISerializable {

    private ArrayList<ToolbarElement> toolbarElements;

    public Toolbar() {
        super();

        toolbarElements = new ArrayList<>();
    }

    public ArrayList<ToolbarElement> getToolbarElements() {
        return new ArrayList<ToolbarElement>(toolbarElements);
    }

}