package editor.userinterface.control;

import editor.userinterface.Control;

/**
* Defines the needed methods for multiple Input Boxes.
* Each Integer should be in a separate Input Box.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class InputBoxes extends Control {

    private static final long serialVersionUID = 1L;

    /**
     * Fills each InputBox, in order, with an integer.
     * 
     * @param value A list of integer values
     */
    abstract public void setValues(Integer[] value);

    /**
     * Returns the user's input values.
     * Eventually hangs the program until the user responds.
     * 
     * @return A list of integer values
     */
    abstract public Integer[] getValues();

}