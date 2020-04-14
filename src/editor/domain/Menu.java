package editor.domain;

import java.util.ArrayList;

public abstract class Menu extends Drawable {

    protected ArrayList<Control> controls;

    public Menu() {
        super();
        
        controls = new ArrayList<>();
    }

}