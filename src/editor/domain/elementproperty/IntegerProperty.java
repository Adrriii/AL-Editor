package editor.domain.elementproperty;

import editor.domain.ElementProperty;

/**
* An Element's attribute that can be represented by an Integer.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class IntegerProperty extends ElementProperty {

    private static final long serialVersionUID = 1L;

    /**
     * The value of the property
     */
    public int value;

    /**
     * Create a new property represented by an integer
     * 
     * @param value The value of the property
     */
    public IntegerProperty(int value) {
        this.value = value;
    }

    @Override
    public ElementProperty Clone() {
        return new IntegerProperty(value);
    }
}