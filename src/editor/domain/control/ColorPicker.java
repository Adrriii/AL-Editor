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

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    abstract public void setColor(ColorProperty color);
    abstract public ColorProperty getColor();

}