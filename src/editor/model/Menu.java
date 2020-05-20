package editor.model;

import java.util.ArrayList;

import editor.controller.Control;

/**
* A menu listing controls.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class Menu extends Drawable {

    private static final long serialVersionUID = 1L;

    /**
     * A list of controls available to the user in this menu
     */
    protected ArrayList<Control> controls;

    /**
     * Create a new empty menu
     */
    public Menu() {
        super();
        
        controls = new ArrayList<>();
    }

}