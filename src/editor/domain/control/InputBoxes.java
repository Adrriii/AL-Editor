package editor.domain.control;

import editor.domain.Control;

/**
* Defines the needed methods for multiple Input Boxes.
* Each Integer should be in a separate Input Box.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class InputBoxes extends Control {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    abstract public void setValues(Integer[] value);
    abstract public Integer[] getValues();

}