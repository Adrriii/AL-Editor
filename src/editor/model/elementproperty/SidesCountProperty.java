package editor.model.elementproperty;

import editor.model.ElementProperty;

/**
* An integer property representing an amount of sides.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class SidesCountProperty extends IntegerProperty {

    private static final long serialVersionUID = 1L;

    /**
     * An integer property used to define a number of sides in a shape
     * 
     * @param count The number of sides
     */
    public SidesCountProperty(int count) {
        super(count);
    }

    @Override
    public ElementProperty Clone() {
        return new SidesCountProperty(value);
    }
}