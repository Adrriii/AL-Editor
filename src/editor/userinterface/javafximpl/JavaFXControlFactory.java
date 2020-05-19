package editor.userinterface.javafximpl;

import editor.userinterface.ControlFactory;
import editor.domain.control.*;
import editor.userinterface.javafximpl.control.*;

/**
* Allows to fetch all implemented Controls with JavaFX.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class JavaFXControlFactory implements ControlFactory {

    @Override
    public ColorPicker GetColorPicker() {
        return new JavaFXColorPicker();
    }

    @Override
    public InputBoxes GetInputBoxes(String[] labels) {
        return new JavaFXInputBoxes(labels);
    }

    @Override
    public Slider GetSlider(String text, int value, int lower, int upper) {
        return new JavaFXSlider(text, value, lower, upper);
    }

    @Override
    public Slider GetSlider(String text, int lower, int upper) {
        return new JavaFXSlider(text, lower, upper);
    }

    @Override
    public Slider GetSlider(String text) {
        return new JavaFXSlider(text);
    }

    

}