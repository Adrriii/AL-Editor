package editor.userinterface.javafximpl;

import editor.domain.control.ColorPicker;
import editor.userinterface.ControlFactory;
import editor.userinterface.javafximpl.control.JavaFXColorPicker;

public class JavaFXControlFactory implements ControlFactory {

    @Override
    public ColorPicker GetColorPicker() {
        return new JavaFXColorPicker();
    }

}