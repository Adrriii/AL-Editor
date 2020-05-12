package editor.domain.control;

import editor.domain.Control;

public abstract class InputBoxes extends Control {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    abstract public void setValues(Integer[] value);
    abstract public Integer[] getValues();

}