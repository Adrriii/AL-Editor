package editor.userinterface;

import editor.domain.control.*;

public interface ControlFactory {

    public ColorPicker GetColorPicker();
    public InputBoxes GetInputBoxes(String[] labels);

    public Slider GetSlider(String text, int value, int lower, int upper);
    public Slider GetSlider(String text, int lower, int upper);
    public Slider GetSlider(String text);
}