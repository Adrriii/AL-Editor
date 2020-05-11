package editor.domain;

import java.io.Serializable;

public abstract class ElementProperty implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    abstract public ElementProperty Clone();
}