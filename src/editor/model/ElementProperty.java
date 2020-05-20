package editor.model;

import java.io.Serializable;

/**
* A formatted value that can be held by an element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class ElementProperty implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Create an exact copy of this property
     * @return A new element property
     */
    abstract public ElementProperty Clone();
}