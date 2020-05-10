package editor.domain.elementproperty;

import editor.domain.ElementProperty;

public class IntegerProperty extends ElementProperty {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public int value;

    public IntegerProperty(int value) {
        this.value = value;
    }
}