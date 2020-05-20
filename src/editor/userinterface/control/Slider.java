package editor.userinterface.control;

import editor.userinterface.Control;

/**
* Defines the needed methods for a Slider.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class Slider extends Control {

    private static final long serialVersionUID = 1L;
    protected String text;
    protected int value, lower, upper;

    /**
    * Sets the basic properties of a Slider.
    * 
    * @param text The label text to be displayed.
    * @param value The starting value of the slider.
    * @param lower The minimum value of the slider.
    * @param upper The maximum value of the slider.
    */
    public Slider(String text, int value, int lower, int upper) {
        this.text = text;
        this.value = value;
        this.lower = lower;
        this.upper = upper;
    }

    public Slider(String text, int lower, int upper) {
        this(text, lower + (upper-lower)/2, lower, upper);
    }

    public Slider(String text) {
        this(text, 50, 0, 100);
    }

    /**
     * Sets the default value of the slider's head.
     * 
     * @param value An integer value that should be bound to the sliders min and max
     */
    abstract public void setValue(int value);

    /**
     * Get the user's input.
     * 
     * @return An integer value
     */
    abstract public int getValue();

}