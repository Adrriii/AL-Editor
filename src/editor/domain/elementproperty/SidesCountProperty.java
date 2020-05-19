package editor.domain.elementproperty;

import editor.domain.ElementProperty;

/**
* An integer property representing an amount of sides.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class SidesCountProperty extends IntegerProperty {

    private static final long serialVersionUID = 1L;

    public SidesCountProperty(int count) {
        super(count);
    }

    @Override
    public ElementProperty Clone() {
        return new SidesCountProperty(value);
    }
}