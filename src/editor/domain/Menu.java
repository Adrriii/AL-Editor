package editor.domain;

import java.util.ArrayList;

/**
* A menu listing controls.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class Menu extends Drawable {

    private static final long serialVersionUID = 1L;
    protected ArrayList<Control> controls;

    public Menu() {
        super();
        
        controls = new ArrayList<>();
    }

}