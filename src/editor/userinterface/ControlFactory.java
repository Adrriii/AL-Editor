package editor.userinterface;

import editor.domain.control.*;

public interface ControlFactory {

    public ColorPicker GetColorPicker();
    public InputBoxes GetInputBoxes(String[] labels);
}