package editor.domain;

import java.util.ArrayList;

public class Toolbar extends Drawable implements ISerializable {

    private ArrayList<ToolbarElement> toolbarElements;

    public Toolbar() {
        super();
        
        toolbarElements = new ArrayList<>();
    }

}