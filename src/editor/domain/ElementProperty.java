package editor.domain;

import java.io.Serializable;

/**
* A formatted value that can be held by an element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class ElementProperty implements Serializable {

    private static final long serialVersionUID = 1L;

    abstract public ElementProperty Clone();
}