package editor.userinterface;

import editor.controller.control.*;

/**
* Defines a Factory that should provide for all required controls.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public interface ControlFactory {

    public ColorPicker GetColorPicker();
    public InputBoxes GetInputBoxes(String[] labels);

    public Slider GetSlider(String text, int value, int lower, int upper);
    public Slider GetSlider(String text, int lower, int upper);
    public Slider GetSlider(String text);
}