package editor.userinterface.javafximpl;

import editor.userinterface.ControlFactory;
import editor.domain.control.*;
import editor.userinterface.javafximpl.control.*;

public class JavaFXControlFactory implements ControlFactory {

    @Override
    public ColorPicker GetColorPicker() {
        return new JavaFXColorPicker();
    }

    @Override
    public InputBoxes GetInputBoxes(String[] labels) {
        return new JavaFXInputBoxes(labels);
    }

}