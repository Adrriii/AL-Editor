package editor.domain.control;

import editor.domain.Control;
import editor.domain.elementproperty.ColorProperty;

public abstract class ColorPicker extends Control {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    abstract public void setColor(ColorProperty color);
    abstract public ColorProperty getColor();

}