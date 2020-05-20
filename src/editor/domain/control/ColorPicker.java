package editor.domain.control;

import editor.domain.Control;
import editor.domain.elementproperty.ColorProperty;

/**
* Defines the needed methods for a ColorPicker.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class ColorPicker extends Control {

    private static final long serialVersionUID = 1L;

    /**
     * Sets the colorpicker's displayed color
     * 
     * @param color A ColorProperty
     */
    abstract public void setColor(ColorProperty color);

    /**
     * Returns the user's input value.
     * Eventually hangs the program until the user responds.
     * 
     * @return A ColorProperty
     */
    abstract public ColorProperty getColor();

}