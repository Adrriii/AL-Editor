package editor.controller;

import editor.model.Drawable;

/**
 * A displayable control allowing to interact with the user.
 * 
 * @author Adrien Boitelle
 * @version 1.0
 */
public abstract class Control extends Drawable {

    private static final long serialVersionUID = 1L;

    /**
     * Print the control to the user
     */
    abstract public void show();

}