package editor.domain;

import java.util.ArrayList;

public abstract class Menu extends Drawable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected ArrayList<Control> controls;

    public Menu() {
        super();
        
        controls = new ArrayList<>();
    }

}