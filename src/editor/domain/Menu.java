package editor.domain;

import java.util.ArrayList;

public class Menu extends Drawable {

    protected ArrayList<Control> controls;

    public Menu() {
        super();
        
        controls = new ArrayList<>();
    }

}